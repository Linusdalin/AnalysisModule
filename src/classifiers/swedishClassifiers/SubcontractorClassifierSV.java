package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SubcontractorClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SubcontractorClassifierSV extends SubcontractorClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("under(leverantör|konsult)[ernas]*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };

    public SubcontractorClassifierSV(){

        super(RuleList);
        name = "Underleverantörer";

    }

}
