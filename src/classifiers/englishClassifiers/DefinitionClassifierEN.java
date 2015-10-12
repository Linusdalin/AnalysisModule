package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefinitionClassifier;
import classifiers.baseClassifiers.DefinitionSourceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DefinitionClassifierEN extends DefinitionClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecific = {

    };


    public DefinitionClassifierEN(){

        super(LanguageSpecific);
        keywords = "";
        name = "DefinitionConcept";

    }



}
