package extractors.baseExtractors;

import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import featureTypes.FeatureTypeTree;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 *          This is the base extractor. This should add all tech expressions that
 *          are common between languages.
 *
 */

public abstract class TechExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public TechExtractor(){

        super();

        regExpList = new ArrayList<TextPattern>(){{

            add(new TextPattern("(MBIT|mbit)"));

        }};


        type = FeatureTypeTree.ITOperation;


    }


}
