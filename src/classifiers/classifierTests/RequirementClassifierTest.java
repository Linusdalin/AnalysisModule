package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.ReqSpecClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.ReqSpecClassifierSV;
import document.AbstractDefinition;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class RequirementClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();

        mockDocument.addDefinition(new AbstractDefinition("Part1", 0));

    }



    @Test
       public void testAnbudsförfråganITdrift2014Examples(){

            mockDocument.addDefinition(new AbstractDefinition("Tjänsten", 0));
            mockDocument.addDefinition(new AbstractDefinition("Leverantören", 0));

           try {
                  new ClassificationTester("För krav på Tjänsten hänvisas till Avtal"+
                                                "sbilaga 2 (EHM:s krav på Tjänsten). ")
                           .withParser(swedishParser)
                           .withProject(mockProject, mockDocument)
                           .withHeadline("1.2 Upphandlingens omfattning")
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ReqSpecClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                                   .withTag("")
                           )
                       .test();

                  new ClassificationTester("Då Tjänsten ska utföras på ett sådan"+
                                                "t sätt att EHM får en säker, samordnad o"+
                                                "ch ändamålsenlig leverans. Tjänsten s"+
                                                "ka vara skalbar, tillgänglig och ha "+
                                                "hög prestanda. Tjänsten och Leverantören"+
                                                "s agerande under Avtalet ska präg"+
                                                "las av proaktivitet och Leverantören "+
                                                "ska arbeta med ständiga förbättringa"+
                                                "r för att säkerställa en kostnadseffekti"+
                                                "v och driftsäker leverans. Leverantören "+
                                                "förväntas genom samarbete med EHM få en "+
                                                "god förståelse för EHM:s verksamheter. ")
                           .withParser(swedishParser)
                          .withProject(mockProject, mockDocument)
                           .withHeadline("1.2 Upphandlingens omfattning")
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ReqSpecClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                                   .withTag("")
                           )
                       .test();

                  new ClassificationTester("5 Krav på Tjänsten")
                           .withParser(swedishParser)
                          .withProject(mockProject, mockDocument)
                           .withHeadline("5 Krav på Tjänsten")
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ReqSpecClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                                   .withTag("")
                           )
                       .test();

                  new ClassificationTester("4 Krav på Leverantören")
                           .withParser(swedishParser)
                          .withProject(mockProject, mockDocument)
                           .withHeadline("4 Krav på Leverantören")
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ReqSpecClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                                   .withTag("")
                           )
                       .test();

               new ClassificationTester("Då Tjänsten ska utföras på ett sådan"+
                                             "t sätt att EHM får en säker, samordnad o"+
                                             "ch ändamålsenlig leverans. Tjänsten s"+
                                             "ka vara skalbar, tillgänglig och ha "+
                                             "hög prestanda. ")
                        .withParser(swedishParser)
                       .withProject(mockProject, mockDocument)
                        .withHeadline("1.2 Upphandlingens omfattning")
                       .withClassifier(new NumberClassifierSV())
                       .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new ReqSpecClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
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
        *      Testing Classification by examples for tag #REQ_SPEC
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */
   
   
       @Test
       public void testNewTMSMainDocumentExamples(){
           try {

                  new ClassificationTester("It is not only important to provide POS "+
                                                "terminals with correct parameters, it is"+
                                                " also important to provide them in an ef"+
                                                "ficient way; safe, fast and at the right"+
                                                " time. The current system is very sophis"+
                                                "ticated and flexible and it is important"+
                                                " that the new system is even more effici"+
                                                "ent to keep and increece the acquirer bu"+
                                                "siness. It is important that all termina"+
                                                "ls can be managed separately, as they ca"+
                                                "n have a unique set up. All terminals ca"+
                                                "n belong to a certain profile/group but "+
                                                "must be able to be changed separately wi"+
                                                "th a custom set up, since this is a requ"+
                                                "irement from the customers' business.")
                           .withParser(englishParser)
                           .withHeadline("2.2  Background & Supporting Information")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ReqSpecClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                               .withTag("")
                         )
                       .test();
   
   



                  new ClassificationTester("To enable the easy identification of the"+
                                                " electronically sent response documents,"+
                                                " Vendors shall use the following file na"+
                                                "ming convention:")
                           .withParser(englishParser)
                           .withHeadline("3.8.1  A Vendors RFP Response shall consist of the following documents.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ReqSpecClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                               .withTag("")
                         )
                       .test();
   
   

                  new ClassificationTester("— Sweden is the principal market b"+
                                                "ut the solution should be capable of man"+
                                                "aging terminals routed from :")
                           .withParser(englishParser)
                           .withHeadline("— Sweden is the principal market but the solution should be capable of managing terminals routed from :")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ReqSpecClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                               .withTag("")
                         )
                       .test();
   
   

                  new ClassificationTester("The number of directly and indirectly te"+
                                                "rminals will change over time as large c"+
                                                "ustomers change their infrastructure and"+
                                                " new PSP's occur on the market. The capa"+
                                                "city of the system must however remain r"+
                                                "egardless of how many terminals that are"+
                                                " directly and indirectly managed.")
                           .withParser(englishParser)
                           .withHeadline("1.1  Scope ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ReqSpecClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
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
        *
        */


       @Test
       public void basicTest(){
           try {

                  new ClassificationTester("it must be")
                           .withParser(englishParser)
                           .withHeadline("2.2  Requirements")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ReqSpecClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                               .withTag("")
                         )
                       .test();


               new ClassificationTester("must be handled")
                        .withParser(englishParser)
                        .withHeadline("2.2  Requirements")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new ReqSpecClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("should be able")
                        .withParser(englishParser)
                        .withHeadline("2.2  Requirements")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new ReqSpecClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.SolutionReq, 1)
                            .withTag("")
                      )
                    .test();



              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }



}
