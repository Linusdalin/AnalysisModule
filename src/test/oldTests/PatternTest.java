package test.oldTests;

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
 */

public class PatternTest {




    @Test
    public void basicTest(){

        AnalysisFragment fragment = new AnalysisFragment("This is a text that includes words and comments");
        TextPattern pattern;

        pattern = new TextPattern("word").verbatimForms();
        assertThat(pattern.matches(fragment), is(false));

        pattern = new TextPattern("word");
        assertThat(pattern.matches(fragment), is(true));

    }

    /*********************************************************'
     *
     *          Test proximity as conditions for match. The specification should be:
     *
     *           - adjacent: no words in between
     *
     *
     */

    @Test
    public void proximityTest(){

        AnalysisFragment fragment = new AnalysisFragment("he will immediately pay the bill or will pay a Fine");
        TextPattern pattern;

        //testing Any of

        pattern = new TextPattern(new String[]{"immediately", "directly"}).anyOf();
        assertThat(pattern.matches(fragment), is(true));

        pattern = new TextPattern(new String[]{"just", "nu"}).anyOf();
        assertThat(pattern.matches(fragment), is(false));

        // testing none of

        pattern = new TextPattern(new String[]{"just","nu"}).noneOf();
        assertThat(pattern.matches(fragment), is(true));

        pattern = new TextPattern(new String[]{"pay", "immediately"}).noneOf();
        assertThat(pattern.matches(fragment), is(false));

        // Find the second hit

        pattern = new TextPattern(new String[]{"will", "pay"}).adjacent();
        assertThat(pattern.matches(fragment), is(true));

        // Three words

        pattern = new TextPattern(new String[]{"will", "pay", "a"}).adjacent();
        assertThat(pattern.matches(fragment), is(true));

        // One in between is not ok for adjacent

        pattern = new TextPattern(new String[]{"pay", "bill"}).adjacent();
        assertThat(pattern.matches(fragment), is(false));
        assertThat(pattern.getExplanation(), is("VERY_CLOSE found (required ADJACENT)"));


        pattern = new TextPattern(new String[]{"he", "fine"}).adjacent();
        assertThat(pattern.matches(fragment), is(false));
        assertThat(pattern.getExplanation(), is("ALL_PRESENT found (required ADJACENT)"));


        // Try very close. This should allow for minor words
        // Find the second hit

        pattern = new TextPattern(new String[]{"will", "pay"}).veryClose();
        assertThat(pattern.matches(fragment), is(true));

        // Three words

        pattern = new TextPattern(new String[]{"will", "pay", "a"}).veryClose();
        assertThat(pattern.matches(fragment), is(true));

        // One small word is not implemented

        pattern = new TextPattern(new String[]{"pay", "bill"}).veryClose();
        assertThat(pattern.matches(fragment), is(true));

        pattern = new TextPattern(new String[]{"he", "fine"}).veryClose();
        assertThat(pattern.matches(fragment), is(false));
        assertThat(pattern.getExplanation(), is("ALL_PRESENT found (required VERY_CLOSE)"));

        // Close. This should allow for one major word in between
        // Find the second hit

        pattern = new TextPattern(new String[]{"will", "pay"}).close();
        assertThat(pattern.matches(fragment), is(true));

        // Three words

        pattern = new TextPattern(new String[]{"will", "pay", "a"}).close();
        assertThat(pattern.matches(fragment), is(true));

        // One in between is not ok for adjacent

        pattern = new TextPattern(new String[]{"pay", "bill"}).close();
        assertThat(pattern.matches(fragment), is(true));

        // One major and a minor word should work

        pattern = new TextPattern(new String[]{"pay", "or"}).close();
        assertThat(pattern.matches(fragment), is(true));

        // One in between is not ok for adjacent

        pattern = new TextPattern(new String[]{"will", "pay"}).close();
        assertThat(pattern.matches(fragment), is(true));

        pattern = new TextPattern(new String[]{"he", "fine"}).close();
        assertThat(pattern.matches(fragment), is(false));



        pattern = new TextPattern(new String[]{"he", "fine"}).allPresent();
        assertThat(pattern.matches(fragment), is(true));

        pattern = new TextPattern(new String[]{"he", "can"}).allPresent();
        assertThat(pattern.matches(fragment), is(false));

    }

    /***************************************************************************'
     *
     *
     *      Double pattern is added with the .and(TextPattern) method
     *
     *      All patterns shall be fulfilled
     *
     */


    @Test
    public void doublePattern(){

        AnalysisFragment fragment = new AnalysisFragment("he will immediately pay the bill or will pay a Fine");
        TextPattern pattern;

        pattern = new TextPattern(new String[]{"pay", "bill"}).veryClose()
                .and(new TextPattern(new String[]{"pay", "something"}).veryClose()
        );

        assertThat(pattern.matches(fragment), is(false));

        pattern = new TextPattern(new String[]{"pay", "bill"}).veryClose()
                .and(new TextPattern(new String[]{"pay", "fine"}).veryClose()
        );

        assertThat(pattern.matches(fragment), is(true));

    }

    /*************************************************************************'
     *
     *      This tests the thresholds for most (60%) and some (20%).
     *
     *      The values shall be equal or more This gives:
     *
     *       - Some: e.g:  1/3, 1/4, 2/7 but not 1/5
     *       - Most: e.g. 4/6 5/7 5/8 but not 5/9
     *
     *
     */


    @Test
    public void testSomeAndMost(){

        AnalysisFragment fragment1 = new AnalysisFragment("This is just a list: volvo, bmw, saab, ford, renault, fiat.");
        AnalysisFragment fragment2 = new AnalysisFragment("This is no list, just a text on volvo and saab.");
        AnalysisFragment fragment3 = new AnalysisFragment("This is just on bmw");
        TextPattern pattern;

        pattern = new TextPattern(new String[]{"volvo", "bmw", "saab", "ford", "renault"}).someOf();

        assertThat(pattern.matches(fragment1), is(true));
        assertThat(pattern.matches(fragment2), is(true));
        assertThat(pattern.matches(fragment3), is(false));  // Only 1 in 5

        pattern = new TextPattern(new String[]{"volvo", "bmw", "saab", "ford", "renault"}).mostOf();

        assertThat(pattern.matches(fragment1), is(true));
        assertThat(pattern.matches(fragment2), is(false));
        assertThat(pattern.matches(fragment3), is(false));


    }

}
