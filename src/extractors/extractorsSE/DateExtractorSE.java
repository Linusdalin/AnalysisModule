package extractors.extractorsSE;

import extractors.baseExtractors.DateExtractor;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class DateExtractorSE extends DateExtractor {

    public DateExtractorSE(){

        super();
        name="Datum";
        searchKeywords = new String[] {"tid", "period" };

    }
}
