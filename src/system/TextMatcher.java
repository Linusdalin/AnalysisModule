package system;

import language.LanguageInterface;
import log.AnalysisLogger;
import stemming.StemmerInterface;
import synonyms.SynonymList;
import synonyms.SynonymManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/********************************************************************************
 *
 *          Text matcher
 *
 *          Matches a search string with a text using heuristics like:
 *
 *           - word stem matching
 *           - synonyms
 *
 */

public class TextMatcher {

    private LanguageInterface language;
    private String[] originalSearchList;      // The original search string (tokenized)
    private String[] stemmedSearchList;       // The search string tokenized and stemmed
    private List<String> finalizedWordList;   // The matched words (in original version) to be returned
    private boolean useStemSearch;

    // Threshold for the word count

    private static final double THRESHOLD = 0.5;

    SynonymManager synonymManager = new SynonymManager();
    SynonymList synonymList;
    private boolean caseInsensitive = false;
    private boolean synonymSearch = false;

    public TextMatcher() {

    }

    public TextMatcher caseInsensitive() {

        caseInsensitive = true;
        return this;
    }

    public TextMatcher useSynonyms() {

        synonymSearch = true;
        return this;
    }

    public TextMatcher useStemMatch() {

        stemmedSearchList = wordStem(originalSearchList);
        useStemSearch = true;
        return this;
    }

    public TextMatcher useHashTags() {

        return useHashTags(language);
    }

    public TextMatcher useHashTags(LanguageInterface tagLanguage) {

        stemmedSearchList = replaceTags(originalSearchList, tagLanguage);
        useStemSearch = true;
        return this;
    }


    public TextMatcher setLanguage(LanguageInterface language, String modelDirectory) {

        this.language = language;
        synonymList = synonymManager.getSynonymList(language, modelDirectory);
        return this;

    }


    public TextMatcher prepareSearchString(String searchString) {

        caseInsensitive = false;
        synonymSearch = false;
        useStemSearch = false;

        originalSearchList = tokenize(searchString);
        stemmedSearchList = originalSearchList;

        //System.out.println("Washed list: " + Arrays.asList(stemmedSearchList).toString());

        finalizedWordList = new ArrayList<>();

        return this;
    }




    public List<String> getMatch(String text) {

        finalizedWordList = new ArrayList<>();

        if(stemmedSearchList == null)
            throw new RuntimeException("Search string not prepared");

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Testing match. Body=\"" + text + "\" search " + Arrays.asList(stemmedSearchList).toString() + " caseInsensitive=" + caseInsensitive + " stem=" + useStemSearch);

        // Relevant word list is the washed list of words in the search string

        double textRelevance = calculateTextRelevance(stemmedSearchList, text);

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Relevance is: " + textRelevance);
        boolean isHit = evaluateRelevance(textRelevance);

        if(isHit)
            return finalizedWordList;
        else
            return null;
    }







    /***********************************************************************
     *
     *          Relevance for the text field
     *
     *
     * @param wordList - the list of search terms
     * @param text - the name of the classification
     * @return - a value between 0 and 1.
     *
     *
     *      //TODO: This is used from two places. Should be refactored
     */


    private int calculateTextRelevance(String[] wordList, String text) {

        //System.out.println(" wordlist: " + Arrays.asList(wordList).toString());

        //PukkaLogger.log(PukkaLogger.Level.INFO, "Calculate Text Relevance 1");

        int hit = 0;
        int wordCount = 0;

        if(text == null)
            return 0;

        //PukkaLogger.log(PukkaLogger.Level.INFO, "Calculate Text Relevance 2");

        String[] tokenList = tokenize(text);

        //PukkaLogger.log(PukkaLogger.Level.INFO, "Calculate Text Relevance 3");

        String[] stemmedList;
        if(useStemSearch)
            stemmedList = wordStem(tokenList);
        else
            stemmedList = tokenList;

        //PukkaLogger.log(PukkaLogger.Level.INFO, "Calculate Text Relevance 4");

        for(int i = 0; i < wordList.length; i++){


            if(find(wordList[i], originalSearchList[i], stemmedList, tokenList)){

                hit++;
            }
            wordCount++;
        }

        //PukkaLogger.log(PukkaLogger.Level.INFO, "Calculate Text Relevance 5");

        return (hit/wordCount);

    }

    private boolean find(String word, String originalWord, String[] washList, String[] originalList) {

        List<String> synonyms = null;

        //System.out.println("word = " + word);

        if(synonymSearch)
            synonyms = synonymList.getSynonym(originalWord);

        //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Find: " + word + "(" + originalWord + ")" + Arrays.asList(washList).toString() + "/" + Arrays.asList(originalList).toString() + "  ");

        for(int i = 0; i < washList.length; i++){

            if(caseInsensitive && word.equalsIgnoreCase(washList[i]) || word.equals(washList[i])){
                finalizedWordList.add(originalList[i]);
                //System.out.print(" match!");
                return true;
            }

            if(synonyms != null && synonyms.contains(originalList[i])){

                finalizedWordList.add(originalList[i]);
                //System.out.print(" synonym!");
                return true;
            }

        }
        //System.out.print(" not found...");
        //System.out.println("");

        return false;
    }

    /**********************************************************
     *
     *          final relevance calculation. This uses a very simple threshold functionality
     *
     * @param textRelevance  - relevance from searching in the text
     * @return
     */


    private boolean evaluateRelevance(double textRelevance) {


        if(textRelevance > THRESHOLD)
            return true;

        return false;
    }



    private String[] wordStem(String[] wordList) {

        if(language == null)
            throw new RuntimeException("No language defined");

        StemmerInterface stemmer = language.getStemmer();

        String[] stemmedList = new String[wordList.length];

        for(int i = 0; i < wordList.length; i++){

            stemmer.setCurrent(wordList[i]);
            stemmer.stem();
            stemmedList[i] = stemmer.getCurrent();
        }

        return stemmedList;
    }

    /*******************************************************************
     *
     * //TODO: This only uses the document language to replace tags. Should go through all languages
     *
     *
     * @param wordList
     * @param language
     * @return
     */


    private String[] replaceTags(String[] wordList, LanguageInterface language) {

        String[] taggedList = new String[wordList.length];

        for(int i = 0; i < wordList.length; i++){


            if(!wordList[i].startsWith("#")){

                taggedList[i] = wordList[i];
            }
            else{

                AnalysisLogger.log(AnalysisLogger.Level.INFO, "Replacing tag " + wordList[i] + " in language " + language.getLanguageCode().code);

                String replacedtag = language.getClassificationForName(wordList[i]);

                if(replacedtag == null){
                    System.out.println(" with nothing!?");
                    taggedList[i] = wordList[i];
                }
                else{
                    System.out.println("with " + replacedtag);
                    taggedList[i] = replacedtag;
                }
            }

        }

        System.out.println("taggedList: " + Arrays.asList(taggedList).toString());

        return taggedList;


    }




    private static boolean isInList(String word, String[] ignoreList) {


        for(String ignoreWord : ignoreList)
            if(word.equalsIgnoreCase(ignoreWord))
                return true;

        return false;
    }


    /******************************************************************************
     *
     *      Simple tokenizaer
     *

     * @param string
     * @return
     */


    private static String clean(String string) {

        String s = string;
        s = s.replaceAll("[!?,.:;-]", " ");
        return s;

    }

    private static String[] tokenize(String string) {

        // Split into multiple words

        return clean(string).split("\\s+");

    }



}
