package featureTypes;


/**
 *
 *
 */


public class FeatureTypeSupportMaint extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#SUPPORT_MAINTENANCE";
    private static final String akaTag = "";


    public FeatureTypeSupportMaint() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Scope );

    }
}
