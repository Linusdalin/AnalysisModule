package analysis;

import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;

import java.util.List;

/**
 *      This is the interface of a feature extractor.
 */

public interface FeatureExtractorInterface {

    // Classify the feature returning an AnalysisOutcome (with a list of FeatureDefinitions

    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, FeatureTypeTree tree);
    public String getName();
    public List<String> getKeywords();
    FeatureActionType getAction();
}
