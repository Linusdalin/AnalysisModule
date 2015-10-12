package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.ClassifiedNounClassifierEN;
import classifiers.swedishClassifiers.ClassifiedNounClassifierSV;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *            Testing a classified entity like:
 *
 *              - a big house
 *
 *
 *              The classified entity should return the noun as a semantic extraction which in turn can be used in another rule
 *
 */


public class ClassifiedNounTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
     *
     *          Basic tests
     *
     */

    @Test
    public void basicTest(){


        try {


            new ClassificationTester("A big house is CE")
                    .withParser(englishParser)
                    .withClassifier(new ClassifiedNounClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.CLASSIFIED_ENTITY)
                            .expectingNodeText("house")
                    )

                    .test();


            new ClassificationTester("A house is also CE")
                    .withParser(englishParser)
                    .withClassifier(new ClassifiedNounClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.CLASSIFIED_ENTITY))

                    .test();

            new ClassificationTester("Actually a classified entity is also CE")
                    .withParser(englishParser)
                    .withClassifier(new ClassifiedNounClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.CLASSIFIED_ENTITY ))

                    .test();

            new ClassificationTester("A named entity is also CE")
                    .withParser(englishParser)
                    .withClassifier(new ClassifiedNounClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.CLASSIFIED_ENTITY)
                            .expectingNodeText("entity")
                    )
                    .test();

            new ClassificationTester("writing entity is also CE")
                    .withParser(englishParser)
                    .withClassifier(new ClassifiedNounClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.CLASSIFIED_ENTITY)
                            .expectingNodeText("entity")
                    )
                    .test();


            new ClassificationTester("En skada")
                    .withParser(swedishParser)
                    .withClassifier(new ClassifiedNounClassifierSV())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.CLASSIFIED_ENTITY)
                        .expectingNodeText("skada")

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }





}
