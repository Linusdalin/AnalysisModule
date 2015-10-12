package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceCriteriaClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AcceptanceCriteriaClassifierEN extends AcceptanceCriteriaClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().word("award").word("criteria"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Award Criteria")



    };


    public AcceptanceCriteriaClassifierEN(){

        super(LanguageSpecific);
        name = "AcceptanceCriteria";

    }

}
