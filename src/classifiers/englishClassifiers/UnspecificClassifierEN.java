package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.UnspecificClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class UnspecificClassifierEN extends UnspecificClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


    };


    public UnspecificClassifierEN(){

        super(LanguageSpecific);
        name = "Unspecific";

    }

}
