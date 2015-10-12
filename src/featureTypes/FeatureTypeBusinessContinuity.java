package featureTypes;


/**
 *
 *
 */


public class FeatureTypeBusinessContinuity extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#BUSINESS_CONTINUITY";
    private static final String akaTag = "";


    public FeatureTypeBusinessContinuity() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.SLA );

    }
}
