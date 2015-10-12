package classifiers.swedishClassifiers;

import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ReferenceClassifier;

/********************************************************************************************'
 *
 *          Extract references
 *
 *
 *          //TODO: Rework these to be TokenReplacers to include the article automatically
 *
 *
 */



public class ReferenceClassifierSV extends ReferenceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new RegExpReplacer("((?:enligt |i (?:enlighet med )*|per |framgår av |(?:se )(?:också |även )*)(?:bilaga(?:n)* )(\\d+[\\.\\d+]*))")
                            .withExtraction(1)
                            .withSemanticExtraction(2)
                            .withTag("Bilaga"),

            //TODO: Close these

            new RegExpReplacer("((?:enligt |\\bi |framgår av | motsvaras av |per |enlighet med )(?:punkt(?:en)* |kapitel |kapitlet |avsnitt(?:et)* |stycke(?:t)* |appendix(?:et)* |paragraf(?:en)* )*(\\d+[\\.\\d+]*))")
                            .withExtraction(1)
                            .withSemanticExtraction(2)
                            .withTag("Kapitel"),

            new RegExpReplacer("((?:se )(?:också |även )*(?:punkt |kapitel |avsnitt |stycke |appendix |paragraf )*(\\d+[\\.\\d+]*))")
                            .withExtraction(1)
                            .withSemanticExtraction(2)
                            .withTag("Kapitel"),


    };


    public ReferenceClassifierSV(){

        super(LanguageSpecificRuleList);
    }

    String chapterRef = "(?:enligt |framgår av |motsvaras av |per |enlighet med |\\bi )(?:punkt(?:en)* |kapitel | kapitlet |avsnitt(?:et)* |stycke(?:t)* |appendix(?:et)* |paragraf(?:en)* )*";


    @Override
    public void classify(TextFragment sentence, int currentPass){


        super.classify(sentence, chapterRef, "Kapitel", "Dokument", currentPass);
        name = "#referens";

    }



}
