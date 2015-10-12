package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeLegalReference extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#LEGAL_REFERENCE";
    private static final String akaTag = "";

    public FeatureTypeLegalReference() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Reference );

    }
}
