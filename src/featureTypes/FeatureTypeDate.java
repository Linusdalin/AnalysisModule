package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeDate extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#DATE";

    private static final String akaTag = "#NUMEX";


    public FeatureTypeDate() {

        super(tag, akaTag);

        // Set partof

        setIsPartOf(FeatureTypeTree.Deadline);
        setIsPartOf(FeatureTypeTree.Period);

        //Hierarchy

        setParent(FeatureTypeTree.Structure);

    }

}
