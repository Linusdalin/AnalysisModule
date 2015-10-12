package featureTypes;


/**
 *
 *
 */


public class FeatureTypeDefects extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#Defects";
    private static final String akaTag = "";


    public FeatureTypeDefects() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.SLA );

    }
}
