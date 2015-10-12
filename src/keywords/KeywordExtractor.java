package keywords;

import analysis2.NewAnalysisOutcome;
import analysis2.ParseNodeInterface;
import analysis2.TextFragment;
import analysis2.WordNode;
import classifiers.Classification;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

import java.util.*;

/**
 *
 *          Extract and store all the keywords from the analysed fragments.
 *
 *          The keywords will be used in the search completion
 *
 *          // TODO: Count occurrences and only add the most frequent words
 */

public class KeywordExtractor {

    private Set<String> keywordList = new HashSet<>();

    public KeywordExtractor(){

    }

    /****************************************************
     *
     *      Get keywords from the analysis, keywords, tags and extracted patterns
     *
     *
     * @param outcome - analysis outcome with classifications
     */

    public void extractKeywordFromAnalysis(NewAnalysisOutcome outcome){


        // Go through the classifications and add the findings

        for(Classification classification : outcome.getClassifications()){

            if(irrelevantAsKeyword(classification)){

                break;
            }

            checkAndAdd(classification.getPattern().getText());

            /* Add all words from the keyword list.

                Removed this. Not relevent with tags

            TODO: Optimization: Check the efficiency of this.

            for(String keyword : classification.getKeywords().split(" ") ){

                checkAndAdd(keyword);
            }

                */


        }

    }

    /********************************************************************************************
     *
     *      Some of the generated classifications are irrelevant as keywords
     *
     * @param classification - the classification
     * @return - shall we ignore this or not?
     *
     */

    private boolean irrelevantAsKeyword(Classification classification) {

        return (classification.getType().getName().equals(FeatureTypeTree.Reference.getName()) ||
                classification.getRelevance() <= Classification.DEFAULT_LOW_RELEVANCE);
    }

    /*******************************************************************'
     *
     *          Conditional adding of a keyword. There are restrictions
     *
     *
     * @param keyword - keyword to check
     */


    private void checkAndAdd(String keyword) {

        // Conditions on being a keyword


        if(keyword.length() <= 2){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " **** NOT adding " + keyword + " too short");
            return;

        }

        if(keyword.length() >= 30){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " **** NOT adding " + keyword + " too long");
            return;

        }


        if(keyword.equals("null")){
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " **** NOT adding keyword as it is null");
            return;
        }

        if(keyword.startsWith("#")){
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " **** NOT adding " + keyword + " as it is a classification");
            return;
        }

        if(isNumeric(keyword)){
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " **** NOT adding " + keyword + " numeric");
            return;
        }

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " **** Adding " + keyword + " as keyword");
        keywordList.add(stripKeyword(keyword));
    }

    private String stripKeyword(String keyword) {

        return keyword.replaceAll("(\\{\\d+\\})", "")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&amp;", "&");

    }

    private boolean isNumeric(String keyword) {

        try{
            double d = Double.parseDouble(keyword);

        }catch(NumberFormatException nfe){

            return false;

        }
        return true;

    }

    /******************************************************************'
     *
     *      get text from the actual fragment. Currently this includes:
     *
     *          - All words that are not minor (e.g. prepositions etc)
     *
     * @param fragment - this is now already parsed and POS analysed
     *
     *                 //TODO: Add more intelligence here for words that are relevant
     *
     */

    public void extractKeywordFromFragment(TextFragment fragment){

        if(fragment.isHeadline()){

            for(ParseNodeInterface node : fragment.getChildren()){

                WordNode word = (WordNode)node;

                if(!word.isMinor()){

                    keywordList.add(word.getText());
                }

            }

        }

    }


    public List<String> getAllKeywords() {

        List<?> list = new ArrayList(keywordList);
        return (List<String>)list;

    }
}
