package test.oldTests;

import extractors.HeadlineReferenceAnalyser;
import org.junit.Test;
import parse.AnalysisFragment;
import textPatterns.Distance;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by Linus
 */

public class ReferencesTest {


    /*************************************************
     *
     *         will pay
     *          he will immediately pay the bill or pay fine

     */

    @Test
    public void matchTest(){


        HeadlineReferenceAnalyser headlineReferenceAnalyser = new HeadlineReferenceAnalyser();

        assertThat("Matching part of a chapter number", headlineReferenceAnalyser.matches("7.", "6.7. Headline"), is(false));
        assertThat(headlineReferenceAnalyser.matches("7.", "7. Headline"), is(true));
        assertThat(headlineReferenceAnalyser.matches("7", "7. Headline"), is(true));

        assertThat(headlineReferenceAnalyser.matches("7.", "7 Headline"), is(true));

    }
}
