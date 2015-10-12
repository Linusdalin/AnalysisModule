package featureTypes;


/**
 *              Risk Management Framework
 *
 */


public class FeatureTypeRiskMgmnt extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#RISK_MANAGEMENT";
    private static final String akaTag = "";


    public FeatureTypeRiskMgmnt() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.SOLUTION);

        description = "Describing the risk management aspect of the work";


    }
}
