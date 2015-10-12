package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeDelivery extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#DELIVERY";
    private static final String akaTag = "";


    public FeatureTypeDelivery() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Scope );

    }
}
