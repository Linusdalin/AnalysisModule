package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeUnspecific extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#UNSPECIFIC";
    private static final String akaTag = "";


    public FeatureTypeUnspecific() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Risk );

    }
}
