package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.PaymentClassifierSV;
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


public class PaymentClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
        *
        *      Testing Classification by examples for tag Payment
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("13.4.5 Vid försenad betalning äger Lever"+
                                                "antören rätt att debitera dröjsmålsränta"+
                                                " enligt räntelagen (1975:635). Vid event"+
                                                "uell räntefaktura ska fakturanummer för "+
                                                "ursprungsfakturan finnas angiven.")
                           .withParser(swedishParser)
                           .withHeadline("13.4.5 Vid försenad betalning äger Leverantören rätt att debitera dröjsmålsränta enligt räntelagen (1975:635). Vid eventuell räntefaktura ska fakturanummer för ursprungsfakturan finnas angiven.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new PaymentClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PAYMENT, 1)
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
                           .withClassifier(new PaymentClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PAYMENT, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("13.4.3 Betalningstiden är trettio (30) d"+
                                                "agar från fakturadatum.")
                           .withParser(swedishParser)
                           .withHeadline("13.4.3 Betalningstiden är trettio (30) dagar från fakturadatum.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new PaymentClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PAYMENT, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

}
