package classifiers.classifierTests;

import classifiers.swedishClassifiers.*;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class ReportingClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    /***********************************************************
        *
        *      Testing Classification by examples for tag CommunicationAndReporting
        *      Document:   "Huvudavtal_EHM_IT_Drift.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testHuvudavtal_EHM_IT_DriftExamples(){
           try {
                  new ClassificationTester("\nUppsägning enligt punkten 24.1 ova"+
                                                "n ska inte medföra några kostnader eller"+
                                                " andra påföljder för EHM. Vidare gäller "+
                                                "att Leverantören är skyldig att informer"+
                                                "a EHM om sådana omständigheter som har b"+
                                                "etydelse för EHM vid bedömning av punkte"+
                                                "n 24.1 ovan.")
                           .withParser(swedishParser)
                           .withHeadline("25 Förtida upphörande av Avtalet")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ReportingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Leverantören ska fortlöpande tillhandahå"+
                                                "lla konkreta förslag till förbättringar "+
                                                "och effektiviseringar. Baserat på Levera"+
                                                "ntörens förslag ska Parterna gemensamt d"+
                                                "efiniera förbättringsprojekt för att eff"+
                                                "ektivisera Tjänsten.")
                           .withParser(swedishParser)
                           .withHeadline("7 Avtalets omfattning och Tjänsten")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ReportingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
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
      *      Testing Classification by examples for tag CommunicationAndReporting
      *      Document:   " Avtal avseende IT-drift"
      *      Language:   "SV"
      *
      */


     @Test
     public void testAvtalavseendeITdriftExamples(){
         try {


                new ClassificationTester("24.3 Uppsägning enligt punkten 24.1 ovan"+
                                              " ska inte medföra några kostnader eller "+
                                              "andra påföljder för EHM. Vidare gäller a"+
                                              "tt Leverantören är skyldig att informera"+
                                              " EHM om sådana omständigheter som har be"+
                                              "tydelse för EHM vid bedömning av punkten"+
                                              " 24.1 ovan.")
                         .withParser(swedishParser)
                         .withHeadline("24.3 Uppsägning enligt punkten 24.1 ovan ska inte medföra några kostnader eller andra påföljder för EHM. Vidare gäller att Leverantören är skyldig att informera EHM om sådana omständigheter som har betydelse för EHM vid bedömning av punkten 24.1 ovan.")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new ReportingClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
                                 .withTag("")
                         )
                     .test();


                new ClassificationTester("26.5 Aviseringsskyldighet")
                         .withParser(swedishParser)
                         .withHeadline("26.5 Aviseringsskyldighet")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new ReportingClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
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
       *      Testing Classification by examples for tag #REPORTING
       *      Document:   "Bilaga 3B - Personuppgiftsbitr??desavtal.docx"
       *      Language:   "unknown"
       *
       */


      @Test
      public void testLVExamples(){
          try {

                 new ClassificationTester("Personuppgiftsbiträdet och den eller de personer som arbetar unde"+
                                     "r dennes ledning får endast behandla personuppgifter i enlighet med de"+
                                     " instruktioner som lämnats av Las Vegas och för det ändamål som Person"+
                                     "uppgiftsbiträdet anlitats för. För det fall att Personuppgiftsbiträdet"+
                                     " saknar instruktioner som Personuppgiftsbiträdet bedömer är nödvändiga"+
                                     " för att genomföra uppdraget som Personuppgiftsbiträdet erhållit från "+
                                     "Las Vegas skall Säljaren, utan dröjsmål, informera Las V"+
                                     "egas om sin inställning och invänta de instruktioner som Las Vegas bed"+
                                     "ömer erfordras.")
                          .withParser(englishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          //.withClassifier(new ObligationClassifierSV())              // Handle that the same fragment has two classifications
                          .withClassifier(new ReportingClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
                              .withPattern("skall Säljaren , utan dröjsmål , informera")
                        )
                      .test();


                 new ClassificationTester("3.2  Köparen skall utan dröjsmål informera Personuppgiftsbiträdet om"+
                                     " förändringar i behandlingen vilken påverkar Personuppgiftsbiträdets s"+
                                     "kyldigheter. Las Vegas skall tillika informera Personuppgiftsbiträdet "+
                                     "om tredje parts, däribland Datainspektionen och registrerades åtgärder"+
                                     " med anledning av behandlingen.")
                          .withParser(englishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ReportingClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
                              .withPattern("Köparen skall utan dröjsmål informera")
                        )
                      .test();


                 new ClassificationTester("Part skall utan dröjsmål informera Las Vegas om"+
                                     " eventuella kontakter från Datainspektionen som rör eller kan vara av "+
                                     "betydelse för behandling av personuppgifter. Personuppgiftsbiträdet ha"+
                                     "r inte rätt att företräda Las Vegas eller agera för Las Vegass räkning"+
                                     " gentemot Datainspektionen eller annan tredje man.")
                          .withParser(englishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new PartsClassifierSV())
                          .withClassifier(new ReportingClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
                              .withPattern("Part skall utan dröjsmål informera")
                        )
                      .test();


                 new ClassificationTester("3.1  Las Vegas skall tillse att behandlingen sker i enlighet med PUL. "+
                                     "Köparen ansvarar bland annat för att informera de Registrerade om be"+
                                     "handlingen, för att i nödvändiga fall inhämta samtycke från de registr"+
                                     "erade och för att i tillämpliga fall anmäla behandlingen till Datainsp"+
                                     "ektionen.")
                          .withParser(englishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ReportingClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REPORTING, 1)
                              .withPattern("Köparen ansvarar bland annat för att informera")
                        )
                      .test();


             } catch (Exception e) {
                  e.printStackTrace(System.out);
                  assertTrue(false);
             }
          }

}
