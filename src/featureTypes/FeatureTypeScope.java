package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeScope extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#Scope";
    private static final String akaTag = "";


    public FeatureTypeScope() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.SOLUTION);

    }
}
