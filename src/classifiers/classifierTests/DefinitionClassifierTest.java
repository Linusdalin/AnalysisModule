package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.DefinitionRepetitionClassifierEN;
import classifiers.englishClassifiers.DefinitionSourceClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.swedishClassifiers.*;
import document.AbstractDefinition;
import document.AbstractDocument;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Definition tests
 *
 *           - definition sources
 *           - definition usage
 *
 */


public class DefinitionClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){


        init();

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);
    }

    @Test
    public void basicTest(){


        try {

            new ClassificationTester("Med \"Avtalet\" avses det Avtal, med tillhörande bilagor, som ingåtts mellan Swedavia och Leverantören och till vilket Swedavias allmänna villkor utgör bilaga")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Avtalet"))

                    .test();

            new ClassificationTester("Med \"Avtalade specifikationer\" avses de specifikationer, krav och villkor angående kvantitet, kvalitet etc. som ska äga tillämpning på respektive Leveransåtagande inom ramen för Avtalet.")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Avtalade specifikationer"))

                    .test();


            new ClassificationTester("the technical and organisational measures necessary to protect the personal data are implemented. " +
                                        "When using a subcontractor (a “subprocessor”) who processes the Customer’s personal data, the " +
                    "Supplier, as the Customer’s representative, shall sign an agreement ")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("subprocessor"))


                    .test();



            new ClassificationTester("These Google Analytics Terms of Service ( this \"Mutual Agreement\") are entered into by Google Inc.")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Mutual Agreement"))


                    .test();

            new ClassificationTester("This is a definition (\"Buyer\")")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Buyer"))


                    .test();


            new ClassificationTester("Detta avtal (\"Avtalet\") gäller enligt...")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Avtalet"))


                    .test();

            new ClassificationTester("Dessa dokument ( tillsammans \"Avtalet\") beskriver...")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Avtalet"))


                    .test();

            new ClassificationTester("Firma AB Las Vegas med dotterbolag (se 1.5 nedan) (nedan kallad \"Las Vegas\") ")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Las Vegas")
                    )

                    .test();

            new ClassificationTester("Någonting (nedan kallad Någonting Annat)")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Någonting Annat")
                    )

                    .test();




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /*******************************************************************************
     *
     *          A\tB is sometimes a definition. The rule is:
     *
     *           - A must not contain a verb
     *           - B must be a sentencs (i.e. contain at least a verb an a noun
     *
     *
     */


    @Test
    public void whitespace(){


        try {

            new ClassificationTester("Kontaktperson:    []")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 0)
                    )

                    .test();




            new ClassificationTester("\t\t\"Resultatet\" avser samtliga de resultat och allt material, oavsett lagrings- eller leveranssätt, som Leverantören själv tagit fram eller låtit ta fram vid fullgörandet av ett Uppdrag, såsom men inte begränsat till: utbildningars innehåll, dokumentation, information, anteckningar, beskrivningar, specifikationer, illustrationer, bilder, upptäckter, innovationer, know-how, uppfinningar, iakttagelser, processer, dokument, dataprogram, källkoder, verk och dylikt, oavsett om dessa är immaterialrättsligt skyddbara eller annars kan bli föremål för något skydd eller inte, som Leverantören under ett Uppdrag och/eller därefter har producerat/kommer att producera, eller på annat sätt medverkar till framställning av inom ramen för ett Uppdrag eller som annars uppkommer i samband med Leverantörens utförande av Uppdrag. ")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Resultatet")
                    )

                    .test();


            new ClassificationTester("Firma\t[] (nedan kallad \"Leverantören\")")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Leverantören")
                    )

                    .test();

            // This is not a definition as the description is too short

            new ClassificationTester("Agreement\tthe document")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 0)
                    )

                    .test();

            // This is not a definition because the name contains a verb

            new ClassificationTester("Signing the Agreement\tmeans closing the deal")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 0)
                    )

                    .test();

            new ClassificationTester("Skriva på avtalet\tär ingen definition")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 0)
                    )

                    .test();

            // This is a definition

            new ClassificationTester("Agreement\tthis document with appendicies")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Agreement"))

                    .test();




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }



    @Test
    public void StartFromLeft(){


        try {


            new ClassificationTester("This is a document with tabs:")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                    )


                    .test();

            new ClassificationTester("The big test is the tabs and special characters like åäö !\\\"#¤%&/()[]{ and } ")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                    )


                    .test();

            /*

            new ClassificationTester("The Big Test is the tabs and special characters like åäö !\"#¤%&/()[]{ and } ")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Definition, 1)
                        .withPattern("The Big Test")
                    )


                    .test();

            */

            new ClassificationTester("Then there is a list:")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                    )


                    .test();



            new ClassificationTester("Agreement is the legally binding document")
                    .withHeadline("Definitions")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                    )


                    .test();

            new ClassificationTester("POP3: Post Office Protocol")
                    .withHeadline("Definitions")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                    )


                    .test();



            new ClassificationTester("It is the agreement is not a definition")
                    .withHeadline("Definitions")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                                       )


                    .test();

            new ClassificationTester("Hon är ingen definition")
                    .withHeadline("Definitioner")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0)
                    )


                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }




    @Test
    public void Headline(){


        try {


            new ClassificationTester("Agreement   is this document.")
                    .withHeadline("Definitions")
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Agreement"))


                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    @Test
    public void TagIsNotMatch(){


        AbstractDocument avtal = mockProject.documents.get(0);
        avtal.addDefinition(new AbstractDefinition("B", 0));

        try {

            new ClassificationTester("<b>Kalle</b> is not a match")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionUsageClassifierSV())
                    .withProject(mockProject, avtal)
                    .expectingNoNode(NodeClass.Type.DEFINITION_USAGE)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 0))


                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    @Test
    public void sourceIsNotDefinition(){


        AbstractDocument avtal = mockProject.documents.get(0);
        avtal.addDefinition(new AbstractDefinition("Buyer", 0));

        try {

            new ClassificationTester("This is a definition (\"Buyer\") but no reference")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .withClassifier(new DefinitionUsageClassifierSV())
                    .withProject(mockProject, avtal)
                    //.expectingNoNode(NodeType.DEFINITION_USAGE)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 0))


                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }



    @Test
    public void LeftColumn(){

        try {


            new ClassificationTester("Agreement")
                    .withHeadline("Definitions")
                    .withCellInfo(0, 0, 2)
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Agreement"))


                    .test();

            new ClassificationTester("Avtalet")
                    .withCellInfo(0, 0, 2)
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Avtalet"))


                    .test();


            new ClassificationTester("Agreement")
                    .withHeadline("Definitions")
                    .withCellInfo(0, 0, 3)
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 0)

                    )


                    .test();

            new ClassificationTester("Software & Parameter Load Specification")
                    .withCellInfo(0, 0, 2)
                    .withParser(englishParser)
                    .withClassifier(new DefinitionSourceClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Software & Parameter Load Specification")
                            .withTag("LeftColumn"))


                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    @Test
    public void correctDefinitionUsage(){


        String[] definitionUsageExamples = {

                "då skall Köparen erlägga",
                "på Säljarens inrådan",
        };

        AbstractDocument avtal = mockProject.documents.get(0);


        try {

            for(String definition : definitionUsageExamples){

                new ClassificationTester(definition)
                        .withParser(swedishParser)
                        //.withClassifier(new NumberClassifierSE())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withProject(mockProject, avtal)
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.DEFINITION))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                                .withTag("Korrekt"))

                        .test();




            }




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }




    @Test
    public void incorrectDefinitionUsage(){


        String[] definitionUsageExamples = {

                "då skall köparen erlägga",
                "på säljarens inrådan",
        };

        AbstractDocument avtal = mockProject.documents.get(0);


        try {

            for(String definition : definitionUsageExamples){

                new ClassificationTester(definition)
                        .withParser(swedishParser)
                        //.withClassifier(new NumberClassifierSE())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withProject(mockProject, avtal)
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.DEFINITION_USAGE))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                                .withTag("Inkorrekt"))

                        .test();




            }




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    @Test
    public void negativeTest(){

        try {


            new ClassificationTester("Möjligheter att begära in kompletterande uppgifter eller förtydliganden av anbudet är begränsade. För att det ska vara möjligt att pröva och utvärdera anbudet är det därför viktigt att anbudet innehåller samtliga begärda uppgifter och handlingar. Anbud som innehåller reservationer eller på annat sätt inte uppfyller ställda krav, saknar begärda uppgifter eller är ofullständiga, kan komma att uteslutas.")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_CONCEPT, 0))



                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
      *
      *      Testing Two definition usage in the same fragment
      *      Language:   "SV"
      *
      */


    @Test
    public void testTwoDefinitions(){
         try {
                new ClassificationTester("Både Säljaren och Köparen är överens om att")
                         .withParser(swedishParser)
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                                 .withPattern("Säljaren")
                                 .withTag("Korrekt"))
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 2)
                                 .withPattern("Köparen")
                                 .withTag("Korrekt")
                         )
                     .test();


            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }


    /***********************************************************
      *
      *      Testing Two definition usage with parts of word
      *      Language:   "SV"
      *
      */


    @Test
    public void testDefinitionPartsOfWord(){
         try {

                mockDocument.addDefinition(new AbstractDefinition("Part", 0));

                new ClassificationTester("Parts åttagande är en definitionsreferen")
                         .withParser(swedishParser)
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new NumberClassifierSV())
                         .withClassifier(new DefinitionUsageClassifierSV())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                                 .withPattern("Part")
                         )
                     .test();


            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }



     /***********************************************************
      *
      *       A definition could be repeated in the text.
      *
      *       Here we do not expect to find a definition usage for Terminal
      *
      */


    @Test
    public void testDefinitionRepeated(){
         try {

                mockDocument.addDefinition(new AbstractDefinition("Terminal", 0));
                mockDocument.addDefinition(new AbstractDefinition("TMS", 0)
                                                    .withDefinition("Terminal Management System"));

                new ClassificationTester("Swedbank looks for managed service what replaces current Terminal Management System that serves today circa 112.000 of 144.000 Swedbank's POSes")
                         .withParser(englishParser)
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new DefinitionRepetitionClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 0)

                         )
                     .test();


            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }

    @Test
    public void testSubsetDefinition(){
         try {

                mockDocument.addDefinition(new AbstractDefinition("POS terminal", 0));
                mockDocument.addDefinition(new AbstractDefinition("Terminal", 0));

                new ClassificationTester("looks for managed service what replaces POS terminals")
                         .withParser(englishParser)
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new DefinitionRepetitionClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                                 .withPattern("POS terminal")

                         )
                     .test();


            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }

    @Test
    public void testPartialMatchDefinition(){
         try {

                mockDocument.addDefinition(new AbstractDefinition("PSP", 0));

                new ClassificationTester(" new PSP’s and PSPs on the PSP's market.")
                         .withParser(englishParser)
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new DefinitionRepetitionClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                                 .withPattern("PSP"))
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 2)
                                 .withPattern("PSP"))
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 3)
                                 .withPattern("PSP")

                         )
                     .test();

             new ClassificationTester(" PSP is a definition and PSP;s and PSP's and PSPs. But not PSPort. And finally PSP")
                      .withParser(englishParser)
                      .withProject(mockProject, mockDocument)
                      .withClassifier(new DefinitionRepetitionClassifierEN())
                      .withClassifier(new DefinitionUsageClassifierEN())
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 1)
                              .withPattern("PSP"))
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 2)
                              .withPattern("PSP"))
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 3)
                              .withPattern("PSP"))
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 4)
                              .withPattern("PSP"))
                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 5)
                              .withPattern("PSP"))


                  .test();

            } catch (Exception e) {
                 e.printStackTrace();
                 assertTrue(false);
            }
         }


    @Test
    public void testNonClassifier(){
         try {

                mockDocument.addDefinition(new AbstractDefinition("Yes", 0));

                new ClassificationTester("The word Yes is not a classification usage")
                         .withParser(englishParser)
                         .withProject(mockProject, mockDocument)
                         .withClassifier(new DefinitionRepetitionClassifierEN())
                         .withClassifier(new DefinitionUsageClassifierEN())
                         .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 0)

                         )
                     .test();

         } catch (Exception e) {
              e.printStackTrace();
              assertTrue(false);
         }
      }

    @Test
    public void testEmbedded(){

        try {

            new ClassificationTester("Säljaren och Köparen är nedan var för sig benämnda \"Part\".")
                    .withParser(swedishParser)
                    .withClassifier(new ClassifiedNounClassifierSV())
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Part")
                    )

                    .test();

         } catch (Exception e) {
              e.printStackTrace();
              assertTrue(false);
         }
      }

    /********************************************************************'
     *
     *          Here is a duplicate definition. It shall NOT be classified as a definition usage
     *
     *
     */


    @Test
    public void testDuplicate(){

        try {
            mockDocument.addDefinition(new AbstractDefinition("Ramavtalet", 0));

            new ClassificationTester("Parterna har denna dag ingått följande ramavtal (\"Ramavtalet\").")
            //new ClassificationTester("Parterna har denna dag ingått följande Ramavtalet.")
                    .withParser(swedishParser)
                    .withProject(mockProject, mockDocument)
                    .withClassifier(new ClassifiedNounClassifierSV())
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .withClassifier(new DefinitionUsageClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 0)

                    )

                    .test();

         } catch (Exception e) {
              e.printStackTrace();
              assertTrue(false);
         }
      }


    /********************************************************************'
     *
     *          These do not work. They should be fixed
     *
     *
     */


    @Test
    public void toBeFixed(){

            new ClassificationTester("\"Avropsavtal\" avser de vid var tid mellan Parterna ingångna avtal som enligt dess lydelse utgör Avropsavtal till detta Ramavtal. Avropsavtal ska utformas i enlighet med Bilaga 4.")
                    .withParser(swedishParser)
                    .withClassifier(new DefinitionSourceClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION_USAGE, 0))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.DEFINITION, 1)
                            .withPattern("Avropsavtal")
                    )

                    .test();

    }

}
