package classifiers;

import analysis2.TextFragment;
import featureTypes.FeatureTypeInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public interface ClassifierInterface {

    public String getClassificationName();
    public String getDescription();
    public FeatureTypeInterface getType();
    public void classify(TextFragment s, int mainPass);

}
