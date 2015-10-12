package extractors;

import analysis.*;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts a definition from a fragment. The definition is the content within the quote inside brackets.
 *
 *      ("definition") or ( a "definition" )
 */

@Deprecated
public abstract class DefinitionExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Name and special keywords that will be used to tag the fragment

    public DefinitionExtractor(){

        super();
        type = FeatureTypeTree.DEFINITION;

    }




}
