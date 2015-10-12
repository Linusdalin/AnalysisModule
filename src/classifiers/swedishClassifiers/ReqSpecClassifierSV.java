package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ReqSpecClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ReqSpecClassifierSV extends ReqSpecClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("krav")
                            .pattern("(på|för)")
                            .node(NodeClass.Type.DEFINITION_USAGE))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.DEFINITION_USAGE)
                            .pattern("(ska(ll)*|bör|måste)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

    };


    public ReqSpecClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Krav";
    }


}
