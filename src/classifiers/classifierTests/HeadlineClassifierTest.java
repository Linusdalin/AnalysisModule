package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.englishClassifiers.HeadlineClassifierEN;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Headline tests
 *
 */


public class HeadlineClassifierTest extends AnalysisTest {

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


            new ClassificationTester("1.0 Chapter").withHeadline("1.0 Chapter")
                    .withParser(englishParser)
                    .withClassifier(new HeadlineClassifierEN())
                            .expectingParseNode(NodeClass.Type.HEADLINE, 1)

                            .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
