package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeStandardsCompliance extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#STANDARDS_COMPLIANCE";

    private static final String akaTag = "";


    public FeatureTypeStandardsCompliance() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.COMPLIANCE);
        description = "References to Industry Standard Frameworks relevant to the contract";


    }
}
