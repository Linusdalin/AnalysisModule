package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.InvoicingClassifierSV;
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


public class InvoicingClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
     *
     *      Testing Classification by examples for tag Invoicing
     *      Document:   " Avtal avseende IT-drift"
     *      Language:   "SV"
     *
     */


    @Test
    public void testAvtalavseendeITdriftExamples(){
        try {
               new ClassificationTester("13.4.4 faktura ska vara specificerad så "+
                                             "att arten och omfattningen av utförda tj"+
                                             "änster kan kontrolleras enligt villkoren"+
                                             " i Avtalet. Faktureringsavgifter eller p"+
                                             "åminnelseavgifter ska inte utgå, vare si"+
                                             "g för elektroniska eller pappersfakturor"+
                                             ".")
                        .withParser(swedishParser)
                        .withHeadline("13.4.4 Faktura ska vara specificerad så att arten och omfattningen av utförda tjänster kan kontrolleras enligt villkoren i Avtalet. Faktureringsavgifter eller påminnelseavgifter ska inte utgå, vare sig för elektroniska eller pappersfakturor.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new InvoicingClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.INVOICING, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("13.4.6 Leverantören har inte rätt att fa"+
                                             "kturera ersättning som är äldre än sex ("+
                                             "6) kalendermånader, eller för arbete som"+
                                             " utförts mer än sex (6) kalendermånader "+
                                             "före fakturans utställande, såvida detta"+
                                             " inte i förväg skriftligen överenskommit"+
                                             "s mellan Parterna.")
                        .withParser(swedishParser)
                        .withHeadline("13.4.6 Leverantören har inte rätt att fakturera ersättning som är äldre än sex (6) kalendermånader, eller för arbete som utförts mer än sex (6) kalendermånader före fakturans utställande, såvida detta inte i förväg skriftligen överenskommits mellan Parterna.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new InvoicingClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.INVOICING, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("13.4.2 Fakturering ska ske månadsvis i e"+
                                             "fterskott och efter det att prestationer"+
                                             " som omfattas av fakturan utförts respek"+
                                             "tive fullgjorts.")
                        .withParser(swedishParser)
                        .withHeadline("13.4.2 Fakturering ska ske månadsvis i efterskott och efter det att prestationer som omfattas av fakturan utförts respektive fullgjorts.")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new InvoicingClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.INVOICING, 1)
                            .withTag("")
                      )
                    .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }

}
