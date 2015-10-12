package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.AmountClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Amount tests
 *
 *          TODO: Implement and test semantic extraction of the value of the amount
 */


public class AmountClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    @Test
    public void basicTest(){


        try {


            new ClassificationTester("After 2014-10-10 all cities in the US has more than USD 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("USD 1,200"))

                    .test();






        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    @Test
    public void currenciesNameTest(){


        try {

            new ClassificationTester("After 2014-10-10 all cities in the US has more than SEK 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("SEK 1,200")
                            .withTag("SEK"))

                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than NOK 23 000 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("NOK 23 000")
                            .withTag("NOK"))

                    .test();


            new ClassificationTester("After 2014-10-10 all cities in the US has more than GBP 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("GBP 1,200")
                            .withTag("GBP"))

                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than EUR 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("EUR 1,200")
                            .withTag("EUR"))

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }




    @Test
    public void reverseOrderTest(){


        try {

            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200 USD in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200 USD")
                            .withTag("USD"))

                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200 EUR in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200 EUR")
                            .withTag("EUR"))

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void symbolsTest(){


        try {

            new ClassificationTester("After 2014-10-10 all cities in the US has more than $ 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("$ 1,200")
                            .withTag("USD"))


                    .test();

            new ClassificationTester("After 2014-10-10 all cities in UK has more than £ 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("£ 1,200")
                            .withTag("GBP"))


                    .test();

            new ClassificationTester("After 2014-10-10 all cities in EU has more than € 1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("€ 1,200")
                            .withTag("EUR"))


                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }



    @Test
    public void writeoutTest(){


        try {



            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200 dollar in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200 dollar")
                            .withTag("USD"))

                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200 Euro in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200 Euro")
                            .withTag("EUR"))

                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200 kronor in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200 kronor")
                            .withTag("SEK"))

                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200:- in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200:-")
                            .withTag("SEK"))

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void writeTogetherTest(){


        try {

            new ClassificationTester("After 2014-10-10 all cities in the US has more than $1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("$1,200")
                            .withTag("USD"))


                    .test();

            new ClassificationTester("After 2014-10-10 all cities in the US has more than USD1,200 in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.AMOUNT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("USD 1,200")
                            .withTag("USD"))

                    .test();

            /*

                //TODO: Fix the parser and make this test work

            new ClassificationTester("After 2014-10-10 all cities in the US has more than 1,200SEK in fines")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new AmountClassifierEN())
                    .expectingParseNode(NodeType.AMOUNT)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Amount, 1)
                            .withPattern("1,200 SEK")
                            .withTag("SEK"))

                    .test();

                */
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }


}
