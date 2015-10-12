package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class DateClassifier extends Classifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("((19|20)\\d\\d([- /.])(0[1-9]|1[012])\\3(0[1-9]|[12][0-9]|3[01]))"),                  // ex: 2014-06-30
            new RegExpReplacer("(([0]*[1-9]|1[012])[/.]([0]*[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d)"),                // ex: 06/30/2014    or 6/30/2014
            new RegExpReplacer("(([0]*[1-9]|1[012])[/.]([0]*[1-9]|[12][0-9]|3[01])[/]\\d\\d)(\\D)"),                  // ex: 06/30/14
            new RegExpReplacer("(([0]*[1-9]|1[012])[/.]([0]*[1-9]|[12][0-9]|3[01])[/]\\d\\d)$"),                      // ex: 06/30/14   (end of line)
    };


    public DateClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.DATE, FeatureTypeTree.Date);
    }



}
