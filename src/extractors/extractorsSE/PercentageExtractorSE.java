package extractors.extractorsSE;

import extractors.baseExtractors.PercentageExtractor;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class PercentageExtractorSE extends PercentageExtractor {

    public PercentageExtractorSE(){

        super();
        name="Procent";
        searchKeywords = new String[] { "del", "andel" };

        regExpList.add(new TextPattern("(\\d*[0-9](|.\\d*[0-9]|,\\d*[0-9])?[ ]*procent)"));     // ex: 10 procent


    }
}
