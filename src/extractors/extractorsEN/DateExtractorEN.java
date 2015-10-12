package extractors.extractorsEN;

import extractors.baseExtractors.DateExtractor;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */

@Deprecated
public class DateExtractorEN extends DateExtractor {

    public DateExtractorEN(){

        super();
        name="Date";
        searchKeywords = new String[] { "time", "duration" };

    }
}
