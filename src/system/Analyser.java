package system;

import analysis.AnalysisOutcome;
import analysis.FeatureExtractorInterface;
import analysis2.AnalysisException;
import analysis2.TextFragment;
import analysis2.ClassifierModel;
import analysis2.NewAnalysisOutcome;
import docType.DocumentType;
import docType.DocumentTypeAnalyser;
import document.AbstractDocument;
import document.AbstractProject;
import document.CellInfo;
import extractors.*;
import featureTypes.FeatureTypeTree;
import keywords.KeywordExtractor;
import language.LanguageAnalyser;
import language.LanguageCode;
import language.LanguageInterface;
import log.AnalysisLogger;
import openNLP.NLParser;
import parse.AnalysisFragment;

import java.util.List;

/************************************************************************************'''
 *
 *      Main class of the Text Analyser package.
 *
 *
 *      There are two passes of the analysis
 *
 *       - mainPass - which analyses most of the classifying extractors
 *       - postPass - the analysis of references etc. that uses the result of the main pass
 *
 */


public class Analyser {

    private LanguageInterface language;
    private NLParser parser;
    private ClassifierModel model;
    private KeywordExtractor keywordExtractor;
    private DocumentTypeAnalyser documentTypeAnalyser;
    private static LanguageAnalyser languageAnalyser = new LanguageAnalyser();

    private static FeatureTypeTree tree = new FeatureTypeTree();

    public  static final int MAIN_PASS      = 1;
    private static final int POST_PROCESS   = 2;
    private static final int REFERENCE_PASS = 3;
    public  static final int DEFERENCE_PASS = 4;


    /*********************************************************
     *
     *          Detect language given text
     *
     * @param bodyText - text from the document used for the language analysis
     * @return - the language
     */

    public static LanguageCode detectLanguage(String document, String bodyText) throws AnalysisException{

        try{
            AnalysisLogger.log(AnalysisLogger.Level.INFO, "Detecting language for document " + document + " size: " + bodyText.length());

            return languageAnalyser.getLanguage(document + " " + bodyText).getLanguageCode();

        } catch (AnalysisException e) {

            AnalysisLogger.log( e );
            throw new AnalysisException("Could not detect language from document ").inDocument(document);
        }


    }

    /*********************************************************
     *
     *          Lookup the language given the language code
     *
     * @param languageCode - language code
     * @return             - language itself
     */

    public static LanguageInterface getLanguageByName(String languageCode) throws AnalysisException{

        try{

            LanguageAnalyser languageAnalyser = new LanguageAnalyser();
            return languageAnalyser.getLanguageByName(languageCode);

        } catch (AnalysisException e) {

            AnalysisLogger.log(AnalysisLogger.Level.WARNING, "Error getting language from code " + languageCode);
        }

        throw new AnalysisException("Could not detect language");

    }


    /***********************************************************************
     *
     *      Creating an analyser including analysing the body to get language and type
     *
     * @param languageCode      - document language
     * @param path              - path to the max entropy files
     */


    public Analyser(LanguageCode languageCode, String path){

        LanguageAnalyser languageAnalyser = new LanguageAnalyser();
        this.language = languageAnalyser.getLanguage(languageCode);

        documentTypeAnalyser  = new DocumentTypeAnalyser();

        if(language != null)
            parser = this.language.getParser(path);

        keywordExtractor = new KeywordExtractor();

        model = new ClassifierModel(this.language);

    }


    /**************************************************************************
     *
     *          Analysing a fragment will match it against all FeatureExtractors in the
     *          "supported"-list and return the definition objects for all that matched
     *
     * @param fragmentText - text body of the fragment
     * @param document - the current document (for analysis of headlines, definitions and other references)
     * @param project - the current project (for analysis of headlines, definitions and other references)
     * @return - the outcome with a list of observations and actions
     */



    public NewAnalysisOutcome analyseFragment(String fragmentText,
                                               int fragmentNo,
                                               String headline,
                                               String contextText,
                                               AbstractDocument document,
                                               CellInfo cellInfo,
                                               AbstractProject project){

        // Create the text fragment

        TextFragment analysisFragment = new TextFragment(fragmentText, parser, true, fragmentNo)
                .withHeadline(headline)
                .withContext(contextText)
                .withDocument(document)
                .withCellInfo(cellInfo)
                .withProject(project);

        keywordExtractor.extractKeywordFromFragment(analysisFragment);


        TextFragment analysedFragment = model.classify(analysisFragment, MAIN_PASS);

        NewAnalysisOutcome analysisOutcome = analysedFragment.getOutcome(MAIN_PASS);

        keywordExtractor.extractKeywordFromAnalysis(analysisOutcome);

        //System.out.println("*** After analysis:\n" + analysedFragment.display(0));
        //System.out.println("\n*** After analysis...\n");

        return analysisOutcome;
    }


    /*********************************************************************'
     *
     *          Analyse an existing open reference to see if we can close it
     *
     * @param reference - the open reference text to search for and analyse
     * @param project - whole project to search in
     * @return
     */

    @Deprecated
    public AnalysisOutcome analyseOpenReferenceOld(String reference,
                                               AbstractProject project){


        HeadlineReferenceAnalyser headlineReferenceAnalyser = new HeadlineReferenceAnalyser();
        AnalysisOutcome outcome = headlineReferenceAnalyser.classify(reference, project);
        return outcome;
    }

    //TODO: Refactor these three methods using model

    public NewAnalysisOutcome analyseOpenReferences(String reference, AbstractProject project, boolean usePOS){

        TextFragment fragment = new TextFragment(reference, parser, usePOS, 0)
                .withProject(project);

        TextFragment analysedFragment = model.analyseOpenReferences(fragment, REFERENCE_PASS);

        NewAnalysisOutcome analysisOutcome = analysedFragment.getOutcome(REFERENCE_PASS);

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** After open reference:\n" + analysedFragment.display(0));

        return analysisOutcome;
    }

    /*************************************************************************''
     *
     *          Second pass to post process the fragment.
     *          We keep the tree from the previous pass, using that information
     *
     *
     * @param previousOutcome    - previous outcome
     * @param project            - the current project (for definitions etc.)
     * @return                   - a new complete outcome
     */

    public NewAnalysisOutcome postProcess(NewAnalysisOutcome previousOutcome, AbstractProject project){


        TextFragment analyseFragment = previousOutcome.getTextFragment()
                .withProject(project);

        TextFragment analysedFragment = model.analyseDefinitionUsage(analyseFragment, POST_PROCESS);

        NewAnalysisOutcome analysisOutcome = analysedFragment.getOutcome(POST_PROCESS);

        return analysisOutcome;
    }

    public NewAnalysisOutcome postProcess(String fragmentText, int fragmentNo, AbstractProject project, boolean usePOS){

        TextFragment analysisFragment = new TextFragment(fragmentText, parser, usePOS, fragmentNo)
                .withProject(project);

        TextFragment analysedFragment = model.analyseDefinitionUsage(analysisFragment, POST_PROCESS);

        NewAnalysisOutcome analysisOutcome = analysedFragment.getOutcome(POST_PROCESS);

        return analysisOutcome;

    }



    /*****************************************************************'
     *
     *          Run the analysis with the selected set of extractors
     *
     * @param fragment
     * @param document
     * @param project
     * @param extractors
     * @return
     */

    private AnalysisOutcome analyse(AnalysisFragment fragment,
                                    AbstractDocument document,
                                    AbstractProject project,
                                    FeatureExtractorInterface[] extractors){

        AnalysisOutcome outcome = new AnalysisOutcome();

        // There may be an optional document scope for this fragment. If so we add it to the analysis

        if(document != null)
            fragment.setDocument(document);
        if(project != null)
            fragment.setProject(project);

        for(FeatureExtractorInterface extractor : extractors){

            extractor.classify(fragment, outcome, tree);

        }

        //keywordExtractor.extractKeywordFromAnalysis(outcome);


        documentTypeAnalyser.analyseFragment(fragment);

        //Store the fragment for a later pass
        outcome.addFragment(fragment);
        return outcome;

    }

    /*****************************************************************
     *
     *      Returns the keyWords that is the side effect of the analysis
     *
     * @return
     */

    public List<String> getKeywords(){

        return keywordExtractor.getAllKeywords();

    }

    /*******************************************************************
     *
     *          Get all classifications as keywords for the detected language
     *
     *
     * @return
     */

    public String[] getAllClassificationKeywords(){

        return language.getClassifierKeywords();

    }


    public String[] tokenize(String text){

        String[] tokens = parser.tokenize(text);

        String[] chunks = parser.chunk(tokens);
        return  chunks;
    }


    public LanguageInterface getLanguage() {

        return language;
    }

    public DocumentType getDocumentType() {

        return documentTypeAnalyser.getDocumentType();

    }
}
