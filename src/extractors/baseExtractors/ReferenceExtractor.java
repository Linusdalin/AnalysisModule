package extractors.baseExtractors;

import analysis.*;
import document.AbstractClause;
import document.AbstractDocument;
import document.AbstractProject;
import document.AbstractStructureItem;
import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public abstract class ReferenceExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Name and special keywords that will be used to tag the fragment


    public ReferenceExtractor(){

        super();
        //type = FeatureType.REFERENCE;
        type = FeatureTypeTree.Reference;

        actionType = FeatureActionType.CREATE_DEFINITION;

    }


    /****************************************************************
     *
     * @param fragment
     * @param accordingToRegExp
     * @return
     */

    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, String accordingToRegExp){


        List<FeatureDefinition> definitions = outcome.getDefinitions();

        // First go through the pattern List

        definitions.addAll(matchRegexps(fragment, regExpList, FeatureActionType.CREATE_REFERENCE));

        // Then the matchExpressions

        definitions.addAll(matchTextPatterns(fragment, patterns, FeatureActionType.CREATE_REFERENCE));

        // Look for the document titles in the project as they are references
        // Look for clause headlines in the document, as they are references

        AbstractProject project = fragment.getProject();


        if(project != null){

            // If there is a project, let's go through it
            for(AbstractDocument document : project.documents){

                System.out.println("Looking for references to " + document.name);

                // For each document, check to see if we can find a reference to it

                TextPattern p = new TextPattern(accordingToRegExp + "(" + document.name + ")");
                definitions.addAll(matchRegexp(fragment, p, FeatureActionType.CREATE_REFERENCE));

                for(AbstractStructureItem headline : document.chapters){


                    String body = "";

                    if(headline.getTopElement() != null)
                        body = headline.getTopElement().getBody();


                    if(document.equals(fragment.getDocument())){

                       // Check for references internally in the current document

                        System.out.println("Looking for references internally in the document to " + body);

                        p = new TextPattern(accordingToRegExp + "(" + body + ")");
                        definitions.addAll(matchRegexp(fragment, p, FeatureActionType.CREATE_REFERENCE));


                    }
                    else{

                        System.out.println("Looking for references to " + body + " in document " + document.name);

                        p = new TextPattern("(?:enligt|framgår av| motsvaras av|per|enlighet med) (?:punkt |kapitel |avsnitt |stycke |appendix |paragraf )*(" + body + ")" + "[\\,| i]* "+document.name);
                        definitions.addAll(matchRegexp(fragment, p, FeatureActionType.CREATE_REFERENCE));


                    }
                }


            }
        }


        outcome.setDefinitions(definitions);


    }


    @Override
    public FeatureActionType getAction(){

        return FeatureActionType.CREATE_REFERENCE;
    }



}
