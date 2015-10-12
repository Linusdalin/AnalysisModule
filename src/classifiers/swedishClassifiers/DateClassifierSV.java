package classifiers.swedishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DateClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DateClassifierSV extends DateClassifier implements ClassifierInterface {

    private static final String year = "((19|20)*\\d\\d)";
    private static final String month = "(jan(uari)*|feb(ruari)*|mar(s)*|apr(ill)*|maj|jun(i)*|jul(i)*|aug(usti)*|sep(tember)*|okt(ober)*|nov(ember)*|dec(ember)*)";
    private static final String quarter = "\\b(q[1234]|h[12])\\b";
    private static final String season = "\\b(vår(en*)|sommar(en*)|höst(en*)|vinter(en*))\\b";

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer( "(\\b" + month  + "\\b" + year + "*" +")"),
            new RegExpReplacer( "(\\b" + season  + "\\b"+ year + "*" +")"),
            new RegExpReplacer( "(\\b" + quarter + "\\b" + year + "*" +")"),
            //new RegExpReplacer( "(" + "\\d[\\d]*[\\:]*[a|e]*" + month  + year + "*" +")"),
            new RegExpReplacer( "(" + "\\d[\\d]*[:]*[a|e]* " + month  + year + "*" +")"),

    };


    public DateClassifierSV(){

        super(RuleList);
        keywords = "tid tidpunkt";
        name = "Datum";
    }


}
