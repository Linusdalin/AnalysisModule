package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import extractors.extractorsEN.EmailExtractorEN;
import extractors.extractorsSE.EmailExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;
import test.oldTests.FeatureTestCommon;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class EmailExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    @Test
    public void basicTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        try {

            f = new AnalysisFragment("This contains an email address linus@hotmail.com",
                                "", englishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new EmailExtractorEN().classify( f, o, tree );

            FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

            assertThat(d.getPattern(), is("linus@hotmail.com"));

            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Email"));

            System.out.println("Keywords: " + Arrays.asList(d.getKeywords()).toString());
            assertTrue(d.getKeywords().contains("address"));



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    @Test
    public void basicTestSE(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        try {

            f = new AnalysisFragment("Detta är en mailadress linus.dalin@hotmail.se som skall extraheras",
                                "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new EmailExtractorSE().classify( f, o, tree );

            FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

            assertThat(d.getPattern(), is("linus.dalin@hotmail.se"));

            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Email"));

            System.out.println("Keywords: " + Arrays.asList(d.getKeywords()).toString());
            assertTrue(d.getKeywords().contains("mailadress"));



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    @Test
    public void failTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        String[] figureExamples = {

                "this is not @limus.com",
                "not an email address linus@not.toolong",
        };

        for(String example : figureExamples){


        f = new AnalysisFragment(example, "", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new EmailExtractorEN().classify( f, o, tree );
        assertNoDefinitions("Expecting no hit for \""+ example+"\"", o );


        }

    }


}
