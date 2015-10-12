package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PreconditionClassifier;
import classifiers.baseClassifiers.ScopeClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PreconditionClassifierSV extends PreconditionClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


    };


    public PreconditionClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Förutsättnigar";
    }


}
