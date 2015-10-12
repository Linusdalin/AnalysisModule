package extractors;

import analysis.*;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: User
 *
 *       Extracts all fragments that are related to Terms by looking for Term related word and PoS patterns.
 *
 */

public class TermExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    public TermExtractor(){

        //type = FeatureType.TERM;
        type = FeatureTypeTree.TERM;

        actionType = FeatureActionType.CLASSIFY;

    }


}
