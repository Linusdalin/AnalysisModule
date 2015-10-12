package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeBackground extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#BACKGROUND";

    private static final String akaTag = "";


    public FeatureTypeBackground() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.Content );

        definition = "Iinformation given as background to better under the context.";
        description = "General background information relevant to introduce the users to the context.";

    }
}
