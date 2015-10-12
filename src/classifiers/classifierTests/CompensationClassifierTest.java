package classifiers.classifierTests;

import classifiers.englishClassifiers.CompensationClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.CompensationClassifierSV;
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


public class CompensationClassifierTest extends AnalysisTest {

    @BeforeClass
    public static void preAmble(){

        init();

    }


    /***********************************************************
       *
       *      Testing Risky price definitions
       *      Language:   "SV"
       *
       */


    @Test
    public void testCompensationRiskExamples(){



        try {

            new ClassificationTester("Priser inklusive skatter")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new CompensationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                             .withTag("Risk")
                     )
                 .test();

            new ClassificationTester("I ersättningen ingår skatter, inklusive källskatter.")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new CompensationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                             .withTag("Risk")
                     )
                 .test();

            new ClassificationTester("Priser är exklusive skatter")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new CompensationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                             .withTag("")
                     )
                 .test();

            new ClassificationTester("alltid utföra mindre ändringar i ett Uppdrag såsom exempelvis men inte uteslutande stavfel, buggar m.m., på Las Vegas anmodan, utan tillkommande kostnad")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new CompensationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                             .withTag("Risk")
                             .withPattern("utan tillkommande kostnad")
                     )
                 .test();


            new ClassificationTester("Leverantören får inte ändra priserna under hela avtalsperioden")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new CompensationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                             .withTag("Risk")
                             .withPattern("ändra priserna under hela avtalsperioden")
                     )
                 .test();



          } catch (Exception e) {
               e.printStackTrace();
               assertTrue(false);
          }
       }


    /***********************************************************
       *
       *      Testing Classification by examples for tag Price adjustment
       *      Document:   " Avtal avseende IT-drift"
       *      Language:   "SV"
       *
       */


      @Test
      public void testAvtalavseendeITdriftExamples(){

          try {
                 new ClassificationTester("13.5 Prisjusteringar")
                          .withParser(swedishParser)
                          .withHeadline("13.5 Prisjusteringar")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new CompensationClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                  .withTag("justering")
                          )
                      .test();


                 new ClassificationTester("19.3 Introducerar EHM nya säkerhetsbestä"+
                                               "mmelser eller ändringar i EHM:s säkerhet"+
                                               "spolicy i förhållande till vad som anges"+
                                               " i punkt 19.1 ovan, och detta föranleder"+
                                               " mer än obetydligt ändrade kostnader för"+
                                               " Leverantören, har Leverantören rätt til"+
                                               "l justering av ersättningen för Tjänsten"+
                                               ". Detta under förutsättning att Leverant"+
                                               "ören informerar EHM utan oskäligt dröjsm"+
                                               "ål och tillhandahåller EHM bevis för sin"+
                                               "a ökade kostnader samt ett förslag avsee"+
                                               "nde prisjustering med anledning av förän"+
                                               "dringen. Överenskommelse om justeringar "+
                                               "av priser ska träffas enligt Samverkansm"+
                                               "odellen. Detta ska dock inte påverka Lev"+
                                               "erantörens skyldighet att genomföra ändr"+
                                               "ingen även om Parterna inte överenskommi"+
                                               "t om prisjusteringen med anledning av än"+
                                               "dringen.")
                          .withParser(swedishParser)
                          .withHeadline("19.3 Introducerar EHM nya säkerhetsbestämmelser eller ändringar i EHM:s säkerhetspolicy i förhållande till vad som anges i punkt 19.1 ovan, och detta föranleder mer än obetydligt ändrade kostnader för Leverantören, har Leverantören rätt till justering av ersättningen för Tjänsten. Detta under förutsättning att Leverantören informerar EHM utan oskäligt dröjsmål och tillhandahåller EHM bevis för sina ökade kostnader samt ett förslag avseende prisjustering med anledning av förändringen. Överenskommelse om justeringar av priser ska träffas enligt Samverkansmodellen. Detta ska dock inte påverka Leverantörens skyldighet att genomföra ändringen även om Parterna inte överenskommit om prisjusteringen med anledning av ändringen.")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new CompensationClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                  .withTag("justering")
                          )
                      .test();

              new ClassificationTester("Leverantören svarar själv för kostnader och tidsåtgång för byte av personal " +
                                          "enligt ovan och för att ny personal ska sätta sig in i arbetet (inklusive men inte " +
                                          "begränsat till alla resurser och kostnader som krävs för att på ett fullständigt och " +
                                          "adekvat sätt utbilda ny personal avseende EHM:s verksamhet och behov).")
                       .withParser(swedishParser)
                       .withHeadline("13.5 Prisjusteringar")
                       .withProject(mockProject, mockDocument)
                       .withClassifier(new NumberClassifierSV())
                       .withClassifier(new DefinitionUsageClassifierSV())
                       .withClassifier(new CompensationClassifierSV())
                       .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                       )
                   .test();

              /*

               new MTClassification("#COMPENSATION", 0, 0, "Huvudavtal_EHM_IT_Drift - 2.docx", 117,
                               "10.2.3 ",
                               "Not set", "", "", "linus"),



               */


             } catch (Exception e) {
                  e.printStackTrace();
                  assertTrue(false);
             }
          }


    /***********************************************************
       *
       *      Testing Classification by examples for tag #COMPENSATION
       *      Document:   "Anbudsförfrågan IT-drift 2014.docx"
       *      Language:   "SV"
       *
       */


      @Test
      public void testAnbudsförfråganITdrift2014Examples(){
          try {
                 new ClassificationTester("Beställaren godtar inga krav på ersättni"+
                                               "ng för deltagande i upphandlingen och/el"+
                                               "ler upprättande av anbud.")
                          .withParser(swedishParser)
                          .withHeadline("2.8 Ingen ersättning för anbud")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new CompensationClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                  .withTag("")
                                  .withPattern("krav på ersättning")
                          )
                      .test();




             } catch (Exception e) {
                  e.printStackTrace();
                  assertTrue(false);
             }
          }


    /***********************************************************
         *
         *      Testing Classification by examples for tag #COMPENSATION
         *      Document:   "Bilaga 2 - Prislista 20140815 (CSC ENG unprotected 0.1).xlsx"
         *      Language:   "SV"
         *
         */


        @Test
        public void testBilaga2PrislistaExamples(){
            try {
                   new ClassificationTester("Ej ifylld gulmarkerad cell är ett noll-p"+
                                                 "ris, det vill säga 0 kr för den angivna "+
                                                 "prisposten.\n(If yellow cells are le"+
                                                 "ft empty (0 kr) it is regarded as zero p"+
                                                 "rice)\n")
                            .withParser(englishParser)
                            .withHeadline("Förutsättningar (Assumptions)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new CompensationClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                    .withTag("")
                            )
                        .test();


                   new ClassificationTester("De angivna prisposterna i denna bilaga ä"+
                                                 "r den enda ersättning leverantör kommer "+
                                                 "att erhålla för utförandet av Tjänsten. "+
                                                 " Alla eventuella kostnader för utförande"+
                                                 "t av Tjänsten (inklusive alla deltjänste"+
                                                 "r) ska därför fördelas på de angivna pri"+
                                                 "sposterna.\n(The  specified prices i"+
                                                 "n this appendix is the only compensation"+
                                                 " bidders will receive for execution of t"+
                                                 "he Service. All costs for the execution "+
                                                 "of the Service (incl. subservices) shall"+
                                                 " be included in the specified prices)	"+
                                                 "2;n")
                            .withParser(englishParser)
                            .withHeadline("Förutsättningar (Assumptions)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new CompensationClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                    .withTag("")
                            )
                        .test();


                   new ClassificationTester("PRISREDUKTION PER ÅR\n(PRICE REDU"+
                                                 "CTION PER YEAR)")
                            .withParser(englishParser)
                            .withHeadline("Prisreduktion (Price reduction)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new CompensationClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                    .withTag("justering")
                            )
                        .test();


                   new ClassificationTester("Prisreduktion gällande IT-drift (exkl. T"+
                                                 "impriser, Övertagande och Överlämnande m"+
                                                 "en inkl. Processordrift) per år under he"+
                                                 "la avtalsperioden\n(Price reductions"+
                                                 " for IT Operations (excl. Hour Rates, Tr"+
                                                 "ansition and Exit but incl. Processor Op"+
                                                 "erations) per year during the whole cont"+
                                                 "ract period)")
                            .withParser(englishParser)
                            .withHeadline("Prisreduktion (Price reduction)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new CompensationClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                    .withTag("justering")
                            )
                        .test();


                   new ClassificationTester("Timpriser (Hour rates)")
                            .withParser(englishParser)
                            .withHeadline("Total utvärderingskostn (Total)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new CompensationClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                    .withTag("")
                            )
                        .test();


                   new ClassificationTester("Prisreduktion\n(Price reduction)")
                            .withParser(englishParser)
                            .withHeadline("Total utvärderingskostn (Total)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new CompensationClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                                    .withTag("justering")
                            )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #COMPENSATION
        *      Document:   " Frågor Och Svar"
        *      Language:   "SV"
        *
        */


       @Test
       public void testFrågorOchSvarExamples(){
           try {
                  new ClassificationTester("Svar: Se Anbudsförfråga"+
                                                "n stycke 1.2 Upphandlingens omfattning"+
                                                ". Driftansvar <"+
                                                "b>ska ingå i priserna lämnade i Prisbi"+
                                                "lagan flik \"IT"+
                                                "-drift\". Maski"+
                                                "nvara ska ingå "+
                                                "i läm- nade priser i "+
                                                "Prisbilagan flik \"IT-Kapacitet\"")
                           .withParser(swedishParser)
                           .withHeadline(" 2014-08-11")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new CompensationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("justering")
                         )
                       .test();


                  new ClassificationTester("Svar: I priskomp"+
                                                "onenten ska ingå det som behövs"+
                                                " för att leverera Datalagring enligt specif"+
                                                "icerad servicenivåklass.")
                           .withParser(swedishParser)
                           .withHeadline(" 2014-08-21")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new CompensationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("justering")
                         )
                       .test();


                  new ClassificationTester(" Svar: Prissättningen sker i priskompone"+
                                                "nten Applikationsserverdrift (se även sv"+
                                                "a- ret på fråga 26). Observera att det i"+
                                                " nulägesbeskrivningen finns beskrivet ap"+
                                                "plikat- ionsservrar som inte ska ingå i "+
                                                "Tjänsten. Detta är företrädesvis applika"+
                                                "tionsservrar i utvecklings- och testmilj"+
                                                "öer.")
                           .withParser(swedishParser)
                           .withHeadline(" Svar: Prissättningen sker i priskomponenten Applikationsserverdrift (se även sva- ret på fråga 26). Observera att det i nulägesbeskrivningen finns beskrivet applikat- ionsservrar som inte ska ingå i Tjänsten. Detta är företrädesvis applikationsservrar i utvecklings- och testmiljöer.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new CompensationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("justering")
                         )
                       .test();


                  new ClassificationTester("17. Angående prislistan: Vilken priskomp"+
                                                "onent i prislistan ska belastas med back"+
                                                "upkostnaderna för av kunden ägda servrar"+
                                                " som inte har primärdata i SAN (lokala d"+
                                                "iskar)?")
                           .withParser(swedishParser)
                           .withHeadline("17. Angående prislistan: Vilken priskomponent i prislistan ska belastas med backupkostnaderna för av kunden ägda servrar som inte har primärdata i SAN (lokala diskar)?")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new CompensationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("justering")
                         )
                       .test();


                  new ClassificationTester("b. Menar EHM att vi ska uppge priset som"+
                                                " priset per 100 GB x 2 (dvs om leverantö"+
                                                "rens pris är 100 kr per 100GB anger vi p"+
                                                "riset 200kr)? Eller ska vi ange priset p"+
                                                "er 100 GB och räkna med att volymen som "+
                                                "uppges är speglad?")
                           .withParser(swedishParser)
                           .withHeadline("b. Menar EHM att vi ska uppge priset som priset per 100 GB x 2 (dvs om leverantörens pris är 100 kr per 100GB anger vi priset 200kr)? Eller ska vi ange priset per 100 GB och räkna med att volymen som uppges är speglad?")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new CompensationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("justering")
                         )
                       .test();


                  new ClassificationTester("Svar: EHM avser att"+
                                                " endast äga "+
                                                "Exadata- servrarna och "+
                                                "där finns pr"+
                                                "iskomponen- ten med i pr"+
                                                "islistan.")
                           .withParser(swedishParser)
                           .withHeadline(" 2014-08-11")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new CompensationClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("justering")
                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #COMPENSATION
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){


           try {
                  new ClassificationTester("1. A breakdown of pricing by cost compon"+
                                                "ent including full disclosure of any fix"+
                                                "ed, tiered, sliding or other pricing sch"+
                                                "edules including disclosure of whether t"+
                                                "he charge is applied on a per transactio"+
                                                "n, weekly, monthly, annual or other basi"+
                                                "s.")
                           .withParser(englishParser)
                           .withHeadline("1. A breakdown of pricing by cost component including full disclosure of any fixed, tiered, sliding or other pricing schedules including disclosure of whether the charge is applied on a per transaction, weekly, monthly, annual or other basis.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("c. Pricing and pricing model used")
                           .withParser(englishParser)
                           .withHeadline("c. Pricing and pricing model used")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("3. An Excel workbook that clearly shows,"+
                                                " based on the volumes provided, the form"+
                                                "ula and calculations used to determine t"+
                                                "he estimate of annual cost provided in 1"+
                                                "6.4 (2) above.  ")
                           .withParser(englishParser)
                           .withHeadline("3. An Excel workbook that clearly shows, based on the volumes provided, the formula and calculations used to determine the estimate of annual cost provided in 16.4 (2) above.  ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("It is a requirement of this RFP that Ven"+
                                                "dors provide a comprehensive pricing pro"+
                                                "posal for the operational and maintenanc"+
                                                "e activities and an indicative cost for "+
                                                "the implementation and migration phase.")
                           .withParser(englishParser)
                           .withHeadline("3.6  Cost / Pricing")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("The scope of work could also be to parti"+
                                                "cipate in the early phase to define fina"+
                                                "l requirements for the new TMS service. "+
                                                "Participation will depend on the vendors"+
                                                " experience and capacity of such work. P"+
                                                "lease estimate cost of such participatio"+
                                                "n at the Cost tab in Appendix F - respon"+
                                                "se sheet and describe the process in you"+
                                                "r Project Plan.")
                           .withParser(englishParser)
                           .withHeadline("2.3  Scope of Work")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("The cost of preparing a response or any "+
                                                "subsequent meetings etc is the sole resp"+
                                                "onsibility of the supplier. Swedbank wil"+
                                                "l not pay any costs associated with resp"+
                                                "onding to this request for response, inc"+
                                                "luding the preparation of a response, pr"+
                                                "inting, delivery, system demonstrations,"+
                                                " or travel costs.")
                           .withParser(englishParser)
                           .withHeadline("4  RFP TERMS AND CONDITIONS")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("2. An estimate of annual cost from years"+
                                                " 1 to 5 based on volumes provided (note:"+
                                                " vendors are encouraged to contact the R"+
                                                "FP administrator should further clarific"+
                                                "ation of transaction volumes be necessar"+
                                                "y, including the advisement of any other"+
                                                " business assumptions necessary in order"+
                                                " to provide the required cost estimate. "+
                                                " Vendors should be aware that in the int"+
                                                "erests of equality any additional volume"+
                                                "tric assumptions developed during this p"+
                                                "rocess will be shared with all)")
                           .withParser(englishParser)
                           .withHeadline("2. An estimate of annual cost from years 1 to 5 based on volumes provided (note: vendors are encouraged to contact the RFP administrator should further clarification of transaction volumes be necessary, including the advisement of any other business assumptions necessary in order to provide the required cost estimate.  Vendors should be aware that in the interests of equality any additional volumetric assumptions developed during this process will be shared with all)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("This is a Request for Proposal. This RFP"+
                                                " does not represent a contract, a promis"+
                                                "e to contract, or a commitment of any ki"+
                                                "nd on the part of Swedbank. Swedbank wil"+
                                                "l not pay for the information submitted "+
                                                "in response to this RFP.")
                           .withParser(englishParser)
                           .withHeadline("3.3  Disclaimer")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("— Cost of additional services, as "+
                                                "required")
                           .withParser(englishParser)
                           .withHeadline("— Cost of additional services, as required")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("— Pricing/Cost of basic service")
                           .withParser(englishParser)
                           .withHeadline("— Pricing/Cost of basic service")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("Swedbank is aware that Vendors may apply"+
                                                " complex tiered or sliding tariffs based"+
                                                " on transaction volumes as well as other"+
                                                " activity based charges and some element"+
                                                "s of fixed based pricing. It is essentia"+
                                                "l that Swedbank has a full understanding"+
                                                " of the total cost of the vendor's propo"+
                                                "sal and with this in mind responses m"+
                                                "ust provide:")
                           .withParser(englishParser)
                           .withHeadline("3.6  Cost / Pricing")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new CompensationClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.COMPENSATION, 1)
                               .withTag("")
                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


}
