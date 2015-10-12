package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceClassifier;
import classifiers.baseClassifiers.IPRClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class IPRClassifierEN extends IPRClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(proprietary)")
                            .pattern("(information)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(intellectual)")
                            .pattern("(property)")
                            .pattern("(right(s)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .pattern("\\b(ipr)\\b"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

    };

    public IPRClassifierEN(){

        super(RuleList);
        name = "IPR";

    }

}
