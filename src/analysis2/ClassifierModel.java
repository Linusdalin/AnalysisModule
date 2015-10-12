package analysis2;

import classifiers.ClassifierInterface;
import language.LanguageInterface;

/**
 *              The main class for classification
 *
 */
public class ClassifierModel {

    private LanguageInterface language;

    public ClassifierModel(LanguageInterface language){


        this.language = language;
    }


    public TextFragment classify(TextFragment sentence, int currentPass){

        ClassifierInterface[] supportedClassifiers =  language.getSupportedClassifiers();

        for(ClassifierInterface classifier : language.getSupportedClassifiers()){

            classifier.classify(sentence, currentPass);

        }

        return sentence;

    }

    public TextFragment analyseOpenReferences(TextFragment sentence, int currentPass){


        for(ClassifierInterface classifier : language.getOpenReferenceClassifiers()){

            classifier.classify(sentence, currentPass);

        }

        return sentence;

    }

    public TextFragment analyseDefinitionUsage(TextFragment sentence, int currentPass){

        //System.out.println(" --- Post processing");


        for(ClassifierInterface classifier : language.getPostProcessClassifiers()){

            //System.out.println(" --- classifying " + classifier.getType());

            classifier.classify(sentence, currentPass);

        }

        return sentence;

    }




}
