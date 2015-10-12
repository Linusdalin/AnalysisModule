package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeParts extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PARTY";

    private static final String akaTag = "#LEGAL_ENTITY";


    public FeatureTypeParts() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent(FeatureTypeTree.LegalEntity);

        definition = "Which are the involved Parties?";
        description = "Defines the legal entity of the Parties.";
    }
}
