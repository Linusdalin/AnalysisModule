package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ExemptionClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ExemptionClassifierSV extends ExemptionClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(förbinder|åtager|åtar|lovar|garanterar)")
                            .pattern("(inte)"))

                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(inte)")
                            .pattern("(förbind(a|er)|åtag(a|er)|åta(r)*|lova(r)*|garantera(r)*)"))

                    .withExtraction(TokenReplacer.WORD_SPAN),


    };


    public ExemptionClassifierSV(){

        super(LanguageSpecific);
        name = "Förbehåll";

    }

}
