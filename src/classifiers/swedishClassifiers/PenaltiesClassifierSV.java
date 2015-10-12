package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PenaltiesClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PenaltiesClassifierSV extends PenaltiesClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(ersätta|ersättning)")
                            .word("för")
                            .node(NodeClass.Type.CLASSIFIED_ENTITY, "skada"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("påföljd(en|er)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

    };

    public PenaltiesClassifierSV(){

        super(RuleList);
        name = "Påföljder";
    }

}
