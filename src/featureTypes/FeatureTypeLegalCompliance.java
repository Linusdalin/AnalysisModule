package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeLegalCompliance extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#LEGAL_COMPLIANCE";

    private static final String akaTag = "";


    public FeatureTypeLegalCompliance() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.COMPLIANCE);
        description =         "References to Legal Frameworks relevant to the contract";


    }
}
