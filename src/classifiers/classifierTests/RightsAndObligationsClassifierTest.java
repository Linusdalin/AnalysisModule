package classifiers.classifierTests;

import classifiers.englishClassifiers.*;
import classifiers.swedishClassifiers.*;
import document.AbstractDefinition;
import document.AbstractDocument;
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


public class RightsAndObligationsClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

        init();

        mockDocument.addDefinition(new AbstractDefinition("Part1", 0));

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
                    .withClassifier(new RightsAndObligationsClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHTS_AND_OBLIGATIONS, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new RightsAndObligationsClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHTS_AND_OBLIGATIONS, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void testRights(){


        try {

            new ClassificationTester("Rättigheter")
                    .withParser(swedishParser)
                    .withHeadline("Rättigheter")
                    .withClassifier(new RightsClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("Rättigheter"))

                    .test();


            new ClassificationTester("Leverantör har möjlighet att anlita underleverantör för utförande av delar av uppdragen under förutsättning att denne redovisas i anbudet. Notera att vissa krav ska uppfyllas av samtliga företag.")
                    .withParser(swedishParser)
                    .withClassifier(new RightsClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("har möjlighet"))

                    .test();


            new ClassificationTester("Anbudsgivaren har inte rätt att utan Las Vegas skriftliga godkännande använda")
                    .withParser(swedishParser)
                    .withClassifier(new RestrictionClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                            .withPattern("har inte rätt"))

                    .test();


            new ClassificationTester("Leverantören ska i första hand alltid erbjuda de personer som presentaras i detta arbetsteam. Leverantören äger rätt att erbjuda andra personer än dessa endast under förutsättning att Leverantören kan visa att angivna personer inte finns tillgängliga vid tillfället för det enskilda uppdraget och orsaken till detta, eller att annan person är mer lämpad för det aktuella avropet.")
                    .withParser(swedishParser)
                    .withClassifier(new RightsClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("äger rätt"))

                    .test();


            new ClassificationTester("Leverantören äger inte rätt att höja priserna i Bilaga 2 under den Initiala Avtalstiden.")
                    .withParser(swedishParser)
                    .withClassifier(new RestrictionClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                            .withPattern("äger inte rätt"))

                    .test();


            new ClassificationTester("\"Uppdrag\"avser de tjänster som Las Vegas har rätt att beställa")
                    .withParser(swedishParser)
                    .withClassifier(new RightsClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("har rätt"))

                    .test();


            new ClassificationTester("Leverantören äger inte rätt att utan Las Vegass skriftliga medgivande byta ut sådan person som enligt Avropsavtal ska utföra visst Uppdrag, eller delar av Uppdrag.")
                    .withParser(swedishParser)
                    .withClassifier(new RestrictionClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                            .withPattern("äger inte rätt"))

                    .test();

            AbstractDocument avtal = mockProject.documents.get(0);
            avtal.addDefinition(new AbstractDefinition("Las Vegas", 0));


            new ClassificationTester("I den mån det är förenligt med LOU äger Las Vegas rätt att, efter skriftligt meddelande och med rimlig framförhållning, under hand ändra ett Uppdrag, dock ska vid väsentlig förändring Leverantörens medgivande först inhämtas. Leverantören ska inte oskäligen och/eller utan att ange sakliga skäl underlåta att lämna sådant medgivande.")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionUsageClassifierSV())
                    .withClassifier(new RightsClassifierSV())
                    .withProject(mockProject, avtal)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("äger Las Vegas rätt"))

                    .test();


            new ClassificationTester("Las Vegas ska betala Leverantörens fakturor senast trettio (30) dagar efter att de har mottagits av behörig befattningshavare hos Las Vegas. Leverantören äger inte rätt att utfärda samlingsfakturor.")
                    .withParser(swedishParser)
                    .withClassifier(new RestrictionClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                            .withPattern("äger inte rätt"))

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



    }


    @Test
    public void testRightsEN(){


        try {


            new ClassificationTester("Ensure Part1 the rights")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionUsageClassifierEN())
                    .withClassifier(new RightsClassifierEN())
                    .withProject(mockProject, mockDocument)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("Ensure Part1 the rights"))

                    .test();

            new ClassificationTester("the Buyer is entitled to payment")
                    .withParser(englishParser)
                    .withClassifier(new RightsClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("is entitled to"))

                    .test();


            new ClassificationTester("If Part1 decides to use the optimized version then Part1 must have full and unrestricted access to the optimizations of the source code, including redistribution rights")
                    .withParser(englishParser)
                    .withClassifier(new RightsClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("unrestricted access"))

                    .test();

            new ClassificationTester("If RIT cancels the agreement according to this clause, RIT has the right to repayment of the paid part of the purchase amount including interest corresponding to the current reference rate.")
                    .withParser(englishParser)
                    .withClassifier(new RightsClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("has the right"))

                    .test();

            new ClassificationTester("Ownership and right of use")
                    .withParser(englishParser)
                    .withClassifier(new RightsClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("right of use"))

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



    }





    /***********************************************************
    *
    *      Testing Classification by admin @2015-01-29
    *      Document:   "Anbudsförfrågan IT-drift 2014.docx"
    *      FragmentNo: 52
    *      Body: "Anbud på annat sätt än enligt ovan, t.ex. per e-post eller fax, godtas inte. Dock kan EHM komma att begära att eventuella förtydliganden eller kompletteringar ska skickas via e-post.\n"
    *
    */
   @Test
   public void testRightsClassifierSV(){
       try {

              new ClassificationTester("Anbud på annat sätt än enligt ovan, t.ex"+
                                            ". per e-post eller fax, godtas inte. Doc"+
                                            "k kan Part1 komma att begära att eventuell"+
                                            "a förtydliganden eller kompletteringar s"+
                                            "ka skickas via e-post.\n")
                       .withParser(swedishParser)
                       .withHeadline("2.6 Inlämnade av anbud")
                       .withClassifier(new DefinitionUsageClassifierSV())
                       .withClassifier(new RightsClassifierSV())
                       .withProject(mockProject, mockDocument)
                       .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("kan Part1 komma att")
                               .withTag("")
                       )
                   .test();


           new ClassificationTester("Anbud på annat sätt än enligt ovan, t.ex"+
                                         ". per e-post eller fax, godtas inte. Doc"+
                                         "k kommer Part1 att begära att eventuell"+
                                         "a förtydliganden eller kompletteringar s"+
                                         "ka skickas via e-post.\n")
                    .withParser(swedishParser)
                    .withHeadline("2.6 Inlämnade av anbud")
                    .withClassifier(new DefinitionUsageClassifierSV())
                    .withClassifier(new RightsClassifierSV())
                    .withProject(mockProject, mockDocument)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                            .withPattern("kommer Part1 att")
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
       *      Testing Classification by examples for tag #RIGHTS_AND_OBLIGATIONS
       *      Document:   "  Request for ProposalNew TMSMain Document"
       *      Language:   "EN"
       *
       */


      @Test
      public void testNewTMSMainDocumentExamples(){
          try {
                 new ClassificationTester("The Vendor is solely responsible for mak"+
                                               "ing sure that the response has been deli"+
                                               "vered in accordance with the terms and c"+
                                               "onditions identified in this RFP and sho"+
                                               "uld ensure that all of the documents ide"+
                                               "ntified in this section 3.8 are included"+
                                               ". ")
                          .withParser(englishParser)
                          .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new ResponsibilitiesClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                                  .withTag("")
                          )
                      .test();



                 new ClassificationTester("Swedbank accepts no responsibility for a"+
                                               "ny consequences based on its inability t"+
                                               "o answer a Vendors question.")
                          .withParser(englishParser)
                          .withHeadline("3.4  Questions and Answers")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new ResponsibilitiesClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("e. Swedbank responsibilities")
                          .withParser(englishParser)
                          .withHeadline("e. Swedbank responsibilities")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new ResponsibilitiesClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("It is the responsibility of the Vendor t"+
                                               "o obtain clarification of any details re"+
                                               "lating to this RFP. ")
                          .withParser(englishParser)
                          .withHeadline("3.4  Questions and Answers")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new ResponsibilitiesClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                                  .withTag("")
                          )
                      .test();


              new ClassificationTester("Swedbank also reserves the right to igno"+
                                                       "re from information given to a Vendor by"+
                                                       " other members of the New TMS than the R"+
                                                       "FP Coordinator. ")
                                  .withParser(englishParser)
                                  .withHeadline("3.2  Single point of contact")
                                  .withProject(mockProject, mockDocument)
                                  .withClassifier(new NumberClassifierEN())
                                  .withClassifier(new DefinitionUsageClassifierEN())
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                      .withTag("")
                                )
                              .test();


                         new ClassificationTester("  Swedbank does not bind itself to accep"+
                                                       "t the lowest price offered or indeed any"+
                                                       " proposal")
                                  .withParser(englishParser)
                                  .withHeadline("1.3  Evaluation")
                                  .withProject(mockProject, mockDocument)
                                  .withClassifier(new NumberClassifierEN())
                                  .withClassifier(new DefinitionUsageClassifierEN())
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                      .withTag("")
                                )
                              .test();


                         new ClassificationTester("Swedbank reserves the right to request c"+
                                                       "larifications to any aspects of a Vendor"+
                                                       "s proposal before and after the closing "+
                                                       "date. Where it considers information to "+
                                                       "be missing from a Vendors proposal Swedb"+
                                                       "ank reserves the right to ask for the mi"+
                                                       "ssing information or to perform the eval"+
                                                       "uation without the missing information.")
                                  .withParser(englishParser)
                                  .withHeadline("4.5  Clarifications and missing information")
                                  .withProject(mockProject, mockDocument)
                                  .withClassifier(new NumberClassifierEN())
                                  .withClassifier(new DefinitionUsageClassifierEN())
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                      .withTag("")
                                )
                              .test();


                         new ClassificationTester("Answers on questions or other informatio"+
                                                       "n from others than the RFP Coordinator c"+
                                                       "annot be considered by the vendor. If it"+
                                                       " becomes known that a Vendor is not resp"+
                                                       "ecting the single point of contact by co"+
                                                       "ntacting other relevant members of the T"+
                                                       "MS project, Swedbank reserves the right "+
                                                       "to decide that a Vendor will not be part"+
                                                       " of the procurement process further on. "+
                                                       "Swedbank will in that case inform the ac"+
                                                       "tual Vendor/s in writing.  ")
                                  .withParser(englishParser)
                                  .withHeadline("3.2  Single point of contact")
                                  .withProject(mockProject, mockDocument)
                                  .withClassifier(new NumberClassifierEN())
                                  .withClassifier(new DefinitionUsageClassifierEN())
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                      .withTag("")
                                )
                              .test();


                         new ClassificationTester("Swedbank makes no commitments that the R"+
                                                       "FP will result in any business transacti"+
                                                       "on with any Vendors or entity. The RFP i"+
                                                       "s not an offer by Swedbank, but may resu"+
                                                       "lt in a contract. Swedbank does not unde"+
                                                       "rtake to accept the lowest proposal, or "+
                                                       "part, or all of any proposal and submiss"+
                                                       "ion of any proposal shall not constitute"+
                                                       " any actual or implied agreement between"+
                                                       " Swedbank and the Vendor. Swedbank reser"+
                                                       "ves the right to accept any part, or all"+
                                                       ", of any proposal or proposals at its so"+
                                                       "le discretion.")
                                  .withParser(englishParser)
                                  .withHeadline("4.6  Treatment of Proposal")
                                  .withProject(mockProject, mockDocument)
                                  .withClassifier(new NumberClassifierEN())
                                  .withClassifier(new DefinitionUsageClassifierEN())
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                      .withTag("")
                                )
                              .test();


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
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                      .withTag("")
                                )
                              .test();


                         new ClassificationTester("Swedbank reserves the right to make an a"+
                                                       "ward to the best advantage of Swedbank, "+
                                                       "cost and other factors considered such a"+
                                                       "s:")
                                  .withParser(englishParser)
                                  .withHeadline("1.3  Evaluation")
                                  .withProject(mockProject, mockDocument)
                                  .withClassifier(new NumberClassifierEN())
                                  .withClassifier(new DefinitionUsageClassifierEN())
                                  .withClassifier(new RightsClassifierEN())
                                  .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
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
        *      Testing Classification by examples for tag #RIGHT
        *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testLVExamples(){
           try {
              // Found 26 re-classifications

                  new ClassificationTester("Säljaren ska ha rätt att, själv eller genom annan, genomföra en "+
                                      "revision (varmed avses såväl platsbesök som genomgång av skriftlig dok"+
                                      "umentation och genomförande av intervjuer etc.) hos Leverantören eller"+
                                      " dennes underleverantörer för att säkerställa att Avtalet efterlevs. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("Säljaren ska ha rätt")
                         )
                       .test();


                  new ClassificationTester("26.2 Utan hinder av vad som anges i punkt 26.1 ovan har Säljaren rätt"+
                                      " att utan särskilt medgivande från Leverantören överlåta Avtalet eller"+
                                      " rättigheter och skyldigheter i anledning därav till annat bolag inom "+
                                      "den koncern i vilken Las Vegas från tid till annan ingår, förutsatt at"+
                                      "t överlåtelsen är förenlig med lagstiftning och praxis gällande offent"+
                                      "lig upphandling.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("har Säljaren rätt")

                         )
                       .test();


                  new ClassificationTester("14.5 Vid eventuella fel eller brister i den aktuella interaktiva utbil"+
                                      "dningen, ska Leverantören åtgärda bristen inom åtta (8) timmar från de"+
                                      "t att felet/bristen rapporterades av Las Vegas eller upptäcktes av Lev"+
                                      "erantören. Åtgärdstiden räknas bara mellan kl. 06-24. Vid brist i avta"+
                                      "lad åtgärdstid har Köparen rätt till vite om tio (10) procent av mån"+
                                      "adsavgiften för hostingen av den aktuella utbildningen per påbörjad ti"+
                                      "mme utöver åtta (8) timmar, dock med ett sammanlagt högsta vite om hun"+
                                      "dra (100) procent av månadsavgiften för hostingen av den aktuella utbi"+
                                      "ldningen.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("har Köparen rätt")

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
                           .withClassifier(new PartsClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("Part äger , med undantag av det sagda , föra")

                         )
                       .test();


                  new ClassificationTester("12.4 För det fall Uppdrag eller del därav är försenat av omständighet "+
                                      "som anges i punkt 12.3 ovan med mer än sextio (60) dagar får Säljaren"+
                                      " häva ifrågavarande Avropsavtal med omedelbar verkan. Las Vegas är där"+
                                      "vid berättigat till ersättning för all skada som drabbar Las Vegas med"+
                                      " anledning härav.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("får Säljaren häva")

                         )
                       .test();



                  new ClassificationTester("16.1 Las Vegas ska, med full och fri förfoganderätt, utan någon som he"+
                                      "lst begränsning i tid, territorium eller i övrigt, ha full och exklusi"+
                                      "v äganderätt till Resultatet, om inte annat anges i respektive Avropsa"+
                                      "vtal. Följaktligen överlåter Leverantören, från tidpunkten då de uppko"+
                                      "mmer, samtliga rättigheter, såväl materiella som immateriella, till Re"+
                                      "sultatet och tillika alla vidareutvecklingar därav och varje rättighet"+
                                      " som kan uppkomma vid nyttjande av Resultatet, till Las Vegas. Leveran"+
                                      "tören har inte rätt till någon ytterligare ersättning än vad som stadg"+
                                      "as i Avtalet på grund av något Las Vegass nyttjande av Resultatet.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("ha full och exklusiv äganderätt")

                         )
                       .test();



                  new ClassificationTester("Detta innefattar bl.a. rätt, men inte skyldighet, för Säljaren att, i"+
                                      " förekommande fall på varje möjligt sätt skydda Resultatet och/eller v"+
                                      "arje annan innovation som kan härledas till eller ur Resultatet, samt "+
                                      "att fritt och utan begränsning, själv eller genom tredje part, ändra o"+
                                      "ch/eller vidareöverlåta Resultatet eller del därav till varje tredje m"+
                                      "an. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("rätt , men inte skyldighet , för , men inte skyldighet , Säljaren")   //TODO: Fix extraction here

                         )
                       .test();


                  new ClassificationTester("22.2 Utgivande av vite inverkar ej på Säljarens rätt att göra andra p"+
                                      "åföljder gällande i anledning av avtalsbrottet.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("inverkar ej på Säljaren på s rätt")       //TODO: Fix extraction here

                         )
                       .test();



                  new ClassificationTester("12.5 Om Leverantören vid upprepade tillfällen är försenad med Uppdrag "+
                                      "ska Säljaren ha rätt att säga upp Avtalet i sin helhet med omedelbar "+
                                      "verkan. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("ska Säljaren ha rätt")

                         )
                       .test();


                  new ClassificationTester("15.7 Ersättningen och överenskomna priser är angivna exklusive mervärd"+
                                      "esskatt men inkluderar övriga skatter och avgifter av vilket slag det "+
                                      "än må vara. För det fallet att Leverantören vid någon tidpunkt saknar "+
                                      "F-skattsedel förbehåller sig Säljaren rätten att från debiterat belop"+
                                      "p avräkna, eller kräva åter, vad som enligt lag, annan föreskrift elle"+
                                      "r myndighets beslut ankommer på Las Vegas att innehålla.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("förbehåller sig Säljaren sig rätten")             //TODO: Fix extraction here

                         )
                       .test();



                  new ClassificationTester("Säljaren garanterar att Resultatet är framtaget exklusivt för"+
                                      " Las Vegas och att ingen del därav, varken genom nyttjande eller vidar"+
                                      "eupplåtelse/överlåtelse, gör intrång i rättighet tillhörande tredje ma"+
                                      "n. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new WarrantyClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.WARRANTY, 1)
                               .withPattern("Säljaren garanterar")

                         )
                       .test();



                  new ClassificationTester("15.11  Vid försenad betalning får Köparen debitera dröjsmålsränta"+
                                      " i enlighet med räntelagen (1975:635). Leverantören får inte debi"+
                                      "tera faktureringsavgift, expeditionsavgift, eller andra liknande icke "+
                                      "avtalade tilläggsavgifter.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("får Köparen debitera")

                         )
                       .test();


                  new ClassificationTester("15.10  Las Vegass betalning av faktura utgör inte godkännande av Uppdr"+
                                      "ag. Till undvikande av missförstånd överenskommes särskilt att Las Veg"+
                                      "ass betalning av faktura inte innebär att Part frånsäger sig rätt"+
                                      "en att reklamera utfört arbete som är hänförligt till sådan betald fak"+
                                      "tura. ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new PartsClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("inte innebär att Part innebär att frånsäger")       //TODO: Fix extraction

                         )
                       .test();


                  new ClassificationTester("1.4  Säljaren förbehåller sig rätten att separat upphandla utbildning"+
                                      "stjänster vilka Las Vegas bedömer ligga utanför Ramavtalets tillämpnin"+
                                      "gsområde. Vidare förbehåller sig de bolag inom Las Vegas-koncernen som"+
                                      " inte omfattas av LOU (se punkten 1.5 nedan) att välja annan leverantö"+
                                      "r för de utbildningar som omfattas av Ramavtalet.   ")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("Säljaren förbehåller sig rätten")

                         )
                       .test();




                  new ClassificationTester("8.1 I den mån det är förenligt med LOU äger Säljaren rätt att, efter "+
                                      "skriftligt meddelande och med rimlig framförhållning, under hand ändra"+
                                      " ett Uppdrag, dock ska vid väsentlig förändring Leverantörens medgivan"+
                                      "de först inhämtas. Leverantören ska inte oskäligen och/eller utan att "+
                                      "ange sakliga skäl underlåta att lämna sådant medgivande.")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new RightsClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                               .withPattern("äger Säljaren rätt")

                         )
                       .test();




              } catch (Exception e) {
                   e.printStackTrace(System.out);
                   assertTrue(false);
              }
           }


}
