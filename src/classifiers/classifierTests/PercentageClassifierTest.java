package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.PercentageClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.PercentageClassifierSV;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
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


public class PercentageClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }



    @Test
    public void basicTest(){


        try {


            new ClassificationTester("This contains a percentage value 10%")
                    .withParser(englishParser)
                    .withClassifier(new NumberClassifierEN())
                    .withClassifier(new PercentageClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.PERCENT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Percentage, 1)
                            .withPattern("10 %"))


                    .test();

            new ClassificationTester("10 procent funkar på svenska")
                    .withParser(swedishParser)
                    .withClassifier(new NumberClassifierSV())
                    .withClassifier(new PercentageClassifierSV())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.PERCENT))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Percentage, 1)
                                                .withPattern("10 procent"))
                    .test();

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
         *
         *      Testing Classification by examples for tag #PERCENTAGE
         *      Document:   " Avtalsbilaga 2a"
         *      Language:   "SV"
         *
         */


        @Test
        public void testAvtalsbilaga2aExamples(){
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
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new PercentageClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Percentage, 1)
                                .withTag("")
                          )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }




}
