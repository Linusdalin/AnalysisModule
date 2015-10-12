package test.oldTests;

import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import analysis.FeatureHit;
import featureTypes.FeatureTypeTree;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 */



public class ExplanationTest {



    @Test
    public void basicText(){

        FeatureDefinition explanation = new FeatureDefinition("", FeatureHit.IS_A, "", new ArrayList<String>(), FeatureActionType.CLASSIFY, FeatureTypeTree.None);

        assertThat(explanation.isMatch(), is(true));

    }



}