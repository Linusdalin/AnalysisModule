package analysis2;

import featureTypes.FeatureTypeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-04
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class Replacer implements ReplacerInterface {

    protected String ruleName;
    protected String tag = "";  // Default empty
    List<Criteria> criteria = new ArrayList<>();
    private FeatureTypeInterface featureType = null;
    protected boolean verbose = false;
    protected String semanticExtraction = null;

    public static final int CASE_SENSITIVE = 0;


    @Override
    public ReplacerInterface withTag(String tag) {

        this.tag = tag;
        return this;
    }

    @Override
    public String getTag() {
        return tag;
    }


    @Override
    public ReplacerInterface withRuleName(String ruleName) {

        this.ruleName = ruleName;
        return this;
    }

    @Override
    public ReplacerInterface withCriteria(Criteria criteria) {

        this.criteria.add(criteria);
        return this;
    }


    @Override
    public ReplacerInterface withClassification(FeatureTypeInterface type) {

        this.featureType = type;
        return this;
    }


    @Override
    public FeatureTypeInterface getFeatureType() {
        return featureType;
    }

    @Override
    public ReplacerInterface verbose() {

        this.verbose = true;
        return this;
    }


    @Override
    public String getBody() {

        if(criteria.size() == 0)
            return "empty rule";
        return criteria.get(0).tokenList.toString();
    }




}
