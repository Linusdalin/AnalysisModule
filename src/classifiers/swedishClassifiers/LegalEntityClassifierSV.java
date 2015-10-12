package classifiers.swedishClassifiers;

import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.LegalEntityClassifier;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LegalEntityClassifierSV extends LegalEntityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

           // new RegExpReplacer("\\b(\\d\\d\\d\\d\\d\\d)")
           //         .withTag("Fysisk Person"),                   // ex: 701210-1234


            new RegExpReplacer("\\b(\\d\\d(0[1-9]|1[012])\\d\\d-\\d{4})\\b")
                    .withTag("Fysisk Person"),                   // ex: 701210-1234
            new RegExpReplacer("\\b(\\d\\d[23456789]\\d\\d\\d-\\d{4})\\b")
                    .withTag("Juridisk Person"),                   // ex: 702210-1234

            new RegExpReplacer("org[\\. ]*nr[\\.:; ]*[\\(\\[]((.{6}-.{4}))[\\)\\]]")
                    .withTag("Juridisk Person"),                   // ex: 702210-1234

    };

    public LegalEntityClassifierSV(){

        super(RuleList);
        name = "LegalEntity";

    }

}
