package extractors.baseExtractors;

import analysis.*;
import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts a date
 *
 *           - yyyy-mm-dd
 */

@Deprecated
public abstract class DateExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Name and special keywords that will be used to tag the fragment



    public DateExtractor(){

        super();
        type = FeatureTypeTree.Date;

        name = "Date";


        regExpList = new ArrayList<TextPattern>(){{

            add(new TextPattern("((19|20)\\d\\d([- /.])(0[1-9]|1[012])\\3(0[1-9]|[12][0-9]|3[01]))"));                   // ex: 2014-06-30
            add(new TextPattern("(([0]*[1-9]|1[012])[/.]([0]*[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d)"));                       // ex: 06/30/2014    or 6/30/2014
            add(new TextPattern("(([0]*[1-9]|1[012])[/.]([0]*[1-9]|[12][0-9]|3[01])[/]\\d\\d)(\\D)"));                         // ex: 06/30/14
            add(new TextPattern("(([0]*[1-9]|1[012])[/.]([0]*[1-9]|[12][0-9]|3[01])[/]\\d\\d)$"));                             // ex: 06/30/14   (end of line)

        }};


    }




}
