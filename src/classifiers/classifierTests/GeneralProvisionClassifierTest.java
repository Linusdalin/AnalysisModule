package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.GeneralProvisionsClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
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


public class GeneralProvisionClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    /***********************************************************
      *
      *      Testing Classification by examples for tag #GeneralProvisions
      *      Document:   "  Request for ProposalNew TMSMain Document"
      *      Language:   "EN"
      *
      */
 
 
     @Test
     public void testNewTMSMainDocumentExamples(){

         try {
                new ClassificationTester("The substantive law of Sweden shall appl"+
                                              "y any question regarding the validity, s"+
                                              "cope, meaning, or interpretation of the "+
                                              "Request.")
                         .withParser(englishParser)
                         .withHeadline("4.7  Applicable Law ")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new GeneralProvisionsClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GeneralProvisions, 1)
                                 .withTag("applicable law")
                         )
                     .test();
 
 
                new ClassificationTester("4.7  Applicable Law ")
                         .withParser(englishParser)
                         .withHeadline("4.7  Applicable Law ")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new GeneralProvisionsClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GeneralProvisions, 1)
                                 .withPattern("Applicable Law")
                                 .withTag("applicable law")
                         )
                     .test();
 
 
                new ClassificationTester("Swedbank reserves the right to alter, am"+
                                              "end, delete or add to, in whole or in pa"+
                                              "rt, any terms or provisions of this RFP."+
                                              " Swedbank reserves the right to cancel t"+
                                              "his RFP at any time for any reason or fo"+
                                              "r no reason. ")
                         .withParser(englishParser)
                         .withHeadline("4.6  Treatment of Proposal")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new GeneralProvisionsClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GeneralProvisions, 1)
                                 .withTag("")
                         )
                     .test();
 
 


 
            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }


}
