package extractors.extractorsEN;

import extractors.baseExtractors.PercentageExtractor;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 *
 */

@Deprecated
public class PercentageExtractorEN extends PercentageExtractor {

    public PercentageExtractorEN(){

        super();
        name="Percent";
        searchKeywords = new String[] { "fraction", "share" };

        regExpList.add(new TextPattern("(\\d*[0-9](|.\\d*[0-9]|,\\d*[0-9])?[ ]*percent)"));     // ex: 10 procent


    }
}
