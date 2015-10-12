package classifiers.englishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ObligationClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ObligationClassifierEN extends ObligationClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(guarantee(s)*|shall|must)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.IS_HEADLINE))
                            .anyOf()
                            .pattern("(obligation)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),




    };


    public ObligationClassifierEN(){

        super(LanguageSpecific);
        name = "Obligation";

    }

}
