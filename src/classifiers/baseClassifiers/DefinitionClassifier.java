package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 *          Language generic Definitions
 *
 *
 */



public abstract class DefinitionClassifier extends Classifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {



    };


    public DefinitionClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.DEFINITION, FeatureTypeTree.DEFINITION_CONCEPT);
    }



}
