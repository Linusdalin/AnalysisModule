package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeExpenses extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#EXPENSES";

    private static final String akaTag = "";


    public FeatureTypeExpenses() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.TERMS);
        description = "Describes how expenses related to delivery are compensated.";

    }
}
