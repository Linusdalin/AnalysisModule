package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.SubcontractorClassifierSV;
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


public class SubcontractorClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
       *
       *      Testing Classification by examples for tag Subcontractor
       *      Document:   " Avtal avseende IT-drift"
       *      Language:   "SV"
       *
       */


      @Test
      public void testAvtalavseendeITdriftExamples(){
          try {
                 new ClassificationTester("Leverantören ska i avtal med underlevera"+
                                               "ntör, tillförsäkra EHM rätt att genomför"+
                                               "a revision av sådan leverantör såsom ang"+
                                               "es i denna punkt 21.")
                          .withParser(swedishParser)
                          .withHeadline("19. Säkerhet och kontinuitetsplan")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new SubcontractorClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SUBCONTRACTORS, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("10.3 Underleverantörer")
                          .withParser(swedishParser)
                          .withHeadline("10.3 Underleverantörer")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new SubcontractorClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SUBCONTRACTORS, 1)
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
                          .withClassifier(new SubcontractorClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SUBCONTRACTORS, 1)
                                  .withTag("")
                          )
                      .test();


             } catch (Exception e) {
                  e.printStackTrace();
                  assertTrue(false);
             }
          }
}
