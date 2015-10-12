package featureTypes;


/**
 *              Unspecific or vague expressions
 *
 */


public class FeatureTypePenalties extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#PENALTIES";
    private static final String akaTag = "";


    public FeatureTypePenalties() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Risk );
        description = "Describes the penalty a Party has to pay in case of breach, beyond the actual damage.";

    }
}
