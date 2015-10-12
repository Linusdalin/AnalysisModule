package extractors.baseExtractors;

import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

@Deprecated
public abstract class AwardCriteriaExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public AwardCriteriaExtractor(){

        super();

        type = FeatureTypeTree.ACCEPTANCE_CRITERIA;


    }


}
