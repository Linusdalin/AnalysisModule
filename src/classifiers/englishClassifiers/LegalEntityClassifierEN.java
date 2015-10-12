package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.LegalEntityClassifier;
import classifiers.baseClassifiers.PartsClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LegalEntityClassifierEN extends LegalEntityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("\\b(\\d\\d(0[1-9]|1[012])\\d\\d-\\d{4})\\b")
                    .withTag("Person"),                   // ex: 701210-1234
            new RegExpReplacer("\\b(\\d\\d[23456789]\\d\\d\\d-\\d{4})\\b")
                    .withTag("Organization"),                   // ex: 702210-1234

            new RegExpReplacer("org[\\. ]*nr[\\.:; ]*[\\(\\[]((.{6}-.{4}))[\\)\\]]")
                    .withTag("Organization"),                   // ex: 702210-1234


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(contracting|involved)")
                            .pattern("(parties|entit(y|ies))"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),
    };

    public LegalEntityClassifierEN(){

        super(RuleList);
        name = "LegalEntity";

    }

}
