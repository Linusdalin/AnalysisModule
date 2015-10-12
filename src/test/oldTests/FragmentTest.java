package test.oldTests;

import analysis.AnalysisOutcome;
import extractors.extractorsEN.RiskExtractorEN;
import featureTypes.FeatureTypeTree;
import language.English;
import openNLP.NLParser;
import org.junit.Test;
import parse.AnalysisFragment;
import parse.POSClassification;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class FragmentTest {

    private static final FeatureTypeTree tree = new FeatureTypeTree();


    @Test
    public void basicTest(){

        NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");

        AnalysisFragment f;

        f = new AnalysisFragment("The House is a mess","", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"" + f.display());

        assertTrue(f.getWords().get(0).getText().equals("The"));
        assertThat(f.getWords().get(0).getPos(), is(POSClassification.DT));
        assertThat(f.getWords().get(0).getChunk().getText(), is("The House"));
        assertThat(f.getWords().get(0).getChunk().getSize(), is(2));


    }

    @Test
    public void chunkTest(){

        NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");

        AnalysisFragment f = new AnalysisFragment("The little yellow dog barked at the cat", "", englishParser);

        System.out.println("Fragment: \"" + f.getText() + "\"" + f.display());

        assertTrue(f.getWords().get(0).getText().equals("The"));
        assertThat(f.getWords().get(0).getPos(), is(POSClassification.DT));
        assertThat(f.getWords().get(0).getChunk().getText(), is("The little yellow dog"));
        assertThat(f.getWords().get(0).getChunk().classification, is("NP"));


    }


    @Test
    public void bracketTest(){

        NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");

        AnalysisFragment f;

        f = new AnalysisFragment("These Google Analytics Terms of Service (this \"Agreement\") are entered into by Google Inc. (\"Google\")", "", englishParser);
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

        assertTrue(f.getWords().get(7).getText().equals("this"));
        assertThat(f.getWords().get(7).getChunk().getSize(), is(1));         // Two words in the fragment
        assertThat(f.getWords().get(7).getChunk().isBracket(), is(true));    // Is in a bracket


        // "Are entered"

        assertTrue(f.getWords().get(12).getText().equals("are"));
        assertThat(f.getWords().get(12).getChunk().getSize(), is(2));
        assertThat(f.getWords().get(12).getChunk().isBracket(), is(false));


        /*

        assertThat(f.words[0].getPos(), is(POSClassification.DT));
        assertTrue(f.words[0].getChunk().getText().equals("The House "));
        assertThat(f.words[0].getChunk().getSize(), is(2));
          */

    }

    @Test
    public void LiveExamplesTest(){

        AnalysisOutcome o = new AnalysisOutcome();


        NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");

        AnalysisFragment f = new AnalysisFragment("\"2. All applicable security-, privacy- and data protection laws, rules, and regulations which are or which may in the future be applicable to the Services or to information or data relating to Services or ONECOMPANY’s and employees of ONECOMPANY, its affiliates and Authorized Users. \"", "", englishParser);

        System.out.println("Fragment: \"" + f.getText() + "\"" + f.display());
        new RiskExtractorEN().classify( f, o, tree);


    }

}
