package classifiers.swedishClassifiers;

import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefinitionUsageClassifier;
import language.Swedish;

/******************************************************************************
 *
 *
 *          Definition usage classifier
 *
 *          NOTE: This has a overridden classify method that loops over definitions in the project
 *
 */



public class DefinitionUsageClassifierSV extends DefinitionUsageClassifier implements ClassifierInterface {



    public DefinitionUsageClassifierSV(){

        super();
        keywords = "";
        name = "Definitionsreferens";

    }



    public void classify(TextFragment sentence, int currentPass){

            //AnalysisLogger.log(Level.DEBUG, " In DefinitionUsage Classifier!");

            //System.out.println(sentence.display( 0 ));
            //AnalysisLogger.log(Level.DEBUG, "***************");

        super.classify(sentence, "Inkorrekt", "Korrekt", currentPass, Swedish.commonWords);

        //AnalysisLogger.log(Level.DEBUG, " After DefinitionUsage Classifier!");

        //System.out.println(sentence.display( 0 ));
        //System.out.println(" ******************");


    }

}
