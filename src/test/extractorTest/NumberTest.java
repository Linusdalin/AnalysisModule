package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import classifiers.classifierTests.ExtractorTesterOld;
import document.AbstractDocument;
import document.AbstractProject;
import extractors.extractorsEN.NumberExtractorEN;
import extractors.extractorsSE.DetailedValueExtractorSE;
import extractors.extractorsSE.NumberExtractorSE;
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


public class NumberTest extends FeatureTestCommon {

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
    public void numberTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] figureExamples = {

                "10",
                "1.5",
                "100,000",
                "100,000.25",

        };

        for(String example : figureExamples){


            AnalysisOutcome o = new AnalysisOutcome();
            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new NumberExtractorSE().classify( f, o, tree);
            d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

            assertThat(d.getPattern(), is(example));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "NUMEX"));
            assertThat(d.getAction(), is(FeatureActionType.CLASSIFY));

            AnalysisOutcome o2 = new AnalysisOutcome();
            f = new AnalysisFragment(example, "", englishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new NumberExtractorEN().classify( f, o2, tree);
            d = getDefinition("Expecting hit for \""+ example+"\"", o2, 0, 1);

            assertThat(d.getPattern(), is(example));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "NUMEX"));
            assertThat(d.getAction(), is(FeatureActionType.CLASSIFY));

        }

    }

    @Test
    public void Special(){



        new ExtractorTesterOld("Det är 50 saker", tree)
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE( ))
                .expectingType(FeatureTypeTree.Numex)
                .expectingPattern("50")
                .test(0, 1);


        new ExtractorTesterOld("Det är -10 grader", tree)
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE())
                .expectingType(FeatureTypeTree.Numex)
                .expectingPattern("-10")
                .test(0, 1);


        new ExtractorTesterOld("Det är 3-10 grader", tree)
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE( ))
                .expectingType(FeatureTypeTree.Numex)
                .expectingPattern("3")
                .test(0, 2);


        new ExtractorTesterOld("på 10,000 meter", tree)
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE( ))
                .expectingType(FeatureTypeTree.Numex)
                .expectingPattern("10,000")
                .test(0, 1);

        new ExtractorTesterOld("på 10 000 meter", tree)
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE( ))
                .expectingType(FeatureTypeTree.Numex)
                .expectingPattern("10 000")
                .test(0, 1);


        new ExtractorTesterOld("bara 10.4 mm", tree)
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE( ))
                .expectingType(FeatureTypeTree.Numex)
                .expectingPattern("10.4")
                .test(0, 1);


    }

    @Test
    public void Headline(){



        new ExtractorTesterOld("1.1 Introduktion", tree)
                .withHeadline("1.1 Introduktion")
                .withParser(swedishParser)
                .withExtractor(new NumberExtractorSE())
                .expectToFail();

    }

    @Test
    public void DetailedValue(){



        new ExtractorTesterOld("Femtio ( 50 )", tree)
                .withParser(swedishParser)
                .withExtractor(new DetailedValueExtractorSE(swedishParser.getLanguage()) )
                .expectingType(FeatureTypeTree.DetailedValue)
                .expectingPattern("50")
                .test(0, 1);


    }


}
