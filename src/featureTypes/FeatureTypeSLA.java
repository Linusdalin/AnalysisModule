package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeSLA extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#SLA";
    private static final String akaTag = "";


    public FeatureTypeSLA() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.SolutionReq );

    }
}
