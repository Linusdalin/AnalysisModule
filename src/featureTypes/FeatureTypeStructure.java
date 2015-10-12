package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeStructure extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#STRUCTURE";
    private static final String akaTag = "";


    public FeatureTypeStructure() {

        super(tag, akaTag);

        setParent( FeatureTypeTree.Highlight );
        description = "Structure related tags. For navigation";

    }
}
