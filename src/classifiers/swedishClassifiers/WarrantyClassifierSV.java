package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.WarrantyClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class WarrantyClassifierSV extends WarrantyClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(lova(r)*|garantera(r)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)


    };

    public WarrantyClassifierSV(){

        super(RuleList);
        name = "Garantiansvar";

    }

}
