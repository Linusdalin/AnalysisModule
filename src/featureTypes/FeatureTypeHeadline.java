package featureTypes;


/**
 *
 *      This is mostly for internal use
 */

public class FeatureTypeHeadline extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#HEADLINE";

    private static final String akaTag = "";


    public FeatureTypeHeadline() {

        super(tag, akaTag);

        setParent(FeatureTypeTree.Structure);



    }
}
