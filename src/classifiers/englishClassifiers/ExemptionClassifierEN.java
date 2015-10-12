package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.BackgroundClassifier;
import classifiers.baseClassifiers.ExemptionClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ExemptionClassifierEN extends ExemptionClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


    };


    public ExemptionClassifierEN(){

        super(LanguageSpecific);
        name = "Exemption";

    }

}
