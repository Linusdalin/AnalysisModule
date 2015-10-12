package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeReference extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#REFERENCE";
    private static final String akaTag = "";

    public FeatureTypeReference() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Content );

    }
}