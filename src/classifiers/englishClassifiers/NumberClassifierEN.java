package classifiers.englishClassifiers;

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



public class NumberClassifierEN extends NumberClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("\\b(zero|one|two|three|four|five|six|seven|eight|nine|ten|eleven|twelve)\\b")
                    .withExtraction(1)
                    .withTag("numeral"),

            new RegExpReplacer("\\b((thir|four|fif|six|seven|eight|nine)teen)\\b")
                    .withExtraction(1)
                    .withTag("numeral"),

            new RegExpReplacer("\\b(twenty|thirty|forty|fifty|sixty|seventy|eighty|ninety)(one|two|three|four|five|six|seven|eight|nine)\\b")
                    .withExtraction(1)
                    .withTag("numeral"),
    };

    public NumberClassifierEN(){

        super(RuleList);
        name = "Number";
    }

}
