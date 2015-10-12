package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import document.AbstractDocument;
import document.AbstractProject;
import extractors.extractorsEN.PercentageExtractorEN;
import extractors.extractorsSE.PercentageExtractorSE;
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


public class PercentageTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static AbstractProject mockProject;
    private static AbstractDocument mockDocument;

    private static final FeatureTypeTree tree = new FeatureTypeTree();

    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

    }


    @Test
    public void figuresTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] figureExamples = {

                "10%",
                "1.5%",
                "10%",
                "20.0000%",
                "100 procent",
                "10procent",
        };

        for(String example : figureExamples){

            AnalysisOutcome o = new AnalysisOutcome();

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new PercentageExtractorSE().classify( f, o, tree );
            d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

            assertThat(d.getPattern(), is(example));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "PERCENTAGE"));
            assertThat(d.getAction(), is(FeatureActionType.CLASSIFY));

            assertThat("Expecting to find " + FeatureTypeTree.Percentage.getAkaTag() + " in tag " + d.getKeywords().toString(), d.getKeywords().contains(FeatureTypeTree.Percentage.getAkaTag()), is(true));

        }

    }

    @Test
    public void figuresInEnglishTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] figureExamples = {

                "10%",
                "1.5%",
                "100 percent",
                "10percent",
        };

        for(String example : figureExamples){

            AnalysisOutcome o = new AnalysisOutcome();
            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new PercentageExtractorEN().classify( f, o, tree );
            d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

            assertThat(d.getPattern(), is(example));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Percentage"));
            assertThat(d.getAction(), is(FeatureActionType.CLASSIFY));

        }

    }

}
