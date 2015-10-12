package classifiers.classifierTests;

import classifiers.englishClassifiers.ConfidentialityClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.ConfidentialityClassifierSV;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Email tests
 *
 */


public class ConfidentialityClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag Confidentiality
        *      Document:   "Anbudsförfrågan IT-drift 2014.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAnbudsförfråganITdrift2014Examples(){
           try {
                  new ClassificationTester("2.12 Offentlighetsprincipen")
                           .withParser(swedishParser)
                           .withHeadline("2.12 Offentlighetsprincipen")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ConfidentialityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("Legal")
                                   .withPattern("Offentlighetsprincipen")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

    /***********************************************************
        *
        *      Testing Classification by examples for tag #CONFIDENTIALITY
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void basicTest(){
           try {
                  new ClassificationTester("17. Sekretess")
                           .withParser(swedishParser)
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ConfidentialityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                               .withTag("Sekretess")
                               .withPattern("Sekretess")
                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #CONFIDENTIALITY
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {
                  new ClassificationTester("— shall be treated as strictly confidential;")
                           .withParser(englishParser)
                           .withHeadline("— shall be treated as strictly confidential;")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ConfidentialityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("")
                           )
                       .test();



                  new ClassificationTester("ALL INFORMATION CONTAINED WITHIN THIS DO"+
                                                "CUMENT IS CONFIDENTIAL AND IS PROVIDED O"+
                                                "NLY TO GIVE SUPPLIERS AN ADEQUATE UNDERS"+
                                                "TANDING OF SWEDBANK'S REQUIREMENTS. UNDE"+
                                                "R NO CIRCUMSTANCES SHOULD INFORMATION BE"+
                                                " DISCLOSED TO ANY OUTSIDE PARTY WITHOUT "+
                                                "PRIOR WRITTEN CONSENT FROM SWEDBANK.")
                           .withParser(englishParser)
                           .withHeadline("---")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ConfidentialityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("4.1  Confidentiality")
                           .withParser(englishParser)
                           .withHeadline("4.1  Confidentiality")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ConfidentialityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("(b) Disclose the proposal on a confident"+
                                                "ial basis to third parties retained by S"+
                                                "wedbank to assist it with the RFP proces"+
                                                "s as well as to Swedbank´s subsidiaries "+
                                                "and parental companies and, as the case "+
                                                "may be, the members of the Swedish Savin"+
                                                "gs Banks Association (\"Sparbankerna"+
                                                "s Riksförbund\").")
                           .withParser(englishParser)
                           .withHeadline("(b) Disclose the proposal on a confidential basis to third parties retained by Swedbank to assist it with the RFP process as well as to Swedbank´s subsidiaries and parental companies and, as the case may be, the members of the Swedish Savings Banks Association (\"Sparbankernas Riksförbund\").")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ConfidentialityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("All responses and material submitted to "+
                                                "Swedbank will be used for the Swedbank i"+
                                                "nternal RFP process, any RFP or contract"+
                                                " negotiation process and may be returned"+
                                                " at the discretion of Swedbank. Supplier"+
                                                " responses or specific information in th"+
                                                "ose responses marked as confidential wil"+
                                                "l be held in confidence during the revie"+
                                                "w process. ")
                           .withParser(englishParser)
                           .withHeadline("4  RFP TERMS AND CONDITIONS")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ConfidentialityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("CONFIDENTIALITY")
                           .withParser(englishParser)
                           .withHeadline("---")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ConfidentialityClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CONFIDENTIALITY, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

}
