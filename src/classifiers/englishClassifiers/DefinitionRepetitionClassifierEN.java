package classifiers.englishClassifiers;

import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefinitionRepetitionClassifier;
import classifiers.baseClassifiers.DefinitionUsageClassifier;

/******************************************************************************
 *
 *
 *          Definition usage classifier
 *
 *          NOTE: This has a overridden classify method that loops over definitions in the project
 *
 */



public class DefinitionRepetitionClassifierEN extends DefinitionRepetitionClassifier implements ClassifierInterface {



    public DefinitionRepetitionClassifierEN(){

        super();
        keywords = "";
        name = "DefinitionRepetition";

    }



    public void classify(TextFragment sentence, int currentPass){


        super.classify(sentence,  currentPass);

    }

}
