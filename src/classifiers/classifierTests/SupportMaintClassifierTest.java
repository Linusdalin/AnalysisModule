package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.SupportMaintenanceClassifierSV;
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


public class SupportMaintClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
        *
        *      Testing Classification by examples for tag #SUPPORT_MAINTENANCE
        *      Document:   " Frågor Och Svar"
        *      Language:   "SV"
        *
        */


       @Test
       public void testFrågorOchSvarExamples(){
           try {
                  new ClassificationTester("Svar: Serviceavtalet är Oracles standardavtal (Oracle "+
                                                "Premier Support for Systems gällande h"+
                                                "årdvara). Vid problem<"+
                                                "b> på produkterna k"+
                                                "ommer Oracles supportforum"+
                                                " att använda"+
                                                "s. Avtalstiden är ett<"+
                                                "b> år från <"+
                                                "/b>den 21 mars 2014.")
                           .withParser(swedishParser)
                           .withHeadline(" 2014-08-05")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SupportMaintenanceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SupportMaint, 1)
                                   .withTag("service")
                           )
                       .test();


                  new ClassificationTester("11. Kan EHM förtydliga hur befintligt se"+
                                                "rviceavtal för Exadata ser ut med avse- "+
                                                "ende på åtagande för underhåll, support "+
                                                "och responstid samt avtalslängd?")
                           .withParser(swedishParser)
                           .withHeadline("11. Kan EHM förtydliga hur befintligt serviceavtal för Exadata ser ut med avse- ende på åtagande för underhåll, support och responstid samt avtalslängd?")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SupportMaintenanceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SupportMaint, 1)
                                   .withTag("service")
                           )
                       .test();


                  new ClassificationTester("12. Har EHM några planer på att byta ut "+
                                                "Exadata till något annat efter att serviceavtalet gått ut?")
                           .withParser(swedishParser)
                           .withHeadline("12. Har EHM några planer på att byta ut Exadata till något annat efter att serviceavtalet gått ut?")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SupportMaintenanceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SupportMaint, 1)
                                   .withTag("service")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }
}
