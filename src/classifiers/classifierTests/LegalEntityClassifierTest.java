package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.LegalEntityClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.LegalEntityClassifierSV;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import log.AnalysisLogger;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class LegalEntityClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
     *
     *          Not implemented classifier, so the tests should not work
     *
     */

    @Test
    public void basicTest(){


        try {

            new ClassificationTester("Linus Dalin, 701210-0637 test")
                    .withParser(swedishParser)
                    .withClassifier(new LegalEntityClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LegalEntity, 1)

                            .withTag("Fysisk Person")
                            .withPattern("701210-0637")
                    )

                    .test();


            new ClassificationTester("Pukka gaming, 552210-8802")
                    .withParser(swedishParser)
                    .withClassifier(new LegalEntityClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LegalEntity, 1)

                            .withTag("Juridisk Person")
                            .withPattern("552210-8802")
                    )

                    .test();



            new ClassificationTester("Org.nr: [xxxxxx-xxxx]")
                    .withParser(swedishParser)
                    .withClassifier(new LegalEntityClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LegalEntity, 1)

                            .withTag("Juridisk Person")
                            .withPattern("xxxxxx-xxxx")
                    )

                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #LEGAL_ENTITY
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {
                  new ClassificationTester("1.5  Contracting Entity")
                           .withParser(englishParser)
                           .withHeadline("1.5  Contracting Entity")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new LegalEntityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LegalEntity, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Swedbank AB (publ) id.502017-7753, the scope of the license shall include all entities within the Swedbank Group.")
                           .withParser(englishParser)
                           .withHeadline("1.5  Contracting Entity")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new LegalEntityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LegalEntity, 1)
                                   .withTag("Organization")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

}
