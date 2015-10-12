package classifiers.classifierTests;

import classifiers.englishClassifiers.ArbitrationClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.SLAClassifierEN;
import classifiers.swedishClassifiers.ArbitrationClassifierSE;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.SLAClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class SLAClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
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
                    .withClassifier(new ArbitrationClassifierSE())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new ArbitrationClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #SLA
        *      Document:   "Bilaga 2 - Prislista 20140815 (CSC ENG unprotected 0.1).xlsx"
        *      Language:   "EN"
        *
        */


       @Test
       public void testBilaga2PrislistaExamples(){
           try {
                  new ClassificationTester("Drift Servicenivåklass B (Operations Ser"+
                                                "vice level class  B)")
                           .withParser(englishParser)
                           .withHeadline("IT-drift (IT Operations) ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new SLAClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SLA, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Drift Servicenivåklass A (Operations Ser"+
                                                "vice level class  A)")
                           .withParser(englishParser)
                           .withHeadline("IT-drift (IT Operations) ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SLAClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SLA, 1)
                                   .withTag("")
                           )
                       .test();



              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #SLA
        *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testLVExamples(){
           try {
              // Found 2 re-classifications

                  new ClassificationTester("14.2 Leverantören garanterar att av Leverantören hostad interaktiv ans"+
                                      "varsutbildning har en tillgänglighet mätt per kalendermånad på minst 9"+
                                      "9 % under tiden måndag-söndag kl 06.00-24.00 (\"Avtalad Tillgängli"+
                                      "ghet\"). Dock gäller inte Avtalad Tillgänglighet om nertid kan här"+
                                      "ledas till Las Vegass server/it-miljö eller handhavande, d v s faktore"+
                                      "r som Leverantören inte har kontroll över, eller om Las Vegas och Leve"+
                                      "rantören kommit överens om planerad nertid.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SLAClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SLA, 1)
                               .withPattern("Avtalad Tillgänglighet")
                         )
                       .test();


                  new ClassificationTester("14.7 I Leverantörens hostingåtagande ingår tillhandahållande av suppor"+
                                      "t. Supportärenden av teknisk och kritisk karaktär ska besvaras inom 24"+
                                      " timmar på vardagar. För helgdagar ska ärenden besvaras närmast följan"+
                                      "de vardag. Under perioden 15 juni - 15 augusti kan andra svarstider öv"+
                                      "erenskommas mellan Parterna.  ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SLAClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SLA, 1)
                               .withPattern("Supportärenden av teknisk och kritisk karaktär ska besvaras")

                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace(System.out);
                   assertTrue(false);
              }
           }

}
