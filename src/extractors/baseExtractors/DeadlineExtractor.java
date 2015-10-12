package extractors.baseExtractors;

import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import extractors.extractorsSE.NumberExtractorSE;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;
import openNLP.NLParser;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public abstract class DeadlineExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public DeadlineExtractor(LanguageInterface language){

        super();


        type = FeatureTypeTree.Deadline;


    }


}
