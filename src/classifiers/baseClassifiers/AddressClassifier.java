package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class AddressClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("\\b([A-z](\\w)+((väg|gata|gränd|torg|allé)(en|et|n)*\\s+\\d[\\d]*)*[,]*\\s+(\\b(SE-)*\\d\\d\\d \\d\\d\\b)\\s+([A-Z]\\w*)*)"),

    };

    protected AddressClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.STRUCTURE, FeatureTypeTree.ADDRESS);
    }

}
