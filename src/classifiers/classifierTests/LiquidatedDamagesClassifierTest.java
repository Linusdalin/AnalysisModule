package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.LiquidatedDamagesClassifierSV;
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


public class LiquidatedDamagesClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
     *
     *      Testing Classification by examples for tag Liquidated Damages
     *      Document:   " Avtal avseende IT-drift"
     *      Language:   "SV"
     *
     */


    @Test
    public void testAvtalavseendeITdriftExamples(){
        try {

               new ClassificationTester("iv) EHM har, från och med tidpunkten för"+
                                             " Felet/avtalsbrottet, rätt till avdrag p"+
                                             "å ersättning för de åtaganden som berörs"+
                                             " av Felet/avtalsbrottet i den utsträckni"+
                                             "ng som svarar mot Felet/avtalsbrottet al"+
                                             "ternativt i Avtalet särskilt angivet bel"+
                                             "opp;")
                        .withParser(swedishParser)
                        .withHeadline("iv) EHM har, från och med tidpunkten för Felet/avtalsbrottet, rätt till avdrag på ersättning för de åtaganden som berörs av Felet/avtalsbrottet i den utsträckning som svarar mot Felet/avtalsbrottet alternativt i Avtalet särskilt angivet belopp;")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new LiquidatedDamagesClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIQUIDATED_DAMAGES, 1)
                            .withTag("prisavdrag")
                      )
                    .test();


               new ClassificationTester("i) Leverantören ska betala Förseningsvit"+
                                             "e vid försening av Övertagandeprojektet "+
                                             "enligt vad som framgår av punkt 14.3. Ti"+
                                             "ll förtydligande anges att Förseningsvit"+
                                             "e även kan utgå i annat fall om så uttry"+
                                             "ckligen anges i Avtalet;")
                        .withParser(swedishParser)
                        .withHeadline("i) Leverantören ska betala Förseningsvite vid försening av Övertagandeprojektet enligt vad som framgår av punkt 14.3. Till förtydligande anges att Förseningsvite även kan utgå i annat fall om så uttryckligen anges i Avtalet;")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new LiquidatedDamagesClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIQUIDATED_DAMAGES, 1)
                            .withTag("vite")
                      )
                    .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }

}
