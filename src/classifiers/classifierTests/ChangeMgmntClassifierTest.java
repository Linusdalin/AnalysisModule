package classifiers.classifierTests;

import classifiers.swedishClassifiers.ChangeMgmntClassifierSV;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
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
 *
 */


public class ChangeMgmntClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
       *
       *      Testing Classification by examples for tag #CHANGE_MGMNT
       *      Document:   " Frågor Och Svar"
       *      Language:   "SV"
       *
       */


      @Test
      public void testFrågorOchSvarExamples(){

          try {
                 new ClassificationTester("6. Vad avser \"Ändringshantering\""+
                                               " i namnet på Avtalsbilaga 6, Samverkan"+
                                               " och Ändringshantering. Är det \"Cha"+
                                               "nge management\" enligt ITIL eller ä"+
                                               "r det av- talsförändringar såsom volymer"+
                                               " och förändringar i tjänsteleveransen so"+
                                               "m av- ses?")
                          .withParser(swedishParser)
                          .withHeadline("6. Vad avser \"Ändringshantering\" i namnet på Avtalsbilaga 6, Samverkan och Ändringshantering. Är det \"Change management\" enligt ITIL eller är det av- talsförändringar såsom volymer och förändringar i tjänsteleveransen som av- ses?")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ChangeMgmntClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CHANGE_MGMNT, 1)
                                  .withTag("process")
                          )
                      .test();


             } catch (Exception e) {
                  e.printStackTrace();
                  assertTrue(false);
             }
          }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #CHANGE_MGMNT
        *      Document:   " Avtal avseende IT-drift"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalavseendeITdriftExamples(){
           try {
                  new ClassificationTester("för Ändringshantering. Om Leverantören u"+
                                                "tför en viss tjänst, aktivitet, funktion"+
                                                " eller annan åtgärd utan att ha lämnat m"+
                                                "eddelande till EHM enligt denna punkt sk"+
                                                "a åtgärden i fråga alltid anses som en d"+
                                                "el av Tjänsten och omfattas av och ingå "+
                                                "i avtalad ersättning.")
                           .withParser(swedishParser)
                           .withHeadline("6. Avtalets omfattning och Tjänsten")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ChangeMgmntClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CHANGE_MGMNT, 1)
                               .withTag("process")
                         )
                       .test();


                  new ClassificationTester("6.7 För det fall Leverantören anser att "+
                                                "viss aktivitet, funktion eller annan åtg"+
                                                "ärd inte omfattas av Tjänsten utan istäl"+
                                                "let utgör en Konsulttjänst eller annan å"+
                                                "tgärd och för vilken Leverantören anser "+
                                                "sig berättigad till ersättning utöver va"+
                                                "d som anges i Bilaga 8 (Ersättning och P"+
                                                "riser), åligger det Leverantören att sna"+
                                                "rast - och senast inom tio (10) kalender"+
                                                "dagar från den tidpunkt då Leverantören "+
                                                "uppmärksammade eller borde ha uppmärksam"+
                                                "mat behovet av den aktuella aktiviteten,"+
                                                " funktionen eller åtgärden - skriftligen"+
                                                " meddela EHM sin uppfattning. En eventue"+
                                                "ll förändring av Tjänsten, om behov av s"+
                                                "ådan förändring konstateras föreligga, s"+
                                                "ka överenskommas genom processen")
                           .withParser(swedishParser)
                           .withHeadline("6.7 För det fall Leverantören anser att viss aktivitet, funktion eller annan åtgärd inte omfattas av Tjänsten utan istället utgör en Konsulttjänst eller annan åtgärd och för vilken Leverantören anser sig berättigad till ersättning utöver vad som anges i Bilaga 8 (Ersättning och Priser), åligger det Leverantören att snarast - och senast inom tio (10) kalenderdagar från den tidpunkt då Leverantören uppmärksammade eller borde ha uppmärksammat behovet av den aktuella aktiviteten, funktionen eller åtgärden - skriftligen meddela EHM sin uppfattning. En eventuell förändring av Tjänsten, om behov av sådan förändring konstateras föreligga, ska överenskommas genom processen")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new ChangeMgmntClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.CHANGE_MGMNT, 1)
                               .withTag("process")
                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


}
