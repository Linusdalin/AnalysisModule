package analysis2;

import featureTypes.FeatureTypeInterface;
import openNLP.NLParser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public interface ReplacerInterface {

    List<TextSpan> extractSpan(ParseNodeInterface originalSentence);
    ReplacerInterface withRuleName(String s);

    ReplacerInterface withTag(String s);
    String getTag();
    String getBody();

    ReplacerInterface withExtraction(int span);
    ReplacerInterface withSemanticExtraction(int i);
    ReplacerInterface withSemanticExtraction(String key);

    ReplacerInterface withCriteria(Criteria criteria);
    ReplacerInterface withGroupRestrictionCriteria(int group, SemanticRestriction.RestrictionType restriction);


    public int getMainExtractionSpan();
    public int getSemanticExtractionSpan();

    boolean evaluateExtractionRestrictions(List<TextSpan> newNode, NLParser spans);

    // Setting and getting the feature type overriding the feature type set in the classifier

    public ReplacerInterface withClassification(FeatureTypeInterface type);
    FeatureTypeInterface getFeatureType();

    ReplacerInterface verbose();
}
