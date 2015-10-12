package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import document.AbstractDocument;
import document.AbstractProject;
import document.AbstractStructureItem;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

/**********************************************************************************
 *
 *              Generic classifier for references
 *
 *
 *              The overridden classification method does (beside the regular matches)
 *              also look for chapter names and document names in the project.
 *
 */



public abstract class ReferenceClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


    };


    protected ReferenceClassifier(ReplacerInterface[] languageSpecificRuleList){

        super(RuleList, languageSpecificRuleList, NodeClass.Type.REFERENCE,  FeatureTypeTree.Reference);
    }


    /***************************************************************************'
     *
     *          Overriding general functionality adding the matching of documents and headlined from the project
     *
     *
     * @param sentence              - the parsed text
     * @param chapterForLanguage    - the name of the tag for a chapter reference in the respective language
     * @param documentForLanguage   - the name of the tag for a document reference in the respective language
     * @param currentPass
     */


    // TODO: here the title rules and headline rules are compiled once for every fragment. Should be done once for the project


    public void classify(TextFragment sentence, String languageSpecificChapterRef, String chapterForLanguage, String documentForLanguage, int currentPass){

        super.classify(sentence, currentPass);

        // Look for the document titles in the project as they are references
        // Look for clause headlines in the document, as they are references

        AbstractProject project = sentence.getProject();


        if(project != null){

            // If there is a project, let's go through it
            for(AbstractDocument document : project.documents){

                String name = trimNumbering(document.name);

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Looking for references to " + name);

                // For each document, check to see if we can find a reference to it

                ReplacerInterface titleRule = new RegExpReplacer("(" + languageSpecificChapterRef + "(" + name + "))")
                        .withExtraction(1)
                        .withSemanticExtraction(2)
                        .withTag(documentForLanguage);

                executeRule(titleRule, sentence, currentPass);

                for(AbstractStructureItem headline : document.chapters){

                    // Ignore self

                    if(headline.getTopElement() != null
                            && !headline.getTopElement().getBody().equals(sentence.getText())

                            ){

                        //String body = strip(trimNumbering(headline.getTopElement().getBody()));
                        String body = headline.getTopElement().getBody();

                        if(document.equals(sentence.getDocument())){

                           // Check for references internally in the current document

                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Looking for references internally in the document to " + body);
                            //String regex = "(" + languageSpecificChapterRef + "(" + body + "))";
                            //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Testing " + sentence.getText() + " against regex: " + regex);

                            //ReplacerInterface headlineRule = new RegExpReplacer(regex)
                            //        .withExtraction(1)
                            //        .withSemanticExtraction(2)
                            //        .withTag(chapterForLanguage);

                            //executeRule(headlineRule, sentence, currentPass);
                            //executeRule(headline.getClassifierRuleThisDocument(), sentence, currentPass);

                        }
                        else{

                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Looking for references to " + body + " in document " + document.name);
                            //String regex = "(" + languageSpecificChapterRef + "(" + body + ")" + "[\\,|i |in ]* "+document.name + ")";
                            //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Testing " + sentence.getText() + " against regex: " + regex);


                            //ReplacerInterface headlineRule = new RegExpReplacer(regex)
                            //        .withExtraction(1)
                            //        .withSemanticExtraction(2);
                            //
                            //executeRule(headlineRule, sentence, currentPass);

                            //executeRule(headline.getClassifierRuleOtherDocument(document.name), sentence, currentPass);

                        }
                    }
                }
            }
        }

    }

    private String trimNumbering(String text) {

        int len = text.length();
        int st = 0;
        char[] val = text.toCharArray();

        while ((st < len) && (val[st] == '.' || (val[st] >= '0' && val[st] <= '9'))){
            st++;
        }
        return ((st > 0) ? text.substring(st).trim() : text);
    }


}
