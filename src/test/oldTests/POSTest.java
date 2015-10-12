package test.oldTests;

import org.junit.Test;
import parse.POSClassification;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class POSTest {


    @Test
    public void testEnum(){


        String coordinatingConjunction = POSClassification.CC.getDescription();
        assertTrue(coordinatingConjunction.equals("Coordinating conjunction"));

    }

}
