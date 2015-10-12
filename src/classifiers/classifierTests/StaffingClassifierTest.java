package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.StaffingClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.StaffingClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class StaffingClassifierTest extends AnalysisTest {



    @BeforeClass
    public static void preAmble(){

        init();
    }


    @Test
    public void basicTest(){
        try {
               new ClassificationTester(    "6.1.5 Leverantören ska kunna tillhandahålla personer med den kompetens och erfarenhet som framgår av Bilaga 1. I respektive Avropsavtal ska anges vilken/vilka person(er) som ska medverka i respektive Uppdrag. Leverantören ska i första hand alltid erbjuda det arbetsteam som presenterats i Bilaga 1. I arbetsteamet ska minst en projektledare, en produktionsledare och en manusförfattare/innehållsansvarig ingå. Om Leverantören inte kan ställa angivet arbetsteam eller däri ingående personer till förfogande för ett Uppdrag, och Las Vegas inte godkänt annat arbetsteam eller däri ingående personer, äger Las Vegas rätt att säga upp det aktuella Avropsavtalet till omedelbart upphörande."
               )
                        .withParser(swedishParser)
                        .withHeadline("...")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new StaffingClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("roll")
                        )
                    .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }





    /***********************************************************
        *
        *      Testing Classification by examples for tag Personnel
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("10. Personal och underleverantörer")
                           .withParser(swedishParser)
                           .withHeadline("10. Personal och underleverantörer")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
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
        *      Testing Classification by examples for tag #STAFFING
        *      Document:   "Bilaga 2 - Prislista 20140815 (CSC ENG unprotected 0.1).xlsx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testBilaga2PrislistaExamples(){
           try {

                  new ClassificationTester("Drifttekniker (Operations Technichian)")
                           .withParser(englishParser)
                           .withHeadline("Timpriser (Hour rates)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();


                  new ClassificationTester("Analytiker inom säkerhet\n(Analysts "+
                                                "within security)")
                           .withParser(englishParser)
                           .withHeadline("Överlämnande (Exit_Hand-over)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();



                  new ClassificationTester("Tekniker för teknisk utrustning\n(Te"+
                                                "chnichian for technichal equipment)")
                           .withParser(englishParser)
                           .withHeadline("Timpriser (Hour rates)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();


                  new ClassificationTester("Arkitekt (Architect)")
                           .withParser(englishParser)
                           .withHeadline("Timpriser (Hour rates)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();



                  new ClassificationTester("Drift DBA (Operations DBA)")
                           .withParser(englishParser)
                           .withHeadline("Timpriser (Hour rates)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();



                  new ClassificationTester("Analytiker inom driftperformance\n(A"+
                                                "nalysts within operations performance)")
                           .withParser(englishParser)
                           .withHeadline("Timpriser (Hour rates)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();


                  new ClassificationTester("Projektledare (Project Manager)")
                           .withParser(englishParser)
                           .withHeadline("Timpriser (Hour rates)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StaffingClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                   .withTag("roll")
                           )
                       .test();



              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    @Test
    public void testBilaga2PrislistaExamplesEN(){
        try {

               new ClassificationTester("Drifttekniker (Operations Technichian)")
                        .withParser(englishParser)
                        .withHeadline("Timpriser (Hour rates)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();


               new ClassificationTester("Analytiker inom säkerhet\n(Analysts "+
                                             "within security)")
                        .withParser(englishParser)
                        .withHeadline("Överlämnande (Exit_Hand-over)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();



               new ClassificationTester("Tekniker för teknisk utrustning\n(Te"+
                                             "chnichian for technichal equipment)")
                        .withParser(englishParser)
                        .withHeadline("Timpriser (Hour rates)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();


               new ClassificationTester("Arkitekt (Architect)")
                        .withParser(englishParser)
                        .withHeadline("Timpriser (Hour rates)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();



               new ClassificationTester("Drift DBA (Operations DBA)")
                        .withParser(englishParser)
                        .withHeadline("Timpriser (Hour rates)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();




               new ClassificationTester("Analytiker inom driftperformance\n(A"+
                                             "nalysts within operations performance)")
                        .withParser(englishParser)
                        .withHeadline("Timpriser (Hour rates)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();


               new ClassificationTester("Projektledare (Project Manager)")
                        .withParser(englishParser)
                        .withHeadline("Timpriser (Hour rates)")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new DefinitionUsageClassifierEN())
                        .withClassifier(new StaffingClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STAFFING, 1)
                                .withTag("role")
                        )
                    .test();



           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }

}
