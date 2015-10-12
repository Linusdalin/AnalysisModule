package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeGovernance extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#GOVERNANCE";

    private static final String akaTag = "";


    public FeatureTypeGovernance() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.ContractDelivery);

        definition = "How is the contract Governed?";
        description = "Includes Change Mgmt, Reporting, Audit and Disclosure terms. These are key issues for any successfull execution of a contract.";
    }
}
