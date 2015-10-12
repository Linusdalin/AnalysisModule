package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ConfidentialityClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ConfidentialityClassifierSV extends ConfidentialityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().word("offentlighetsprincipen"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Legal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().word("sekretess"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Sekretess"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .word("konfidentiell")
                            .word("information"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Legal"),


    };


    public ConfidentialityClassifierSV(){

        super(LanguageSpecificRuleList);
        keywords = "hemligt, confidensiality, disclosure";
        name = "Konfidensialityt";

    }


}
