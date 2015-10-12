package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeITOPeration extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#IT_OPERATION";

    private static final String akaTag = "";


    public FeatureTypeITOPeration() {

        super(tag, akaTag);

        // Set part of hierarchy

        //setIsPartOf( FeatureTypeTree.SLA );

    }
}
