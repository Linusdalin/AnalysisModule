package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeTerm extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#TERM";
    private static final String akaTag = "";


    public FeatureTypeTerm() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.TERM_AND_TERMINATION);

    }
}
