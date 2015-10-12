package extractors;

import analysis.*;
import featureTypes.FeatureTypeTree;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts an email
 *
 *           - linus@x.y
 */

@Deprecated
public abstract class EmailExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Name and special keywords that will be used to tag the fragment



    public EmailExtractor(){

        super();
        //type = FeatureType.EMAIL;
        type = FeatureTypeTree.EMAIL;
        name = "email";


        regExpList = new ArrayList<TextPattern>(){{

            add(new TextPattern("([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})\\b"));            // ex: a@b.com
        }};

    }

}
