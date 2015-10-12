package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.TermExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to contract terms
 *
 *
 *
 */

public class TermExtractorEN extends TermExtractor implements FeatureExtractorInterface {


    public TermExtractorEN(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"date",  "effective"}).adjacent()
                .and(new TextPattern(new String[]{"agreement"}).allPresent()).withTag("Period"));

            add(new TextPattern(new String[] {"Effective", "Date"}).adjacent()
                .and(new TextPattern(new String[]{"Agreement"}).allPresent()).withTag("Period"));


            add(new TextPattern(new String[] {"extend",  "term", "expiration"}).allPresent()
                .and(new TextPattern(new String[]{"date", "months", "days"}).someOf()).withTag("Extension Term"));

            add(new TextPattern(new String[] {"periods",  "additional"}).close()
                .and(new TextPattern(new String[]{"right", "extend"}).allPresent()).withTag("Extension Term"));
        }};



        name = "Term";
        searchKeywords = new String[] {"Agreement"};


    }

}
