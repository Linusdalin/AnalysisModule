package classifiers.swedishClassifiers;

import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.LegalReferenceClassifier;
import classifiers.baseClassifiers.NumberClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LegalReferenceClassifierSE extends LegalReferenceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


    };

    public LegalReferenceClassifierSE(){

        super(RuleList);
        name = "Lagreferens";

    }

}
