package analysis;

import featureTypes.FeatureTypeInterface;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;
import parse.AnalysisFragment;
import textPatterns.RecursiveExtraction;
import textPatterns.TextPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * Common functionality
 */


public class FeatureExtractor {

    protected List<TextPattern> patterns = new ArrayList<>();
    protected List<TextPattern> regExpList = new ArrayList<>();
    public String name;
    protected String[] searchKeywords;
    protected FeatureTypeInterface type = FeatureTypeTree.None;
    protected FeatureActionType actionType = FeatureActionType.CLASSIFY;


    protected final static int FIRST_WORD   = 1;
    protected final static int SECOND_WORD  = 2;
    protected final static int THIRD_WORD   = 3;
    protected final static int FORTH_WORD   = 4;

    protected final static int GREY =       25;
    private FeatureTypeTree tree = null;

    public FeatureExtractor() {

    }

    /************************************************************************
     *
     *          classify goes through the pattern list and matches any of them
     *
     *
     * @param fragment
     * @param tree
     * @return
     */

    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, FeatureTypeTree tree){

        this.tree = tree;

        if(patterns == null)
            throw new RuntimeException("No pattern set for classify");

        // First go through the pattern List

        addAllNew(matchRegexps(fragment, regExpList, actionType), outcome);


        // Then the matchExpressions

        addAllNew(matchTextPatterns(fragment, patterns, actionType), outcome);


    }


    /******************************************************************************
     *
     *          add new will add all new definitions found.
     *
     *          The exclude list defines what definitions that should be ignored
     *
     *
     * @param featureDefinitions    - the definitions from the analysis of the active extractor
     * @param outcome               - total outcome
     */

    private void addAllNew(List<FeatureDefinition> featureDefinitions, AnalysisOutcome outcome) {


        for(FeatureDefinition newDefinition : featureDefinitions){

            if(!ignore(newDefinition, outcome.getDefinitions()))

                outcome.getDefinitions().add(newDefinition);

        }


    }

    /*******************************************************************************
     *
     *          Ignore if there is already a feature extracted for the current pattern in the ancestor list
     *
     *
     * @param newDefinition - this definition
     * @param existingDefinitions   - existing definitions (found previously)
     * @return
     */

    private boolean ignore(FeatureDefinition newDefinition, List<FeatureDefinition> existingDefinitions) {

        List<FeatureTypeInterface> isPartOfList = type.getIsPartOfList();


        for(FeatureDefinition definition : existingDefinitions){


            //System.out.println("In "+ getName()+ ": Checking ignore: " + definition.getType().toString() + " vs " + isPartOfList.toString());


            if(isPartOfList.contains(definition.getType())){

                // We have found a definition that would trigger an override. Now check if
                // they are in the same place. (It could be two different patterns...)

                if(newDefinition.getPos() >= definition.getPos() &&
                   newDefinition.getPos() <= (definition.getPos() + definition.getLength())){


                    System.out.println("Found an override. Existing classification " + definition.getType().getName() + " blocking new classification " + type.getName());
                    return true;

                }
            }

        }

        return false;
    }



    private boolean isCapitalized(String word) {

        return Character.isUpperCase(word.charAt(0));
    }

    public String getName() {
        return name;
    }

    public List<String> getKeywords() {

        List<String> keywordList = new ArrayList<>();

        keywordList.addAll(Arrays.asList(searchKeywords));

        return keywordList;
    }


    /****************************************************************
     *
     *          Analyse a text pattern as a regex over the entire fragment.
     *          Extract the first ( )
     *
     *
     *
     * @param fragment - the fragment
     * @param patternList - a list of regexp patterns
     * @param action - the action to perform given the match and extraction
     * @return - list of strings matching the patterns
     *
     */

    protected List<FeatureDefinition> matchRegexps(AnalysisFragment fragment, List<TextPattern> patternList, FeatureActionType action) {

        List<FeatureDefinition> definitionList = new ArrayList<>();

        for(TextPattern pattern : patternList){

            List<FeatureDefinition> definitions = matchRegexp(fragment, pattern, action);

            for(FeatureDefinition definition : definitions){


                // A pattern could appear multiple times in the text. Only add one.
                // The others will automatically be highlighted

                //if(!alreadyMatchedPattern(definition, definitionList))
                //    definitionList.add(definition);

                definitionList.add(definition);

            }



        }
        return definitionList;
    }

    private boolean alreadyMatchedPattern(FeatureDefinition definition, List<FeatureDefinition> definitionList) {

        for(FeatureDefinition d : definitionList){

            if(     d.getType() == definition.getType() &&
                    d.getTag().equals(definition.getTag()) &&
                    d.getPattern().equalsIgnoreCase(definition.getPattern()))
                return true;

        }

        return false;
    }

    /******************************************************************************
     *
     *          match one reg exp.
     *
     *          As a reg exp can extract multiple instances of a pattern, it can return a list of definitions
     *
     * @param fragment
     * @param pattern
     * @param action
     * @return
     *
     *         //TODO: Add a pre-processing step on the regexp to handle case sensitivity, multiple whitespace etc.
     *
     */


    protected List<FeatureDefinition> matchRegexp(AnalysisFragment fragment, TextPattern pattern, FeatureActionType action) {

        List<FeatureDefinition> definitionList = new ArrayList<>();

        //System.out.println("Matching " + pattern.getFirst() + " with " + fragment.getText());

            // Match the regexp

        try{
            Pattern regExpPattern = Pattern.compile(pattern.getFirst());
            Matcher matcher = regExpPattern.matcher(fragment.getText());

            if(pattern.evaluateMeta(fragment)){

                while(matcher.find())
                {
                    String hit = matcher.group(1);  // This is hard coded. The match is the first group of the reg ex.

                    FeatureDefinition definition = new FeatureDefinition(name, pattern.getHitType(), hit, getKeywords(), action, type);

                    definition.setPos(matcher.start(), hit.length());

                    // Set the correct significance

                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " significance for pattern " + pattern.getTag() + " is" + pattern.significance);

                    if(pattern.significance != -1){

                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " Setting significance " + pattern.significance);
                        definition.setSignificance(pattern.significance);

                    }

                    System.out.println("Extracted a "+ type.getName()+"-pattern \"" + matcher.group(1) + "\" at position "+ matcher.start()+" with action " + action);

                    definition.addTag(pattern.getTag());  // Transfer the tag from the pattern to the outcome
                    definition.addKeyword(type.getAkaTag());
                    definitionList.add(definition);

                }
            }
        }catch(PatternSyntaxException e){

            System.out.println("FATAL: regexp fail in " + name + " Error: " + e.getMessage());
        }

        return definitionList;
    }

    private String createTag(String tag) {

        return tag + " " + this.type.getAkaTag();



    }


    /*********************************
     *
     *      Go through all patterns in the list
     *
     * @param fragment
     * @param patternList
     * @return
     */

    protected List<FeatureDefinition> matchTextPatterns(AnalysisFragment fragment, List<TextPattern> patternList, FeatureActionType action) {

        List<FeatureDefinition> definitionList = new ArrayList<>();

        for(TextPattern p : patternList){

                definitionList.addAll(matchTextPattern(fragment, p, action));
        }

        return definitionList;


    }


    /*********************************************************************************'
     *
     *          matching one text pattern
     *
     *
     * @param fragment
     * @param pattern
     * @param action
     * @return
     */


    protected List<FeatureDefinition> matchTextPattern(AnalysisFragment fragment, TextPattern pattern, FeatureActionType action) {

        List<FeatureDefinition> matchList = new ArrayList<>();

        List<RecursiveExtraction> extractors = pattern.getRecursiveExtractions();

        // Apply all recursive extractors and replace the text in the fragment.

        AnalysisFragment recursivelyAnalysedFragment = fragment.recursiveReplace(extractors, tree);

        FeatureDefinition outcome = new FragmentCondition(recursivelyAnalysedFragment).contains(pattern).testClassification(action, type, name);

        if(outcome != null){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " significance for pattern " + pattern.getTag() + " is" + pattern.significance);

            if(pattern.significance != -1){

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " Setting significance " + pattern.significance);
                outcome.setSignificance(pattern.significance);

            }

            matchList.add(outcome);
        }

        return matchList;

    }

    public FeatureActionType getAction(){

        return FeatureActionType.NO_ACTION;
    }


}
