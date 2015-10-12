package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypeSecurity extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#SECURITY";
    private static final String akaTag = "";


    public FeatureTypeSecurity() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.RiskMgmnt );
        description = "Security risk";
    }
}
