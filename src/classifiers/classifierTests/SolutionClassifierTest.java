package classifiers.classifierTests;

import classifiers.englishClassifiers.SolutionClassifierEN;
import classifiers.swedishClassifiers.SolutionClassifierSV;
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
 *
 */


public class SolutionClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
     *
     *          Not implemented classifier, so the tests should not work
     *
     */

    @Test
    public void basicTest(){


        try {


            new ClassificationTester("No test")
                    .withParser(swedishParser)
                    .withClassifier(new SolutionClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SOLUTION, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new SolutionClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SOLUTION, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }



    /***********************************************************
     *
     *      Testing Classification by admin @2015-01-27
     *      Document:   "Anbudsförfrågan IT-drift 2014.docx"
     *      FragmentNo: 10
     *      Body:  "Tjänsten innebär tillhandahållande och drift av plattform för applikationer, servrar,
     *              datalagring, kommunikation, och kringtjänster inom hela EHM:s IT-drift i enlighet med Avtalet.
     *              Gällande Exadatamaskinerna så tillhandahåller EHM dessa till Leverantören på sätt som framgår
     *              av Avtalsbilaga 2a (EHM:s befintliga IT-miljö). Tjänsten omfattar ett helhetsåtagande för
     *              informationsflöden, funktionalitet och processer som krävs för att leverera olika delar av Tjänsten."
     *
     */
    @Test
    public void testSolutionClassifierSV(){
        try {

               new ClassificationTester("Tjänsten innebär tillhandahållande och d"+
                                             "rift av plattform för applikationer, ser"+
                                             "vrar, datalagring, kommunikation, och kr"+
                                             "ingtjänster inom hela EHM:s IT-drift i e"+
                                             "nlighet med Avtalet. Gällande Exadatamas"+
                                             "kinerna så tillhandahåller EHM dessa til"+
                                             "l Leverantören på sätt som framgår av Av"+
                                             "talsbilaga 2a (EHM:s befintliga IT-miljö"+
                                             "). Tjänsten omfattar ett helhetsåtagande"+
                                             " för informationsflöden, funktionalitet "+
                                             "och processer som krävs för att leverera"+
                                             " olika delar av Tjänsten.")
                        .withParser(englishParser)
                        .withHeadline("1.2 Upphandlingens omfattning")
                        .withClassifier(new SolutionClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SOLUTION, 1)
                                .withTag("beskrivning")
                        )
                    .test();
           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }

}
