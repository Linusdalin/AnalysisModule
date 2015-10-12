package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SLAClassifier;
import classifiers.baseClassifiers.SecurityClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SecurityClassifierEN extends SecurityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(risk|threat)")
                            .pattern("(analysis|handling|level(s)*|mitigation)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("risk"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(create|delete|modify)")
                            .pattern("role(s)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("role(s)*")
                            .pattern("(restriction(s)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(access)")
                            .pattern("(information)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(audit)")
                            .pattern("(trail)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),


    };


    public SecurityClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "Security";
    }


}
