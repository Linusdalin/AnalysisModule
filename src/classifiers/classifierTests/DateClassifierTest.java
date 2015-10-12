package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.DateClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.DateClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Date tests
 *
 *          TODO: Implement and test semantic extraction of the date converting it to a correct date value regardless of format
 */


public class DateClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {


            new ClassificationTester("This contains a date 2014-07-01")
                    .withParser(englishParser)
                    .withClassifier(new DateClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.DATE))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                            .withPattern("2014-07-01")
                            .withKeywords("time"))

                    .test();

            new ClassificationTester("Detta är ett datum 2014-07-01")
                    .withParser(swedishParser)
                    .withClassifier(new DateClassifierSV())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.DATE))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                            .withPattern("2014-07-01")
                            .withKeywords("tid"))

                    .test();

            new ClassificationTester("This contains a date Feb 20, 2015")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new DateClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.DATE))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                            .withPattern("Feb 20, 2015")
                            .withKeywords("time"))

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    /******************************************************************************************'
     *
     *          Lokking for number and date should still only return one
     *
     */


    @Test
    public void combinationTest(){



        new ClassificationTester("This contains a date 2014-07-01")
                .withParser(englishParser)
                .withClassifier(new NumberClassifierEN())
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                            .withPattern("2014-07-01"))

                .test();

        // The numbers for the date is actually found but should not be counted.
        // Hence the 7 is still the first number found

        new ClassificationTester("This contains both a date 2014-07-01 and a number 7.")
                .withParser(englishParser)
                .withClassifier(new NumberClassifierEN())
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                            .withPattern("2014-07-01"))
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 4)
                            .withPattern("7"))

                .test();



    }

    @Test
    public void namedDateTest(){

        new ClassificationTester("A named date: March 3d 2015")
                .withParser(englishParser)
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                        .withPattern("March 3d 2015"))
                .test();

        new ClassificationTester("A named date: 1a januari 2015")
                .withParser(swedishParser)
                .withClassifier(new DateClassifierSV())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                        .withPattern("1a januari"))
                .test();


    }

    @Test
    public void usDateTest(){


        new ClassificationTester("This is actually also a date, however strange... 05/06/2014")
                .withParser(englishParser)
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                        .withPattern("05/06/2014"))
                .test();

        new ClassificationTester("This is actually also a date, however strange... 05/06/2014")
                .withParser(englishParser)
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                        .withPattern("05/06/2014"))
                .test();


        new ClassificationTester("This too (sigh...)... 10/11/12")
                .withParser(englishParser)
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                        .withPattern("10/11/12"))
                .test();


        new ClassificationTester("Omitting 0)... 9/6/14")
                .withParser(englishParser)
                .withClassifier(new DateClassifierEN())
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Date, 1)
                        .withPattern("9/6/14"))
                .test();



    }

    /*


    @Test
    public void NonDatesTest(){


        new ClassificationTester("This is not a date. The format should be MM/DD/YY 30/06/14")
                .withParser(englishParser)
                .withExtractor(new DateExtractorEN())
                .expectingType(FeatureTypeTree.Date)
                .test(0, 0);




    }

    */



}
