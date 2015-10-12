package extractors.extractorsEN;

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

public class CompensationExtractorEN extends CompensationExtractor implements FeatureExtractorInterface {


    public CompensationExtractorEN(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                // About invoicing

                add(new TextPattern(new String[] {"not",  "invoiced"}).close()
                .and(new TextPattern(new String[]{"properly", "correctly", "charges", "fees"}).someOf()).withTag("Invoice Term"));

            add(new TextPattern(new String[] {"invoice",  "monthly"}).close().withTag("Invoice Term"));

            add(new TextPattern(new String[] {"approve",  "invoice"}).allPresent().withTag("Invoice Term"));

            add(new TextPattern(new String[] {"invoice"}).allPresent()
                .and(new TextPattern(new String[] {"not",  "entitled"}).close()).withTag("Invoice Term"));

                // The actual payment

            add(new TextPattern(new String[] {"payment"}).withTag("Payment Term"));

            add(new TextPattern(new String[] {"installment"}).withTag("Payment Term"));

            add(new TextPattern(new String[] {"due",  "date"}).ordered().adjacent()
                .and(new TextPattern(new String[]{"invoice"}).allPresent()).withTag("Payment Term"));

            add(new TextPattern(new String[] {"will",  "pay"}).ordered().veryClose()
                .and(new TextPattern(new String[]{"days","invoice"}).allPresent()).withTag("Payment Term"));

            add(new TextPattern(new String[] {"receipt", "of"}).adjacent()
                .and(new TextPattern(new String[]{"days","invoice"}).allPresent()).withTag("Payment Term"));



            }};

        name = "Payment";
        searchKeywords = new String[] {};


    }

}
