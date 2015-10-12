package featureTypes;


/**
 *          Top level classification for content classifications like definitions and reference
 */

public class FeatureTypeContent extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#CONTENT";

    private static final String akaTag = "";


    public FeatureTypeContent() {

        super(tag, akaTag);
        setParent( FeatureTypeTree.Highlight );

        description = "Content related tags for navigation";

    }
}
