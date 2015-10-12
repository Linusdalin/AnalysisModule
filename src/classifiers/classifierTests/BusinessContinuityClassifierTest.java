package classifiers.classifierTests;

import classifiers.swedishClassifiers.BusinessContinuityClassifierSV;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
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


public class BusinessContinuityClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    /***********************************************************
        *
        *      Testing Classification by examples for tag BusinessContinuity
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("19.4 Leverantören ska kostnadsfritt uppr"+
                                                "ätta, följa och vidmakthålla en kontinui"+
                                                "tetplan (Disaster Recovery Plan) för Tjä"+
                                                "nsten. Kontinuitetsplanen ska överlämnas"+
                                                " till EHM för godkännande senast tre (3)"+
                                                " månader efter Effektiv Övertagandedag o"+
                                                "ch minst uppfylla kraven i Bilaga 2 (EHM"+
                                                ":s krav på Tjänsten) och Bilaga 3 (Tjäns"+
                                                "te-och processbeskrivningar). I den mån "+
                                                "EHM gör befogad anmärkning på innehållet"+
                                                " ska Leverantören snarast justera innehå"+
                                                "llet. Leverantören ska under avtalstiden"+
                                                " föreslå de förändringar av kontinuitets"+
                                                "planen i syfte att upprätthålla en god k"+
                                                "ontinuitet för Tjänsten Den av EHM godkä"+
                                                "nda kontinuitetsplanen utgör en del av D"+
                                                "okumentationen. Om Parterna inte kan ena"+
                                                "s om innehållet kan frågan hänskjutas av"+
                                                " endera Parten för omedelbar behandling "+
                                                "enligt Samverkansmodellen.")
                           .withParser(swedishParser)
                           .withHeadline("19.4 Leverantören ska kostnadsfritt upprätta, följa och vidmakthålla en kontinuitetplan (Disaster Recovery Plan) för Tjänsten. Kontinuitetsplanen ska överlämnas till EHM för godkännande senast tre (3) månader efter Effektiv Övertagandedag och minst uppfylla kraven i Bilaga 2 (EHM:s krav på Tjänsten) och Bilaga 3 (Tjänste-och processbeskrivningar). I den mån EHM gör befogad anmärkning på innehållet ska Leverantören snarast justera innehållet. Leverantören ska under avtalstiden föreslå de förändringar av kontinuitetsplanen i syfte att upprätthålla en god kontinuitet för Tjänsten Den av EHM godkända kontinuitetsplanen utgör en del av Dokumentationen. Om Parterna inte kan enas om innehållet kan frågan hänskjutas av endera Parten för omedelbar behandling enligt Samverkansmodellen.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new BusinessContinuityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.BusinessContinuity, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }
}
