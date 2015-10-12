package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.CompensationExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to payment and invoicing
 *
 *
 *
 */

public class CompensationExtractorSE extends CompensationExtractor implements FeatureExtractorInterface {


    public CompensationExtractorSE(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                // About Prices

                add(new TextPattern(new String[] {"pris",  "offerera(?:t|r|s)"}).close().extractWordSpan().withTag("Ersättning"));
                add(new TextPattern(new String[] {"offerera(?:t|r|s)", "pris"}).close().extractWordSpan().withTag("Ersättning") );


            add(new TextPattern(new String[] {"(?:års|månads|vecko|dags|tim|minut|sekund)[\\w]*(?:kostnad|pris|taxa|avgift)"}).close().extractWordSpan().withTag("Ersättning"));

            add(new TextPattern(new String[] {"(?:kostnad|pris|avgift)", "per", "(?:\\bår|månad|vecka|dag|timme|timma|minut|sekund|enhet)"}).close().extractWordSpan().withTag("Ersättning"));

            add(new TextPattern(new String[] {"(?:kostnads|pris)", "(?:bild|förslag|uppgift)"}).close().extractWordSpan().withTag("Ersättning"));

            add(new TextPattern(new String[] {"(?:max|tak|bas|standard|enhets|fast)[\\w]*(?:kostnad|pris|taxa|avgift)"}).close().extractWordSpan().withTag("Ersättning"));

                // The actual payment

            add(new TextPattern(new String[] {"(?:av)*betalning[s]*[\\w]*(?:krav|dag|villkor)" }).anyOf().withTag("Betalningsvilkor"));



            }};

        name = "Betalning";
        searchKeywords = new String[] {};


    }

}
