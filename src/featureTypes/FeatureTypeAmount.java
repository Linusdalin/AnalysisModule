package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeAmount extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#AMOUNT";

    private static final String akaTag = "#NUMEX";


    public FeatureTypeAmount() {

        super(tag, akaTag);

        // Set partof hierarchy

        setParent(FeatureTypeTree.Structure);

    }

}
