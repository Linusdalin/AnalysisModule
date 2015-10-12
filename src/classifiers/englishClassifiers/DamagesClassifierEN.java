package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DamagesClassifier;
import classifiers.baseClassifiers.InvoicingClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DamagesClassifierEN extends DamagesClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(claim(s)*)")
                                .pattern("(damage(s)*)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),
    };

    public DamagesClassifierEN(){

        super(RuleList);
        name = "Damages";

    }

}
