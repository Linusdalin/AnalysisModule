package analysis2;

import classifiers.classifierTests.NodeAssertion;
import classifiers.englishClassifiers.*;
import language.English;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import classifiers.classifierTests.AnalysisTest;
import system.Analyser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**********************************************************************************''
 *
 *
 *              Generic tests
 *
 *              //TODO: Break this into tests for different extractors
 *
 */


public class ParseTest extends AnalysisTest {

    private static NLParser parser;


    @BeforeClass
    public static void preAmble(){

        parser = new NLParser(new English(), "TextAnalysis/models");

    }

    @Test
    public void inferenceTest(){



        TextFragment s = new TextFragment("After 2014-10-10 all cities in the US has more than USD 1,200 in fines", parser, true, 0);
        System.out.println(s.display(2));

        TextFragment classifiedSentence = new ClassifierModel(new English()).classify(s, Analyser.MAIN_PASS);

        System.out.println(classifiedSentence.display(2));


    }

    @Test
    public void regexpTest(){


        TextFragment s = new TextFragment("All cities in the US has more than USD1,200 in fines", parser, true, 0);
        System.out.println(s.display(2));

        new NumberClassifierEN().classify(s, Analyser.MAIN_PASS);

        System.out.println(s.display(2));

        assertNode(s, new NodeAssertion(NodeClass.Type.NUMBER));


    }

    @Test
    public void tokenMatchTest(){


        TextFragment s = new TextFragment("After 2014-10-10 all cities in the US has more than USD1,200 in fines", parser, true, 0);
        System.out.println(s.display(2));

        TextFragment classifiedSentence = new ClassifierModel(new English()).classify(s, Analyser.MAIN_PASS);

        System.out.println(classifiedSentence.display(2));

        assertNode(classifiedSentence, new NodeAssertion(NodeClass.Type.AMOUNT));


    }

    @Test
    public void closeMatchTest(){


        TextFragment s = new TextFragment("The latest on the 2014-10-10 all cities in the US must have more than USD1,200 in fines", parser, true, 0);
        System.out.println(s.display(2));

        TextFragment classifiedSentence = new ClassifierModel(new English()).classify(s, Analyser.MAIN_PASS);

        System.out.println(classifiedSentence.display(2));

        assertNode(classifiedSentence, new NodeAssertion(NodeClass.Type.SENTENCE));


    }

    @Test
    public void sentenceTest(){


        TextFragment s = new TextFragment("The first sentence. This is a second sentence. But this is not a sentence (beginning with but or and). And this neither. Using vs. does not break a sentence and vsx is v s x.", parser, true, 0);
        System.out.println(s.display(2));

        TextFragment classifiedSentence = new ClassifierModel(new English()).classify(s, Analyser.MAIN_PASS);

        System.out.println(classifiedSentence.display(2));

        assertNode(classifiedSentence, new NodeAssertion(NodeClass.Type.SENTENCE));


    }


    @Test
    public void someOfTest(){


        TextFragment s = new TextFragment("This is a sentence that we want to match words in", parser, true, 0);
        System.out.println(s.display(2));

        TextFragment classifiedSentence = new ClassifierModel(new English()).classify(s, Analyser.MAIN_PASS);

        System.out.println(classifiedSentence.display(2));

        assertNode(classifiedSentence, new NodeAssertion(NodeClass.Type.SENTENCE));


    }



}
