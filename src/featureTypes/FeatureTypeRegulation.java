package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeRegulation extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#REGULATION";

    private static final String akaTag = "";


    public FeatureTypeRegulation() {

        super(tag, akaTag);

        setParent( FeatureTypeTree.Structure );

        //isPartOf( FeatureTypeTree.XXX );

    }
}
