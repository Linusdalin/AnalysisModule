package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.URLClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class URLClassifierSV extends URLClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

    };


    public URLClassifierSV(){

        super(LanguageSpecificRuleList);
        keywords = "url, link";
        name = "Url";

    }


}
