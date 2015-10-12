package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeCompliance extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#COMPLIANCE";

    private static final String akaTag = "";


    public FeatureTypeCompliance() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.ContractDelivery);
        description = "Requirements on or references to compliance frameworks";
    }
}
