package language;

import classifiers.ClassifierInterface;
import log.AnalysisLogger;
import openNLP.NLParser;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-05-29
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */

public abstract class Language implements LanguageInterface {

    private static final int hitThreshold = 4;
    protected static String[] classifierKeywords = null;


    // Language specific values, passed through the constructor

    private final ClassifierInterface[] supportedClassifiers;
    private final ClassifierInterface[] referenceClassifiers;
    private final ClassifierInterface[] postProcessClassifiers;
    private ClassifierInterface[] allClassifiers = null;

    private final String[] ignoreList;
    private String[][] posList;

    public Language(ClassifierInterface[] supportedClassifiers, ClassifierInterface[] referenceClassifiers, ClassifierInterface[] postProcessClassifiers, String[] ignoreList, String[][] posList) {

        this.supportedClassifiers   = supportedClassifiers;
        this.referenceClassifiers   = referenceClassifiers;
        this.postProcessClassifiers = postProcessClassifiers;
        this.ignoreList = ignoreList;
        this.posList = posList;
    }


    /*****************************************************************************
     *
     *              main function for detecting a language
     *
     *
     * @param commonWords
     * @param bodyText
     * @param languageCode
     * @return
     *
     *              //TODO: This could be done better.... Apply ML here
     */

    protected boolean containsWords(String[] commonWords, String bodyText, LanguageCode languageCode) {

        int wordHit = 0;
        int totalWords = 0;

        String[] tokens = bodyText.split(" ");

        for(String token : tokens){


            // Only look for words that are more than one character and does not contain a digit

            if(token.length() > 1 && !token.matches(".*\\d+.*")){

                //System.out.print("token: " + token);

                totalWords++;
                if(contains(commonWords, token)){
                    wordHit++;
                    //System.out.print("...HIT!");
                }

                //System.out.println("...");
            }
        }

        boolean isLanguage = (wordHit * 100 / totalWords > hitThreshold);

        System.out.println(" - Analysing language " + languageCode.code + ": Hits: " + wordHit + "/" + totalWords + " Returning " + isLanguage );
        return isLanguage;

    }

    public static boolean contains( String[] array, String v ) {
        for ( String e : array )
            if ( v != null && v.equalsIgnoreCase( e ) )
                return true;

        return false;
    }

    /******************************************************************
     *
     *      Lookup of the tag given the name in a specific language
     *
     *
     * @param nameInLanguage - the name of the classification  e.g. #nummer (in swedish)
     * @return               - the tag                         e.g. #NUMEX
     *
     *
     *      NOTE: The methods expect a "#"  and will strip it for the comparason
     */

    @Override
    public String getClassificationForName(String nameInLanguage) {

        for (ClassifierInterface classifier : supportedClassifiers) {

            if(classifier.getClassificationName().equalsIgnoreCase(nameInLanguage.substring( 1 )))
                return classifier.getType().getName();
        }

        for (ClassifierInterface classifier : postProcessClassifiers) {

            if(classifier.getClassificationName().equalsIgnoreCase(nameInLanguage.substring( 1 )))
                return classifier.getType().getName();
        }

        // If not found return null, but this is probably an error
        AnalysisLogger.log(AnalysisLogger.Level.WARNING, "Could not find classification for localized tag " + nameInLanguage);
        return null;
    }

    @Override
    public String getLocalizedClassification(String tag){

        for (ClassifierInterface classifier : supportedClassifiers) {


            if(classifier.getType().getName().equalsIgnoreCase(tag))
                return classifier.getClassificationName();
        }

        for (ClassifierInterface classifier : referenceClassifiers) {


            if(classifier.getType().getName().equalsIgnoreCase(tag))
                return classifier.getClassificationName();
        }

        // If not found return null, but this is probably an error
        AnalysisLogger.log(AnalysisLogger.Level.WARNING, "Could not find localized classification for tag " + tag);
        return null;

    }

    //TODO: Optimize this

    @Override
    public String wash(String text) {

        String washedText = text;

        washedText = washedText
                .replaceAll("“", "\"")
                .replaceAll("”", "\"")
                .replaceAll("“", "\"")
                .replaceAll("<b>", "")
                .replaceAll("</b>", "")
                .replaceAll("<i>", "")
                .replaceAll("</i>", "")
                .replaceAll("<strike>", "")
                .replaceAll("</strike>", "");

        return washedText;
    }

    /*****************************************************************************************'
     *
     *          Use language specific word lists to enhance the pos tagging.
     *
     *          These should be moved to the actual pos tagger by training it
     *
     *
     *
     * @param tokens
     * @param posTags
     */



    @Override
    public void enhancePOS(String[] tokens, String[] posTags) {

        for(int i = 0; i < tokens.length; i++){

            //System.out.println("   --- " + tokens[i] +" " + posTags[i]);

            for (String[] correction : posList) {

                if(tokens[i].equals(correction[0])){
                    posTags[i] = correction[1];
                    System.out.println("       ! Change to " + correction[1]);

                }
            }

        }


    }


    protected String[] generateClassifierKeywords(ClassifierInterface[] supportedClassifiers) {

        String[] keywords = new String[supportedClassifiers.length];
        int i = 0;

        for (ClassifierInterface supportedClassifier : supportedClassifiers) {

            String keyword = supportedClassifier.getType().getName();

            if(keyword != null && !keyword.equals(""))
                keywords[i++] = keyword;

        }

        return keywords;

    }


    public String[] getIgnoreList(){

        return ignoreList;
    }


    @Override
    public ClassifierInterface[] getSupportedClassifiers() {

        return supportedClassifiers;
    }

    @Override
    public ClassifierInterface[] getOpenReferenceClassifiers() {

        return referenceClassifiers;
    }


    @Override
    public ClassifierInterface[] getPostProcessClassifiers() {

        return postProcessClassifiers;
    }

    @Override
    public ClassifierInterface[] getAllClassifiers() {

        if(allClassifiers == null){

            int l1 = supportedClassifiers.length;
            int l2 = postProcessClassifiers.length;

           allClassifiers = new ClassifierInterface[l1 + l2];
           System.arraycopy(supportedClassifiers,   0, allClassifiers, 0, l1);
           System.arraycopy(postProcessClassifiers, 0, allClassifiers, l1, l2);

        }

       return allClassifiers;
    }

}
