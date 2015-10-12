package featureTypes;


/**
 *          Potential risk
 *
 */
public class FeatureTypeRisk extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#RISK";
    private static final String akaTag = "";


    public FeatureTypeRisk() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Highlight );
        description = "Potential risk in the interpretation of the document";

    }
}
