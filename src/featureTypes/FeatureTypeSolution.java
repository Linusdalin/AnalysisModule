package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeSolution extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#SOLUTION";

    private static final String akaTag = "";


    public FeatureTypeSolution() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent(FeatureTypeTree.ContractDelivery);
        description = "Descriping the target solution to deliver";
    }
}
