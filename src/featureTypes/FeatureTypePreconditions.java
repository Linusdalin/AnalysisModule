package featureTypes;


/**
 *
 *
 */


public class FeatureTypePreconditions extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PRECONDITION";
    private static final String akaTag = "";


    public FeatureTypePreconditions() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Scope );

    }
}
