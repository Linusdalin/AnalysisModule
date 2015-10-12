package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeEmail extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#EMAIL";

    private static final String akaTag = "";


    public FeatureTypeEmail() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Structure );

    }
}
