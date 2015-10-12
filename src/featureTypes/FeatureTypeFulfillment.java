package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeFulfillment extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#FULFILLMENT";
    private static final String akaTag = "";


    public FeatureTypeFulfillment() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Phases );

    }
}
