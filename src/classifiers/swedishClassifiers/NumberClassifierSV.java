package classifiers.swedishClassifiers;

import analysis2.RegExpReplacer;
import classifiers.ClassifierInterface;
import analysis2.ReplacerInterface;
import classifiers.baseClassifiers.NumberClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class NumberClassifierSV extends NumberClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("\\b(noll|ett|en|två|tre|fyra|fem|sex|sju|åtta|nio|tio|elva|tolv)\\b")
                    .withExtraction(1)
                    .withTag("numeral"),

            new RegExpReplacer("\\b((tre|fjor|fem|sex|sju|ar|nit)ton)\\b")
                    .withExtraction(1)
                    .withTag("numeral"),

            new RegExpReplacer("\\b((tjug[i]*|tretti|förti|femti|sexti|sjutti|åtti|nitti)[o]*(ett|två|tre|fyra|fem|sex|sju|åtta|nio)*)+\\b")
                    .withExtraction(1)
                    .withTag("numeral"),

    };

    public NumberClassifierSV(){

        super(RuleList);
        name = "Nummer";
    }

}
