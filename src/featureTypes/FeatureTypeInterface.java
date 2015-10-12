package featureTypes;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-23
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public interface FeatureTypeInterface {

    public List<FeatureTypeInterface> getIsPartOfList();
    boolean isPartOf(FeatureTypeInterface higherAbstractionFeature);
    void setIsPartOf(FeatureTypeInterface higherAbstractionFeature);
    void setParent(FeatureTypeInterface higherAbstractionFeature);

    public String getName();
    public FeatureTypeInterface getParent();
    public String getAkaTag();
    public String toString();
    public String getDescription();
    public String getDefinition();

    String getHierarchy();
    String createKeywordString(String keywords);
}
