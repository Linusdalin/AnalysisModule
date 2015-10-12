package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.LegalComplianceClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class LegalComplianceClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
         *
         *      Testing Classification by examples for tag LawCompliance
         *      Document:   " Avtal avseende IT-drift"
         *      Language:   "SV"
         *
         */


        @Test
        public void testAvtalavseendeITdriftExamples(){
            try {
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
                            .withClassifier(new LegalComplianceClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LEGAL_COMPLIANCE, 1)
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
                           .withClassifier(new LegalComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LEGAL_COMPLIANCE, 1)
                                   .withTag("")
                           )
                        .test();


                   new ClassificationTester("21.3 Leverantören är inte skyldig att ge"+
                                                 " tillgång till sin kostnadsstruktur, inf"+
                                                 "ormation rörande andra kunders förhållan"+
                                                 "den och data eller till sådan informatio"+
                                                 "n i övrigt som inte är nödvändig att ta "+
                                                 "del av för bedömningen av om Leverantöre"+
                                                 "n uppfyller avtalade krav. Skyldighet at"+
                                                 "t lämna information föreligger heller in"+
                                                 "te om detta strider mot lag.")
                            .withParser(swedishParser)
                            .withHeadline("21.3 Leverantören är inte skyldig att ge tillgång till sin kostnadsstruktur, information rörande andra kunders förhållanden och data eller till sådan information i övrigt som inte är nödvändig att ta del av för bedömningen av om Leverantören uppfyller avtalade krav. Skyldighet att lämna information föreligger heller inte om detta strider mot lag.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new LegalComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LEGAL_COMPLIANCE, 1)
                                   .withTag("")
                           )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }


}
