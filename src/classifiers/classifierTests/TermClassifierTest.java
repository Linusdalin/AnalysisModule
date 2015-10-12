package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.TermClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.TermClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class TermClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();

    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #TERM
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("5. Avtalsperiod")
                           .withParser(swedishParser)
                           .withHeadline("5. Avtalsperiod")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new TermClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                   .withTag("")
                           )
                       .test();

               new ClassificationTester("5.2 Avtalet gäller under fyra (4) år frå"+
                                                           "n datum för avtalstecknande.")
                                      .withParser(swedishParser)
                                      .withHeadline("5.2 Avtalet gäller under fyra (4) år från datum för avtalstecknande.")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new TermClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                          .withTag("")
                                    )
                                  .test();


               new ClassificationTester("5.4 EHM ska skriftligen meddela Leverant"+
                                                         "ören om en eventuell förlängning senast "+
                                                         "sex (6) månader innan respektive kontrak"+
                                                         "tsperiods utgång.")
                                    .withParser(swedishParser)
                                    .withHeadline("5.4 EHM ska skriftligen meddela Leverantören om en eventuell förlängning senast sex (6) månader innan respektive kontraktsperiods utgång.")
                                    .withProject(mockProject, mockDocument)
                                    .withClassifier(new NumberClassifierSV())
                                    .withClassifier(new DefinitionUsageClassifierSV())
                                    .withClassifier(new TermClassifierSV())
                                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                        .withTag("förlängning")
                                  )
                                .test();


                           new ClassificationTester("5.3 Därefter har EHM rätt att, på i övri"+
                                                         "gt oförändrade villkor, förlänga Avtalet"+
                                                         " vid två (2) tillfällen med två (2) år i"+
                                                         " taget. Avtalet kan således maximalt löp"+
                                                         "a i totalt åtta (8) år.")
                                    .withParser(swedishParser)
                                    .withHeadline("5.3 Därefter har EHM rätt att, på i övrigt oförändrade villkor, förlänga Avtalet vid två (2) tillfällen med två (2) år i taget. Avtalet kan således maximalt löpa i totalt åtta (8) år.")
                                    .withProject(mockProject, mockDocument)
                                    .withClassifier(new NumberClassifierSV())
                                    .withClassifier(new DefinitionUsageClassifierSV())
                                    .withClassifier(new TermClassifierSV())
                                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                        .withTag("förlängning")
                                  )
                                .test();



              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #TERM
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {
                  new ClassificationTester("b. Confirms the period of validity of th"+
                                                "e proposal ")
                           .withParser(englishParser)
                           .withHeadline("b. Confirms the period of validity of the proposal ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new TermClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("3.5  Period of Validity of the"+
                                                " Proposal")
                           .withParser(englishParser)
                           .withHeadline("3.5  Period of Validity of the Proposal")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new TermClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("The Vendors shall be bound by its propos"+
                                                "al until September 30th 2015."+
                                                " This shall apply even if Swedbank and t"+
                                                "he Vendors enter into negotiations regar"+
                                                "ding the proposal before the aforesaid d"+
                                                "ate.")
                           .withParser(englishParser)
                           .withHeadline("3.5  Period of Validity of the Proposal")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new TermClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERM, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

}
