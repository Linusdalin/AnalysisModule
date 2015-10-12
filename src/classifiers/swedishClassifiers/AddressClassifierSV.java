package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AddressClassifier;
import classifiers.baseClassifiers.EmailClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AddressClassifierSV extends AddressClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

    };


    public AddressClassifierSV(){

        super(LanguageSpecificRuleList);
        keywords = "adress, post";
        name = "Adress";

    }


}
