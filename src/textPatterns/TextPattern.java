package textPatterns;

import analysis.FeatureExtractorInterface;
import analysis.FeatureHit;
import openNLP.Chunk;
import openNLP.NLParser;
import parse.AnalysisFragment;
import parse.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Linus
 */

public class TextPattern {


    private static final int SOME_THRESHOLD = 25;
    private static final int MOST_THRESHOLD = 60;


    private ExtractionRule extractionRule = ExtractionRule.NO_EXTRACTION;
    private int extractionWord = 0;
    public int significance = -1;  //Not set


    public String getFirst() {
        return patterns[0];
    }


    public TextPattern lnLeftColumn() {
        columnCondition = 0;       // 0 is the left most column
        return this;
    }

    public FeatureHit getHitType() {
        return FeatureHit.IS_A;         // TOOD: Implement setting hit type
    }

    public List<RecursiveExtraction> getRecursiveExtractions() {
        return recursiveExtractions;
    }

    public String getRealExtraction() {
        return realExtraction;
    }

    /*************************************************************
     *
     *          Setting the significance
     *
     *          Typically using the constant GREY. If no significance is set, it
     *          will not be transferred to the output and the default significance will be used.
     *
     * @param s - significance value (0..100)
     * @return -self
     */

    public TextPattern withSignificance(int s) {

        this.significance = s;
        return this;
    }


    private enum ExtractionRule {NO_EXTRACTION, WORD_SPAN, NEXT_NOUN, SPECIFIC_WORD }

    private enum Proximity { NA, ADJACENT, VERY_CLOSE, CLOSE, ALL_PRESENT, MOST_OF, SOME_OF, NONE_OF, ANY_OF}

    private final String[] patterns;
    private boolean caseSensitive = false;
    private String clauseCondition = null;
    private int columnCondition = -1;
    private boolean isVerbatime = false;       // False = use base forms
    private Proximity proximity;
    private boolean ordered = false;
    private TextPattern next = null;
    private String tag = "";
    private String explanation = "";  // Side effect from the match
    private String extraction = "Not implemented"; // Side effect
    private String realExtraction = "Not implemented"; // Side effect

    private List<RecursiveExtraction> recursiveExtractions = new ArrayList<>();

    public TextPattern(String[] s) {

        patterns = s;
        if(patterns.length == 1)
            proximity = Proximity.NA;
        clauseCondition = null;

    }

    public TextPattern(String s) {

        patterns = new String[1];
        patterns[0] = s;
        proximity = Proximity.NA;
        clauseCondition = null;
    }

    /********************************************************************''
     *
     *      This is the main matching function
     *
     * @param fragment - fragment text
     * @return -match or not
     *
     *
     */


    public boolean matches(AnalysisFragment fragment) {

        explanation = "";

        // First recurse to all "and" clauses

        if(next != null && !next.matches(fragment)){

            extraction = extract(extractionRule, patterns, fragment);
            return false;
        }

        if(proximity == Proximity.ANY_OF){

            extraction = extract(extractionRule, patterns, fragment);
            return findAny(patterns, fragment);

        }

        if(proximity == Proximity.NONE_OF){

            extraction = extract(extractionRule, patterns, fragment);
            return findNone(patterns, fragment);

        }

        if(proximity == Proximity.SOME_OF){
            extraction = extract(extractionRule, patterns, fragment);
            return findSome(patterns, fragment, SOME_THRESHOLD);
        }

        if(proximity == Proximity.MOST_OF){
            extraction = extract(extractionRule, patterns, fragment);
            return findSome(patterns, fragment, MOST_THRESHOLD);
        }


        int distance = new Distance(patterns, recursiveExtractions, fragment, isVerbatime, caseSensitive).getDistance(fragment);
        Proximity calculatedProximity = getProximity(distance);

        if(proximity == Proximity.NA && distance != Distance.INFINITE){

            extraction = extract(extractionRule, patterns, fragment);
            return true;
        }
        if(proximity == Proximity.ADJACENT && calculatedProximity == Proximity.ADJACENT){

            extraction = extract(extractionRule, patterns, fragment);
            return true;
        }
        if(proximity == Proximity.VERY_CLOSE &&
                        (calculatedProximity == Proximity.ADJACENT ||
                         calculatedProximity == Proximity.VERY_CLOSE)){

            extraction = extract(extractionRule, patterns, fragment);
            return true;
        }

        if(proximity == Proximity.CLOSE &&
                        (calculatedProximity == Proximity.ADJACENT ||
                        (calculatedProximity == Proximity.VERY_CLOSE ||
                         calculatedProximity == Proximity.CLOSE))){

            extraction = extract(extractionRule, patterns, fragment);
            return true;
        }

        if(proximity == Proximity.ALL_PRESENT && distance != Distance.INFINITE){

            extraction = extract(extractionRule, patterns, fragment);
            return true;
        }

        explanation =  calculatedProximity.name() + " found (" +(proximity == null ? "non required" : "required " + proximity.name()) + ")";
        return false;


    }


    //TODO: Refactor this to a separate class to allow for more advanced extractor functions


    private String extract(ExtractionRule extractionRule, String[] patterns, AnalysisFragment fragment) {



        String lastWord = patterns[patterns.length-1];
        String firstWord = patterns[0];
        List<Word> words = fragment.getWords();

        switch(extractionRule){

            case NO_EXTRACTION:

                return "";

            case NEXT_NOUN:

                boolean lookForNoun = false;

                for(Word word : words){

                    if(word.matches(lastWord, isVerbatime, caseSensitive))
                        lookForNoun = true;

                    if(lookForNoun && word.isNoun()){

                        realExtraction = word.getText();
                        return word.getSemanticText();

                    }
                }

                // TODO: warn in some way here.

                return "";

            case WORD_SPAN:

                boolean inSpan = false;
                String semanticSpan = "";
                String realSpan = "";

                for(Word word : words){

                    if(word.matches(firstWord,  isVerbatime, caseSensitive)){
                        inSpan = true;
                        semanticSpan += word.getSemanticText();
                        realSpan += word.getText();
                    }
                    else if(inSpan){
                        semanticSpan += " " + word.getSemanticText();
                        realSpan += " " + word.getText();

                    }
                    if(inSpan && word.matches(lastWord,  isVerbatime, caseSensitive)){

                        realExtraction = realSpan;
                        return semanticSpan;
                    }

                }

                System.out.println("Could not make any extractions from " + words.toString());

                return "";

                case SPECIFIC_WORD:

                    System.out.println("*** Trying to extract word " + extractionWord);

                    for(Word word : words){

                        if(word.matches(patterns[extractionWord-1],  isVerbatime, caseSensitive)){
                            System.out.println("*** Extracting word " + word.getText());
                            realExtraction = word.getText();
                            return word.getSemanticText();
                        }

                    }

            default:

                //TODO: Throw exception here

                return "";
        }


    }

    private Proximity getProximity(int distance) {

        if(distance == 0)
            return Proximity.ADJACENT;
        if(distance < 10)
            return Proximity.VERY_CLOSE;
        if(distance < 20)
            return Proximity.CLOSE;
        if(distance < Distance.INFINITE)
            return Proximity.ALL_PRESENT;

        return Proximity.NA;

    }

    /************************************************************'
     *
     *              Find if any of the pattern words are found in the chunk
     *
     * @param chunk
     * @return
     */

    public boolean matches(Chunk chunk, AnalysisFragment fragment) {



        for(String p : patterns){

            for(Word w : chunk.getWords()){

                if(w.matches(p,  isVerbatime, caseSensitive)){

                    //System.out.println("Matching " + p + " and " + w.getText());
                    return true;
                }

            }
        }

        return false;

    }



    /************************************************************************'
     *
     *          Find all patterns
     *
     * @param patterns
     * @param fragment - for recursion
     * @return
     *
     *
     *          //TODO: Handle word separation (including extracting word span)
     */

    private boolean findAny(String[] patterns, AnalysisFragment fragment) {

        List<Word> words = fragment.getWords();

        for(String p : patterns){

            for(Word w : words){

                if(w.matches(p,  isVerbatime, caseSensitive))
                    return true;


            }
        }


        return false;
    }


    private boolean findNone(String[] patterns, AnalysisFragment fragment) {


        List<Word> words = fragment.getWords();

        for(String p : patterns){

            for(Word w : words){

                if(w.matches(p,  isVerbatime, caseSensitive))
                    return false;
            }
        }

        return true;

    }

 // Find more than 10% or less than or equal 50% of patterns in fragment

    /***********************************************************************************
     *
     *          findSome is used for looking for a set of words.
     *
     *
     * @param patterns
     * @param fragment - for recursion
     * @param threshold - the threshold for acceptanse (defined as a value between 0 and 100)
     * @return
     */

    private boolean findSome(String[] patterns, AnalysisFragment fragment, int threshold) {

        List<Word> words = fragment.getWords();


        int numberOfPatternHits = 0;
        int hitRatio = 0;

        for (String p : patterns) {

            for (Word w : words) {

                if (w.matches(p,  isVerbatime, caseSensitive)) {
                    numberOfPatternHits++;
                    break;
                }
            }

        }

        hitRatio = (100*numberOfPatternHits) / patterns.length;

        return (hitRatio > threshold);

    }

    public TextPattern ordered() {

        ordered = true;
        return this;
    }

    public TextPattern withRecursion(String word, FeatureExtractorInterface extractor, NLParser parser) {

        this.recursiveExtractions.add(new RecursiveExtraction(word, extractor, parser));
        return this;
    }


    public TextPattern adjacent() {

        proximity = Proximity.ADJACENT;
        return this;
    }

    public TextPattern veryClose() {

        proximity = Proximity.VERY_CLOSE;
        return this;
    }

    public TextPattern close() {

        proximity = Proximity.CLOSE;
        return this;
    }

    public TextPattern allPresent() {

        proximity = Proximity.ALL_PRESENT;
        return this;
    }

    public TextPattern anyOf() {

        proximity = Proximity.ANY_OF;
        return this;
    }

    public TextPattern noneOf() {

        proximity = Proximity.NONE_OF;
        return this;
    }

    /*************************************************
     *
     *      Some of is defined by the threshold parameter
     *
     * @return
     */

    public TextPattern someOf() {

        proximity = Proximity.SOME_OF;
        return this;
    }

    /*************************************************
     *
     *      Most of is defined by the threshold parameter
     *
     * @return
     */


    public TextPattern mostOf() {

        proximity = Proximity.MOST_OF;
        return this;
    }


    public TextPattern hasHeadline(String pattern) {

        clauseCondition = pattern;
        return this;
    }



    public TextPattern extractNextNoun() {

        extractionRule = ExtractionRule.NEXT_NOUN;
        return this;
    }

    public TextPattern extract(int wordNo) {

        extractionRule = ExtractionRule.SPECIFIC_WORD;
        extractionWord = wordNo;
        return this;
    }



    public TextPattern extractWordSpan() {

        extractionRule = ExtractionRule.WORD_SPAN;
        return this;
    }


    
        //TODO: Any order not implemented

    public TextPattern anyOrder() {

        return this;
    }



    public TextPattern verbatimForms() {

        isVerbatime = true;
        return this;

    }

    //TODO: Not implemented

    public TextPattern caseSensitive() {

        caseSensitive = true;
        return this;

    }

    public TextPattern and(TextPattern textPattern) {

        this.next = textPattern;
        return this;
    }

    public TextPattern withTag(String tag) {

        this.tag = tag;
        return this;
    }

    public String getTag(){

        return  tag;
    }

    public String display() {

        StringBuffer b = new StringBuffer();
        for(String s : patterns){

            b.append(s + ", ");

        }

        return b.toString();
    }


    public String getExplanation(){

        return explanation;
    }

    public String getExtraction(){

        return extraction;
    }



    public boolean evaluateMeta(AnalysisFragment fragment) {

        if(clauseCondition != null){

            Pattern pattern = Pattern.compile(clauseCondition);
            Matcher matcher = pattern.matcher(fragment.getClauseHeadline());

            if(!matcher.find())
            {
                //System.out.println("Could not find clause " + clauseCondition + " found \"" + fragment.getClauseHeadline() + "\"");
                return false;

            }
            //System.out.println("matched headline " + fragment.getClauseHeadline() + " against " + clauseCondition);


        }

        if(columnCondition != -1){

            if(fragment.getColumn() != columnCondition){

                //System.out.println("Could not match column condition " + columnCondition + " found " + fragment.getColumn());
                return false;
            }

            //System.out.println("matched column " + fragment.getColumn());
        }

        return true;

    }

}
