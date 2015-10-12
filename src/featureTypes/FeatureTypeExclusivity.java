package featureTypes;


/**
 *
 *
 */


public class FeatureTypeExclusivity extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#EXCLUSIVITY";

    private static final String akaTag = "";


    public FeatureTypeExclusivity() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Risk );

    }
}
