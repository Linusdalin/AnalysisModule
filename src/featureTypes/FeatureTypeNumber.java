package featureTypes;


/**
 *
 *      Basic classification for number. Used in a lot of classifications
 *
 */
public class FeatureTypeNumber extends FeatureType implements FeatureTypeInterface {

    private static final String tag = "#NUMEX";

    private static final String akaTag = "";


    public FeatureTypeNumber() {

        super(tag, akaTag);

        // Set partof hierarchy

        setIsPartOf(FeatureTypeTree.Date);
        setIsPartOf(FeatureTypeTree.DetailedValue);
        setIsPartOf(FeatureTypeTree.Amount);
        setIsPartOf(FeatureTypeTree.Percentage);
        setIsPartOf(FeatureTypeTree.Headline);    // Chapter numbers
        setIsPartOf(FeatureTypeTree.LegalEntity);      // pnr and org nr

        setParent(FeatureTypeTree.Structure);

    }

}
