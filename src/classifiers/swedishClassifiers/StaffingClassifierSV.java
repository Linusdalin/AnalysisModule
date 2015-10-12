package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ResourcesClassifier;
import classifiers.baseClassifiers.StaffingClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class StaffingClassifierSV extends StaffingClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("personal(en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("((drift)*tekniker|arkitekt|analytiker|projektledare|dba)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("roll"),


    };

    public StaffingClassifierSV(){

        super(RuleList);
        name = "Personal";

    }

}
