package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.StandardComplianceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class StandardsComplianceClassifierSV extends StandardComplianceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .word("sedvänja"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .word("itil"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),




            new RegExpReplacer("\\bPUL\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"http://www.notisum.se/Pub/Doc.aspx?url=/rnp/sls/lag/19980204.htm\">PUL</a>"),



    };


    public StandardsComplianceClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "ComplianceStandarder";
    }


}
