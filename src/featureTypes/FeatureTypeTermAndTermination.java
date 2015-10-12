package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */

public class FeatureTypeTermAndTermination extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#TERM_AND_TERMINATION";
    private static final String akaTag = "";


    public FeatureTypeTermAndTermination() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent(FeatureTypeTree.General);

        definition = "What are the Term and Termination conditions?";
        description = "The term and termination conditions of a Contract.";
    }
}
