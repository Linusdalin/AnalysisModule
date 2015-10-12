package language;

import analysis.FeatureExtractorInterface;
import classifiers.ClassifierInterface;
import openNLP.NLParser;
import stemming.StemmerInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-05-29
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 *
 */

public interface LanguageInterface {

    boolean isLanguage(String bodyText);
    LanguageCode getLanguageCode();
    String getName();
    public String getModelPrefix();

    public String[] getIgnoreList();

    StemmerInterface getStemmer();

    ClassifierInterface[] getSupportedClassifiers();
    ClassifierInterface[] getOpenReferenceClassifiers();
    ClassifierInterface[] getPostProcessClassifiers();
    ClassifierInterface[] getAllClassifiers();

    public String[] getClassifierKeywords();
    public String getClassificationForName(String localizedTag);
    public String getLocalizedClassification(String tag);

    public NLParser getParser(String modelDirectory);

    String wash(String text);

    void enhancePOS(String[] tokens, String[] posTags);
}
