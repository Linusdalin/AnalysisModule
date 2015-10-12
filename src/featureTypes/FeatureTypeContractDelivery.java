package featureTypes;


/**
 *          Top level
 */

public class FeatureTypeContractDelivery extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#CONTRACT_DELIVERY";
    private static final String akaTag = "";


    public FeatureTypeContractDelivery() {

        super(tag, akaTag);
        description = "The Contract Delivery Framework. Classifications for identifying and deliver on contractual commitments";

    }
}
