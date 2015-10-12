package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.EmailClassifierEN;
import classifiers.swedishClassifiers.EmailClassifierSE;
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
 *          Email tests
 *
 */


public class EmailClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }



    @Test
    public void basicTest(){


        try {


            new ClassificationTester("This contains an email linus@gmail.com")
                    .withParser(englishParser)
                    .withClassifier(new EmailClassifierEN())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.EMAIL))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EMAIL, 1)
                            .withPattern("linus@gmail.com")
                            .withKeywords("address"))


                    .test();

            new ClassificationTester("This contains an email linus@gmail.com")
                    .withParser(swedishParser)
                    .withClassifier(new EmailClassifierSE())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.EMAIL))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EMAIL, 1)
                                                .withPattern("linus@gmail.com")
                                                .withKeywords("mailadress"))
                    .test();

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
