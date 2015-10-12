package classifiers.classifierTests;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import analysis.FeatureExtractorInterface;
import document.AbstractDocument;
import document.AbstractProject;
import featureTypes.FeatureTypeInterface;
import featureTypes.FeatureTypeTree;
import openNLP.NLParser;
import org.hamcrest.CoreMatchers;
import parse.AnalysisFragment;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*******************************************************************************
 *
 *          Common tester for a fragment
 */

@Deprecated
public class ExtractorTesterOld {

    public ExtractorTesterOld expectingSignificance(ExpectedSignificance significance) {

        expectedSignificance = significance;
        return this;
    }

    public enum ExpectedSignificance { NONE, HIGH, LOW }

    private final String text;
    private FeatureTypeTree tree;
    private NLParser parser = null;
    private String headline = "";
    private List<FeatureExtractorInterface> extractors = new ArrayList<>();  // List of extractors to apply. Normally only one in a test
    private FeatureTypeInterface featureType;
    private String tag = null;
    private String pattern = null;
    private int patternPos = -1;
    private AbstractProject project = null;
    private AbstractDocument document = null;
    private List<String> keywords = new ArrayList<>();  // List of expected keywords. All should match

    private ExpectedSignificance expectedSignificance = ExpectedSignificance.NONE;



    private static final int HIGH = 60;
    private static final int LOW = 30;


    public ExtractorTesterOld(String text){

        this(text, null);
    }

    public ExtractorTesterOld(String text, FeatureTypeTree tree){

        this.text = text;
        this.tree = tree;
    }


    public ExtractorTesterOld withParser(NLParser parser){

        this.parser = parser;
        return this;
    }

    public ExtractorTesterOld withHeadline(String headline){

        this.headline = headline;
        return this;
    }

    public ExtractorTesterOld expectingType(FeatureTypeInterface type) {
        featureType = type;
        return this;
    }

    public ExtractorTesterOld expectingTag(String tag) {
        this.tag = tag;
        return this;
    }

    public ExtractorTesterOld expectingPattern(String pattern, int pos) {

        this.pattern = pattern;
        this.patternPos = pos;
        return this;
    }

    public ExtractorTesterOld expectingPattern(String pattern) {

        this.pattern = pattern;
        return this;
    }


    public ExtractorTesterOld expectingKeywords(String keyword) {
        this.keywords.add(keyword);
        return this;
    }


    public ExtractorTesterOld withExtractor(FeatureExtractorInterface extractor){

        this.extractors.add(extractor);
        return this;
    }

    public ExtractorTesterOld withProject(AbstractProject project, AbstractDocument document){

        this.project = project;
        this.document = document;

        return this;
    }



    public void test(int fragmentNo, int totalNoFragments){

        String assertionDescription = "****************************************\n* Tested Extractor\n";

        if(parser == null)
            throw new RuntimeException("No parser set for test");
        if(extractors.isEmpty())
            throw new RuntimeException("No extractor set for test");


        try {
            AnalysisOutcome o = new AnalysisOutcome();
            AnalysisFragment f = new AnalysisFragment(text, headline, parser);

            if(project != null)
                f.setProject(project);

            if(document != null)
                f.setDocument(document);

            for(FeatureExtractorInterface extractor : extractors){

                extractor.classify(f, o, tree);

            }

            FeatureDefinition d = getDefinition("Expecting hit(s) for \"" + f.getText() +"\"", o, fragmentNo, totalNoFragments);
            assertionDescription += " Got " + totalNoFragments + " hits for fragment \"" + f.getText() + "\"";
            assertionDescription += "\n*    Hit " + fragmentNo + ": ";

            if(d != null){

                if(featureType != null){
                    assertThat(d.getType(), is(featureType));
                    assertionDescription += "(" + featureType + ")";
                }
                if(tag != null){
                    assertThat(d.getTag(), is(tag));
                    assertionDescription += " tag = " + d.getTag();

                }
                if(pattern != null){
                    assertThat(d.getPattern(), is(pattern));
                    assertionDescription += " pattern = \"" + d.getPattern() + "\"";

                }
                if(patternPos != -1)
                    assertThat(d.getPos(), is(patternPos));


                if(expectedSignificance == ExpectedSignificance.HIGH)
                      assertThat("Expecting a high significance", d.getSignificance() > 60, is(true));

                if(expectedSignificance == ExpectedSignificance.LOW)
                      assertThat("Expecting a low significance", (d.getSignificance() >= 20 && d.getSignificance() < 60), is(true));



                for(String keyword : keywords){

                    assertThat("Expecting keyword " + keyword + " in " + d.getKeywords().toString(), d.getKeywords().contains(keyword), is(true));
                    assertionDescription += " keywords: " + d.getKeywords().toString();
                }

            }


            assertionDescription += "\n*\n";
            System.out.println(assertionDescription);


        } catch (Exception e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            assertTrue(false);
        }

    }


    public void expectToFail(){

        String assertionDescription = "****************************************\n* Tested Extractor\n";

        if(parser == null)
            throw new RuntimeException("No parser set for test");
        if(extractors.isEmpty())
            throw new RuntimeException("No extractor set for test");


        try {
            AnalysisOutcome o = new AnalysisOutcome();
            AnalysisFragment f = new AnalysisFragment(text, headline, parser);

            if(project != null)
                f.setProject(project);

            if(document != null)
                f.setDocument(document);

            for(FeatureExtractorInterface extractor : extractors){

                extractor.classify(f, o, tree);

            }


            assertionDescription += "* Expecting no hits for " + f.getText() + "\n";

            assertNoDefinitions( o );


            assertionDescription += "*\n";
            System.out.println(assertionDescription);


        } catch (Exception e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            assertTrue(false);
        }

    }




    protected static final int ANY_NUMBER = -1;

    protected FeatureDefinition getDefinition(String text, AnalysisOutcome o, int no, int expected) {

        int matches = o.getDefinitions().size();

        if(expected == ANY_NUMBER){

            assertThat(text, matches > 0, is(true));

        }
        else
            assertThat(text, matches, is(expected));

        if(no >= 0)
            return o.getDefinitions().get(no);
        else
            return null;

    }


    protected void assertNoDefinitions(AnalysisOutcome o) {

        assertThat("Not expecting any classifications", o.getDefinitions().size(), CoreMatchers.is(0));
    }


    protected void assertNoDefinitions(String text, AnalysisOutcome o) {

        assertThat(text, o.getDefinitions().size(), CoreMatchers.is(0));
    }

    /***********************************************************************************
     *
     *              Mock documents for analysis test (definitions and references
     *
     */


}
