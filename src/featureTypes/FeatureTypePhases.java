package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypePhases extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PHASES";
    private static final String akaTag = "";


    public FeatureTypePhases() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.ContractDelivery );

    }
}
