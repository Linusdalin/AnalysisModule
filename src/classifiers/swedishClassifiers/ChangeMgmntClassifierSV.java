package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ChangeMgmntClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ChangeMgmntClassifierSV extends ChangeMgmntClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(ändringshantering(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(för)*ändring(s|en)*")
                            .pattern("(tjänst|produkt|system|leverans)(en|er||erna)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),


    };

    public ChangeMgmntClassifierSV(){

        super(RuleList);
        name = "Ändringshantering";

    }

}
