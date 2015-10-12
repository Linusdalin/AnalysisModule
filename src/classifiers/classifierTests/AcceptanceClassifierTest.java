package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.AcceptanceClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.AcceptanceClassifierSV;
import classifiers.swedishClassifiers.AcceptanceCriteriaClassifierSV;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class AcceptanceClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {


            new ClassificationTester("Denna mening definierar tilldelningsgrund")
                    .withParser(swedishParser)
                    .withClassifier(new AcceptanceCriteriaClassifierSV())
                    .expectingParseNode(NodeClass.Type.CONTRACT_TERM, 1)

                    .test();


            new ClassificationTester("Samtliga fel/defekter skall vara åtgärdade innan acceptans.")
                     .withParser(swedishParser)
                     .withHeadline("Table 1")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new AcceptanceCriteriaClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                         .withPattern("Samtliga fel / defekter skall vara åtgärdade innan acceptans")
                         .withTag("Subjective")
                   )
                 .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    /***********************************************************
        *
        *      Testing Classification by examples for tag #AcceptanceCriteria
        *      Document:   "Bilaga 3 Utvärderingsmodell.xlsx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testBilaga3UtvärderingsmodellExamples(){
           try {
                  new ClassificationTester("Som underlag för utvärdering begärs en p"+
                                                "rojektplan med ingående aktivitetsplan f"+
                                                "ör Övertagandeprojektet, dvs. aktivitete"+
                                                "r och åtgärder för Övertagandet. I plane"+
                                                "n ska även de krav anbudsgivaren har på "+
                                                "nuvarande leverantör, där så är applicer"+
                                                "bart, ingå samt eventuella krav på EHM f"+
                                                "ör ett effektivt Övertagandeprojekt (t.e"+
                                                "x. krav på dokumentation, verktyg, works"+
                                                "hops, m.m.).\nBeskrivs av anbudsgiva"+
                                                "ren i Avtalsbilaga 4, Övertagandeprojekt"+
                                                ".")
                           .withParser(swedishParser)
                           .withHeadline("Table 1")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AcceptanceCriteriaClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                               .withPattern("underlag för utvärdering")
                         )
                       .test();


                  new ClassificationTester("Faktorer som kommer att beaktas i utvärd"+
                                                "eringen är: - om planen visar att Levera"+
                                                "ntören kontinuerligt utvecklar sina tjän"+
                                                "ster och kommer att proaktivt föreslå ef"+
                                                "fektiviseringar, informera om nya lösnin"+
                                                "gar osv.\n- och i vilken utsträcknin"+
                                                "g lämnad beskrivning är relevant för oli"+
                                                "ka områden inom Tjänsten (tex säkerhet, "+
                                                "virtualisering, innovation baserat på te"+
                                                "knisk utveckling).\nBeskrivningar so"+
                                                "m visar en gedigen systematisk process o"+
                                                "ch metodik kommer att premieras i utvärd"+
                                                "eringen, liksom typfall/exempel från gen"+
                                                "omförda förbättringsåtgärder, som kan ti"+
                                                "llämpas i förhållande till EHM.")
                           .withParser(swedishParser)
                           .withHeadline("Table 1")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AcceptanceCriteriaClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                               .withPattern("beaktas i utvärderingen")
                         )
                       .test();


                  new ClassificationTester("Faktorer som kommer att beaktas i utvärd"+
                                                "eringen är om Leverantören kommer att ti"+
                                                "llämpa dokumenterade miljösäkringsrutine"+
                                                "r som säkerställer att hela det avtalade"+
                                                " åtagandet genomförs på ett sådant sätt "+
                                                "att överenskomna miljömål uppnås och upp"+
                                                "rätthålls.\nEn  klar, tydlig och log"+
                                                "isk beskrivningen av miljöpolicy samt ru"+
                                                "tiner för miljöarbete premieras.")
                           .withParser(swedishParser)
                           .withHeadline("Table 1")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AcceptanceCriteriaClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("Som underlag för utvärdering begärs förs"+
                                                "lag till samverkansmodell på strategisk,"+
                                                " taktisk och operativ nivå.\nBeskriv"+
                                                "ningen skall omfatta process, roller möt"+
                                                "esforum och organisaiton.\nBeskrivs "+
                                                "av Anbudsgivaren i Avtalsbilaga 6, Samve"+
                                                "rkan och Ändringshantering.\nSom und"+
                                                "erlag för utvärdering begärs ett förslag"+
                                                " till modell för hur SLA enligt avtal ka"+
                                                "n följas upp på vecko- och månadsbasis ("+
                                                "process, stödsystem/rapportering samt mö"+
                                                "tesforum). Exempel på SLA-rapporter lämn"+
                                                "as in.\nBeskrivs av Anbudsgivaren i "+
                                                "Avtalsbilaga 6, Samverkan och Ändringsha"+
                                                "ntering.")
                           .withParser(swedishParser)
                           .withHeadline("Table 1")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AcceptanceCriteriaClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("Faktorer som kommer att beaktas i utvärd"+
                                                "eringen är: hur klar, tydlig och logisk "+
                                                "beskrivningen är, om den säkerhetsansvar"+
                                                "ige kommer att tillämpa en tydlig och be"+
                                                "prövad arbetsmetodik (tex processer/ruti"+
                                                "ner/ramverk/gränsnsitt för samverkan ) s"+
                                                "åsom  Information Security Management IT"+
                                                "IL eller motsvarande för området.\nT"+
                                                "ydliga exempel på rollens agerande för a"+
                                                "tt uppnå säkerhetskraven enligt Avtalsbi"+
                                                "laga 2 (EHM:S krav på Tjänsten) kommer a"+
                                                "tt premieras  såsom:\n- Teknik och m"+
                                                "odell för uppföljning av säkehetsinciden"+
                                                "ter\n- Omvärldsbevakning\n- Iden"+
                                                "tifiering av potentiella risker och åtgä"+
                                                "rder för att mitigera dessa")
                           .withParser(swedishParser)
                           .withHeadline("Table 1")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AcceptanceCriteriaClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
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
      *      Testing Classification by examples for tag #ACCEPTANCE_CRITERIA
      *      Document:   "Anbudsförfrågan IT-drift 2014.docx"
      *      Language:   "SV"
      *
      */


     @Test
     public void testAnbudsförfråganITdrift2014Examples(){
         try {
                new ClassificationTester("Syftet med detta kriterium är att utvärd"+
                                              "era det sätt på vilket anbudsgivaren kom"+
                                              "mer att samverka med Beställaren samt hu"+
                                              "r uppföljning av leveransen av Tjänsten "+
                                              "kommer att ske. ")
                         .withParser(swedishParser)
                         .withHeadline("---")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new AcceptanceCriteriaClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                             .withTag("")
                       )
                     .test();



                new ClassificationTester("Syftet med detta kriterium är att utvärd"+
                                              "era innehållet i de tjänster som anbudsg"+
                                              "ivaren kommer att tillhandahålla Beställ"+
                                              "aren samt hur anbudsgivaren kommer att a"+
                                              "rbeta för uppfyllande av säkerhetskraven"+
                                              ". Kriteriet omfattar underkriterierna Be"+
                                              "skrivning av funktioner och processer oc"+
                                              "h Kontinuitetsplanering.")
                         .withParser(swedishParser)
                         .withHeadline("---")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new AcceptanceCriteriaClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                             .withTag("")
                       )
                     .test();



                new ClassificationTester("Vid utvärderingen kommer följande utvärd"+
                                              "eringskriterier att användas:")
                         .withParser(swedishParser)
                         .withHeadline("6.2 Utvärdering")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new AcceptanceCriteriaClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                             .withTag("")
                       )
                     .test();


                new ClassificationTester("Bedömning av utvärderingskriterier")
                         .withParser(swedishParser)
                         .withHeadline("6.2 Utvärdering")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new AcceptanceCriteriaClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
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
        *      Testing Classification by examples for tag #ACCEPTANCE
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testTMSMainDocumentExamples(){
           try {
                  new ClassificationTester("  Swedbank does not bind itself to accept the lowest price offered or indeed any"+
                                                " proposal")
                           .withParser(englishParser)
                           .withHeadline("1.3  Evaluation")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Swedbank reserves the right to make an award to the best advantage of Swedbank, cost and other factors considered such as:")
                           .withParser(englishParser)
                           .withHeadline("— Implemented solution will align with the strategic direction of Swedbank's values. ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
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
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Failure to supply this will result in au"+
                                                "tomatic disqualification from the process.")
                           .withParser(englishParser)
                           .withHeadline("---")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("1.3  Evaluation")
                           .withParser(englishParser)
                           .withHeadline("1.3  Evaluation")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
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
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("If necessary, some Vendors may be reques"+
                                                "ted to present their response. We would "+
                                                "like all Vendors to be prepared of suppl"+
                                                "ying Swedbank with a demonstration of th"+
                                                "e system. All Vendors will get a notific"+
                                                "ation weightier you are qualified to the"+
                                                " next phase or not and those who are qua"+
                                                "lified will be required to present the s"+
                                                "olution.")
                           .withParser(englishParser)
                           .withHeadline("3.9  RFP Presentation")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new AcceptanceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
                                   .withPattern("required to present to the solution")
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
         *      Testing Classification by examples for tag #ACCEPTANCE_CRITERIA
         *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
         *      Language:   "SV"
         *
         */


        @Test
        public void testLVExamples(){
            try {
               // Found 1 re-classifications

                   new ClassificationTester("10.1 Säljaren ska anses ha slutfört ett Uppdrag då samtliga delar "+
                                       "av Uppdraget har genomförts och levererats i enlighet med specifikatio"+
                                       "nen i aktuellt Avropsavtal och Köparen har godkänt dessa i enlighet "+
                                       "med punkt 11 nedan.")
                            .withParser(swedishParser)
                            .withHeadline(" add headline...")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new AcceptanceCriteriaClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE_CRITERIA, 1)
                                .withPattern("Köparen har godkänt")
                                .withTag("Subjective")
                          )
                        .test();


                new ClassificationTester("11.  Uppdrags godkännande")
                                     .withParser(swedishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new AcceptanceClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ACCEPTANCE, 1)
                                             .withPattern("Uppdrags godkännande")

                                     )
                                 .test();

               } catch (Exception e) {
                    e.printStackTrace(System.out);
                    assertTrue(false);
               }
            }

}
