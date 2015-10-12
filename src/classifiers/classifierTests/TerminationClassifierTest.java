package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.TerminationClassifierEN;
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


public class TerminationClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    /***********************************************************
     *
     *
     *
     */

    @Test
    public void basicTestExamples(){

        try {

            new ClassificationTester("Party may terminate this contract at any time for any reason by giving at " +
                    "least thirty (30) days notice in writing to the [Other Party]. Such termination shall not be deemed " +
                    "a breach of contract. [Party] agrees to pay the [Other Party] for all unpaid invoices and uncompensated " +
                    "staff time and expenses up to the date of termination. ")
                     .withParser(englishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierEN())
                     .withClassifier(new DefinitionUsageClassifierEN())
                     .withClassifier(new TerminationClassifierEN())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                             .withTag("Convenience")
                     )
                 .test();



            new ClassificationTester("Utöver vad som anges i punkt ovan, äger Kunden rätt att helt eller delvis säga upp detta Avtal till förtida upphörande per sådan tidpunkt som Kunden bestämmer, dock senast sex (6) månader efter uppsägningen, i följande fall:")
                    .withParser(swedishParser)
                    .withHeadline("...")
                    .withProject(mockProject, mockDocument)
                    .withClassifier(new NumberClassifierSV())
                    .withClassifier(new DefinitionUsageClassifierSV())
                    .withClassifier(new TerminationClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 2)
                            .withTag("Convenience")
                            .withPattern("förtida upphörande")
                    )
                .test();


            new ClassificationTester("Part har rätt att häva avtalet om....")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new TerminationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                             .withTag("")
                     )
                 .test();

            new ClassificationTester("Part har rätt att omedelbart häva avtalet om den andra parten i väsentligt hänseende brutit mot avtalet.")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new TerminationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                             .withTag("Breach")
                     )
                 .test();


                new ClassificationTester("Hävning")
                         .withParser(swedishParser)
                         .withHeadline("Hävning")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new TerminationClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                         )
                     .test();




        } catch (Exception e) {
             e.printStackTrace();
             assertTrue(false);
        }
     }

    /***********************************************************
        *
        *      Testing Classification by examples for tag #TERMINATION
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("vii) Part äger rätt att säga upp Avtalet"+
                                                " eller del därav i förtid enligt vad som"+
                                                " anges i punkt 24 eller i övrigt i Avtal"+
                                                "et.")
                           .withParser(swedishParser)
                           .withHeadline("vii) Part äger rätt att säga upp Avtalet eller del därav i förtid enligt vad som anges i punkt 24 eller i övrigt i Avtalet.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Avtalet kan sägas upp till förtida upphö"+
                                                "rande i enlighet vad som anges i punkten"+
                                                " 24 nedan.")
                           .withParser(swedishParser)
                           .withHeadline("3. Avtalshandlingar")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("25. Avveckling vid Avtalets upphörande")
                           .withParser(swedishParser)
                           .withHeadline("25. Avveckling vid Avtalets upphörande")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("20.2 Leverantören ska ha god kunskap om "+
                                                "de Lagkrav som styr EHM:s verksamhet. De"+
                                                "tta gäller i synnerhet regler kring beha"+
                                                "ndling av känsliga personuppgifter. Leve"+
                                                "rantören får inte vidta åtgärd som inneb"+
                                                "är eller kan innebära åsidosättande av l"+
                                                "ag, strida mot Lagkrav, handelsbruk elle"+
                                                "r annan sedvänja i den bransch och det a"+
                                                "vtalsområde där Leverantören är verksam."+
                                                " Leverantören förbinder sig att av under"+
                                                "leverantör avkräva motsvarande utfästels"+
                                                "e. Skulle Leverantören brista i fullgöra"+
                                                "ndet av detta åtagande äger EHM rätt att"+
                                                " säga upp Avtalet till omedelbart upphör"+
                                                "ande.")
                           .withParser(swedishParser)
                           .withHeadline("20.2 Leverantören ska ha god kunskap om de Lagkrav som styr EHM:s verksamhet. Detta gäller i synnerhet regler kring behandling av känsliga personuppgifter. Leverantören får inte vidta åtgärd som innebär eller kan innebära åsidosättande av lag, strida mot Lagkrav, handelsbruk eller annan sedvänja i den bransch och det avtalsområde där Leverantören är verksam. Leverantören förbinder sig att av underleverantör avkräva motsvarande utfästelse. Skulle Leverantören brista i fullgörandet av detta åtagande äger EHM rätt att säga upp Avtalet till omedelbart upphörande.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("24. Förtida upphörande av avtalet.")
                           .withParser(swedishParser)
                           .withHeadline("24. Förtida upphörande av Avtalet")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
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
        *      Testing Classification by examples for tag #TERMINATION
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {
                  new ClassificationTester("Swedbank reserves the right to alter, am"+
                                                "end, delete or add to, in whole or in pa"+
                                                "rt, any terms or provisions of this RFP."+
                                                " Swedbank reserves the right to cancel t"+
                                                "his RFP at any time for any reason or fo"+
                                                "r no reason. ")
                           .withParser(englishParser)
                           .withHeadline("4.6  Treatment of Proposal")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new TerminationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                                   .withTag("")
                                   .withPattern("cancel this RFP")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

    /***********************************************************
        *
        *      Testing Classification by examples for tag #TERMINATION
        *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testLVExamples(){
           try {
              // Found 13 re-classifications

                  new ClassificationTester("14.8 Köparen har rätt att säga upp Leverantörens hosting inklusive s"+
                                      "upport i respektive Avropsavtal med iakttagande av en uppsägningstid o"+
                                      "m tre (3) månader. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Köparen har rätt att säga upp")

                         )
                       .test();


                  new ClassificationTester("8.3 Detta Avropsavtal kan sägas upp till förtida upphörande i enlighet"+
                                      " med bestämmelserna i Ramavtalet.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("kan sägas upp")

                         )
                       .test();



                  new ClassificationTester("Part har rätt att säga upp detta Ramavtal och/eller Avropsavtal m"+
                                      "ed omedelbar verkan om:")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new PartyUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Part har rätt att säga upp")

                         )
                       .test();


                  new ClassificationTester("Köparen har även rätt att säga upp Avropsavtal och/eller detta "+
                                      "Ramavtal med omedelbar verkan och/eller i förtid om Las Vegass verksam"+
                                      "het förändras genom lag eller myndighetsbeslut eller om Las Vegas är f"+
                                      "örhindrad genom lag eller myndighetsbeslut att fullgöra sina skyldighe"+
                                      "ter enligt detta Avtal.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Köparen har även rätt att säga upp")
                         )
                       .test();


                  new ClassificationTester("6.1.5 Leverantören ska kunna tillhandahålla personer med den kompetens"+
                                      " och erfarenhet som framgår av Bilaga 1. I respektive Avropsavtal ska "+
                                      "anges vilken/vilka person(er) som ska medverka i respektive Uppdrag. L"+
                                      "everantören ska i första hand alltid erbjuda det arbetsteam som presen"+
                                      "terats i Bilaga 1. I arbetsteamet ska minst en projektledare, en produ"+
                                      "ktionsledare och en manusförfattare/innehållsansvarig ingå. Om Leveran"+
                                      "tören inte kan ställa angivet arbetsteam eller däri ingående personer "+
                                      "till förfogande för ett Uppdrag, och Las Vegas inte godkänt annat arbe"+
                                      "tsteam eller däri ingående personer, äger Köparen rätt att säga upp "+
                                      "det aktuella Avropsavtalet till omedelbart upphörande.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Köparen rätt att säga upp")

                         )
                       .test();


                  new ClassificationTester("Varar sådan omständighet som avses i denna bestämmelse i över sex"+
                                      "tio (60) dagar äger den icke förhindrade Parten rätt att skriftligen h"+
                                      "äva Avropsavtalet utan att motparten har rätt till ersättning härför. "+
                                      "")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("rätt att skriftligen häva")
                         )
                       .test();



                  new ClassificationTester("27.2 Om det vid Ramavtalets upphörande finns gällande Avropsavtal ska "+
                                      "dessa fortsätta att gälla under den avtalstid som följer av respektive"+
                                      " Avropsavtal och Ramavtalet ska således vara fortsatt tillämpligt avse"+
                                      "ende sådant Uppdrag. Någon automatisk förlängning av Avropsavtal ska d"+
                                      "ock inte ske, om inte Parterna särskilt överenskommer härom. Om Ramavt"+
                                      "alet sägs upp till förtida upphörande enligt någon av punkterna nedan "+
                                      "ska dock Part ha rätt att samtidigt säga upp samtliga under Ramavtalet"+
                                      " ingångna Avropsavtal till förtida upphörande.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Ramavtalets upphörande")

                         )
                       .test();


                  new ClassificationTester("Uppsägning av Avropsavtal och/eller detta Ramavtal ska ske skrift"+
                                      "ligen.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Uppsägning av Avropsavtal")

                         )
                       .test();


                  new ClassificationTester("27.3 Avropsavtal gäller under den avtalstid och med den uppsägningstid"+
                                      " som följer av respektive Avropsavtal. Därutöver kan ett Avropsavtal s"+
                                      "ägas upp till förtida upphörande enligt bestämmelserna nedan. Uppsägni"+
                                      "ng av ett enskilt Avropsavtal innebär inte uppsägning av detta Ramavta"+
                                      "l eller annat Avropsavtal, förutom om ett Avropsavtal sägs upp till fö"+
                                      "rtida upphörande enligt någon av punkterna nedan i vilket fall Part sk"+
                                      "a ha rätt att samtidigt säga upp Ramavtalet och samtliga under Ramavta"+
                                      "let ingångna Avropsavtal.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("kan ett Avropsavtal sägas upp")
                         )
                       .test();



                  new ClassificationTester(
                                      "Köparen har rätt att säga upp Leverantörens ansvar för hosting inklusi"+
                                      "ve support enligt ovan med iakttagande av en uppsägningstid om tre (3)"+
                                      " månader. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TerminationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMINATION, 1)
                               .withPattern("Köparen har rätt att säga upp")

                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace(System.out);
                   assertTrue(false);
              }
           }

}
