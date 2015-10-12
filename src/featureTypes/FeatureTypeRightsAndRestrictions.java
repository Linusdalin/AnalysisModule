package featureTypes;


/**
 *          Compound feature type
 */

public class FeatureTypeRightsAndRestrictions extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#RIGHTS_AND_OBLIGATIONS";

    private static final String akaTag = "";


    public FeatureTypeRightsAndRestrictions() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.ContractDelivery);

        definition = "What must and can the Parties do?";
        description= "The Rights, Restrictions, Responsibilities and Obligations of the Parties";
    }
}
