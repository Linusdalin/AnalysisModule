package test.oldTests;

import analysis.AnalysisOutcome;
import analysis2.NewAnalysisOutcome;
import classifiers.Classification;
import docType.DocumentType;
import featureTypes.FeatureTypeTree;
import language.English;
import org.junit.BeforeClass;
import org.junit.Test;
import system.Analyser;
import classifiers.classifierTests.AnalysisTest;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by Linus
 *
 *
 *      Integration test for the main class of the module "Analyser"
 *
 *
 *      // Todo: Improvement: Add definition usage test with a document scope (with definitions)
 *
 */

public class IntegrationTest extends AnalysisTest {

    static final String fragment1 = "1.0 This is the first fragment";
    static final String fragment2 = "This is the second fragment listing a possible penalty and a date 2014-01-01 .";

    static final String path = "TextAnalysis/models";

    static String body;

    @BeforeClass
    public static void preAmble(){

        // Just constructing the document body
        body = fragment1 + " " + fragment2;
    }


    @Test
    public void newAnalysisTest(){

        List<String> keywords;

        try{

            // Creating a new Analyser, passing the body text for document language/type analysis

            Analyser analyser = new Analyser(new English().getLanguageCode(), path);

            // This should have been classified correctly now. It is a
            // regular document (i.e. not really classified as anything) and it is English

            assertVerbose("Checking the language", analyser.getLanguage().getLanguageCode().code, is("EN"));
            assertVerbose("Checking the document type", analyser.getDocumentType(), is(DocumentType.RegularDocument));

            // Now we analyse the first fragment. We don't find any definitions here

            NewAnalysisOutcome outcome1 = analyser.analyseFragment(fragment1, 0, fragment1, "", null,  null, null);
            //assertThat(outcome1.getDefinitions().size(), is(0));

            keywords = analyser.getKeywords();
            System.out.println("Keywords: " + keywords.toString());
            //assertVerbose("Expecting to find keywords", keywords.size(), is( 3 ));
            //assertThat(analyser.getKeywords().get(0), is(fragment1));
            //assertThat(analyser.getKeywords().get(1), is("fragment"));
            //assertThat(analyser.getKeywords().get(2), is("first"));


            // The second fragment should get a classification as it includes something that could be a risk.

            NewAnalysisOutcome outcome2 = analyser.analyseFragment(fragment2, 0, "", "", null, null, null);

            List<Classification> classifications = outcome2.getClassifications();

            assertVerbose("Expecting 2 classifications", classifications.size(), is(2));
            Classification first = classifications.get(0);
            Classification second = classifications.get(1);

            assertVerbose("Expecting classification date", second.getType(), is(FeatureTypeTree.Date));
            assertVerbose("Expecting pattern", second.getPattern().getText(), is("2014-01-01"));


            assertVerbose("Expecting classification risk", first.getType(), is(FeatureTypeTree.Risk));
            assertVerbose("Expecting pattern", first.getPattern().getText(), is("penalty"));
            assertVerbose("Expecting significance", first.getSignificance(), is(Classification.DEFAULT_STRONG_SIGNIFICANCE));

            keywords = analyser.getKeywords();
            System.out.println("Keywords: " + keywords.toString());
            assertVerbose("Expecting to find keywords", keywords.size(), is( 4 ));   // This is volatile. May change when keyword algorithm is updated
            assertVerbose("Expecting to find keyword penalty", analyser.getKeywords().contains("penalty"), is(true));

            NewAnalysisOutcome postProcessOutcome = analyser.postProcess(outcome2, null);
            List<Classification> postProcessClassifications = postProcessOutcome.getClassifications();
            assertVerbose("Expecting 0 classifications", postProcessClassifications.size(), is( 0 ));

        }catch(Exception e){

            e.printStackTrace();
            assertTrue(false);
        }
    }


}
