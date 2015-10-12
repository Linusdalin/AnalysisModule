package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.TermExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class TermExtractorSE extends TermExtractor implements FeatureExtractorInterface {


    public TermExtractorSE(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"gäll(?:a|er)",  "under"}).adjacent()
                    .and(new TextPattern(new String[]{"avtal"}).allPresent()).extractWordSpan().withTag("Period"));

            add(new TextPattern(new String[] {"[A|a]vtalsperiod"}).allPresent()
                                                    .withSignificance(GREY)                       // Set a very low significance. This should not be classified, but not missing
                                                    .extractWordSpan().withTag("Period"));


            add(new TextPattern(new String[] {"förlänga(?:a|s)",  "utöka"}).anyOf()
                .and(new TextPattern(new String[]{"avtalsperiod"}).anyOf()).extractWordSpan().withTag("Förlänging"));


            }};

        name = "Avtalsperiod";
        searchKeywords = new String[] {};


    }

}
