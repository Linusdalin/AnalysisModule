package extractors;

import analysis.FeatureActionType;
import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: User
 *
 *       Extracts all fragments that are related to Payments
 *
 */

public abstract class CompensationExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    public CompensationExtractor(){

        type = FeatureTypeTree.COMPENSATION;

        actionType = FeatureActionType.CLASSIFY;

    }


}
