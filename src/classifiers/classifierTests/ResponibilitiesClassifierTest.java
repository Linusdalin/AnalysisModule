package classifiers.classifierTests;

import classifiers.englishClassifiers.*;
import document.AbstractDefinition;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class ResponibilitiesClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {

            mockDocument.addDefinition(new AbstractDefinition("Swedbank", 0));


             new ClassificationTester("The cost of preparing a response or any "+
                                           "subsequent meetings etc is the sole resp"+
                                           "onsibility of the supplier. Swedbank wil"+
                                           "l not pay any costs associated with resp"+
                                           "onding to this request for response, inc"+
                                           "luding the preparation of a response, pr"+
                                           "inting, delivery, system demonstrations,"+
                                           " or travel costs.")
                      .withParser(englishParser)
                      .withHeadline("4  RFP TERMS AND CONDITIONS")
                      .withProject(mockProject, mockDocument)
                      .withClassifier(new NumberClassifierEN())
                      .withClassifier(new DefinitionUsageClassifierEN())
                      .withClassifier(new ResponsibilitiesClassifierEN())
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                              .withPattern("sole responsibility")
                              .withTag("")
                      )
                  .test();


            new ClassificationTester("The Vendor is solely responsible for mak"+
                                          "ing sure that the response has been deli"+
                                          "vered in accordance with the terms and c"+
                                          "onditions identified in this RFP and sho"+
                                          "uld ensure that all of the documents ide"+
                                          "ntified in this section 3.8 are included"+
                                          ". ")
                     .withParser(englishParser)
                     .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierEN())
                     .withClassifier(new DefinitionUsageClassifierEN())
                     .withClassifier(new ResponsibilitiesClassifierEN())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                             .withTag("")
                     )
                 .test();

            new ClassificationTester("e. Swedbank responsibilities")
                     .withParser(englishParser)
                     .withHeadline("e. Swedbank responsibilities")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierEN())
                     .withClassifier(new DefinitionUsageClassifierEN())
                     .withClassifier(new ResponsibilitiesClassifierEN())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                             .withTag("")
                     )
                 .test();

            new ClassificationTester("— Vendor is accountable for manage"+
                                          "ment and successful completion of end to"+
                                          " end migration")
                     .withParser(englishParser)
                     .withHeadline("— Vendor is accountable for management and successful completion of end to end migration")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierEN())
                     .withClassifier(new DefinitionUsageClassifierEN())
                     .withClassifier(new ResponsibilitiesClassifierEN())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESPONSIBILITY, 1)
                             .withTag("")
                     )
                 .test();





        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }



}
