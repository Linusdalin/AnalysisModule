package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SupportMaintenanceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SupportMaintenanceClassifierSV extends SupportMaintenanceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .pattern("(serviceavtal(et)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("service"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(åtagande(et)*)")
                            .pattern("(support|underhåll(et)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("service"),



    };


    public SupportMaintenanceClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Support och Underhåll";
    }


}
