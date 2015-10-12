package featureTypes;


/**
 *
 *
 */


public class FeatureTypeSolutionReq extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#REQ_SPEC";
    private static final String akaTag = "";


    public FeatureTypeSolutionReq() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Scope );

    }
}
