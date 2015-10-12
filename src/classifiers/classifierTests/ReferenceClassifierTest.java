package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.ReferenceClassifierEN;
import classifiers.swedishClassifiers.*;
import document.AbstractDocument;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Reference Test
 *
 *          Test the extraction of references.
 */


public class ReferenceClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();

    }


    @Test
    public void chapterReferences(){


        String[] chapterReferenceExamples = {

                "enligt punkt 27.1",
                "se även 27.1",
                "se även kapitel 27.1",
                "i punkt 27.1",
                "enligt kapitel 27.1",
                "i avsnitt 27.1",
                "i appendix 27.1",
                "i punkt 27.1",
                "enligt kapitel 27.1",
                "som framgår av stycke 27.1",
                "i enlighet med avsnitt 27.1",
        };


        try {

            for(String reference : chapterReferenceExamples){

                new ClassificationTester(reference)
                        .withParser(swedishParser)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new ReferenceClassifierSV())
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 1)
                                .withSemanticExtraction("27.1")
                                .withTag("Kapitel"))

                        .test();




            }




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    @Test
    public void chapterReferencesEN(){


        String[] chapterReferenceExamples = {

                "see also 27.1",
                "according to item 27.1",
                "in accordance with chapter 27.1",
                "in section 27.1",
                "in appendix 27.1",
                "under item 27.1",
                "described in Schedule 27.1",
                "set out in Schedule 27.1",
                "in accordance with clause 27.1",
                "pursuant to clause 27.1",
                "listed in Exhibit 27.1",
                "is attached to this Agreement as Exhibit 27.1",
                "is described in Exhibit 27.1",
                "contained in Exhibit 27.1",
                "provisions of this Exhibit 27.1",
                "set out in the Exhibit 27.1",
                "incorporated into this Agreement as Exhibit 27.1",
                "in accordance with Exhibit 27.1",
                "according to Exhibit 27.1",
                "as further specified in Exhibit 27.1",
                "as specified in Exhibit 27.1",
                "In Scope Services, but excluding Services under Exhibit 27.1",
                "included in Exhibits 27.1 (SOW).",
                "according to Attachment 27.1",
                "statement in Exhibit 27.1",
                "attached hereto as Exhibit 27.1",
                "transformation as required and stated in Exhibit 27.1",
                "defined in Exhibit 27.1",
                "As agreed in Exhibit 27.1",
                "set forth in Exhibit 27.1",
                "See Exhibit 27.1",
                "as stated in Appendix 27.1"
        };


        try {

            for(String reference : chapterReferenceExamples){

                new ClassificationTester(reference)
                        .withParser(englishParser)
                        .withClassifier(new NumberClassifierEN())
                        .withClassifier(new ReferenceClassifierEN())
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 1)
                                .withSemanticExtraction("27.1")
                                .withTag("Chapter"))

                        .test();




            }




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }





    @Test
    public void chapterReferencesByName(){


        String[] chapterReferenceExamples = {

                "enligt punkt Introduktion",
                "i punkt Introduktion",
                "enligt kapitel Introduktion",
                "i avsnitt Introduktion",
                "i appendix Introduktion",
                "i punkt Introduktion",
                "enligt kapitel Introduktion",
                "som framgår av stycke Introduktion",
                "i enlighet med avsnitt Introduktion",
        };


        try {

            for(String reference : chapterReferenceExamples){

                new ClassificationTester(reference)
                        .withParser(swedishParser)
                        //.withClassifier(new NumberClassifierSE())
                        .withClassifier(new ReferenceClassifierSV())
                        .withProject(mockProject, mockDocument)
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 1)
                                .withSemanticExtraction("Introduktion"))

                        .test();




            }




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    @Test
    public void documentReferencesByNumber(){


        String[] documentReferenceExamples = {

                "enligt Bilaga 1",
                "i Bilaga 1",
                "som framgår av bilaga 1",
                "i enlighet med bilaga 1",
        };

        AbstractDocument avtal = mockProject.documents.get(0);


        try {

            for(String reference : documentReferenceExamples){

                System.out.println("\n\n***********************\n" +
                        "testing: " + reference + "\n");

                new ClassificationTester(reference)
                        .withParser(swedishParser)
                        //.withClassifier(new NumberClassifierSE())
                        .withClassifier(new ReferenceClassifierSV())
                        .withProject(mockProject, avtal)
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 1)
                                .withSemanticExtraction("1"))

                        .test();

            }


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    /*******************************************************************
     *
     *          Matching a chapter reference should return the
     *          fragment key stored in the abstract reference
     *
     *
     */


    @Test
    public void closeReferenceForChapter(){

        try {

            new ClassificationTester("Introduktion")
                    .withParser(swedishParser)
                    .withClassifier(new OpenReferenceClassifierSV())
                    .withProject(mockProject, mockDocument)
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 1)
                            .withPattern("Introduktion")
                            .withSemanticExtraction("dummyKey1"))

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    /*****************************************************************
     *
     *          Trying to detect a reference called "bilaga X".
     *          This should match against the full name of bilaga
     *
     *
     */

    @Test
    public void closeReferenceTestBilaga(){


        try {

                new ClassificationTester("Bilaga 1")
                        .withParser(swedishParser)
                        .withClassifier(new OpenReferenceClassifierSV())
                        .withProject(mockProject, mockDocument)
                        .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 1)
                                .withPattern("Bilaga 1"))

                        .test();

            // No match here. There is no bilaga 2 in the mock project

            new ClassificationTester("Bilaga 2")
                    .withParser(swedishParser)
                    .withClassifier(new OpenReferenceClassifierSV())
                    .withProject(mockProject, mockDocument)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Reference, 0)

                    )

                    .test();

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
