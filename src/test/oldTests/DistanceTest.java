package test.oldTests;

import org.junit.Test;
import parse.AnalysisFragment;
import textPatterns.Distance;
import textPatterns.RecursiveExtraction;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by Linus
 */

public class DistanceTest {


    /*************************************************
     *
     *         will pay
     *          he will immediately pay the bill or pay fine

     */

    @Test
    public void basicTest(){

        List<RecursiveExtraction> noRecursion = new ArrayList<>();


        AnalysisFragment fragment = new AnalysisFragment("he will immediately pay the bill or will pay a Fine");
        Distance d;

        d = new Distance(new String[] {"will", "pay", "fine"}, noRecursion, fragment, true, true);
        assertThat(d.getDistance(fragment), is(Distance.INFINITE));  // One word in between ("a")

        d = new Distance(new String[] {"will", "pay", "fine"}, noRecursion, fragment, false, true);
        assertThat(d.getDistance(fragment), is(1));  // One small word in between ("a")


    }
}
