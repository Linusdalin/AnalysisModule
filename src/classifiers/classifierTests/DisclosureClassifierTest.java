package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.DisclosureClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
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


public class DisclosureClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


/***********************************************************
     *
     *      Testing Classification by examples for tag Disclosure
     *      Document:   "Huvudavtal_EHM_IT_Drift.docx"
     *      Language:   "SV"
     *
     */


    @Test
    public void testHuvudavtal_EHM_IT_DriftxExamples(){
        try {
               new ClassificationTester("Skyldighet att lämna information föreligger heller inte om detta strider mot lag.")
                        .withParser(swedishParser)
                        .withHeadline("22 Uppföljning av avtalat åtagande")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new DisclosureClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DISCLOSURE, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("Leverantören är inte skyldig att ge till"+
                                             "gång till sin kostnadsstruktur, informat"+
                                             "ion rörande andra kunders förhållanden o"+
                                             "ch data eller till sådan information i ö"+
                                             "vrigt som inte är nödvändig att ta del a"+
                                             "v för bedömningen av om Leverantören upp"+
                                             "fyller avtalade krav.")
                        .withParser(swedishParser)
                        .withHeadline("22 Uppföljning av avtalat åtagande")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new DisclosureClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DISCLOSURE, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("Leverantören förbinder sig att, när EHM "+
                                             "så begär, till EHM överlämna Dokumentati"+
                                             "on och information samt allt övrigt mate"+
                                             "rial som avser avtalat åtagande inkluder"+
                                             "ande för Avtalet specifika anpassningar,"+
                                             " integrationsgränssnitt, konfigurationer"+
                                             " etc. Överlämnat material ska ha en såda"+
                                             "n kvalitet att EHM eller tredjepart anli"+
                                             "tad av EHM, ges möjlighet att själv elle"+
                                             "r genom tredje part fortsätta verksamhet"+
                                             "en i samma omfattning och med samma kval"+
                                             "itet som gäller för Avtalet.")
                        .withParser(swedishParser)
                        .withHeadline("22 Uppföljning av avtalat åtagande")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new DisclosureClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DISCLOSURE, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("Leverantören ska utan tillkommande kostn"+
                                             "ad tillhandahålla erforderligt underlag "+
                                             "för att genomföra kontroller. Den som ut"+
                                             "för revisionen ska följa Leverantörens s"+
                                             "käliga säkerhetsföreskrifter. Leverantör"+
                                             "en har rätt att övervaka revision.")
                        .withParser(swedishParser)
                        .withHeadline("22 Uppföljning av avtalat åtagande")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new DisclosureClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DISCLOSURE, 1)
                            .withTag("")
                      )
                    .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }

}
