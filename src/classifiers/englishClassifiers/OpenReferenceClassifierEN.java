package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceClassifier;
import classifiers.baseClassifiers.OpenReferenceAnalyser;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class OpenReferenceClassifierEN extends OpenReferenceAnalyser implements ClassifierInterface {

    private static final String ExhibitNames = "^(exhibit|document)[ ]*(\\d+[\\.\\d+]*)";

    public OpenReferenceClassifierEN(){

        super();
        namesForExhibit = ExhibitNames;
        name = "Reference";

    }

}
