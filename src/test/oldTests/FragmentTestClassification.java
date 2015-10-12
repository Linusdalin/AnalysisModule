package test.oldTests;

import analysis.FeatureExtractorInterface;
import parse.AnalysisFragment;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by ulfanggard on 28/04/14.
 */
public class FragmentTestClassification {


    private final AnalysisFragment fragment;
    private final List<FeatureExtractorInterface> extractorPositives;

    public FragmentTestClassification(AnalysisFragment fragment, List<FeatureExtractorInterface> extractorPositives){


        this.fragment = fragment;
        this.extractorPositives = extractorPositives;
    }


    public void testIt(List<FeatureExtractorInterface> all) {

        /*

        for(FeatureExtractorInterface extractor : all){

            AnalysisOutcome d= extractor.classify(fragment);

            if(extractorPositives.contains(extractor))
                assertTrue(d.isMA());
            else
                assertFalse(d.isA());


        }

        */
    }
}
