package classifiers.swedishClassifiers;

import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefinitionRepetitionClassifier;
import classifiers.baseClassifiers.DefinitionUsageClassifier;

/******************************************************************************
 *
 *
 *          Definition repetition classifier
 *
 *          NOTE: This has a overridden classify method that loops over definitions in the project
 *
 */



public class DefinitionRepetitionClassifierSV extends DefinitionRepetitionClassifier implements ClassifierInterface {



    public DefinitionRepetitionClassifierSV(){

        super();
        keywords = "";
        name = "Definitionsrepetering";

    }



    public void classify(TextFragment sentence, int currentPass){

            //AnalysisLogger.log(Level.DEBUG, " In DefinitionUsage Classifier!");

            //System.out.println(sentence.display( 0 ));
            //AnalysisLogger.log(Level.DEBUG, "***************");

        super.classify(sentence, currentPass);

        //AnalysisLogger.log(Level.DEBUG, " After DefinitionUsage Classifier!");

        //System.out.println(sentence.display( 0 ));
        //System.out.println(" ******************");


    }

}
