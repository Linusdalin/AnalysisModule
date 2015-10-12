package featureTypes;


/**
 *          Top level
 *
 */
public class FeatureTypeHighlight extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#HIGHLIGHT";
    private static final String akaTag = "";


    public FeatureTypeHighlight() {

        super(tag, akaTag);
        description = "Highlighting contents in the document";

    }
}
