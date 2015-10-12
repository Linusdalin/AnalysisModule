package featureTypes;


/**
 *          Compound feature type containing sub categories like compensation and payment
 */


public class FeatureTypeTerms extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#TERMS";
    private static final String akaTag = "";


    public FeatureTypeTerms() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Financial );
        description = "Contract terms. What will be paied and when.";
    }
}
