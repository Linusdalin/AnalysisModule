package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsEN.DefinitionExtractorEN;
import extractors.extractorsSE.DefinitionExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;
import test.oldTests.FeatureTestCommon;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class DefinitionExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /*********************************************************
     *
     *          Test the extraction of a definition like ("Definition") a very common way of describing a definitio
     *
     *          Also constructions like  ( this/a "Definition")
     *
     *
     */

    @Test
    public void bracketTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        f = new AnalysisFragment("These Google Analytics Terms of Service ( this \"Mutual Agreement\") are entered into by Google Inc.",
                "", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("Mutual Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));


    }


    @Test
    public void moreBracketTest(){


        AnalysisFragment f;
        FeatureDefinition d;

        AnalysisOutcome o = new AnalysisOutcome();
        f = new AnalysisFragment("This is a definition (\"Buyer\")", "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());


        new DefinitionExtractorEN().classify( f, o, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("Buyer"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));



        AnalysisOutcome o2 = new AnalysisOutcome();
        f = new AnalysisFragment("These Google Analytics Terms of Service (          \"Mutual Agreement\") are entered into by Google Inc.",
                "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o2, tree );

        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o2, 0, 1);

        assertThat(d.getPattern(), is("Mutual Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));

        AnalysisOutcome o3 = new AnalysisOutcome();
        f = new AnalysisFragment("These Google Analytics Terms of Service ('Mutual Agreement') are entered into by Google Inc.",
                        "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o3, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o3, 0, 1);

        assertThat(d.getPattern(), is("Mutual Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));




    }


    @Test
    public void moreBracketTestSE(){


        AnalysisFragment f;
        FeatureDefinition d;

        AnalysisOutcome o = new AnalysisOutcome();
        f = new AnalysisFragment("Detta är en definition (\"Buyer\")", "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());


        new DefinitionExtractorSE().classify( f, o, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("Buyer"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));



        AnalysisOutcome o2 = new AnalysisOutcome();
        f = new AnalysisFragment("These Google Analytics Terms of Service (          \"Mutual Agreement\") are entered into by Google Inc.",
                "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorSE().classify( f, o2, tree );

        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o2, 0, 1);

        assertThat(d.getPattern(), is("Mutual Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));

        AnalysisOutcome o3 = new AnalysisOutcome();
        f = new AnalysisFragment("These Google Analytics Terms of Service ('Mutual Agreement') are entered into by Google Inc.",
                        "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorSE().classify( f, o3, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o3, 0, 1);

        assertThat(d.getPattern(), is("Mutual Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));




    }



    @Test
    public void tabDividerTest(){


        AnalysisFragment f;

        AnalysisOutcome o = new AnalysisOutcome();
        f = new AnalysisFragment(" \"Agreement\"     is this document", "definitioner", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));


    }

    @Test
    public void tabDividerNoQuotes(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        f = new AnalysisFragment(" Agreement     is this document", "definitions", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));


    }


    @Test
    public void columnTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        f = new AnalysisFragment("This Agreement", "definitions", englishParser).setColumn(0);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        FeatureDefinition d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getPattern(), is("This Agreement"));

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));


    }

    @Test
    public void failColumnTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        f = new AnalysisFragment("This Agreement", "definitions", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        assertNoDefinitions( o );

    }


    @Test
    public void tabDividerWrongHeadlineTest(){


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        f = new AnalysisFragment("Agreement     is this document", "Lista", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        assertNoDefinitions( o );
    }




    @Test
    public void twoDefinitionsTest(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();

        f = new AnalysisFragment("These Google Analytics Terms of Service (this \"Mutual Agreement\") are entered into by Google Inc. and the user ( \"You\" )");
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorEN().classify( f, o, tree );

        FeatureDefinition d1 = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 2);
        FeatureDefinition d2 = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 1, 2);

        assertThat(d1.getPattern(), is("Mutual Agreement"));
        assertThat(d2.getPattern(), is("You"));


        assertThat(d1.isMatch(), is(true));
        assertThat(d1.getType().getName(), is( "DefinitionDef"));

        assertThat(d2.isMatch(), is(true));
        assertThat(d2.getType().getName(), is( "DefinitionDef"));


    }


    //TODO: NOT implemented. This should give two answers.

    @Test
    public void called(){



        new ExtractorTesterOld("Detta avtal kallas för Avtalet. De tjänster som Du betalar för kallas nedan Tjänsten. Ingen definition kallas nedan Spela")
                .withParser(swedishParser)
                .withExtractor(new DefinitionExtractorSE())
                .expectingType(FeatureTypeTree.DEFINITION)
                .expectingPattern("Avtalet")
                .test(0, 1);



        new ExtractorTesterOld("Detta omfattar alla tjänster, nedan nämnd Tjänsterna")
                .withParser(swedishParser)
                .withExtractor(new DefinitionExtractorSE())
                .expectingType(FeatureTypeTree.DEFINITION)
                .expectingPattern("Tjänsterna")
                .test(0, 1);


    }

    @Test
    public void avser(){


        AnalysisFragment f;
        FeatureDefinition d;

        AnalysisOutcome o = new AnalysisOutcome();
        f = new AnalysisFragment("\"Avropsavtal\" avser de vid var tid mellan Parterna ingångna avtal som enligt dess lydelse utgör Avropsavtal till detta Ramavtal.", "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorSE().classify( f, o, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));

        assertThat(d.getPattern(), is("Avropsavtal"));

        AnalysisOutcome o2 = new AnalysisOutcome();
        f = new AnalysisFragment("\"Programmera java\" avser både programmering och test", "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorSE().classify( f, o2, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o2, 0, 1);

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));

        assertThat(d.getPattern(), is("Programmera java"));

        AnalysisOutcome o3 = new AnalysisOutcome();
        f = new AnalysisFragment("\"Material\" avser sådant material som Leverantören eventuellt har erhållit från Chigago för Uppdrags utförande.", "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorSE().classify( f, o3, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o3, 0, 1);

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));

        assertThat(d.getPattern(), is("Material"));

        AnalysisOutcome o4 = new AnalysisOutcome();
        f = new AnalysisFragment("\"Ersättningen\" ska ha den betydelse nedan", "", swedishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        new DefinitionExtractorSE().classify( f, o4, tree );
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o4, 0, 1);

        assertThat(d.isMatch(), is(true));
        assertThat(d.getType().getName(), is( "DefinitionDef"));

        assertThat(d.getPattern(), is("Ersättningen"));

    }


}
