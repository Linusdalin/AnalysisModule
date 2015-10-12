package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AuditClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AuditClassifierSV extends AuditClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(genom|ut)för(s|ande|a)*")
                            .pattern("(revision(en|er|ens)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(revision(en|er|ens)*)")
                            .pattern("(ska[ll]*|(genom|ut)för[as]*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(rätt|möjlighet)*")
                            .pattern("(kontrollera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

        };


    public AuditClassifierSV(){

        super(RuleList);
        name = "Revision";

    }

}
