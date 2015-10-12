package classifiers.classifierTests;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Generic tests on the classifiers
 *
 */


public class GenericTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    //TODO: Make testcases for these


    @Test
    public void multipleClassifications(){


        try {

            String testString;

            // This should have two classifications, both a deadline and a termination

            testString = "Köparen har rätt att, med två (2) veckors varsel, när som helst, helt eller delvis, avbeställa ej utförda delar av Uppdrag";

            // This should be an exepmtion, not an obligation, regardless of the order

            testString = "Säljaren kan dock inte garantera några volymer.";

            // This is both #Reporting and #Obligation. Both should be classified, regardless of the order of classifiers

            testString = "Part skall utan dröjsmål informera Las Vegas om eventuella kontakter från Datainspektionen";


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
