package classifiers.classifierTests;

import classifiers.englishClassifiers.DateClassifierEN;
import classifiers.englishClassifiers.DeadlineClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.*;
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


public class DeadlineClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    @Test
       public void testDeadlineClassifierSV(){
           try {

                  new ClassificationTester("Anbudsgivaren bör kunna genomföra en såda"+
                                                "n anbudspresentation inom 10 arbetsdaga"+
                                                "r från när Beställaren så begär.")
                           .withParser(swedishParser)
                           .withHeadline("2.9 Anbudspresentation")
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DeadlineClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)

                                   .withTag("Reaktion")
                           )
                       .test();
              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #DEADLINE
        *      Document:   "Anbudsförfrågan IT-drift 2014.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAnbudsförfråganITdrift2014Examples(){
           try {

                  new ClassificationTester("För att säkerställa att Beställaren uppf"+
                                                "attat uppgifterna i anbudet korrekt komm"+
                                                "er Beställaren att kalla anbudsgivare ti"+
                                                "ll anbudspresentation, där anbudsgivaren"+
                                                " redogör för innehållet i anbudet. Bestä"+
                                                "llaren kommer dock inte att lämna någon "+
                                                "information eller besvara några frågor u"+
                                                "nder anbudspresentationen, och några för"+
                                                "handlingar kommer inte att genomföras. A"+
                                                "nbudsgivaren bör kunna genomföra en såda"+
                                                "n anbudspresentation inom tio arbetsdaga"+
                                                "r från när Beställaren så begär.")
                           .withParser(swedishParser)
                           .withHeadline("2.9 Anbudspresentation")
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new NumberExpressionClassifierSV())
                           .withClassifier(new DeadlineClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("Reaktion")
                           )
                       .test();
                  new ClassificationTester("Svar på frågor publiceras senast sex dag"+
                                                "ar före sista anbudsdag.")
                           .withParser(swedishParser)
                           .withHeadline("2.1 Frågor och eventuella förtydliganden")
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new NumberExpressionClassifierSV())
                           .withClassifier(new DeadlineClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("Reaktion")
                           )
                       .test();
                  new ClassificationTester("Anbudet ska vara Beställaren tillhanda senast 2014-09-10"+
                                                ". ")
                           .withParser(swedishParser)
                           .withHeadline("2.4 Anbudstiden utgång")
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new NumberExpressionClassifierSV())
                           .withClassifier(new DateClassifierSV())
                           .withClassifier(new DeadlineClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("Angivet datum")
                           )
                       .test();
                  new ClassificationTester("Genom att lämna anbud förbinder sig anbu"+
                                                "dsgivare att, i händelse av att anbudsgi"+
                                                "varen tilldelas avtal, underteckna och å"+
                                                "tersända av Beställaren översänt avtal i"+
                                                "nom fem dagar från mottagande av avtalet"+
                                                ". Avtalet är bindande för Beställaren fö"+
                                                "rst när Beställaren undertecknat detta.")
                           .withParser(swedishParser)
                           .withHeadline("2.10 Tilldelningsbeslut och avslutande av upphandling")
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new NumberExpressionClassifierSV())
                           .withClassifier(new DeadlineClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("Reaktion")
                                   .withPattern("inom fem dagar")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #DEADLINE
        *      Document:   " Participation Agreement ? TINC Silicon Valley"
        *      Language:   "EN"
        *
        */


       @Test
       public void testParticipationAgreementExamples(){
           try {
                  new ClassificationTester("The net cost of the program after subsid"+
                                                "y is NOK 23,000 per participant. The ful"+
                                                "l fee will be invoiced by Innovation Nor"+
                                                "way, and then a grant from VINNOVA will "+
                                                "make up for the difference. Please note "+
                                                "that due to the costs Innovation Norway "+
                                                "must incur to set up the program, you wi"+
                                                "ll be responsible for the program fee if"+
                                                " you cancel less than 20 days prior to t"+
                                                "he start of TINC, for any reason other t"+
                                                "han a medical condition. Please note tha"+
                                                "t the participation fee cannot be applie"+
                                                "d for future programs.")
                           .withParser(englishParser)
                           .withHeadline("1. About TINC Silicon Valley")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new DeadlineClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("Reaktion")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #DEADLINE
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {


                  new ClassificationTester("Every potential Vendor that intends to p"+
                                                "articipate in the RFP process shall late"+
                                                "st March 3th 2015 confirm the"+
                                                "ir participation in writing or e-mail to"+
                                                " the RFP Coordinator.")
                           .withParser(englishParser)
                           .withHeadline("3.1  Confirmation of Participation")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DateClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new DeadlineClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withPattern("latest March 3th 2015")
                                   .withTag("stipulated date")
                           )
                       .test();



                  new ClassificationTester("Closing date for submitting response to "+
                                                "the RFP Coordinator. Swedbank will confirm that RFP answer has been received")
                           .withParser(englishParser)
                           .withHeadline("")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DateClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new DeadlineClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("")
                           )
                       .test();



                  new ClassificationTester("The Time Plan below lists the milestones of the RFP Process. Please note that this Time plan is preliminary and can be subject to change by Swedbank.")
                           .withParser(englishParser)
                           .withHeadline("")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DateClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new DeadlineClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("milestone")
                           )
                       .test();


                  new ClassificationTester("Responses shall be received by Swedbank "+
                                                "and the RFP Coordinator no later than Ma"+
                                                "y 12 th 2015, 15.00 local time (15.00 CET).")
                           .withParser(englishParser)
                           .withHeadline("3.6  Cost / Pricing")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DateClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new DeadlineClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                                   .withTag("stipulated date")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
       *
       *      Testing Classification by examples for tag #DEADLINE
       *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
       *      Language:   "SV"
       *
       */


      @Test
      public void testLVExamples(){
          try {
             // Found 18 re-classifications

              new ClassificationTester(//"18.2 Las Vegas ska meddela Leverantören om önskad tidpunkt för sådan "+
                                  "revision senast fjorton (14) dagar innan planerat revisionsdatum. ")
                       .withParser(swedishParser)
                       .withHeadline(" add headline...")
                       .withProject(mockProject, mockDocument)
                       .withClassifier(new NumberClassifierSV())
                       .withClassifier(new NumberExpressionClassifierSV())
                       .withClassifier(new DeadlineClassifierSV())
                       .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                           .withPattern("senast fjorton (14) dagar")
                           .withTag("Reaktion")
                     )
                   .test();



                 new ClassificationTester("14.8 Las Vegas har rätt att säga upp Leverantörens hosting inklusive s"+
                                     "upport i respektive Avropsavtal med iakttagande av en uppsägningstid o"+
                                     "m tre (3) månader. ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("uppsägningstid om tre (3)")
                              .withTag("uppsägning")
                        )
                      .test();




                 new ClassificationTester("27.1 Detta Ramavtal gäller under två (2) år från och med dess undertec"+
                                     "knande (\"Initial Avtalsperiod\"). Las Vegas har rätt att förl"+
                                     "änga Avtalsperioden två gånger om vardera ett (1) år, varför Avtalsper"+
                                     "ioden som längst kan uppgå till totalt fyra (4) år. Om Las Vegas önska"+
                                     "r förlänga Avtalsperioden ska Las Vegas skriftligen meddela Leverantör"+
                                     "en detta senast tre (3) månader innan Avtalsperiodens utgång. Om ingen"+
                                     " förlängning sker löper Avtalet ut vid Avtalsperiodens utgång utan för"+
                                     "egående uppsägning. ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("senast tre (3) månader")
                              .withTag("Reaktion")
                        )
                      .test();


                 new ClassificationTester("(ii)  om avsänt med rekommenderat brev: tre (3) dagar efter avlämnande för postbefordran")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("tre (3) dagar efter")
                              .withTag("Reaktion")
                        )
                      .test();


                 new ClassificationTester("8.2 [INFOGA: om Leverantören ska ansvara för hosting inkl support:] La"+
                                     "s Vegas har rätt att säga upp Leverantörens ansvar för hosting inklusi"+
                                     "ve support enligt ovan med iakttagande av en uppsägningstid om tre (3)"+
                                     " månader. ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("uppsägningstid om tre (3)")
                              .withTag("uppsägning")
                        )
                      .test();


                 new ClassificationTester("(i)  den andra Parten i väsentlig mån åsidosätter sina åtaganden "+
                                     "enligt Avtalet och inte vidtar rättelse inom femton (15) dagar från de"+
                                     "t att Parten skriftligen anmodats därtill, ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("inom femton (15) dagar")
                              .withTag("Reaktion")
                        )
                      .test();


                 new ClassificationTester("13.3 Sedan reklamation enligt denna bestämmelse skett ska Leverantören"+
                                     " utan dröjsmål avhjälpa felet eller bristen. Om Leverantören inte snar"+
                                     "ast, dock senast inom femton (15) arbetsdagar, från att reklamationen "+
                                     "skett har avhjälpt felet eller bristen får Las Vegas fastställa en slu"+
                                     "tlig frist inom vilket avhjälpandet ska ha skett. Kvarstår fel eller b"+
                                     "rist efter denna frists utgång får Las Vegas, efter eget val: (a) göra"+
                                     " ett mot felets eller bristens värde svarande avdrag från Leverantören"+
                                     "s ersättning eller, om betalning redan har skett, kräva motsvarande be"+
                                     "lopp åter från Leverantören; eller (b) på Leverantörens bekostnad låta"+
                                     " fullgöra Uppdraget och/eller avhjälpa felet med hjälp av en annan lev"+
                                     "erantör; eller c) häva Avtalet eller ifrågavarande Avropsavtal, varvid"+
                                     " Las Vegas har rätt till ersättning för all skada som uppstått på grun"+
                                     "d av felet eller bristen.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("inom femton (15) arbetsdagar")
                              .withTag("Reaktion")
                        )
                      .test();


                 new ClassificationTester("15.8 Las Vegas ska betala Leverantörens fakturor senast trettio (30) d"+
                                     "agar efter att de har mottagits av behörig befattningshavare hos Las V"+
                                     "egas. Leverantören äger inte rätt att utfärda samlingsfakturor.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("senast trettio (30) dagar")
                              .withTag("Reaktion")
                        )
                      .test();


                  /*

                                  new ClassificationTester("12.4 För det fall Uppdrag eller del därav är försenat av omständighet "+
                                     "som anges i punkt 12.3 ovan med mer än sextio (60) dagar får Las Vegas"+
                                     " häva ifrågavarande Avropsavtal med omedelbar verkan. Las Vegas är där"+
                                     "vid berättigat till ersättning för all skada som drabbar Las Vegas med"+
                                     " anledning härav.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("...")
                              .withTag("Reaktion")
                        )
                      .test();


                 new ClassificationTester("20.3 Varar sådan omständighet som avses i denna bestämmelse i över sex"+
                                     "tio (60) dagar äger den icke förhindrade Parten rätt att skriftligen h"+
                                     "äva Avropsavtalet utan att motparten har rätt till ersättning härför. "+
                                     "")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("...")
                              .withTag("Reaktion")
                        )
                      .test();



                  */


                 new ClassificationTester("15.5 Begäran om prisjustering i enlighet med punkten 15.4 ovan ska ske"+
                                     " skriftligen minst en (1) månad före ikraftträdandet. Motparten ska in"+
                                     "om två (2) veckor från mottagandet av begäran om prisjustering skriftl"+
                                     "igen bekräfta dess överenstämmelse med avtalade prisjusteringsvillkor."+
                                     " Det nya timpriset får tillämpas först från och med påföljande kalende"+
                                     "rmånadsskifte. Vid fastställande av nytt pris ska det nya priset allti"+
                                     "d avrundas nedåt till närmast hela krontal. Överenskommelse om prisjus"+
                                     "tering ska för att vara giltig vara skriftlig och behörigen underteckn"+
                                     "ad av båda Parter. I överenskommelsen ska alltid anges den aktuella pr"+
                                     "isjusteringen och datum för när prisjusteringen träder i kraft. ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("en (1) månad före")
                              .withTag("Reaktion")
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
                          .withClassifier(new NumberExpressionClassifierSV())
                          .withClassifier(new DeadlineClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Deadline, 1)
                              .withPattern("inom 24 timmar")
                              .withTag("Reaktion")
                        )
                      .test();




             } catch (Exception e) {
                  e.printStackTrace(System.out);
                  assertTrue(false);
             }
          }


}
