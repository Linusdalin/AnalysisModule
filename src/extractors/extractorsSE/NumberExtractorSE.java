package extractors.extractorsSE;

import extractors.baseExtractors.NumberExtractor;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-09-15
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */
public class NumberExtractorSE extends NumberExtractor{

    public NumberExtractorSE(){

        super();
        name="Nummer";
        searchKeywords = new String[] {"siffra", "värde" };

    }

}
