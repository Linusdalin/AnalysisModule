package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypePercentage extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PERCENTAGE";
    private static final String akaTag = "#NUMEX";

    public FeatureTypePercentage() {

        super(tag, akaTag);

        // Set partof

        //setIsPartOf( FeatureTypeTree.Structure );

        setParent( FeatureTypeTree.Structure );


    }

}
