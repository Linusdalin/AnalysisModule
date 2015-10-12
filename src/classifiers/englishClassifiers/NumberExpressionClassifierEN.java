package classifiers.englishClassifiers;

import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.NumberClassifier;
import classifiers.baseClassifiers.NumberExpressionClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class NumberExpressionClassifierEN extends NumberExpressionClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

    };

    public NumberExpressionClassifierEN(){

        super(RuleList);
        name = "Number";
    }

}
