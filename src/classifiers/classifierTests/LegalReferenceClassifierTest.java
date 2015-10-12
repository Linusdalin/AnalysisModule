package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.swedishClassifiers.LegalReferenceClassifierSE;
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
 *          Email tests
 *
 */


public class LegalReferenceClassifierTest extends AnalysisTest {

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


            new ClassificationTester("See also LOU SFS (1998:123)")
                    .withParser(swedishParser)
                    .withClassifier(new NumberClassifierSV())
                    .withClassifier(new LegalReferenceClassifierSE())
                    .expectingParseNode(new NodeAssertion(NodeClass.Type.REFERENCE))
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.LegalReference, 1)
                            .withPattern("(1998:123)"))


                    .test();

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
