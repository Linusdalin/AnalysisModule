package classifiers.baseClassifiers;

import analysis2.NodeClass;
import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
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



public abstract class URLClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("\\b(?:https?:\\/\\/)?(([\\da-z\\.-]+)\\.(com|se|no|dk|co\\.uk|de|fr|be|nl|ru)([\\/\\w][\\/]*)*)\\b")
                    .withExtraction(1)
                    .withTag("URL")
    };

    protected URLClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.URL, FeatureTypeTree.URL);
    }

}
