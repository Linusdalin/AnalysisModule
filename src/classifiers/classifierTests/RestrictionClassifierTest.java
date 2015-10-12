package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.RestrictionClassifierEN;
import classifiers.swedishClassifiers.*;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Restriction Criteria tests
 *
 */


public class RestrictionClassifierTest extends AnalysisTest {



    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #RESTRICTION
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){

           try {



                  new ClassificationTester("— shall not be disclosed to any third party without prior written authoris"+
                                                "ation from Swedbank; and")
                           .withParser(englishParser)
                           .withHeadline("— shall not be disclosed to any third party without prior written authorisation from Swedbank; and")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new RestrictionClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("— shall not to be used for any pur"+
                                                "pose other than responding to this RFP; "+
                                                "")
                           .withParser(englishParser)
                           .withHeadline("— shall not to be used for any purpose other than responding to this RFP; ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new RestrictionClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Furthermore, the supplier, by submitting"+
                                                " a response, agrees that it will not cla"+
                                                "im damages, for whatever reason, relatin"+
                                                "g to the RFP or in respect of the compet"+
                                                "itive process that may be relevant. ")
                           .withParser(englishParser)
                           .withHeadline("4  RFP TERMS AND CONDITIONS")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new RestrictionClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /*******************************************************************************************
     *
     *          Examples from LV project
     *
     *
     *
     */

    @Test
      public void testLVExamples(){


          try {

                 new ClassificationTester("2.5  För det fall att registrerad, Datainspektion eller tredje man beg"+
                                     "är information från Personuppgiftsbiträdet som rör behandling av perso"+
                                     "nuppgifter skall Personuppgiftsbiträdet hänvisa till Las Vegas. Av pun"+
                                     "kten 2.2 ovan och punkten 4.1 nedan följer bland annat att Part "+
                                     "inte får lämna ut personuppgifter eller annan information "+
                                     "om behandlingen av personuppgifter utan uttrycklig instruktion från La"+
                                     "s Vegas.")
                          .withParser(englishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new PartsClassifierSV())
                          .withClassifier(new RestrictionClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                              .withPattern("Part inte får")

                        )
                      .test();


                 new ClassificationTester("2.4 Part får endast använda sig av underbiträden för"+
                                     " behandling av Las Vegass personuppgifter, i den mån Las Vegas har skr"+
                                     "iftligen godkänt detta i förväg. Personuppgiftsbiträdet ska hålla Las "+
                                     "Vegas löpande informerad om användning av underbiträden. Om godkännand"+
                                     "e till användning av underbiträde ges, ger Las Vegas Personuppgiftsbit"+
                                     "rädet mandat att ingå underbiträdesavtal med underbiträden som ska inn"+
                                     "ehålla villkor motsvarande de villkor som framgår av detta Avtal (inkl"+
                                     "usive Las Vegass revisionsrätt enligt Avtalet).   ")
                          .withParser(englishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new PartsClassifierSV())
                          .withClassifier(new RestrictionClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                              .withPattern("Part får endast")

                        )
                      .test();


              new ClassificationTester("Part äger heller inte rätt att anlita underleverantör som är "+
                                              "föremål för omständigheter som redovisas i detta avsnitt.")
                                   .withParser(englishParser)
                                   .withHeadline(" add headline...")
                                   .withProject(mockProject, mockDocument)
                                   .withClassifier(new NumberClassifierSV())
                                   .withClassifier(new DefinitionUsageClassifierSV())
                                   .withClassifier(new PartsClassifierSV())
                                   .withClassifier(new RestrictionClassifierSV())
                                   .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                       .withPattern("Part äger heller inte rätt")

                                 )
                               .test();

              new ClassificationTester("7.1 Allt arbete som utförs av Leverantören under detta Avropsavtal ska"+
                                                " anses ingå i den ersättning som anges i punkt 6 ovan. Anser Leverantö"+
                                                "ren att visst arbete faller utanför detta Avropsavtals tillämpningsomr"+
                                                "åde ska Leverantören lämna särskild offert avseende detta till Las Veg"+
                                                "as för godkännande. Sådan offert ska baseras på och vara prissatt i li"+
                                                "nje med de överenskomna ersättningsnivåer som framgår av Ramavtalet oc"+
                                                "h detta Avropsavtal. Sådant arbete ska endast utföras efter det att La"+
                                                "s Vegas skriftligen godkänt offerten och Parterna därmed avtalat särsk"+
                                                "ilt om utförandet av arbetet. Om Leverantören likväl utför arbete utan"+
                                                "för detta Avropsavtals tillämpningsområde, äger Part inte rätt"+
                                                " till särskild ersättning för sådant arbete.")
                                     .withParser(swedishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartsClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                             .withPattern("äger Part inte rätt")

                                     )
                                 .test();



                            new ClassificationTester("9.1 Las Vegas har rätt att, med två (2) veckors varsel, när som helst,"+
                                                " helt eller delvis, avbeställa ej utförda delar av Uppdrag. Sker sådan"+
                                                " avbeställning ska Las Vegas utge ersättning till Leverantören för red"+
                                                "an utfört arbete. Leverantören har även rätt till ersättning i skälig "+
                                                "omfattning för direkta kostnader som Leverantören haft med anledning a"+
                                                "v Las Vegass avbeställning, om sådana kostnader inte skäligen borde ha"+
                                                " kunnat undvikas. Säljaren har dock ingen rätt till ersättning för"+
                                                " sådana direkta kostnader om Las Vegass avbeställning beror på att Lev"+
                                                "erantören vid sitt fullgörande av Uppdrag har gjort sig skyldigt till "+
                                                "fel eller försummelse.")
                                     .withParser(swedishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                    .withClassifier(new PartsClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                             .withPattern("Säljaren har dock ingen rätt")

                                     )
                                 .test();


                            new ClassificationTester("Part får inte utan den andra Partens skriftliga tillstånd göra pressut"+
                                                "talanden eller annat offentligt uttalande med anledning av Avtalet ell"+
                                                "er på annat sätt utnyttja den andra Partens namn eller logotyp vid sin"+
                                                " marknadsföring.")
                                     .withParser(swedishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                    .withClassifier(new PartsClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                             .withPattern("Part får inte")

                                     )
                                 .test();

                            new ClassificationTester("8.3 Uppdrag, har anspråk på sådan nu sagd ytterligare ersättning ska L"+
                                                "everantören skriftligen meddela Las Vegas detta. Har Part inte"+
                                                ", innan utförande av det ändrade Uppdraget påbörjas, specificerat den "+
                                                "merkostnad som ändringen medför samt framställt anspråk om kompensatio"+
                                                "n därför, ska ytterligare ersättning för det ändrade Uppdraget inte ut"+
                                                "gå.")
                                     .withParser(swedishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                    .withClassifier(new PartsClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                             .withPattern("Har Part inte , innan utförande av det ändrade Uppdraget påbörjas , specificerat")

                                     )
                                 .test();


                            new ClassificationTester("16.2 Las Vegas upplåter genom Avtalet en icke-exklusiv rätt till Lever"+
                                                "antören att nyttja Materialet begränsat till vad som fordras för Lever"+
                                                "antörens fullgörande av Uppdrag. Inga immateriella rättigheter i övrig"+
                                                "t i anledning av Uppdrag tillkommer sålunda Leverantören och ej heller"+
                                                " eventuella andra rättigheter om så inte uttryckligen har specificerat"+
                                                "s i respektive Avropsavtal. Säljaren äger följaktligen inte rätt a"+
                                                "tt utnyttja vare sig Materialet eller Resultatet för annat ändamål än "+
                                                "Uppdrags utförande, om inte annat överenskommits skriftligen mellan Pa"+
                                                "rterna.")
                                     .withParser(swedishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                    .withClassifier(new PartsClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                             .withPattern("Säljaren äger följaktligen inte rätt")

                                     )
                                 .test();



             } catch (Exception e) {
                  e.printStackTrace(System.out);
                  assertTrue(false);
             }
          }

}
