package extractors.extractorsSE;

import extractors.EmailExtractor;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class EmailExtractorSE extends EmailExtractor {

    public EmailExtractorSE(){

        super();
        name="Email";
        searchKeywords = new String[] { "email", "adress", "mailadress", "epost" };

    }
}
