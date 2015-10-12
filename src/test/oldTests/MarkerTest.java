package test.oldTests;

import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import analysis.FragmentCondition;
import featureTypes.FeatureTypeTree;
import org.junit.Test;
import parse.AnalysisFragment;
import textPatterns.TextPattern;


import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by Linus
 *
 *
 *      Markers are used to extract from TextPattern
 *
 *
 */

public class MarkerTest {

    @Test
    public void textNextNoun(){


        AnalysisFragment f = new AnalysisFragment("These Google Analytics Terms of Service (this \"Mutual Agreement\") are entered into by Google Inc.");
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());
        FragmentCondition c = new FragmentCondition(f).contains(new TextPattern("entered").extractNextNoun());

        FeatureDefinition d = c.testClassification(FeatureActionType.CLASSIFY, FeatureTypeTree.None, "");

        assertThat(d.getPattern(), is("Google"));

    }

    @Test
    public void textMarkerTest(){


        AnalysisFragment f = new AnalysisFragment("These Google Analytics Terms of Service (this \"Mutual Agreement\") are entered into by Google Inc.");
        System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());
        FragmentCondition c = new FragmentCondition(f).contains(new TextPattern("enter").extractNextNoun());

        FeatureDefinition d = c.testClassification(FeatureActionType.CLASSIFY, FeatureTypeTree.None, "");

        assertThat(d.getPattern(), is("Google"));

    }





}
