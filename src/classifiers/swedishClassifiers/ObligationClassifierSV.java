package classifiers.swedishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ObligationClassifier;
import parse.POSClassification;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ObligationClassifierSV extends ObligationClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ska(ll)*|måste|bör|ansvarar)")
                            .subordinateClause()
                            .pattern(actionsSV))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ska(ll)*|måste|bör)")
                            .pattern("(ha)")
                            .anyWord(POSClassification.ANY_JJ))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Vague"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ska(ll)*|får)")
                            .pattern("(inte|ej|aldrig)")
                            .subordinateClause()
                            .pattern("(undlåta|underlåta|(för*)vägra)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ska(ll)*|måste|bör|ansvarar|garanterar)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(det)")
                            .pattern("(åligger)")
                            .node(NodeClass.Type.ACTOR))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(åligger)")
                            .pattern("(det)")
                            .node(NodeClass.Type.ACTOR))
                    .withExtraction(TokenReplacer.WORD_SPAN),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(ska(ll)*|måste|bör)")
                            .node(NodeClass.Type.ACTOR)
                            .subordinateClause()
                            .pattern(actionsSV))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(förbinder|åtager|åtar|lovar|garanterar)")
                            .pattern("(sig)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(att)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(förbinder|åtager|åtar|lovar)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(sig)")
                            .pattern("(att)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(förbinder|åtager|åtar|lovar)")
                            .pattern("(sig)"))

                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(skyldig)")
                            .pattern("(att)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(har)")
                            .pattern("((helhets|total)ansvar)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.IS_HEADLINE))
                            .anyOf()
                            .pattern("(åtagande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),




    };


    public ObligationClassifierSV(){

        super(LanguageSpecific);
        name = "AcceptanceCriteria";

    }

}
