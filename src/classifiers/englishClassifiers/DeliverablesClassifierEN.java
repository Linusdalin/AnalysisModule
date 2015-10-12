package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DeliverablesClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DeliverablesClassifierEN extends DeliverablesClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


    };

    public DeliverablesClassifierEN(){

        super(RuleList);
        name = "Deliverable";
    }

}
