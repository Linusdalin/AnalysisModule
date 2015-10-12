package extractors;

import analysis.*;
import document.AbstractDefinition;
import document.AbstractDocument;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts a definition from a fragment. The definition is the content within the quote inside brackets.
 *
 *      ("definition") or ( a "definition" )
 */

public abstract class DefinitionUsageExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public DefinitionUsageExtractor(){

        super();
        //type = FeatureType.DEFINITION_USAGE;
        type = FeatureTypeTree.DEFINITION_USAGE;

    }


    /************************************************************************
     *
     *          classify goes through the pattern list and matches any of them
     *
     * @param fragment
     * @return
     *
     *      //TODO: Add detection of usage of definition without capital letter
     */


    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, String tag){

        //System.out.println("Classifying definition usage ( "+ fragment.getText()+")");

        // TODO: First check if this is actually the definition.
        // If that is the case, we don't classify it as usage


        List<FeatureDefinition> matchList = outcome.getDefinitions();

        AbstractDocument document = fragment.getDocument();

        if(document != null && document.getDefinitions() != null){


            for(AbstractDefinition definition : document.getDefinitions()){
                TextPattern p;

                p = new TextPattern("(\\b" + definition + "\\b)").caseSensitive();
                List<FeatureDefinition> matches = matchRegexp(fragment, p, FeatureActionType.CORRECT_DEFINITION_USAGE);

                if(!matches.isEmpty()){

                    if(!outcome.contains(FeatureTypeTree.DEFINITION, definition.term))

                        matchList.addAll(matches);

                    }
                else
                    AnalysisLogger.log(AnalysisLogger.Level.INFO, "Found definition " + definition + " in usage extractor, but it is ignored as a definition");



                if(!definition.term.toLowerCase().equals(definition.term)){

                    // If the definition is NOT lowercase, we look for a match with lowercase.
                    // This is an incorrect usage (or at least ambiguity in the document

                    p = new TextPattern("(\\b" + definition.term.toLowerCase() + "\\b)").caseSensitive().withTag(tag);
                    matches = matchRegexp(fragment, p, FeatureActionType.INCORRECT_DEFINITION_USAGE);

                    if(!matches.isEmpty()){

                        if(!outcome.contains(FeatureTypeTree.DEFINITION, definition.term))

                            matchList.addAll(matches);

                        }
                    else
                        AnalysisLogger.log(AnalysisLogger.Level.INFO, "Found definition " + definition + " in usage extractor, but it is ignored as a definition");

                }

            }

        }


        outcome.setDefinitions(matchList);

    }


    public FeatureActionType getAction(){

        return FeatureActionType.CORRECT_DEFINITION_USAGE;
    }


}
