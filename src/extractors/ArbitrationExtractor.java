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

public abstract class ArbitrationExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    public ArbitrationExtractor(){

        //type = FeatureType.Arbitration;
        type = FeatureTypeTree.ARBITRATION;

        actionType = FeatureActionType.CLASSIFY;

    }


}
