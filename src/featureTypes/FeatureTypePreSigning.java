package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypePreSigning extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PRE_SIGNING";
    private static final String akaTag = "";


    public FeatureTypePreSigning() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Phases );

    }
}
