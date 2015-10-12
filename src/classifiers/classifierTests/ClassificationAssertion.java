package classifiers.classifierTests;

import featureTypes.FeatureTypeInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-06
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationAssertion {


    public final FeatureTypeInterface featureType;
    public final int count;
    public String tag = null;
    public String pattern = null;
    public String semanticExtraction = null;
    public String keywords = null;

    ClassificationAssertion(FeatureTypeInterface featureType, int count){


        this.featureType = featureType;
        this.count = count;
    }


    ClassificationAssertion withTag(String tag){

        this.tag = tag;
        return this;
    }

    public ClassificationAssertion withPattern(String pattern) {

        this.pattern = pattern;
        return this;
    }

    public ClassificationAssertion withSemanticExtraction(String pattern) {

        this.semanticExtraction = pattern;
        return this;
    }


    public ClassificationAssertion withKeywords(String keywords) {

        this.keywords = keywords;
        return this;
    }


}
