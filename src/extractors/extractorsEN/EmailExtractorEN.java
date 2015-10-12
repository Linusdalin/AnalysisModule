package extractors.extractorsEN;

import extractors.EmailExtractor;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-14
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */

@Deprecated
public class EmailExtractorEN extends EmailExtractor {

    public EmailExtractorEN(){

        super();
        name="Email";
        searchKeywords = new String[] { "email", "address" };

    }
}
