package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import classifiers.classifierTests.ExtractorTesterOld;
import document.*;
import extractors.extractorsEN.ReferenceExtractorEN;
import extractors.extractorsSE.ReferenceExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;
import test.oldTests.FeatureTestCommon;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *
 */


public class ReferenceExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();


    private static AbstractProject mockProject;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

        mockProject = new AbstractProject();


        mockProject
                .addDocument(getMockAvtal(mockProject))
                .addDocument(getMockAppendix(mockProject));

    }


    @Test
    public void chapterExtractionTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] chapterReferenceExamples = {

                "se även 27.1",
                "se även kapitel 27.1",
                "enligt punkt 27.1",
                "i punkt 27.1",
                "enligt kapitel 27.1",
                "i avsnitt 27.1",
                "i appendix 27.1",
                "i punkt 27.1",
                "enligt kapitel 27.1",
                "som framgår av stycke 27.1",
                "i enlighet med avsnitt 27.1",
        };

        for(String example : chapterReferenceExamples){

            AnalysisOutcome o = new AnalysisOutcome();

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new ReferenceExtractorSE().classify(f, o, tree);
            d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

            assertThat(d.getPattern(), is("27.1"));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Reference"));

        }

    }

    @Test
    public void chapterExtractionTestEnglish(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] chapterReferenceExamples = {

                "see also 27.1",
                "according to item 27.1",
                "in 27.1",
                "in accordance with chapter 27.1",
                "in section 27.1",
                "in appendix 27.1",
                "under item 27.1",
        };

        for(String example : chapterReferenceExamples){

            AnalysisOutcome o = new AnalysisOutcome();

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new ReferenceExtractorEN().classify(f, o, tree);
            d = getDefinition("Expecting hit for \""+ example+"\"", o, 0, 1);

            assertThat(d.getPattern(), is("27.1"));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Reference"));

        }

    }


    @Test
    public void chapterByNameTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] chapterReferenceExamples = {

                "enligt punkt Introduktion",
                "i punkt Introduktion",
                "enligt kapitel Introduktion",
                "i avsnitt Introduktion",
                "i appendix Introduktion",
                "i punkt Introduktion",
                "enligt kapitel Introduktion",
                "som framgår av stycke Introduktion",
                "i enlighet med avsnitt Introduktion",
        };

        AbstractDocument avtal = mockProject.documents.get(0);

        for(String example : chapterReferenceExamples){

            AnalysisOutcome o = new AnalysisOutcome();
            f = new AnalysisFragment(example, "", swedishParser).setProject(mockProject).setDocument(avtal);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new ReferenceExtractorSE().classify(f, o, tree);
            d = getDefinition("Expecting hit for \""+ example+"\"",o, 0, 1);

            assertThat(d.getPattern(), is("Introduktion"));
            assertThat(d.isMatch(), is(true));
            assertThat(d.getType().getName(), is( "Reference"));

        }


    }

    //FAIL this
    //

    @Test
    public void failReferenceTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        String[] chapterReferenceExamples = {

                "Introduction is normal, but this should be detected 2014-06-01 in analysis",
                "for approval normaly used in 2 steps with costcenter approval and a IT standard approval (Vendor responsible). "
        };
        AbstractDocument avtal = mockProject.documents.get(0);

        for(String example : chapterReferenceExamples){


            f = new AnalysisFragment(example, "", swedishParser).setDocument(avtal);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new ReferenceExtractorSE().classify(f, o, tree);
            assertThat(o.getDefinitions().size(), is( 0 ));

        }

    }


    @Test
    public void DocumentReferenceTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;


        AbstractDocument avtal = mockProject.documents.get(0);


        new ExtractorTesterOld("enligt Bilaga 1")
                .withParser(swedishParser)
                .withExtractor(new ReferenceExtractorSE())
                .withProject(mockProject, avtal)
                .expectingType(FeatureTypeTree.Reference)
                .expectingPattern("Bilaga 1")
                .test(0, 1);

        new ExtractorTesterOld("kapitel 2 i Bilaga 1")
                .withParser(swedishParser)
                .withExtractor(new ReferenceExtractorSE())
                .withProject(mockProject, avtal)
                .expectingType(FeatureTypeTree.Reference)
                .expectingPattern("Bilaga 1")
                .test(0, 1);

        new ExtractorTesterOld("som framgår av kapitel Introduktion, Avtalet")
                .withParser(swedishParser)
                .withExtractor(new ReferenceExtractorSE())
                .withProject(mockProject, avtal)
                .expectingType(FeatureTypeTree.Reference)
                .expectingPattern("Introduktion")
                .test(0, 1);


    }
}
