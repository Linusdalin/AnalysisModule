package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import document.AbstractDocument;
import extractors.extractorsSE.DefinitionUsageExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import document.AbstractProject;
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


public class DefinitionUsageTest extends FeatureTestCommon {

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
    public void definitionUsageTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        AbstractDocument avtal = mockProject.documents.get(0);


        f = new AnalysisFragment("På köpedagen skall Köparen betala alla pengar", "", swedishParser)
                .setDocument(avtal);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());



        new DefinitionUsageExtractorSE().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("Köparen"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionUsage"));
        assertThat(d.getAction(), is(FeatureActionType.CORRECT_DEFINITION_USAGE));

        // Get both

        f = new AnalysisFragment("På köpedagen skall Köparen betala Säljaren alla pengar", "", swedishParser)
                .setDocument(avtal);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionUsageExtractorSE().classify( f, o, tree);

        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 2);
        assertThat(d.getPattern(), is("Köparen"));
        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionUsage"));
        assertThat(d.getAction(), is(FeatureActionType.CORRECT_DEFINITION_USAGE));

        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 1, 2);
        assertThat(d.getPattern(), is("Säljaren"));
        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionUsage"));
        assertThat(d.getAction(), is(FeatureActionType.CORRECT_DEFINITION_USAGE));



    }


    @Test
    public void definitionIsNotUsageTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        AbstractDocument avtal = mockProject.documents.get(0);

        f = new AnalysisFragment("This is a definition (\"Köparen\")", "", swedishParser).setDocument(avtal);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionUsageExtractorSE().classify( f, o, tree);
        assertThat(o.getDefinitions().size(), is( 0 ));

    }



    @Test
    public void incorrectDefinitionUsageTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;
        AbstractDocument avtal = mockProject.documents.get(0);

        f = new AnalysisFragment("Or incorrectly as this: köparen.", "", swedishParser)
                .setDocument(avtal);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionUsageExtractorSE().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("köparen"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionUsage"));
        assertThat(d.getAction(), is(FeatureActionType.INCORRECT_DEFINITION_USAGE));


        f = new AnalysisFragment("På köpedagen skall köparen betala alla pengar", "", swedishParser)
                .setDocument(avtal);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionUsageExtractorSE().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("köparen"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionUsage"));
        assertThat(d.getTag(),   is("Tvetydig definitionsreferens"));
        assertThat(d.getAction(), is(FeatureActionType.INCORRECT_DEFINITION_USAGE));


    }



}
