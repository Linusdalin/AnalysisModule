package classifiers.classifierTests;

import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Sentence tests
 *
 *          TODO: Implement and test semantic extraction of the date converting it to a correct date value regardless of format
 *          TODO: Implement and add textual descriptions (e.g. July 4)
 */


public class SentenceClassifierTest extends AnalysisTest {

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

/*
            new ClassificationTester("The first sentence. This is a second sentence. But this is not a sentence (beginning with but or and). And this neither. Using vs. does not break a sentence and vsx is v s x.")
                    .withParser(englishParser)
                    .withClassifier(new SentenceClassifierEN())
                    .expectingParseNode(NodeType.SENTENCE, 3)

                    .test();
  */

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
