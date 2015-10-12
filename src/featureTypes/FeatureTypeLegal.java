package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeLegal extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#LEGAL";

    private static final String akaTag = "";


    public FeatureTypeLegal() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.ContractDelivery);
        description = "Defines the specific legal requirements attached to a Contract.";

    }
}
