package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.TerminationClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class TerminationClassifierEN extends TerminationClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(cancel(ation)*|terminat(e|ion)|revok(e|ation)|withdraw(al)*)")
                                .pattern("(rfp|rfq|contract|proposal|agreement)")
                                .pattern("(any)")
                                .pattern("(time)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag("Convenience"),


            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(cancel(ation)*|terminat(e|ion)|revok(e|ation)|withdraw(al)*)")
                                .pattern("(rfp|rfq|contract|proposal|agreement)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),


    };

    public TerminationClassifierEN(){

        super(RuleList);
        name = "Termination";
    }

}
