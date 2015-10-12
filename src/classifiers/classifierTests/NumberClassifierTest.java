package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.HeadlineClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.NumberExpressionClassifierSV;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import log.AnalysisLogger;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Email tests
 *
 */


public class NumberClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

        init();

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);
    }



    @Test
    public void basicTest(){


        try {


            new ClassificationTester("This contains a number 10 value.")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.NUMBER))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 1)
                            .withPattern("10"))


                    .test();

            new ClassificationTester("Nummer 10.2 funkar också.")
                    .withParser(swedishParser)
                    .withClassifier(new NumberClassifierSV())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.NUMBER))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 1)
                            .withPattern("10.2"))
                    .test();

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    /*************************************************************************
     *
     *          A headline with numbering should not be flagged as number
     *
     *
     */

    @Test
    public void headlineNumber(){


        try {


            new ClassificationTester("1.1 Background")
                    .withParser(englishParser)
                    .withHeadline("1.1 Background")
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new HeadlineClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 0)

                                )

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    /***********************************************************
         *
         *      Testing Two numbers in the same fragment
         *      Document:   " Avtalsbilaga 2a"
         *      Language:   "SV"
         *
         */


        @Test
        public void twoNumbersInSame(){
            try {
                   new ClassificationTester("Nedanstående bild visar de huvudsakliga "+
                                                 "produktionssystemen och informationsflöd"+
                                                 "et mellan dem. Informationsflödet till e"+
                                                 "xterna tjänster och intressenter visas e"+
                                                 "j. Se kapitel 2.4. Modellen omfattar int"+
                                                 "e de nya systemen EFP, LIC, NILS-rådataf"+
                                                 "iler och SOL. Modellen visar inte heller"+
                                                 " EHM:s externwebb. En zoom på minst 250%"+
                                                 " rekommenderas för att se alla detaljer "+
                                                 "i bilden.")
                            .withParser(swedishParser)
                            .withHeadline("Inledning")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new NumberExpressionClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 1)
                                    .withTag("regular number")
                                    .withPattern("2.4")
                            )
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 2)
                                    .withTag("regular number")
                                    .withPattern("250")
                            )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }



    @Test
    public void numberExpression(){
        try {

            new ClassificationTester("under en tid om två (2) till sju ( 7 ) månader")
                     .withParser(swedishParser)
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new NumberExpressionClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 1)
                             .withTag("Expression")
                             .withPattern("två (2)")
                     )
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 4)
                             .withTag("Expression")
                             .withPattern("sju ( 7 )")
                     )
                 .test();


               new ClassificationTester("under en tid om sex ( 6 ) månader")
                        .withParser(swedishParser)
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new NumberExpressionClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 1)
                                .withTag("Expression")
                                .withPattern("sex ( 6 )")
                        )
                    .test();

            new ClassificationTester("under en tid om tolv (12) månader")
                     .withParser(swedishParser)
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new NumberExpressionClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Numex, 1)
                             .withTag("Expression")
                             .withPattern("tolv (12)")
                     )
                 .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }





}
