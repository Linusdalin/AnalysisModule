package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypePayment extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PAYMENT";

    private static final String akaTag = "";


    public FeatureTypePayment() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.TERMS);
        description = "Describes how payment is done.";

    }
}
