package classifiers.classifierTests;

import classifiers.swedishClassifiers.*;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class AuditClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag Audit
        *      Document:   "Huvudavtal_EHM_IT_Drift.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testHuvudavtal_EHM_IT_DriftExamples(){
           try {
                  new ClassificationTester("Leverantören ska i avtal med underlevera"+
                                                "ntör, tillförsäkra EHM rätt att genomför"+
                                                "a revision av sådan leverantör såsom ang"+
                                                "es i denna punkt 21.")
                           .withParser(swedishParser)
                           .withHeadline("22 Uppföljning av avtalat åtagande")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AuditClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.AUDIT, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Revision ska ske under Leverantörens kon"+
                                                "torstid. Revision ska påkallas senast fe"+
                                                "m (5) Arbetsdagar före planerat genomför"+
                                                "ande. Revision av Leverantören kan även "+
                                                "påkallas och utföras av myndighet som ut"+
                                                "övar tillsyn över den verksamhet som bed"+
                                                "rivs av EHM, varvid begränsningen ovan i"+
                                                "nte ska äga tillämpning, utan Leverantör"+
                                                "en ska medverka fullt ut vid revision en"+
                                                "ligt myndighetens krav. Vid Incidenter s"+
                                                "om innebär att samhällskritiska funktion"+
                                                "er störs i väsentlig mån, kan revision p"+
                                                "åkallas utan de begräsningar som anges o"+
                                                "van.")
                           .withParser(swedishParser)
                           .withHeadline("22 Uppföljning av avtalat åtagande")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AuditClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.AUDIT, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Vardera Part svarar för sina kostnader i"+
                                                " anledning av revision, dock att Leveran"+
                                                "tören har rätt till skälig ersättning fö"+
                                                "r nedlagd tid i samband med revisionen. "+
                                                "Om revision utvisar att Leverantören i i"+
                                                "cke-oväsentlig mån har brutit mot sina å"+
                                                "taganden enligt Avtalet ska Leverantören"+
                                                " ersätta EHM dess kostnader i samband me"+
                                                "d genomförande av revisionen (inklusive "+
                                                "eventuellt utbetald ersättning till Leve"+
                                                "rantören för nedlagd tid i samband med r"+
                                                "evisionen) och EHM:s skada p.g.a. Levera"+
                                                "ntörens avtalsbrott.")
                           .withParser(swedishParser)
                           .withHeadline("22 Uppföljning av avtalat åtagande")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AuditClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.AUDIT, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("\nEHM, part med uppgift att utföra r"+
                                                "evision eller granskning av EHM eller av"+
                                                " EHM anvisad tredje part, ska ha rätt at"+
                                                "t hos Leverantören kontrollera att avtal"+
                                                "ade åtaganden efterlevs i enlighet med a"+
                                                "vtalade villkor. Leverantören ska, för a"+
                                                "tt uppfylla åtagandet i denna punkt 21 v"+
                                                "ara beredd att tillhandahålla erforderli"+
                                                "g information och medge tillträde till l"+
                                                "okaler och utrustning som används för at"+
                                                "t utföra avtalat åtagande.")
                           .withParser(swedishParser)
                           .withHeadline("22 Uppföljning av avtalat åtagande")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AuditClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.AUDIT, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Leverantören ska utan tillkommande kostn"+
                                                "ad tillhandahålla erforderligt underlag "+
                                                "för att genomföra kontroller. Den som ut"+
                                                "för revisionen ska följa Leverantörens s"+
                                                "käliga säkerhetsföreskrifter. Leverantör"+
                                                "en har rätt att övervaka revision.")
                           .withParser(swedishParser)
                           .withHeadline("22 Uppföljning av avtalat åtagande")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new AuditClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.AUDIT, 1)
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
      *      Testing Classification by examples for tag #AUDIT
      *      Document:   "Bilaga 3B - Personuppgiftsbitr??desavtal.docx"
      *      Language:   "unknown"
      *
      */


     @Test
     public void testLVExamples(){
         try {
            // Found 1 re-classifications

                new ClassificationTester("2.8  Part har rätt att på egen bekostnad själv eller genom tredje"+
                                    " man kontrollera att Personuppgiftsbiträdet följer detta avtal. Person"+
                                    "uppgiftsbiträdet skall därvid lämna Las Vegas den assistans som behövs"+
                                    ".")
                         .withParser(englishParser)
                         .withHeadline(" add headline...")
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .withClassifier(new PartsClassifierSV())
                         .withClassifier(new AuditClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.AUDIT, 1)
                             .withPattern("Part har rätt att på egen bekostnad själv eller genom tredje man kontrollera")

                       )
                     .test();


            } catch (Exception e) {
                 e.printStackTrace(System.out);
                 assertTrue(false);
            }
         }


}
