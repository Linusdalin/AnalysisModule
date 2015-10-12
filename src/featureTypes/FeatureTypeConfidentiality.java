package featureTypes;


/**
 *          Compound feature type containing sub categories like compensation and payment
 */


public class FeatureTypeConfidentiality extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#CONFIDENTIALITY";
    private static final String akaTag = "";


    public FeatureTypeConfidentiality() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Legal );

    }
}
