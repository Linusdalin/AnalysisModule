package classifiers.classifierTests;

import classifiers.englishClassifiers.ArbitrationClassifierEN;
import classifiers.swedishClassifiers.ArbitrationClassifierSE;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class ArbitrationClassifierTest extends AnalysisTest {


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
       *      Testing Classification by examples for tag #ARBITRATION
       *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
       *      Language:   "SV"
       *
       */


      @Test
      public void testLVExamples(){
          try {
             // Found 7 re-classifications

                 new ClassificationTester("9.4 Skiljenämnden, Parterna, deras ombud samt andra som deltar i skilj"+
                                     "eförfarandet ska iaktta sekretess avseende förfarandet och vad som för"+
                                     "evarit där.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("Skiljenämnden")
                          )
                      .test();


                 new ClassificationTester("9.5 Part äger, med undantag av det sagda, föra fullgörelsetalan vid al"+
                                     "lmän domstol, med Stockholms tingsrätt som första instans, om klar och"+
                                     " förfallen fordran enligt Avtalet.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("fullgörelsetalan")
                          )
                      .test();


                 new ClassificationTester("9.2 Tvist i anledning av detta avtal ska slutligt avgöras genom skilje"+
                                     "dom enligt Skiljedomsregler för Stockholms Handelskammares Skiljedomsi"+
                                     "nstitut. Skiljeförfarandets säte ska vara Stockholm och skiljeförfaran"+
                                     "det ska hållas på svenska. ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("Tvist")
                          )
                      .test();


                 new ClassificationTester("28.4 Skiljenämnden, Parterna, deras ombud samt andra som deltar i skil"+
                                     "jeförfarandet ska iaktta sekretess avseende förfarandet och vad som fö"+
                                     "revarit där.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("Skiljenämnden")
                          )
                      .test();


                 new ClassificationTester("9.3 Institutets Regler för Förenklat Skiljeförfarande ska gälla om int"+
                                     "e Institutet med beaktande av målets svårighetsgrad, tvisteföremålets "+
                                     "värde och övriga omständligheter bestämmer att Regler för Stockholms H"+
                                     "andelskammares Skiljedomsinstitut ska tillämpas på förfarandet. I sist"+
                                     "nämnda fall ska Institutet också bestämma om skiljenämnden ska bestå a"+
                                     "v en eller tre skiljemän.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("Skiljeförfarande")
                          )
                      .test();


                 new ClassificationTester("28.3 Institutets Regler för Förenklat Skiljeförfarande ska gälla om in"+
                                     "te Institutet med beaktande av målets svårighetsgrad, tvisteföremålets"+
                                     " värde och övriga omständligheter bestämmer att Regler för Stockholms "+
                                     "Handelskammares Skiljedomsinstitut ska tillämpas på förfarandet. I sis"+
                                     "tnämnda fall ska Institutet också bestämma om skiljenämnden ska bestå "+
                                     "av en eller tre skiljemän.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("Skiljeförfarande")
                          )
                      .test();


                 new ClassificationTester("28.2 Tvist i anledning av detta Avtal ska slutligt avgöras genom skilj"+
                                     "edom enligt Skiljedomsregler för Stockholms Handelskammares Skiljedoms"+
                                     "institut. Skiljeförfarandets säte ska vara Stockholm och skiljeförfara"+
                                     "ndet ska hållas på svenska.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ArbitrationClassifierSE())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 1)
                                  .withPattern("Tvist")
                          )
                      .test();


             } catch (Exception e) {
                  e.printStackTrace(System.out);
                  assertTrue(false);
             }
          }


}
