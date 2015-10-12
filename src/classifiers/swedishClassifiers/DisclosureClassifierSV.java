package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DisclosureClassifier;
import classifiers.baseClassifiers.ReportingClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DisclosureClassifierSV extends DisclosureClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(skyldig(het|a)*|förbinder|ska|måste)")
                            .pattern("(lämna|tillgång|överlämna|tillhandahålla)")
                            .pattern("(information|dokumentation|underlag)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),
    };

    public DisclosureClassifierSV(){

        super(RuleList);
        name = "Information";

    }

}
