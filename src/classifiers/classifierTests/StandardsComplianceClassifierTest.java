package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.StandardsComplianceClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.StandardsComplianceClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Standards compliance
 *
 */


public class StandardsComplianceClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    /*****************************************************************************'
     *
     *
     *          Detecting some basic standards
     *
     */

    //TODO: "x.690" has a bug in extracting the value. The regex is actually found

    private static final String[] exampleStandards = {"ITU x.690", "ISO 27001",  "ISO8851-5", "ISO 8855", "SNI 2007"};

    @Test
    public void basicTest(){

        try {

            for (String exampleStandard : exampleStandards) {

                System.out.println("Testing: Detta är en standard " + exampleStandard);

                new ClassificationTester("Detta är en standard " + exampleStandard)
                            .withParser(swedishParser)
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new StandardsComplianceClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                    .withPattern(exampleStandard)
                            )
                        .test();

                System.out.println("Testing: this is a standard " + exampleStandard);

                new ClassificationTester("this is a standard " + exampleStandard)
                            .withParser(englishParser)
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierEN())
                            .withClassifier(new DefinitionUsageClassifierEN())
                            .withClassifier(new StandardsComplianceClassifierEN())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                    .withPattern(exampleStandard)
                            )
                        .test();


            }




        } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
        }
    }


    @Test
    public void testCaseSensitive(){

        try {

            new ClassificationTester("The abbr PER is a standard")
                        .withParser(englishParser)
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new StandardsComplianceClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                .withPattern("PER")
                        )
                    .test();

            new ClassificationTester("The word per is not a standard")
                        .withParser(englishParser)
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new StandardsComplianceClassifierEN())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 0)

                        )
                    .test();

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }



    /***********************************************************
        *
        *      Testing Classification by examples for tag #STANDARDS_COMPLIANCE
        *      Document:   "Avtalsbilaga 2 EHM_s krav på Tjänsten.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalsbilaga2EHMExamples(){
           try {

               new ClassificationTester("12.14  Datacentra utanför Sverige får inte användas utan skriftligt godkännande från Swedavia. Datacentra ska vara ISO27001 certifierade. " +
                       "Detta gäller inte när Swedavia använder en cloudtjänst.")
                        .withParser(swedishParser)
                        .withHeadline("...")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new StandardsComplianceClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                .withTag("Security")
                                .withPattern("ISO27001")
                                .withSemanticExtraction("27001")
                        )
                    .test();


                  new ClassificationTester("4.10.2 Leverantören ska arbeta med Servi"+
                                                "ce Asset and Configuration Management en"+
                                                "ligt ITIL v.2011 eller motsvarande ramve"+
                                                "rk och i samverkan med EHM definiera vil"+
                                                "ka Assets och Configuration Items som sk"+
                                                "a ingå.")
                           .withParser(swedishParser)
                           .withHeadline("4.10.2 Leverantören ska arbeta med Service Asset and Configuration Management enligt ITIL v.2011 eller motsvarande ramverk och i samverkan med EHM definiera vilka Assets och Configuration Items som ska ingå.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StandardsComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("4.5.1 Leverantören ska bedriva ett heltä"+
                                                "ckande och processorienterat information"+
                                                "ssäkerhetsarbete, baserat på ISO 27000 e"+
                                                "ller motsvarande standard, med ett tydli"+
                                                "gt gränssnitt för samverkan in mot EHM f"+
                                                "ör att säkerställandet av uppfyllandet a"+
                                                "v Servicenivåer och säkerhetskrav.")
                           .withParser(swedishParser)
                           .withHeadline("4.5.1 Leverantören ska bedriva ett heltäckande och processorienterat informationssäkerhetsarbete, baserat på ISO 27000 eller motsvarande standard, med ett tydligt gränssnitt för samverkan in mot EHM för att säkerställandet av uppfyllandet av Servicenivåer och säkerhetskrav.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StandardsComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                   .withTag("Security")
                                   .withPattern("ISO 27000")
                           )
                       .test();


                  new ClassificationTester("3.7 Leverantören ska arbeta i enlighet m"+
                                                "ed ITIL v. 2011 nomenklatur och processe"+
                                                "r eller liknande ramverk. Då Tjänsten ha"+
                                                "r olika karaktär och mognad kan gränssni"+
                                                "tten mellan Leverantören och EHM komma a"+
                                                "tt förändras i enlighet med Samverkansmo"+
                                                "dellen. Det är Leverantörens ansvar att "+
                                                "tydligt definiera, kommunicera och säker"+
                                                "ställa effektiva processer och gränssnit"+
                                                "t för Tjänsten.")
                           .withParser(swedishParser)
                           .withHeadline("3.7 Leverantören ska arbeta i enlighet med ITIL v. 2011 nomenklatur och processer eller liknande ramverk. Då Tjänsten har olika karaktär och mognad kan gränssnitten mellan Leverantören och EHM komma att förändras i enlighet med Samverkansmodellen. Det är Leverantörens ansvar att tydligt definiera, kommunicera och säkerställa effektiva processer och gränssnitt för Tjänsten.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StandardsComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("5.2.2 Samtliga processer ska utföras i e"+
                                                "nlighet med ITIL v. 2011 Service Lifecyc"+
                                                "le Processes eller liknande ramverk.")
                           .withParser(swedishParser)
                           .withHeadline("5.2.2 Samtliga processer ska utföras i enlighet med ITIL v. 2011 Service Lifecycle Processes eller liknande ramverk.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StandardsComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("4.11.2 Leverantören ska tillhandahålla e"+
                                                "n SPOC som är primär kontaktyta mot EHM "+
                                                "enligt ITIL v.2011 eller motsvarande ram"+
                                                "verk")
                           .withParser(swedishParser)
                           .withHeadline("4.11.2 Leverantören ska tillhandahålla en SPOC som är primär kontaktyta mot EHM enligt ITIL v.2011 eller motsvarande ramverk")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new StandardsComplianceClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
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
        *      Testing Classification by examples for tag #STANDARDS_COMPLIANCE
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
                           .withClassifier(new StandardsComplianceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                   .withTag("")
                           )
                       .test();
   
   

                  new ClassificationTester("1. If you have a formal environmental ma"+
                                                "nagement system then please provide deta"+
                                                "ils of this and whether it is a certifie"+
                                                "d system.  If you plan to seek certifica"+
                                                "tion to an environmental management syst"+
                                                "em standard, such as ISO14001 or the Eco"+
                                                " Management and Audit Scheme (EMAS) then"+
                                                " state this and your timescale.  (Refer "+
                                                "to section 1.13, f)")
                           .withParser(englishParser)
                           .withHeadline("1. If you have a formal environmental management system then please provide details of this and whether it is a certified system.  If you plan to seek certification to an environmental management system standard, such as ISO14001 or the Eco Management and Audit Scheme (EMAS) then state this and your timescale.  (Refer to section 1.13, f)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new StandardsComplianceClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.STANDARDS_COMPLIANCE, 1)
                                   .withTag("environment")
                                   .withSemanticExtraction("14001")
                           )
                       .test();


   
              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }
    

}
