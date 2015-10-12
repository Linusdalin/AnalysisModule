package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeAcceptanceCriteria extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#ACCEPTANCE_CRITERIA";

    private static final String akaTag = "";


    public FeatureTypeAcceptanceCriteria() {

        super(tag, akaTag);

        // Set partof hierarchy

        setParent(FeatureTypeTree.ACCEPTANCE);

    }

}
