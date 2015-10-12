package classifiers.classifierTests;

import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.IPRClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.IprClassifierSV;
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
 *          Award Criteria tests
 *
 */


public class IPRClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }

    @Test
    public void testRisk(){
        try {
               new ClassificationTester("Resultatet (nedan \"Resultatet\") avser samtliga de resultat och allt material, oavsett lagrings- eller leveranssätt, " +
                       "som Leverantören själv tagit fram eller låtit ta fram vid fullgörandet av ett Uppdrag, såsom men inte begränsat till: utbildningars innehåll, " +
                       "dokumentation, information, anteckningar, beskrivningar, specifikationer, illustrationer, bilder, upptäckter, innovationer, know-how, uppfinningar, " +
                       "iakttagelser, processer, dokument, dataprogram, källkoder, verk och dylikt, oavsett om dessa är immaterialrättsligt skyddbara eller annars kan bli föremål " +
                       "för något skydd eller inte, som Leverantören under ett Uppdrag och/eller därefter har producerat/kommer att producera, eller på annat sätt medverkar till framställning " +
                       "av inom ramen för ett Uppdrag eller som annars uppkommer i samband med Leverantörens utförande av Uppdrag.")
                        .withParser(swedishParser)
                        .withHeadline("...")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new IprClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                .withTag("Abstract")
                        )
                    .test();

        } catch (Exception e) {
             e.printStackTrace();
             assertTrue(false);
        }
     }



    /***********************************************************
       *
       *      Testing Classification by examples for tag IPR
       *      Document:   " Avtal avseende IT-drift"
       *      Language:   "SV"
       *
       */


      @Test
      public void testAvtalavseendeITdriftExamples(){
          try {
                 new ClassificationTester("18.2.3 EHM ansvarar för erforderliga lic"+
                                               "ensavtal för EHM:s programvara. I EHM:s "+
                                               "ansvar ligger också att tillförsäkra att"+
                                               " licensavtalet tillåter Leverantören att"+
                                               " utföra Tjänsten avseende EHM:s programv"+
                                               "ara.")
                          .withParser(swedishParser)
                          .withHeadline("18.2.3 EHM ansvarar för erforderliga licensavtal för EHM:s programvara. I EHM:s ansvar ligger också att tillförsäkra att licensavtalet tillåter Leverantören att utföra Tjänsten avseende EHM:s programvara.")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new IprClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("18.2 Rättigheter till system, program, i"+
                                               "nformation och Dokumentation")
                          .withParser(swedishParser)
                          .withHeadline("18.2 Rättigheter till system, program, information och Dokumentation")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new IprClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("18.3.2 Leverantören överlåter till EHM a"+
                                               "ll upphovsrätt och i förekommande fall: "+
                                               "(i) samtliga andra Immateriella Rättighe"+
                                               "ter till Resultat, (ii) äganderätt till "+
                                               "samtliga de exemplar av Resultat som öve"+
                                               "rlämnas till EHM enligt Avtalet; samt (i"+
                                               "ii) till samtliga gjorda integrationer, "+
                                               "anpassningar med mera i EHM:s system ell"+
                                               "er EHM:s programvara utförda av Leverant"+
                                               "ören under Avtalet. Överlåtelsen enligt "+
                                               "denna punkt 18.3.1 är fullständig och in"+
                                               "nefattar rätt att ändra och vidareutveck"+
                                               "la Resultat samt vidareöverlåta respekti"+
                                               "ve vidareupplåta de immateriella rättigh"+
                                               "eterna till Resultat utan begränsning me"+
                                               "d avseende på överlåtelse-/upplåtelseked"+
                                               "jor eller annan omständighet. Alla immat"+
                                               "eriella rättigheter till Resultat tillko"+
                                               "mmer EHM så snart de skapats.")
                          .withParser(swedishParser)
                          .withHeadline("18.3.2 Leverantören överlåter till EHM all upphovsrätt och i förekommande fall: (i) samtliga andra Immateriella Rättigheter till Resultat, (ii) äganderätt till samtliga de exemplar av Resultat som överlämnas till EHM enligt Avtalet; samt (iii) till samtliga gjorda integrationer, anpassningar med mera i EHM:s system eller EHM:s programvara utförda av Leverantören under Avtalet. Överlåtelsen enligt denna punkt 18.3.1 är fullständig och innefattar rätt att ändra och vidareutveckla Resultat samt vidareöverlåta respektive vidareupplåta de immateriella rättigheterna till Resultat utan begränsning med avseende på överlåtelse-/upplåtelsekedjor eller annan omständighet. Alla immateriella rättigheter till Resultat tillkommer EHM så snart de skapats.")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new IprClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("18.2.4 Om inte Parterna överenskommit om"+
                                               " annat ska EHM inneha licenser där EHM a"+
                                               "nvisat viss plattform eller programvara."+
                                               " EHM ansvarar också för grundläggande av"+
                                               "tal med licensutgivare för dessa om inte"+
                                               " annat överenskoms (Tredjepartsprogramva"+
                                               "ra). Priser enligt Avtalet omfattar inte"+
                                               " dessa licenskostnader, om inte annat sä"+
                                               "rskilt anges. Det åligger dock Leverantö"+
                                               "ren att skriftligen inhämta godkännande "+
                                               "från EHM innan ny licens nyttjas eller t"+
                                               "as i bruk. Leverantören kan också efter "+
                                               "skriftliga anvisningar agera ombud åt EH"+
                                               "M vid beställning av licenser. I sådana "+
                                               "fall ska licenskostnader tydligt framgå "+
                                               "av beställning, samt särredovisas vid fa"+
                                               "kturering.")
                          .withParser(swedishParser)
                          .withHeadline("18.2.4 Om inte Parterna överenskommit om annat ska EHM inneha licenser där EHM anvisat viss plattform eller programvara. EHM ansvarar också för grundläggande avtal med licensutgivare för dessa om inte annat överenskoms (Tredjepartsprogramvara). Priser enligt Avtalet omfattar inte dessa licenskostnader, om inte annat särskilt anges. Det åligger dock Leverantören att skriftligen inhämta godkännande från EHM innan ny licens nyttjas eller tas i bruk. Leverantören kan också efter skriftliga anvisningar agera ombud åt EHM vid beställning av licenser. I sådana fall ska licenskostnader tydligt framgå av beställning, samt särredovisas vid fakturering.")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new IprClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("18.3.3 Avseende sådant material som Leve"+
                                               "rantören tillhandahåller under Avtalet o"+
                                               "ch som inte är EHM:s upplåter Leverantör"+
                                               "en i förekommande fall till EHM en icke-"+
                                               "exklusiv, evig, oåterkallelig, till full"+
                                               "o betald rätt att använda sådant materia"+
                                               "l för de syften som framgår")
                          .withParser(swedishParser)
                          .withHeadline("18.3.3 Avseende sådant material som Leverantören tillhandahåller under Avtalet och som inte är EHM:s upplåter Leverantören i förekommande fall till EHM en icke-exklusiv, evig, oåterkallelig, till fullo betald rätt att använda sådant material för de syften som framgår")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new IprClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                  .withTag("")
                          )
                      .test();


                 new ClassificationTester("18.2 Intrång i tredje mans immateriella "+
                                               "rättigheter")
                          .withParser(swedishParser)
                          .withHeadline("18.2 Intrång i tredje mans immateriella rättigheter")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new IprClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
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
        *      Testing Classification by examples for tag #IPR
        *      Document:   "  Request for ProposalNew TMSMain Document"
        *      Language:   "EN"
        *
        */


       @Test
       public void testNewTMSMainDocumentExamples(){
           try {

                  new ClassificationTester("4.2  Proprietary Information")
                           .withParser(englishParser)
                           .withHeadline("4.2  Proprietary Information")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierEN())
                           .withClassifier(new DefinitionUsageClassifierEN())
                           .withClassifier(new IPRClassifierEN())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.IPR, 1)
                                   .withTag("")
                           )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }

}
