package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SolutionClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SolutionClassifierSV extends SolutionClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(tjänst(en)*|produkt(en)*|leveans(en)*|system(et)*)").pattern("(omfattar|innefattar|innebär)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("beskrivning"),
    };

    public SolutionClassifierSV(){

        super(RuleList);
        name = "OmfattningOchMål";
    }

}
