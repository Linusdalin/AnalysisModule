package test.ParsingTests;

import language.English;
import language.Swedish;
import log.AnalysisLogger;
import openNLP.NLParser;
import openNLP.Tokenizer;
import org.apache.commons.lang.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;

import java.util.Arrays;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 *          Simple test runs
 *
 */

public class TestTokenize {




    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    private static final String[] testStrings = {
            "tio ( 10 ) stycken ",
            "Hello world",
            "Hello      world",
            "Hello, world",
            "Swedbank looks for managed service what replaces current Terminal Management System (TMS) that serves today circa 112.000 of 144.000 Swedbank's POS terminals with Key-, Software- and Parameter files. The ambition is to use the outsourced service to manage all terminals in Swedbank home market countries and to be able to follow our customers to the countries they are present in.",
            "This RFP documentation consist of this Main Document and Appendices B, F, Glossary and \"Software & Parameter Load Specification\" which are an integral part of the RFP. The Main Document contains general information about the RFP, a description of the scope, the principles for response and the terms of the RFP.",
            " new PSP’s and PSPs on the PSP's market.",

    };



    @Test
    public void basicTest(){

        int sNo = 0;
        Tokenizer tokenizer = new Tokenizer();
        englishParser.initTokenizer();

        for (String testString : testStrings) {

            String[] apacheTokenString = englishParser.tokenize(testString);
            String[] javaTokenString = tokenizer.tokenize(testString);

            sNo++;


            if(apacheTokenString.length != javaTokenString.length){

                System.out.println("Length difference. Apache: " + apacheTokenString.length + " tokens. Java: " + javaTokenString.length + " tokens");
                System.out.println("apache: " + Arrays.toString(apacheTokenString));
                System.out.println("java  : " + Arrays.toString(javaTokenString));
                continue;
            }


            for (int i = 0; i < apacheTokenString.length; i++) {

                if(! apacheTokenString[i].equals(javaTokenString[i])){

                    System.out.println("Mismatch in token "+ i +".");
                    System.out.println("apache: " + Arrays.toString(apacheTokenString));
                    System.out.println("java  : " + Arrays.toString(javaTokenString));

                }

            }

            for (int i = 0; i < javaTokenString.length; i++) {

                Integer[] positions = tokenizer.getPositions();

                if(javaTokenString[i].charAt( 0 ) != testString.charAt(positions[i])){

                    System.out.println("Position error! Token "+ i + " is " + javaTokenString[i] + " but position = " + positions[i] + " which points at ..." +
                            testString.substring(positions[i]));

                }
            }



        }

        System.out.println("Compared "+ sNo +" example strings.");

    }



    @Test
    public void FragmentTest(){

        AnalysisFragment f;

        f = new AnalysisFragment("100 kronor", "", swedishParser);

        System.out.println(f.getWords().toString());
        assertThat("Tokenized in words", f.getWords().size(), is(2));




        f = new AnalysisFragment("(100 kr)", "", swedishParser);

        System.out.println(f.getWords().toString());
        assertThat("Tokenized in words", f.getWords().size(), is(4));





        f = new AnalysisFragment("100kr", "", swedishParser);

        System.out.println(f.getWords().toString());
        assertThat("Tokenized in words", f.getWords().size(), is(2));




    }
}
