package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceCriteriaClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AcceptanceCriteriaClassifierSV extends AcceptanceCriteriaClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("tilldelningsgrund").pattern("utvärderings(volym|modell|faktor(n|er)*)").pattern("anbudssumma"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Tilldelningsgrund"),

            // mest fördelaktiga anbudet

            new TokenReplacer()
                    .withCriteria(new Criteria().close().pattern("(mest|minst)").word("fördelaktig").word("anbud"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Tilldelningsgrund"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(underlag|bakgrund|beaktas|vägas)")
                            .pattern("(för|i|till)")
                            .pattern("utvärdering(en|ar|arna)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("utvärderings(kriteri(a|um|er)|underlag(en|et)*(s)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(kriteri(a|um))")
                            .pattern("utvärder(a|ing(en|ar|arna)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(samtliga|alla)")
                            .pattern("(defekter|fel|buggar)")
                            .pattern("(acceptans)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag("Subjective"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ska|har)")
                            .pattern("(godkän(na|t)|acceptera(t)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Subjective")



    };


    public AcceptanceCriteriaClassifierSV(){

        super(LanguageSpecific);
        name = "Acceptanskriteria";

    }

}
