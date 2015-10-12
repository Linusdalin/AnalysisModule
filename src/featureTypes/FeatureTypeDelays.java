package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeDelays extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#Delays";
    private static final String akaTag = "";


    public FeatureTypeDelays() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.SLA );

    }
}
