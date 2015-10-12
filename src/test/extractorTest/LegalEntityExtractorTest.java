package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import extractors.extractorsSE.LegalEntityExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;
import test.oldTests.FeatureTestCommon;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class LegalEntityExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    @Test
    public void personnummerTest(){


        AnalysisFragment f;

        try {

            AnalysisOutcome o = new AnalysisOutcome();
            f = new AnalysisFragment("This contains a personnummer 701210-1234",
                                "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new LegalEntityExtractorSE().classify( f, o, tree );

            FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

            assertThat(d.getPattern(), is("701210-1234"));

            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Entity"));
            assertThat(d.getTag(), is("Fysisk person"));

            AnalysisOutcome o2 = new AnalysisOutcome();
            f = new AnalysisFragment("This contains a VAT number 552210-1234",
                                "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new LegalEntityExtractorSE().classify( f, o2, tree );

            d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o2, 0, 1);

            assertThat(d.getPattern(), is("552210-1234"));

            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Entity"));
            assertThat(d.getTag(), is("Juridisk person"));

            AnalysisOutcome o3 = new AnalysisOutcome();
            f = new AnalysisFragment("CC Restaurang AB (deltar i upphandlingen i egenskap av koncernbolag, men utgör dock inte en upphandlande myndighet) 333332-2222",
                                "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new LegalEntityExtractorSE().classify( f, o3, tree );

            d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o3, 0, 1);

            assertThat(d.getPattern(), is("333332-2222"));

            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Entity"));
            assertThat(d.getTag(), is("Juridisk person"));

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }
    @Test
    public void failTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] figureExamples = {

                "Andra gruppen kan inte vara 13 - 19 551310-1122",
                "Bara fyra siffror 552212-12345",
                "Bara 6 siffror 1552212-1234",
        };

        for(String example : figureExamples){

            AnalysisOutcome o = new AnalysisOutcome();

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new LegalEntityExtractorSE().classify( f, o, tree );
            assertNoDefinitions("Expecting no hit for \""+ example+"\"", o );


        }
    }

}
