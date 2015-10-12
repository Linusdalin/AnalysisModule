package classifiers.classifierTests;

import classifiers.englishClassifiers.*;
import classifiers.swedishClassifiers.DeliverablesClassifierSV;
import classifiers.swedishClassifiers.DeliveryClassifierSV;
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


public class DeliveryClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    @Test
       public void testDeliverablesClassifierSV(){
           try {

                  new ClassificationTester("För att säkerställa att Beställaren uppf"+
                                                "attat uppgifterna i anbudet korrekt komm"+
                                                "er Beställaren att kalla anbudsgivare ti"+
                                                "ll anbudspresentation, där anbudsgivaren"+
                                                " redogör för innehållet i anbudet. Bestä"+
                                                "llaren kommer dock inte att lämna någon "+
                                                "information eller besvara några frågor u"+
                                                "nder anbudspresentationen, och några för"+
                                                "handlingar kommer inte att genomföras. A"+
                                                "nbudsgivaren bör kunna genomföra en såda"+
                                                "n anbudspresentation inom tio arbetsdaga"+
                                                "r från när Beställaren så begär.")
                           .withParser(swedishParser)
                           .withHeadline("2.9 Anbudspresentation")
                           .withClassifier(new DeliveryClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                   .withTag("Möte")
                           )
                       .test();
              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
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
                    .withClassifier(new DeliveryClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new DeliveryClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
      *
      *      Testing Classification by examples for tag #Delivery
      *      Document:   "  Request for ProposalNew TMSMain Document"
      *      Language:   "EN"
      *
      */


     @Test
     public void testNewTMSMainDocumentExamples(){
         try {


                new ClassificationTester("One original hard copy of the NDA and Sw"+
                                              "edbank Code of Conduct must be submitted"+
                                              " in writing and be signed by an authoriz"+
                                              "ed representative of the company or equi"+
                                              "valent. ")
                         .withParser(englishParser)
                         .withHeadline("3.8  Response format")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();




                new ClassificationTester("All other RFP response documents shall b"+
                                              "e submitted on three USB memory sticks s"+
                                              "ent to the RFP Coordinator. ")
                         .withParser(englishParser)
                         .withHeadline("3.8  Response format")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();





                new ClassificationTester("This RFP documentation consist of this M"+
                                              "ain Document and Appendices B, F, Glossa"+
                                              "ry and \"Software & Parameter Lo"+
                                              "ad Specification\" which are an inte"+
                                              "gral part of the RFP. The Main Document "+
                                              "contains general information about the R"+
                                              "FP, a description of the scope, the prin"+
                                              "ciples for response and the terms of the"+
                                              " RFP.")
                         .withParser(englishParser)
                         .withHeadline("1.4  RFP Documentation")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();



                new ClassificationTester("You may, at your discretion, submit alte"+
                                              "rnate responses, or responses which devi"+
                                              "ate from the RFP; provided. If a respons"+
                                              "e includes documents additional to those"+
                                              " requested by Swedbank, the Cover letter"+
                                              " should list these documents and identif"+
                                              "y the file name for each document.")
                         .withParser(englishParser)
                         .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();



                new ClassificationTester("As of Appendix B in this RFP the Vendror"+
                                              " must respond to the RFP requirements by"+
                                              " using the Response template, Appendix F"+
                                              ".")
                         .withParser(englishParser)
                         .withHeadline("---")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();



                new ClassificationTester("Appendix F Response Template")
                         .withParser(englishParser)
                         .withHeadline("")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();





                new ClassificationTester("If necessary, some Vendors may be reques"+
                                              "ted to present their response. We would "+
                                              "like all Vendors to be prepared of suppl"+
                                              "ying Swedbank with a demonstration of th"+
                                              "e system. All Vendors will get a notific"+
                                              "ation weightier you are qualified to the"+
                                              " next phase or not and those who are qua"+
                                              "lified will be required to present the s"+
                                              "olution.")
                         .withParser(englishParser)
                         .withHeadline("3.9  RFP Presentation")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();


                new ClassificationTester("3. Provide details of the nearest servic"+
                                              "e location including the address and sta"+
                                              "ffing level. Include similar details for"+
                                              " any other locations which will be used "+
                                              "to provide this solution")
                         .withParser(englishParser)
                         .withHeadline("3. Provide details of the nearest service location including the address and staffing level. Include similar details for any other locations which will be used to provide this solution")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();



                new ClassificationTester("3.8.1  A Vendors RFP Response shall consist of the following documents"+
                                              ".")
                         .withParser(englishParser)
                         .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();



                new ClassificationTester("All documentation in this procurement ma"+
                                              "tter including all correspondence shall "+
                                              "be in the English language.")
                         .withParser(englishParser)
                         .withHeadline("4.4  Language")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();





                new ClassificationTester("If the vendor already provides TMS servi"+
                                              "ces then RFP response should contain als"+
                                              "o description of features and different "+
                                              "options available for customers.")
                         .withParser(englishParser)
                         .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();



                new ClassificationTester("It is a requirement of this RFP that Ven"+
                                              "dors provide a comprehensive pricing pro"+
                                              "posal for the operational and maintenanc"+
                                              "e activities and an indicative cost for "+
                                              "the implementation and migration phase.")
                         .withParser(englishParser)
                         .withHeadline("3.6  Cost / Pricing")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .withClassifier(new DeliveryClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                                 .withTag("")
                         )
                     .test();


             new ClassificationTester("3.8.1  A Vendors RFP Response shall consist of the following documents"+
                                           ".")
                      .withParser(englishParser)
                      .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                      .withProject(mockProject, mockDocument)
                      .withClassifier(new NumberClassifierEN())
                      .withClassifier(new DefinitionUsageClassifierEN())
                      .withClassifier(new DeliveryClassifierEN())
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Delivery, 1)
                              .withTag("")
                      )
                  .test();




            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }


}
