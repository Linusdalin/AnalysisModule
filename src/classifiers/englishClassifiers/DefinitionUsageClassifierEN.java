package classifiers.englishClassifiers;

import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefinitionUsageClassifier;
import language.English;

/******************************************************************************
 *
 *
 *          Definition usage classifier
 *
 *          NOTE: This has a overridden classify method that loops over definitions in the project
 *
 */



public class DefinitionUsageClassifierEN extends DefinitionUsageClassifier implements ClassifierInterface {



    public DefinitionUsageClassifierEN(){

        super();
        keywords = "";
        name = "DefinitionUsage";

    }



    public void classify(TextFragment sentence, int currentPass){


        super.classify(sentence, "Incorrect", "Correct", currentPass, English.commonWords);

    }

}
