package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import document.AbstractDocument;
import document.AbstractFragment;
import document.AbstractProject;
import document.AbstractStructureItem;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 */

public class OpenReferenceAnalyser extends Classifier implements ClassifierInterface {

    protected String namesForExhibit;  // What is an exhibit called in this language. This is used in the matching exhibit reference

    private static final ReplacerInterface[] RuleList = {

            // Not used

    };


    public OpenReferenceAnalyser( ){

        super(RuleList, RuleList, NodeClass.Type.REFERENCE,  FeatureTypeTree.Reference);
    }


    /****************************************************************
     *
     *          Try to identify an open reference within the project.
     *
     *          This is done by going through all the structure items to see if there are any
     *          headlines that match the reference
     *
     *          The
     *
     *          // TODO: This has to be optimized. Create dedicated match here. Not using RegExpreplacer
     *
     *
     * @param reference
     * @param currentPass
     * @return
     */

    @Override
    public void classify(TextFragment reference, int currentPass){

        AbstractProject project = reference.getProject();
        AnalysisLogger.log(AnalysisLogger.Level.INFO, "Analysing reference(2) " + reference.getText());

        if(project == null){

            AnalysisLogger.log(AnalysisLogger.Level.INFO, "Project is empty! ");
        }
        else{

            //First match against the document titles

            for(AbstractDocument aDocument : project.documents){

                String name = getMatchPattern(aDocument.name);

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "(Close) Trying to match with document pattern: " + name);

                ReplacerInterface titleRule = new RegExpReplacer(name)
                        .withExtraction(1)
                ;

                // Remove trailing dots from reference

                reference.setText(reference.getText().replaceAll("\\.(?=\\s|$)", "").replaceAll("\\(", "").replaceAll("\\)", ""));

                executeRule(titleRule, reference, currentPass);


            }

            // Then go through all chapter names (and numbers

            for(AbstractDocument aDocument : project.documents){

                for(AbstractStructureItem structureItem : aDocument.chapters){

                    try{

                        AbstractFragment topElement = structureItem.getTopElement();

                        if(topElement == null){

                            // For some reason there is no top element for the structure element. Warn but continue

                            AnalysisLogger.log(AnalysisLogger.Level.WARNING, "There is no top element for the StructureItem " + structureItem.getID() + "/" + structureItem.getIndentation() + "/" + structureItem.getStyle());
                            break;
                        }
                        String body = getMatchPattern(topElement.getBody());

                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " !!!! Trying to match reference " + reference.getText() + " with pattern " + body);

                        /*

                        ReplacerInterface chapterRule = new RegExpReplacer(body)
                                .withExtraction(1)
                                .withSemanticExtraction(structureItem.getKey());

                        // Remove trailing dots from reference

                        reference.setText(reference.getText().replaceAll("\\.(?=\\s|$)", ""));

                        executeRule(chapterRule, reference, currentPass);

                        */

                    }catch(Exception e){

                        AnalysisLogger.log( e );
                    }


                }

            }


        }


    }

    /*********************************************************
     *
     *          Simple match. This can probably be improved on
     *
     *          The reference could either contain the name or the chapter
     *
     *          Example:
     *
     *                      1.1 Introduction
     *                      should match references like:
     *
     *                       - introduction
     *                       - 1.1
     *
     * @param reference - the body of the reference
     * @param clauseHeadline - the actual headline
     * @return
     */

    public boolean matches(String reference, String clauseHeadline) {

        if(reference == null || reference.equals(""))
            return false;

        // Trim trailing dots from the reference.

        reference = reference.replaceAll("\\.(?=\\s|$)", "").toLowerCase();

        //System.out.print(" - Matching reference " + reference.toLowerCase() + " with " + clauseHeadline.toLowerCase() +".." );

        // Get the chapter from the clause headline

        String pattern = "^(\\d+[\\.\\d+]*)[\\w]*(.*)$";

        String washedHeadline = clauseHeadline.replaceAll("[\\(\\)\\]\\[]", "");

        Pattern regExpPattern = Pattern.compile(pattern);
        Matcher matcher = regExpPattern.matcher(washedHeadline);

        String clauseChapter = "";
        String clauseName = "";

        if(matcher.matches()){
            clauseChapter = matcher.group(1);
            clauseName = matcher.group(2);

            clauseChapter = clauseChapter.replaceAll("\\.(?=\\s|$)", "").toLowerCase();

            //System.out.print("(found: '"+clauseChapter+"' and "+ clauseName+")");
        }
        boolean match = (reference.equals(clauseChapter) || reference.equals(clauseName.toLowerCase()));

        //System.out.println(match);
        return match;
    }


    /*********************************************************
     *
     *          Simple match. This can probably be improved on
     *
     *          The reference could either contain the name or the chapter
     *
     *          Example:
     *
     *                      1.1 Introduction
     *                      should match references like:
     *
     *                       - introduction
     *                       - 1.1
     *
     * @param clauseHeadline - the actual headline
     * @return
     */

    public String getMatchPattern(String clauseHeadline) {


        // Get the chapter from the clause headline

        String pattern = "^(\\d+[\\.\\d+]*)[\\w]*(.*)$";

        // This will be a regex. We need to wash it first as it may contain "regex characters"

        String washedHeadline = clauseHeadline.replaceAll("[\\(\\)\\]\\[]", "").toLowerCase();

        // Special case for "Exhibit" 

        Pattern regExpPattern = Pattern.compile(namesForExhibit);
        Matcher matcher = regExpPattern.matcher(washedHeadline);

        if(matcher.find()){

            System.out.println(" --- Identifying BILAGA." );
            return "(" + matcher.group( 0 ) + ")(.*)";

        }
        else{
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG," --- \""+ washedHeadline+"\" is not a BILAGA according to " + namesForExhibit );

        }
        regExpPattern = Pattern.compile(pattern);
        matcher = regExpPattern.matcher(washedHeadline);

        String clauseChapter = "";
        String clauseName = "";

        if(matcher.matches()){
            clauseChapter = "^"+matcher.group(1).trim();
            clauseName = "|" + matcher.group(2).trim();
            clauseChapter = "|" + clauseChapter.replaceAll("\\.(?=\\s|$)", "").trim();   //TODO: Error: "1" will match chapter 1.1. Fix this
        }

        return ("(" + washedHeadline + clauseChapter + clauseName + ")");
    }



}
