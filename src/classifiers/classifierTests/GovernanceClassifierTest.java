package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.GovernanceClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.*;
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


public class GovernanceClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }

    @Test
    public void testHuvudavtal_EHM_IT_DriftExamples(){
        try {

               new ClassificationTester("22 Uppföljning av avtalat åtagande")
                        .withParser(swedishParser)
                        .withHeadline("22 Uppföljning av avtalat åtagande")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new GovernanceClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GOVERNANCE, 1)
                                .withTag("")
                        )
                    .test();

            new ClassificationTester("Parterna ska kontinuerligt arbeta med förbättringar gällande tjänsteutveckling, " +
                    "produktivitet och standardisering. Dessa åtgärder ska planeras och följas upp gentemot EHM i en årlig " +
                    "förbättringsplan. Arbetet med förslag på förbättringar ska normalt bedrivas av resurserna inom Samverkansmodellen där Parterna står för sina respektive kostnader.")
                     .withParser(swedishParser)
                     .withHeadline("22 Uppföljning av avtalat åtagande")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new GovernanceClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GOVERNANCE, 1)
                             .withTag("")
                     )
                 .test();



           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }



    /***********************************************************
       *
       *      Testing Classification by examples for tag #GOVERNANCE
       *      Document:   "  Request for ProposalNew TMSMain Document"
       *      Language:   "EN"
       *
       */


      @Test
      public void testNewTMSMainDocumentExamples(){

          try {

                 new ClassificationTester("Questions regarding the scope, meaning, "+
                                               "interpretation or expectation of this RF"+
                                               "P, including any clarification and submi"+
                                               "tted proposals, are to be directed, in w"+
                                               "riting, to the RFP Coordinator who is si"+
                                               "ngle point of contact for this procureme"+
                                               "nt and will respond if time permits. ")
                          .withParser(englishParser)
                          .withHeadline("3.2  Single point of contact")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new GovernanceClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GOVERNANCE, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("3.2  Single point of contact")
                          .withParser(englishParser)
                          .withHeadline("3.2  Single point of contact")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new GovernanceClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GOVERNANCE, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("Answers on questions or other informatio"+
                                               "n from others than the RFP Coordinator c"+
                                               "annot be considered by the vendor. If it"+
                                               " becomes known that a Vendor is not resp"+
                                               "ecting the single point of contact by co"+
                                               "ntacting other relevant members of the T"+
                                               "MS project, Swedbank reserves the right "+
                                               "to decide that a Vendor will not be part"+
                                               " of the procurement process further on. "+
                                               "Swedbank will in that case inform the ac"+
                                               "tual Vendor/s in writing.  ")
                          .withParser(englishParser)
                          .withHeadline("3.2  Single point of contact")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new GovernanceClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.GOVERNANCE, 1)
                                  .withTag("")
                          )
                      .test();


             } catch (Exception e) {
                  e.printStackTrace();
                  assertTrue(false);
             }
          }

}
