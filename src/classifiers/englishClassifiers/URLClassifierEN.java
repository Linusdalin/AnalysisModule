package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.EmailClassifier;
import classifiers.baseClassifiers.URLClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class URLClassifierEN extends URLClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

    };


    public URLClassifierEN(){

        super(LanguageSpecificRuleList);
        keywords = "url, link";
        name = "Url";

    }


}
