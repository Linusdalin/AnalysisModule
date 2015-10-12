package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeWarranty extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#WARRANTY";

    private static final String akaTag = "";


    public FeatureTypeWarranty() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Financial);
        description = "Describes the Warranty obligations and conditions of the Deliverables.";

    }
}
