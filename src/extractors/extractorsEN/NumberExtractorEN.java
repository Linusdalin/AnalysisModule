package extractors.extractorsEN;

import extractors.baseExtractors.NumberExtractor;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-09-15
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */

@Deprecated
public class NumberExtractorEN extends NumberExtractor{

    public NumberExtractorEN(){

        super();
        name="Number";
        searchKeywords = new String[] {"figure", "value" };

    }

}
