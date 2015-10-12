package extractors;

import analysis.*;
import document.*;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 */

@Deprecated
public class HeadlineReferenceAnalyser extends FeatureExtractor {


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "HeadlineReference";
    public static final String searchKeywords[] = new String[] {""};


    public HeadlineReferenceAnalyser(){

        super();
        type = FeatureTypeTree.Headline;

        //type = FeatureType.REFERENCED_CLAUSE;

    }



    /****************************************************************
     *
     *          Try to identify an open reference within the project.
     *
     *          This is done by going through all the structure items to see if there are any
     *          headlines that match the reference
     *
     *
     * @param reference
     * @param project
     * @return
     */


    public AnalysisOutcome classify(String reference, AbstractProject project){

        AnalysisOutcome outcome = new AnalysisOutcome();
        AnalysisLogger.log(AnalysisLogger.Level.INFO, "Analysing reference " + reference);

        for(AbstractDocument aDocument : project.documents){

            for(AbstractStructureItem structureItem : aDocument.chapters){

                AbstractFragment topElement = structureItem.getTopElement();

                if(topElement == null){

                    // For some reason there is no top element for the structure element. Warn but continue

                    AnalysisLogger.log(AnalysisLogger.Level.WARNING, "There is no top element for the StructureItem " + structureItem.getID() + "/" + structureItem.getIndentation() + "/" + structureItem.getStyle());
                    break;
                }
                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Trying to match with top element " + topElement.getBody());


                if(matches(reference, topElement.getBody())){

                    FeatureDefinition definition = new FeatureDefinition(name, FeatureHit.IS_A, reference, Arrays.asList(searchKeywords), FeatureActionType.CREATE_REFERENCE_TARGET, type);
                    definition.setClause(structureItem);
                    outcome.addDefinition(definition);
                    AnalysisLogger.log(AnalysisLogger.Level.INFO, "Found a match for " + reference + " in headline " + topElement.getBody());


                }

            }

        }

        return outcome;

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


        Pattern regExpPattern = Pattern.compile(pattern);
        Matcher matcher = regExpPattern.matcher(clauseHeadline);

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


}
