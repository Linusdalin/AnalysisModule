package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PercentageClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PercentageClassifierSV extends PercentageClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().node(NodeClass.Type.NUMBER).word("procent"))
                            .withExtraction(TokenReplacer.WORD_SPAN)

    };


    public PercentageClassifierSV(){

        super(LanguageSpecificRuleList);
        keywords = "";
        name = "Procent";

    }


}
