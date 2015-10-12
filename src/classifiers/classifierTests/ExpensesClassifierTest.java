package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.ExpensesClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.ExpensesClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
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
 *          Expenses
 *
 */


public class ExpensesClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #EXPENSES
        *      Document:   " Participation Agreement ? TINC Silicon Valley"
        *      Language:   "EN"
        *
        */


       @Test
       public void testParticipationAgreementExamples(){
           try {
                  new ClassificationTester("- Travel costs and accommodations for the stay in San Francisco/Silicon Valley")
                           .withParser(englishParser)
                           .withHeadline("- Travel costs and accommodations for the stay in San Francisco/Silicon Valley")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ExpensesClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("- Travel costs and accommodations for the kick off in Oslo")
                           .withParser(englishParser)
                           .withHeadline("- Travel costs and accommodations for the kick off in Oslo")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ExpensesClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("- Travel insurance and other medical insurances that are necessary")
                           .withParser(englishParser)
                           .withHeadline("- Travel insurance and other medical insurances that are necessary")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ExpensesClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("- Living expenses")
                           .withParser(englishParser)
                           .withHeadline("- Living expenses")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new ExpensesClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
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
        *      Testing Classification by examples for tag #EXPENSES
        *      Document:   "Bilaga 2 - Prislista 20140815 Washed.xlsx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testBilaga2Examples(){
           try {
                  new ClassificationTester("De angivna prisposterna i denna bilaga ä"+
                                                "r den enda ersättning leverantör kommer "+
                                                "att erhålla för utförandet av Tjänsten. "+
                                                " Alla eventuella kostnader för utförande"+
                                                "t av Tjänsten (inklusive alla deltjänste"+
                                                "r) ska därför fördelas på de angivna pri"+
                                                "sposterna.\n(The  specified prices i"+
                                                "n this appendix is the only compensation"+
                                                " bidders will receive for execution of t"+
                                                "he Service. All costs for the execution "+
                                                "of the Service (incl. subservices) shall"+
                                                " be included in the specified prices)	"+
                                                "2;n")
                           .withParser(swedishParser)
                           .withHeadline("Förutsättningar (Assumptions)")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ExpensesClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
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
        *      Testing Classification by examples for tag #EXPENSES
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("6.4 Parterna ska kontinuerligt arbeta me"+
                                                "d förbättringar gällande tjänsteutveckli"+
                                                "ng, produktivitet och standardisering. D"+
                                                "essa åtgärder ska planeras och följas up"+
                                                "p gentemot EHM i en årlig förbättringspl"+
                                                "an. Arbetet med förslag på förbättringar"+
                                                " ska normalt bedrivas av resurserna inom"+
                                                " Samverkansmodellen där Parterna står fö"+
                                                "r sina respektive kostnader.")
                           .withParser(swedishParser)
                           .withHeadline("6.4 Parterna ska kontinuerligt arbeta med förbättringar gällande tjänsteutveckling, produktivitet och standardisering. Dessa åtgärder ska planeras och följas upp gentemot EHM i en årlig förbättringsplan. Arbetet med förslag på förbättringar ska normalt bedrivas av resurserna inom Samverkansmodellen där Parterna står för sina respektive kostnader.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ExpensesClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("10.2.3 Leverantören svarar själv för kos"+
                                                "tnader och tidsåtgång för byte av person"+
                                                "al enligt ovan och för att ny personal s"+
                                                "ka sätta sig in i arbetet (inklusive men"+
                                                " inte begränsat till alla resurser och k"+
                                                "ostnader som krävs för att på ett fullst"+
                                                "ändigt och adekvat sätt utbilda ny perso"+
                                                "nal avseende EHM:s verksamhet och behov)"+
                                                ".")
                           .withParser(swedishParser)
                           .withHeadline("10.2.3 Leverantören svarar själv för kostnader och tidsåtgång för byte av personal enligt ovan och för att ny personal ska sätta sig in i arbetet (inklusive men inte begränsat till alla resurser och kostnader som krävs för att på ett fullständigt och adekvat sätt utbilda ny personal avseende EHM:s verksamhet och behov).")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ExpensesClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
                               .withTag("")
                         )
                       .test();


                  new ClassificationTester("21.6 Vardera Part svarar för sina kostna"+
                                                "der i anledning av revision, dock att Le"+
                                                "verantören har rätt till skälig ersättni"+
                                                "ng för nedlagd tid i samband med revisio"+
                                                "nen. Om revision utvisar att Leverantöre"+
                                                "n i icke-oväsentlig mån har brutit mot s"+
                                                "ina åtaganden enligt Avtalet ska Leveran"+
                                                "tören ersätta EHM dess kostnader i samba"+
                                                "nd med genomförande av revisionen (inklu"+
                                                "sive eventuellt utbetald ersättning till"+
                                                " Leverantören för nedlagd tid i samband "+
                                                "med revisionen) och EHM:s skada p.g.a. L"+
                                                "everantörens avtalsbrott.")
                           .withParser(swedishParser)
                           .withHeadline("21.6 Vardera Part svarar för sina kostnader i anledning av revision, dock att Leverantören har rätt till skälig ersättning för nedlagd tid i samband med revisionen. Om revision utvisar att Leverantören i icke-oväsentlig mån har brutit mot sina åtaganden enligt Avtalet ska Leverantören ersätta EHM dess kostnader i samband med genomförande av revisionen (inklusive eventuellt utbetald ersättning till Leverantören för nedlagd tid i samband med revisionen) och EHM:s skada p.g.a. Leverantörens avtalsbrott.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ExpensesClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXPENSES, 1)
                               .withTag("")
                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


}
