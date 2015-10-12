package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ReportingClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ReportingClassifierSV extends ReportingClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(skyldig(het|a)*|förbinder|ska(ll)*|måste|ansvarar)")
                            .pattern("(informera|rapportera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(ska(ll)*|måste|bör|ansvarar)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(informera|rapportera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(fortlöpande)")
                            .pattern("(informaera|tillhandahålla)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .word("Aviseringsskyldighet"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

    };

    public ReportingClassifierSV(){

        super(RuleList);
        name = "Rapportering";

    }

}
