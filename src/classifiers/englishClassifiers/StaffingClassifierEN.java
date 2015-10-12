package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.StaffingClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class StaffingClassifierEN extends StaffingClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("(staff(ing)*|personnel)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("((sub)*contractor)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .word("project")
                            .pattern("manager"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("role"),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("(technic(h)*ian|architect|analyst|dba)(s)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("role"),

    };

    public StaffingClassifierEN(){

        super(RuleList);
        name = "Staffing";

    }

}
