package test.oldTests;

import language.English;
import openNLP.NLParser;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class ParserTest {

    @Test
    public void basicTest(){

        try{

        NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");

        String[] output;

        output =  englishParser.tokenize("  First sentence. Second sentence. ");
        display("Tokenizer",  output);
        assertThat(output.length, is(6));
        assertThat(output[0], is("First"));
        assertThat(output[4], is("sentence"));


        output =  englishParser.posAnalysis("Most large cities in the US had morning and afternoon newspapers.");
        display("POS",  output);
        assertThat(output.length, is(12));
        assertThat(output[0], is("JJS"));
        assertThat(output[4], is("DT"));


        output =  englishParser.posAnalysis(englishParser.tokenize("Most large cities in the US had morning and afternoon newspapers."));
        display("POS",  output);
        assertThat(output.length, is(12));
        assertThat(output[0], is("JJS"));
        assertThat(output[4], is("DT"));



        output =  englishParser.chunk(englishParser.tokenize("Most large cities in the US had morning and afternoon newspapers."));
        display("Chunk",  output);
        assertThat(output.length, is(12));
        assertThat(output[0], is("B-NP"));
        assertThat(output[3], is("B-PP"));


        }catch(Exception e){

            e.printStackTrace();
            assertTrue(false);
        }

    }

    private static void display(String name, String[] strings) {

        System.out.print(name + ": { ");

        for(String s : strings){

            System.out.print("\"" + s + "\", ");
        }

        System.out.println(" }");

    }


}
