package classifiers.classifierTests;

import classifiers.englishClassifiers.RiskClassifierEN;
import classifiers.swedishClassifiers.*;
import document.AbstractProject;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Risk tests
 *
 */


public class RiskClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    @Test
    public void biasTest(){


        try {

            /*

            new ClassificationTester("Tjänster som efterfrågas i denna upphandling ska kunna avropas som pris per timma, per dag, " +
                    "per vecka, per månad eller som fast pris. Vilken prissättning som är bäst lämpad för det specifika uppdraget " +
                    "beslutas av uppdragsgivaren vid varje uppdrag. När pris begärs på annat än enbart timmar ska timpriset framgå.")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new RiskClassifierSE())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Risk, 1)
                             .withTag("Bias")
                             .withPattern("beslutas av uppdragsgivaren")
                     )
                 .test();

            */

            new ClassificationTester("Säljaren äger rätt säga upp")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new RiskClassifierSE())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Risk, 1)
                             .withTag("Non-reciprocal")
                             .withPattern("Säljaren äger rätt")
                     )
                 .test();




            new ClassificationTester("(iii)  Om det är överenskommet att Säljaren ska hantera Tredjemanskravet ska detta ske genom av Köparen godkänt ombud, " +
                    "och Köparen åtar sig att, på Säljarens bekostnad, i skälig utsträckning biträda Säljaren vid hanteringen av kravet. " +
                    "Säljaren åtar sig att inhämta Köparens skriftliga medgivande i förväg avseende samtliga åtgärder som vidtas med anledning av Tredjemanskravet - " +
                    "sådant medgivande från Köparen ska inte oskäligen förvägras eller fördröjas. Köparen förbehåller sig rätten att när som helst träda in och, " +
                    "helt eller delvis, överta hanteringen av Tredjemanskravet från Säljaren.")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new RiskClassifierSE())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Risk, 1)
                             .withTag("Non-reciprocal")
                             .withPattern("Köparen förbehåller sig rätten")
                     )
                 .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



            new ClassificationTester("Deliver a system meeting the highest security standard")
                    .withParser(englishParser)
                    .withClassifier(new RiskClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Risk, 1)
                            .withPattern("highest")
                            .withTag("Scope"))


                    .test();




    }



    @Test
    public void testPenaltyRisk(){


        try {


            new ClassificationTester("If no protective order is obtained and the receiving party has not received a waiver hereunder before one (1) business day prior to the time the recipient must disclose Confidential Information or else stand liable for contempt or suffer other sanction or penalty, the receiving Party may disclose to the minimum extent legally required the requested Confidential Information.")
                    .withParser(englishParser)
                    .withClassifier(new RiskClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Risk, 1)
                            .withPattern("penalty")
                            .withTag("Penalty Risk"))


                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



    }

    /*



     */




    @Test
    public void testUnclear(){


        try {


            new ClassificationTester("\"Resultatet\"avser samtliga de resultat och allt material, oavsett lagrings- eller leveranssätt, som " +
                    "Leverantören själv tagit fram eller låtit ta fram vid fullgörandet av ett Uppdrag, såsom men inte begränsat till:")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("inte begränsat till"))

                    .test();

            new ClassificationTester("Följa tillämpliga blablprocesser")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("tillämpliga blablprocesser"))

                    .test();


            new ClassificationTester("I varje Avropsavtal ska specificeras vilka uppgifter som ingår i respektive Uppdrag. Leverantören har ett helhetsansvar för hela Uppdraget")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("helhetsansvar"))

                    .test();


            new ClassificationTester("I den mån det är förenligt med LOU äger Las Vegas rätt att, efter skriftligt meddelande och med rimlig framförhållning, under hand ändra ett Uppdrag, dock ska vid väsentlig förändring Leverantörens medgivande först inhämtas. Leverantören ska inte oskäligen och/eller utan att ange sakliga skäl underlåta att lämna sådant medgivande.\n")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("rimlig framförhållning")
                            .withTag("Response"))

                    .test();


            new ClassificationTester("I den mån sådan ändring av Uppdrag medför merkostnader för Leverantören har Leverantören rätt till skälig kompensation för detta. För det fallet att Leverantören, på grund av ändring av\n")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("skälig kompensation"))

                    .test();


            new ClassificationTester("Leverantören ansvarar för att snarast åtgärda de brister och/eller avvikelser som påtalats av Las Vegas och som föranlett underkännande. Leverantören ska därefter överlämna reviderat Resultat till Las Vegas för nytt godkännande. Om åtgärdandet av de brister och/eller avvikelser som påtalats medför att Uppdrag inte kan genomföras i tid ska Leverantören utbetala vite enligt punkt 22 nedan.")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("snarast åtgärda")
                            .withTag("Responstid"))

                    .test();



            new ClassificationTester("Sedan reklamation enligt denna bestämmelse skett ska Leverantören utan dröjsmål avhjälpa felet eller bristen.")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("utan dröjsmål"))

                    .test();

            new ClassificationTester("Utöver vad som anges ovan har Kunden alltid rätt att omedelbart häva avtalet om Leverantören lämnat oriktiga uppgifter i " +
                    "anbudet eller på annat sätt i samband med upphandlingen och dessa uppgifter inte varit av oväsentlig betydelse för tecknandet av avtalet.")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("inte varit av oväsentlig betydelse"))

                    .test();

            new ClassificationTester("Kunden har även rätt att häva avtalet vid försenad leverans om förseningen är av väsentlig betydelse för Kunden och Leverantören insett eller bort inse detta utan att Leverantören vidtar tillräckliga åtgärder.")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("bort inse"))

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



    }


    @Test
    public void testOther(){

        try {

            new ClassificationTester("Skall följa god sedvänja")
                    .withParser(swedishParser)
                    .withClassifier(new UnspecificClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withPattern("sedvänja"))

                    .test();

            new ClassificationTester("Skall ombesörja deponering källkod")
                    .withParser(swedishParser)
                    .withClassifier(new RiskClassifierSE())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Risk, 1)
                            .withPattern("deponering källkod"))

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



    }




    /***********************************************************
     *
     *      Testing Classification by examples for tag #UNSPECIFIC
     *      Document:   " Avtal avseende IT-drift"
     *      Language:   "SV"
     *
     */


    @Test
    public void testAvtalavseendeITdriftExamples(){
        try {
               new ClassificationTester("10.1.1 Leverantören åtar sig att för ful"+
                                             "lgörande av avtalat åtagande använda erf"+
                                             "orderligt antal kvalificerade och lämpli"+
                                             "ga personer som uppfyller de krav som st"+
                                             "älls i Avtalet. Således")
                        .withParser(swedishParser)
                        .withHeadline("10.1.1 Leverantören åtar sig att för fullgörande av avtalat åtagande använda erforderligt antal kvalificerade och lämpliga personer som uppfyller de krav som ställs i Avtalet. Således")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("Scope")
                      )
                    .test();


               new ClassificationTester("23.4 Det åligger Part som vill åberopa f"+
                                             "orce majeure att omgående och skriftlige"+
                                             "n underrätta andra Parten härom, med ang"+
                                             "ivande av i vilken omfattning denne mena"+
                                             "r att denna punkt 23 är tillämplig och u"+
                                             "ppskattad varaktighet för hindrande omst"+
                                             "ändighet. Motsvarande underrättelse ska "+
                                             "ske då hindret har övervunnits.")
                        .withParser(swedishParser)
                        .withHeadline("23.4 Det åligger Part som vill åberopa force majeure att omgående och skriftligen underrätta andra Parten härom, med angivande av i vilken omfattning denne menar att denna punkt 23 är tillämplig och uppskattad varaktighet för hindrande omständighet. Motsvarande underrättelse ska ske då hindret har övervunnits.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("9.3 Part ska etablera, vidmakthålla och "+
                                             "löpande utveckla en rationell och adekva"+
                                             "t samverkan till uppfyllande av Avtalet."+
                                             "")
                        .withParser(swedishParser)
                        .withHeadline("9.3 Part ska etablera, vidmakthålla och löpande utveckla en rationell och adekvat samverkan till uppfyllande av Avtalet.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("20.3 Leverantören ska på egen bekostnad "+
                                             "ombesörja, införskaffa och vidmakthålla "+
                                             "samtliga tillstånd, bemyndiganden, dispe"+
                                             "nser och licenser som enligt tillämpliga"+
                                             " författningar, normer eller föreskrifte"+
                                             "r erfordras för att Leverantören ska kun"+
                                             "na utföra sina åtaganden under Avtalet.")
                        .withParser(swedishParser)
                        .withHeadline("20.3 Leverantören ska på egen bekostnad ombesörja, införskaffa och vidmakthålla samtliga tillstånd, bemyndiganden, dispenser och licenser som enligt tillämpliga författningar, normer eller föreskrifter erfordras för att Leverantören ska kunna utföra sina åtaganden under Avtalet.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("")
                      )
                    .test();



               new ClassificationTester("vi) EHM äger rätt att innehålla betalnin"+
                                             "g till Leverantören så länge försening, "+
                                             "Fel eller annat avtalsbrott består med b"+
                                             "elopp som med hänsyn till förekommande f"+
                                             "örsening, Fel, eller avtalsbrott är skäl"+
                                             "igt; samt")
                        .withParser(swedishParser)
                        .withHeadline("vi) EHM äger rätt att innehålla betalning till Leverantören så länge försening, Fel eller annat avtalsbrott består med belopp som med hänsyn till förekommande försening, Fel, eller avtalsbrott är skäligt; samt")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("vii) avsätta erforderliga och adekvata r"+
                                             "esurser för uthålligt fungerande samverk"+
                                             "an med Leverantören i enlighet med punkt"+
                                             " 9 nedan; samt")
                        .withParser(swedishParser)
                        .withHeadline("vii) avsätta erforderliga och adekvata resurser för uthålligt fungerande samverkan med Leverantören i enlighet med punkt 9 nedan; samt")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("25.5 Vid en överföring till ny leverantö"+
                                             "r ska Leverantören säkerställa att EHM e"+
                                             "ller av denne utsedd leverantör i skälig"+
                                             " utsträckning får den tillgång som krävs"+
                                             " till system och lokaler där Tjänsten ut"+
                                             "förs. Leverantören är dock, inte skyldig"+
                                             " att avslöja sina företagshemligheter i "+
                                             "samband med sådan överföring. Kompetensö"+
                                             "verföring till ny leverantör är av störs"+
                                             "ta vikt. Leverantören ska medverka i utb"+
                                             "ildning och överlämnande av dokumentatio"+
                                             "n av processer och produktionsmetoder.")
                        .withParser(swedishParser)
                        .withHeadline("25.5 Vid en överföring till ny leverantör ska Leverantören säkerställa att EHM eller av denne utsedd leverantör i skälig utsträckning får den tillgång som krävs till system och lokaler där Tjänsten utförs. Leverantören är dock, inte skyldig att avslöja sina företagshemligheter i samband med sådan överföring. Kompetensöverföring till ny leverantör är av största vikt. Leverantören ska medverka i utbildning och överlämnande av dokumentation av processer och produktionsmetoder.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("ska den personal som används för produkt"+
                                             "ion och tillhandahållande av Tjänsten in"+
                                             "neha för uppgiften lämplig utbildning, e"+
                                             "rfarenhet och kvalifikationer i övrigt.")
                        .withParser(swedishParser)
                        .withHeadline("ska den personal som används för produktion och tillhandahållande av Tjänsten inneha för uppgiften lämplig utbildning, erfarenhet och kvalifikationer i övrigt.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("Scope")
                      )
                    .test();


               new ClassificationTester("26.5.2 Part är skyldig att utan dröjsmål"+
                                             " meddela den andra Parten sådana avvikel"+
                                             "ser som leder till, eller kan komma att "+
                                             "leda till, att avtalade villkor inte upp"+
                                             "fylls.")
                        .withParser(swedishParser)
                        .withHeadline("26.5.2 Part är skyldig att utan dröjsmål meddela den andra Parten sådana avvikelser som leder till, eller kan komma att leda till, att avtalade villkor inte uppfylls.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new UnspecificClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Unspecific, 1)
                            .withTag("Responstid")
                      )
                    .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }



}
