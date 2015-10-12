package classifiers.englishClassifiers;

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



public class DateClassifierEN extends DateClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("(\\b(jan(uary)*|feb(ruary)*|mar(ch)*|apr(il)*|may|jun(e)*|jul(y)*|aug(ust)*|sep(tember)*|oct(ober)*|nov(ember)*|dec(ember)*)\\b \\d(\\d)*[ ]*(th|d|rd)*[, ]*((19|20)*\\d\\d)*)"),
            new RegExpReplacer("\\b(spring|summer|fall|autumn|winter|q[1234]|h[12]\\b[, ]*((19|20)*\\d\\d)*)"),
    };


    public DateClassifierEN(){

        super(RuleList);
        keywords = "time timestamp";
        name = "Date";

    }



}
