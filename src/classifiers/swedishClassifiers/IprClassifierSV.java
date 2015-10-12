package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.IPRClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class IprClassifierSV extends IPRClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .word("licensavtal").word("immaterial").word("upphovsrätt").word("licenskostnader"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(rätt(en)*|rättighet(er)*)")
                            .pattern("(licens(er|en)*|system|program(vara)*|information)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent()
                            .pattern("(äger)")
                            .pattern("(rätt(en)*|rättighet(er|erna)*)")
                            .pattern("till"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(icke-exklusiv|evig|oåterkallelig)")
                            .pattern("(rätt|rättighet|licens)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(immaterial|immateriell|immateriella)")
                            .pattern("(rättighet(er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            //Mix abstract and defined

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(know-how|processes|information|upptäkter|innovationer|iakttagelser)")
                            .pattern("(dokument(ation)*|(dator)*program|källkod(er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Abstract"),


    };

    public IprClassifierSV(){

        super(RuleList);
        name = "Upphovsrätt";

    }

}
