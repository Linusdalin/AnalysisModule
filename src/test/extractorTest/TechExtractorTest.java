package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import extractors.extractorsEN.TechExtractorEN;
import extractors.extractorsSE.TechExtractorSE;
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


public class TechExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();




    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    String[] examplesBothEnglishSwedish = {

            "MBIT",
    };


    String[] examplesOnlyEnglish = {

            "data base",
    };



    String[] examplesOnlySwedish = {

            "databas",
            "oklarheter om vilket driftansvar som...",
            "oklarheter om vilket driftsansvar som...",
            "Landstinget Västernorrland interna serviceorganisation IT drift och support har under många år arbetat med att standardisera och konsolidera sin serverdrift."
    };



    String[] notTechSwedishExamples = {

    };

    String[] notTechEnglishExamples = {

    };




    /***************************************************************
     *
     *          The tech extractions should work for both swedish and english
     *
     *
     */


    @Test
    public void techExtractorTestEnglish(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;


        for(String example : examplesOnlyEnglish){


            f = new AnalysisFragment(example, "", englishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new TechExtractorEN().classify( f, o, tree );
            getDefinition("Expecting no hit for \"" + example + "\"", o, 0, 1);


        }
    }


    @Test
    public void techExtractorTestSwedish(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        for(String example : examplesOnlySwedish){

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new TechExtractorSE().classify( f, o, tree );
            getDefinition("Expecting no hit for \"" + example + "\"", o, 0, ANY_NUMBER);

        }
    }


    @Test
    public void techExtractorTestCommonLanguage(){

        AnalysisFragment f;
        FeatureDefinition d;


        for(String example : examplesBothEnglishSwedish){

            AnalysisOutcome o = new AnalysisOutcome();

            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new TechExtractorSE().classify( f, o, tree );
            getDefinition("Expecting hit for \"" + example + "\"", o, 0, 1);

            f = new AnalysisFragment(example, "", englishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new TechExtractorEN().classify( f, o, tree );
            getDefinition("Expecting hit for \"" + example + "\"", o, 0, 1);


        }
    }



    @Test
    public void failTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;


        for(String example : notTechSwedishExamples){


            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new TechExtractorSE().classify( f, o, tree );
            assertNoDefinitions("Expecting no hit for \""+ example+"\"", o );


        }

        for(String example : notTechEnglishExamples){


            f = new AnalysisFragment(example, "", englishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new TechExtractorEN().classify( f, o, tree );
            assertNoDefinitions("Expecting no hit for \""+ example+"\"", o );


        }


    }

}
