package classifiers.classifierTests;

import classifiers.swedishClassifiers.ClassifiedNounClassifierSV;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.PenaltiesClassifierSV;
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
 *
 */


public class PenaltiesClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


/***********************************************************
     *
     *      Testing Classification by examples for tag #PENALTIES
     *      Document:   " Avtal avseende IT-drift"
     *      Language:   "SV"
     *
     */


    @Test
    public void testAvtalavseendeITdriftExamples(){
        try {
               new ClassificationTester("v) Part ska, inom ramen för avtalade ans"+
                                             "varsbegränsningar enligt punkt 22 nedan "+
                                             "ersätta den andre Parten för den skada d"+
                                             "enne orsakats med anledning av försening"+
                                             ", Fel eller annat avtalsbrott (i den mån"+
                                             " skadan överstiger redan utbetald övrig "+
                                             "kompensation eller viten enligt Avtalet)"+
                                             ";")
                        .withParser(swedishParser)
                        .withHeadline("v) Part ska, inom ramen för avtalade ansvarsbegränsningar enligt punkt 22 nedan ersätta den andre Parten för den skada denne orsakats med anledning av försening, Fel eller annat avtalsbrott (i den mån skadan överstiger redan utbetald övrig kompensation eller viten enligt Avtalet);")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new ClassifiedNounClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new PenaltiesClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PENALTIES, 1)
                            .withTag("")
                      )
                    .test();


               new ClassificationTester("14.2 Påföljder")
                        .withParser(swedishParser)
                        .withHeadline("14.2 Påföljder")
                        .withProject(mockProject, mockDocument)
                        .withClassifier(new NumberClassifierSV())
                        .withClassifier(new DefinitionUsageClassifierSV())
                        .withClassifier(new PenaltiesClassifierSV())
                        .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PENALTIES, 1)
                            .withTag("")
                      )
                    .test();


           } catch (Exception e) {
                e.printStackTrace();
                assertTrue(false);
           }
        }
}
