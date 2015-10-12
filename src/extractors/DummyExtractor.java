package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Ulf Änggård
 *
*A dummyExtractor is used in order to be able to run ExtractorTestContracts without having to classify all fragments with a relevant extractor.
 *
 *
 */

public class DummyExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for. Extensive use of skipgrams close() is very good

    //TODO: If statement that might apply different patterns depending on ProjectType. Added methods in FeatureExplanation class.

      private static final TextPattern[] patterns = {

              new TextPattern(new String[]{"anononsensentence"}).noneOf()

      };


    public static final String name = "dummy";
    public static final String searchKeywords[] = new String[] {};


    public DummyExtractor(){

        super();

    }

    public AnalysisOutcome classify(AnalysisFragment fragment){


        AnalysisOutcome outcome = null;

        for(TextPattern p : patterns){

            outcome = new AnalysisOutcome().addDefinition(new FragmentCondition(fragment).contains(p).testClassification(FeatureActionType.CLASSIFY, type, name));

        }

        return outcome;
    }


}
