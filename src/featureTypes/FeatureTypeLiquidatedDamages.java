package featureTypes;


/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTypeLiquidatedDamages extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#LIQUIDATED_DAMAGES";

    private static final String akaTag = "";


    public FeatureTypeLiquidatedDamages() {

        super(tag, akaTag);

        // Set part of hierarchy

        setParent( FeatureTypeTree.DAMAGES);
        description = "Describes the Liquidated Damages in case of breach.";

    }
}
