package analysis2;

import log.AnalysisLogger;
import openNLP.NLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *              Replacer for applying a rule on the entire node text.
 *
 *              (See also the TokenReplacer working on tokens/words)
 *
 */

public class RegExpReplacer extends Replacer implements ReplacerInterface {


    private String regexp;
    private int regexGroupSpan = 1;
    private int regexSemanticSpan = 1;
    private int noExtractions = 1;          // How many extractions do we need
    private List<SemanticRestriction> restrictions = new ArrayList<>();
    Pattern regExpPattern;

    public RegExpReplacer(String regexp) {

        this(regexp, Pattern.CASE_INSENSITIVE);
    }

    public RegExpReplacer(String regexp, int flag) {

        this.regexp = regexp;
        regExpPattern = Pattern.compile(regexp, Pattern.MULTILINE | flag);
    }


    /****************************************************'
     *
     *              Set the extraction.
     *
     *              This will return the corresponding reg exp group. Default is 1,
     *              returning the first group
     *
     *
     * @param span - group number
     * @return     - self
     */


    @Override
    public ReplacerInterface withExtraction(int span) {

        this.regexGroupSpan = span;
        return this;
    }


    @Override
    public ReplacerInterface withSemanticExtraction(int span) {

        this.regexSemanticSpan = span;
        updateNoExtractions( span );
        return this;
    }

    /************************************************************'''
     *
     *          Set a specific semantic extraction for the rule.
     *          This will prevent the extraction of any match in the regex
     *
     * @param semanticExtraction       - hard coded extraction from the rule
     * @return
     */

    @Override
    public ReplacerInterface withSemanticExtraction(String semanticExtraction) {

        this.semanticExtraction = semanticExtraction;
        this.regexSemanticSpan = 0;                         // Do not extract span
        return this;
    }

    @Override
    public ReplacerInterface withGroupRestrictionCriteria(int group, SemanticRestriction.RestrictionType type) {

        restrictions.add(new SemanticRestriction(group, type));
        updateNoExtractions(group);
        return this;
    }

    private void updateNoExtractions(int group) {
        if(group > noExtractions)
            noExtractions = group;
    }


    /********************************************************************
     *
     *          ExtractSpan will extract the relevant spans from the match and
     *          return it
     *
     *
     * @param node - analysed node
     * @return      - list of extractions
     */

    public List<TextSpan> extractSpan(ParseNodeInterface node){

        List<TextSpan> resultingSpans = new ArrayList<>();

        if(verbose)
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Matching rule " + regexp);

        // First see if there are any meta Criteria that we need to fulfill

        for(Criteria criterium : criteria){


            if(!criterium.matchMeta(node)){

                if(verbose)
                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Failed to match meta criteria for rule ");
                return resultingSpans;
            }


        }

        Matcher matcher = regExpPattern.matcher(node.getText());

        int hitNo = 0;

        while(matcher.find()){

            try{

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Extracting " + noExtractions + " groups from match " + hitNo++);

                if(this.semanticExtraction != null){

                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Adding Default Semantic Extraction " + this.semanticExtraction);

                }

                if(this.regexSemanticSpan != 0){
                    this.semanticExtraction = matcher.group(this.regexSemanticSpan);
                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Extracting semantic span "+ this.semanticExtraction+" from group " + this.regexSemanticSpan);
                }

                for(int i = 1; i <= noExtractions; i++){

                    String hit = matcher.group( i );
                    int position = matcher.start(i);
                    TextSpan span = new TextSpan(hit, position, i, this.semanticExtraction);
                    resultingSpans.add(span);

                }

            }catch(Exception e){

                AnalysisLogger.log(e, "Error extracting pattern \"" +regExpPattern.pattern() + "\" from \""+ node.getText() + "\"");
            }

        }

        return resultingSpans;
    }

    @Override
    public String getBody() {
        return regexp;
    }


    public int getMainExtractionSpan() {
        return regexGroupSpan;
    }

    public void setRegexGroupSpan(int regexGroupSpan) {
        this.regexGroupSpan = regexGroupSpan;
    }

    public int getSemanticExtractionSpan() {
        return regexSemanticSpan;
    }


    @Override
    public boolean evaluateExtractionRestrictions(List<TextSpan> spans, NLParser parser) {



        boolean evaluateOk = true;

        for (SemanticRestriction restriction : restrictions) {

            for (TextSpan span : spans) {

                TextFragment newNode = new TextFragment(span.getText(), parser, false, 0);


                if(span.getExtractionGroup() == restriction.getGroup()){

                    switch (restriction.getType()) {


                        case IS_NAME_ENTITY:

                            if(!isNameEntity(newNode)){

                                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Failing restriction " + restriction.getType().name() + " for span " + span.getText() + "\nin Node " + newNode.display( 0 ) );
                                return false;
                            }

                            break;

                        case IS_DESCRIBING:

                            if(!isDescribing(newNode)){

                                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Failing restriction " + restriction.getType().name() + " for span " + span.getText() + "\nin Node " + newNode.display( 0 ) );
                                return false;
                            }
                            break;
                    }

                    //System.out.println("Passing restriction " + restriction.getType().name() + " for span " + span.getText() + "\nin Node " + newNode.display( 0 ) );


                }

            }

        }

        return evaluateOk;

    }

    private static final String excludedWords = "chapter bilaga, appendix";

    /******************************************************************************
     *
     * @param newNode
     * @return
     */

    private boolean isNameEntity(ParseNodeInterface newNode) {

        List<ParseNodeInterface> children = newNode.getChildren();

        if(children.size() == 1 && children.get(0).getText().length() == 1){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "\"" + children.get(0).getText() + "\" is not a name entity, it is too short!" );
            return false;

        }


        int indication = 0;  // Indication that this is not a name entity

        for (ParseNodeInterface child : children) {

            WordNode word = (WordNode) child;

            if(word.getPosTag().isVerb())
                indication += 4;
            else if(word.getPosTag().isAdjective())
                indication += 2;
            else if(word.getPosTag().isPreposition())
                indication += 2;
            else
                indication += 1;



        }

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Not name entity Indication = " + indication);

        return (indication < 7);


    }

    /****************************************************************************
     *
     *          The attribute isDescribing is used as restriction on extractions.
     *
     *          The algorithm is very simple and looking for nouns and verbs.
     *          The implementation excludes text in brackets as they normally defines other things.
     *
     *
     * @param newNode
     * @return
     */

    private boolean isDescribing(ParseNodeInterface newNode) {

        List<ParseNodeInterface> children = newNode.getChildren();
        int majorCount = 0;
        int totalCount = 0;
        boolean inBracket = false;

        for (ParseNodeInterface child : children) {

            WordNode word = (WordNode) child;
            //System.out.println(" - Pos for " + word.getText() + " "  + word.getPosTag().name());

            if(!inBracket && (word.getPosTag().isNoun() || word.getPosTag().isVerb()))
                majorCount++;

            if(word.getText().contains("(") || word.getText().contains("[") || word.getText().contains("("))
                inBracket = true;

            if(word.getText().contains(")") || word.getText().contains("]") || word.getText().contains("}"))
                inBracket = false;

            totalCount++;
        }

        //System.out.println(" - count: " + count);

        return (majorCount >= 2 && totalCount >=4);

    }


}
