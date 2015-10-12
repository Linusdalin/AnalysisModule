package featureTypes;


/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeDeadline extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#DEADLINE";

    private static final String akaTag = "#DATE";


    public FeatureTypeDeadline() {

        super(tag, akaTag);

        // Set partof hierarchy

        setParent(FeatureTypeTree.Structure);

    }

}
