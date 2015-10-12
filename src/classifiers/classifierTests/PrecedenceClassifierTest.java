package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.PrecedenceClassifierSV;
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
 *          Award Criteria tests
 *
 */


public class PrecedenceClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag Precedence
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("3.3 Avtalshandlingarna kompletterar vara"+
                                                "ndra. Vid eventuella motstridigheter mel"+
                                                "lan innehåll i Huvudavtal och dess bilag"+
                                                "or har Bilaga7 (Personuppgiftsbiträdesav"+
                                                "tal) tillämpning före detta Huvudavtal o"+
                                                "ch detta Huvudavtals tillämpning före öv"+
                                                "riga bilagor. Övriga bilagor ska gälla i"+
                                                " ovan nämnd ordning om inte omständighet"+
                                                "erna uppenbarligen föranleder annat. Til"+
                                                "l förtydligande anges att Bilaga 2 (EHM:"+
                                                "s krav på Tjänsten) har företräde framfö"+
                                                "r Bilaga 3 (Tjänste- och processbeskrivn"+
                                                "ingar).")
                           .withParser(swedishParser)
                           .withHeadline("3.3 Avtalshandlingarna kompletterar varandra. Vid eventuella motstridigheter mellan innehåll i Huvudavtal och dess bilagor har Bilaga7 (Personuppgiftsbiträdesavtal) tillämpning före detta Huvudavtal och detta Huvudavtals tillämpning före övriga bilagor. Övriga bilagor ska gälla i ovan nämnd ordning om inte omständigheterna uppenbarligen föranleder annat. Till förtydligande anges att Bilaga 2 (EHM:s krav på Tjänsten) har företräde framför Bilaga 3 (Tjänste- och processbeskrivningar).")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new PrecedenceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PRECEDENCE, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


}
