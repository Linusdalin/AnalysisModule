package classifiers.classifierTests;

import analysis2.NodeClass;
import classifiers.swedishClassifiers.URLClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class URLClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){

        try{

            new ClassificationTester("See also http://www.aftonbladet.se")
                .withParser(swedishParser)
                .withClassifier(new URLClassifierSV())
                .expectingParseNode(NodeClass.Type.URL, 1)
                .expectingClassification(new ClassificationAssertion(FeatureTypeTree.URL, 1)
                        .withTag("URL")
                        .withPattern("www.aftonbladet.se"))

                .test();

            new ClassificationTester("See also http://www.aftonbladet.se/sporten")
                    .withParser(swedishParser)
                    .withClassifier(new URLClassifierSV())
                    .expectingParseNode(NodeClass.Type.URL, 1)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.URL, 1)
                            .withTag("URL")
                            .withPattern("www.aftonbladet.se/sporten"))

                    .test();

            new ClassificationTester("See also aftonbladet.se")
                    .withParser(swedishParser)
                    .withClassifier(new URLClassifierSV())
                    .expectingParseNode(NodeClass.Type.URL, 1)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.URL, 1)
                            .withTag("URL")
                            .withPattern("aftonbladet.se"))

                    .test();

            new ClassificationTester("Also aftonbladet.com is a link")
                    .withParser(swedishParser)
                    .withClassifier(new URLClassifierSV())
                    .expectingParseNode(NodeClass.Type.URL, 1)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.URL, 1)
                            .withTag("URL")
                            .withPattern("aftonbladet.com"))

                    .test();

            new ClassificationTester("Also aftonbladet.no is a link")
                    .withParser(swedishParser)
                    .withClassifier(new URLClassifierSV())
                    .expectingParseNode(NodeClass.Type.URL, 1)
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.URL, 1)
                            .withTag("URL")
                            .withPattern("aftonbladet.no"))

                    .test();


            new ClassificationTester("Also org.nr is NOT a link")
                    .withParser(swedishParser)
                    .withClassifier(new URLClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.URL, 0)
                    )
                    .test();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

}
