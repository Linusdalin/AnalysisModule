package classifiers.swedishClassifiers;

import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.OpenReferenceAnalyser;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class OpenReferenceClassifierSV extends OpenReferenceAnalyser implements ClassifierInterface {

    private static final String ExhibitNames = "^((bilaga|dokument) (\\d+[\\.\\d+]*))";

    public OpenReferenceClassifierSV(){

        super();
        namesForExhibit = ExhibitNames;
        name = "Referens";

    }

}
