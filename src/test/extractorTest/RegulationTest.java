package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import document.AbstractDocument;
import document.AbstractProject;
import extractors.extractorsSE.PercentageExtractorSE;
import extractors.extractorsSE.RegulationExtractorSE;
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

/**
 *
 */


public class RegulationTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();


    private static AbstractProject mockProject;
    private static AbstractDocument mockDocument;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

    }


    @Test
    public void legalReferenceTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] figureExamples = {

                "(1998:123)",
                "(2014:12)",
        };

        for(String example : figureExamples){

            AnalysisOutcome o = new AnalysisOutcome();

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new RegulationExtractorSE().classify( f, o, tree );
            d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Regulation"));
            assertThat(d.getAction(), is(FeatureActionType.CLASSIFY));
            assertThat("Classified as SFS-reference", d.getTag().contains("SFS-referens"), is(true));

        }

    }

    @Test
    public void notLegalReferenceTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        String[] figureExamples = {

                "(1888:123)",
                "(2014:)",
                "2014:123",
        };

        for(String example : figureExamples){


        f = new AnalysisFragment(example, "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new RegulationExtractorSE().classify( f, o, tree );

            assertNoDefinitions(o);
        }

    }


    @Test
    public void SpecialCases(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        String[] figureExamples = {

        };

        for(String example : figureExamples){


        f = new AnalysisFragment(example, "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new PercentageExtractorSE().classify( f, o, tree );
        d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "REGULATION"));
        assertThat(d.getAction(), is(FeatureActionType.CLASSIFY));

        }

    }

}
