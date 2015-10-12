package featureTypes;


/**********************************************''
 *
 *          Disclaimer
 *
 */


public class FeatureTypeExemption extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#DISCLAIMER";

    private static final String akaTag = "";


    public FeatureTypeExemption() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.RIGHTS_AND_OBLIGATIONS);

    }
}
