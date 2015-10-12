package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.RegulatoryComplianceClassifierEN;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class RegulatoryComplianceClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    /***********************************************************
      *
      *      Testing Classification by examples for tag #REGULATORY_COMPLIANCE
      *      Document:   "  Request for ProposalNew TMSMain Document"
      *      Language:   "EN"
      *
      */


     @Test
     public void testNewTMSMainDocumentExamples(){
         try {
                new ClassificationTester("As a minimum the Supplier is required to"+
                                              " provide all services to industry standa"+
                                              "rds and comply with all applicable regul"+
                                              "ations, codes, manufacturer's recommenda"+
                                              "tions and standards. ")
                         .withParser(englishParser)
                         .withHeadline("3.10  Supplier Qualification")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new RegulatoryComplianceClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.REGULATORY_COMPLIANCE, 1)
                                 .withTag("")
                         )
                     .test();


            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }


}
