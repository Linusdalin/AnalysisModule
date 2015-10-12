package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeCompensation extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#COMPENSATION";

    private static final String akaTag = "";


    public FeatureTypeCompensation() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.TERMS);
        description = "Describes what will be compensated and what will not.";

    }
}
