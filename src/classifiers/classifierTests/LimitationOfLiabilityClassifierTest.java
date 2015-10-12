package classifiers.classifierTests;

import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.LimitationOfLiabilityClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class LimitationOfLiabilityClassifierTest extends AnalysisTest {



    @BeforeClass
    public static void preAmble(){

        init();
    }

    /***********************************************************
     *
     *          basic tests
     */


    @Test
    public void basicTest(){
        try {
               new ClassificationTester("Leverantörens sammanlagda skadeståndsskyldighet är begränsat till 2 Mkr per skadetillfälle")
                        .withParser(swedishParser)
                        .withHeadline("...")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new LimitationOfLiabilityClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                            .withTag("Uncapped")
                      )
                    .test();

            new ClassificationTester("Ansvarar för personskada enligt lag")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new LimitationOfLiabilityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                         .withTag("")
                         .withPattern("Ansvarar för personskada")
                   )
                 .test();

            new ClassificationTester("Ansvarar för förlust av data")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new LimitationOfLiabilityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                         .withTag("Uncapped")
                         .withPattern("förlust av data")
                   )
                 .test();


            new ClassificationTester("Med undantag från vad som anges i punkt 19.1-19.2 och/eller i Avtalet i övrigt avseende skadeståndsbegränsning, " +
                    "ska Part utge ersättning, utan begränsning, för all skada, dvs såväl direkt som indirekt, som uppkommer för motparten till följd av:")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new LimitationOfLiabilityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                         .withTag("Indirect")
                         .withPattern("skada , dvs såväl direkt som indirekt")
                   )
                 .test();

            new ClassificationTester("Leverantören ansvarar för krav från tredje part")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new LimitationOfLiabilityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                         .withTag("Indirect")
                         .withPattern("krav från tredje part")
                   )
                 .test();

            new ClassificationTester("Leverantörens ansvar för skada under Avtalet är begränsat till tio miljoner (10.000.000) SEK per skadestillfälle.")
                     .withParser(swedishParser)
                     .withHeadline("...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new LimitationOfLiabilityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                         .withTag("Uncapped")
                         .withPattern("ansvar för skada under Avtalet är begränsat till tio miljoner ( 10.000 . 000 ) SEK perskadestillfälle")
                   )
                 .test();



           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
    }





    /***********************************************************
     *
     *      Testing Classification by examples for tag LimitationOfLiability
     *      Document:   " Avtal avseende IT-drift"
     *      Language:   "SV"
     *
     */


    @Test
    public void testAvtalavseendeITdriftExamples(){
        try {
               new ClassificationTester("22. Ansvarsbegränsning")
                        .withParser(swedishParser)
                        .withHeadline("22. Ansvarsbegränsning")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new LimitationOfLiabilityClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
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
        *      Testing Classification by examples for tag #LIMITATION_OF_LIABILITY
        *      Document:   " Frågor Och Svar"
        *      Language:   "SV"
        *
        */


       @Test
       public void testFrågorOchSvarExamples(){
           try {
                  new ClassificationTester("driftstjänster är en parts ansvar för di"+
                                                "rekta skador begränsat till ca 15 % av d"+
                                                "en årliga ersättningen för tjänsterna. E"+
                                                "nligt 22.1och 22.3 begränsas parts ansva"+
                                                "r till sammantaget 100 % av den totala e"+
                                                "rsättningen.  Ansvaret inklude- rar dess"+
                                                "utom ansvar för tredjeparts direkta förl"+
                                                "uster.")
                           .withParser(swedishParser)
                           .withHeadline(" 2014-08-21")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new LimitationOfLiabilityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("Svar: Enligt punkten 22.3 i Huvudav"+
                                                "talet gäller följan"+
                                                "de: \"Till "+
                                                "förtydligande anges att"+
                                                " Leverantörens ansvar<"+
                                                "b> inkluderar att ("+
                                                "inom ramen f"+
                                                "ör ansvarsbegräns"+
                                                "ningarna i d"+
                                                "etta Avtal) ersätta EHM fö"+
                                                "r det fall s"+
                                                "kadelidande användare av <"+
                                                "/b>EHM:s system "+
                                                "och tjänster (såsom"+
                                                " apoteksaktörer) begär ska"+
                                                "destånd eller annan ersätt"+
                                                "ning från EHM som har si"+
                                                "n grund i Leverantörens "+
                                                "utförande av Tjänsterna och den skada "+
                                                "som sådan användare lider "+
                                                "utgör en direkt"+
                                                " förlust för den"+
                                                "ne.\". Således avses s"+
                                                "ådan skada som drabbar"+
                                                " EHM på grund av skada hos exempelv"+
                                                "is Apoteksaktör.")
                           .withParser(swedishParser)
                           .withHeadline(" 2014-07-03")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new LimitationOfLiabilityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                                   .withTag("")
                           )
                       .test();


                  new ClassificationTester("10. Huvudavtalet punkt 22.3 - Ansvar för"+
                                                " indirekt skada: Skada (direkt) som drab"+
                                                "bar EHM:s användare, t.ex. apoteksaktöre"+
                                                "r, ska anses som direkt skada även för E"+
                                                "HM. Vad avses? Kan EHM definiera vad som"+
                                                " ska ses som direkt skada?")
                           .withParser(swedishParser)
                           .withHeadline("10. Huvudavtalet punkt 22.3 - Ansvar för indirekt skada: Skada (direkt) som drabbar EHM:s användare, t.ex. apoteksaktörer, ska anses som direkt skada även för EHM. Vad avses? Kan EHM definiera vad som ska ses som direkt skada?")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new LimitationOfLiabilityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LIMITATION_OF_LIABILITY, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

}
