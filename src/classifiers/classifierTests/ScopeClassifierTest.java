package classifiers.classifierTests;

import classifiers.englishClassifiers.ArbitrationClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.ScopeClassifierEN;
import classifiers.swedishClassifiers.ArbitrationClassifierSE;
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


public class ScopeClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

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


            new ClassificationTester("No test")
                    .withParser(swedishParser)
                    .withClassifier(new ArbitrationClassifierSE())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new ArbitrationClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #Scope
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {

                  new ClassificationTester("2.3  Scope of Work")
                           .withParser(englishParser)
                           .withHeadline("2.3  Scope of Work")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ScopeClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Scope, 1)
                                   .withTag("")
                           )
                       .test();




                  new ClassificationTester("The scope of work could also be to parti"+
                                                "cipate in the early phase to define fina"+
                                                "l requirements for the new TMS service. "+
                                                "Participation will depend on the vendors"+
                                                " experience and capacity of such work. P"+
                                                "lease estimate cost of such participatio"+
                                                "n at the Cost tab in Appendix F - respon"+
                                                "se sheet and describe the process in you"+
                                                "r Project Plan.")
                           .withParser(englishParser)
                           .withHeadline("2.3  Scope of Work")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ScopeClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Scope, 1)
                                   .withTag("")
                           )
                       .test();




                  new ClassificationTester("1.1  Scope ")
                           .withParser(englishParser)
                           .withHeadline("1.1  Scope ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ScopeClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Scope, 1)
                                   .withTag("")
                           )
                       .test();



                  new ClassificationTester("The scope of the Terminal Management Sys"+
                                                "tem under assessment is as follows: ")
                           .withParser(englishParser)
                           .withHeadline("2.3  Scope of Work")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ScopeClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Scope, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }



}
