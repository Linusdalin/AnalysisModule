package textPatterns;

import analysis.FeatureExtractorInterface;
import openNLP.NLParser;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-22
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class RecursiveExtraction {

    public final String word;
    public final FeatureExtractorInterface extractor;
    public NLParser parser;

    RecursiveExtraction(String word, FeatureExtractorInterface extractor, NLParser parser){

        this.word = word;
        this.extractor = extractor;
        this.parser = parser;
    }

}
