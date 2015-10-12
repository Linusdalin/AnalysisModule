package classifiers.classifierTests;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import analysis2.NewAnalysisOutcome;
import analysis2.NodeClass;
import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import document.AbstractDocument;
import document.AbstractProject;
import document.CellInfo;
import document.TableSpan;
import featureTypes.FeatureTypeTree;
import openNLP.NLParser;
import org.hamcrest.CoreMatchers;
import parse.AnalysisFragment;
import system.Analyser;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*******************************************************************************
 *
 *          Common tester for a fragment
 */

public class ClassificationTester extends AnalysisTest{



    public enum ExpectedSignificance { NONE, HIGH, LOW }

    private final String text;
    private FeatureTypeTree tree;
    private NLParser parser = null;
    private String headline = null;
    private String contextText = null;
    private CellInfo cell = null;

    private List<ClassifierInterface> classifiers = new ArrayList<>();  // List of extractors to apply. Normally only one in a test

    private List<NodeAssertion> nodeAssertions = new ArrayList<>();
    private List<NodeAssertion> nodeAssertNots = new ArrayList<>();

    private List<ClassificationAssertion> classificationAssertions = new ArrayList<>();

    private String tag = null;
    private String pattern = null;
    private int patternPos = -1;
    private AbstractProject project = null;
    private AbstractDocument document = null;
    private List<String> keywords = new ArrayList<>();  // List of expected keywords. All should match

    private ExpectedSignificance expectedSignificance = ExpectedSignificance.NONE;



    private static final int HIGH = 60;
    private static final int LOW = 30;


    public ClassificationTester(String text){

        this(text, null);
    }

    public ClassificationTester(String text, FeatureTypeTree tree){

        this.text = text;
        this.tree = tree;
    }


    public ClassificationTester withParser(NLParser parser){

        this.parser = parser;
        return this;
    }

    public ClassificationTester withHeadline(String headline){

        this.headline = headline;
        return this;
    }

    public ClassificationTester withContext(String context){

        this.contextText = context;
        return this;
    }

    public ClassificationTester expectingClassification(ClassificationAssertion assertion) {

        classificationAssertions.add(assertion);
        return this;
    }


    public ClassificationTester expectingParseNode(NodeAssertion assertion) {

        nodeAssertions.add(assertion);
        return this;
    }

    public ClassificationTester expectingNoNode(NodeClass.Type type) {

        nodeAssertNots.add(new NodeAssertion(type));
        return this;
    }


    public ClassificationTester expectingParseNode(NodeClass.Type type, int occurrences) {

        nodeAssertions.add(new NodeAssertion(type).expectingCount( occurrences ));
        return this;
    }

    @Deprecated    // Reimplement in expectingClassification
    public ClassificationTester expectingSignificance(ExpectedSignificance significance) {

        expectedSignificance = significance;
        return this;
    }


    @Deprecated    // Reimplement in expectingClassification
    public ClassificationTester expectingPattern(String pattern, int pos) {

        this.pattern = pattern;
        this.patternPos = pos;
        return this;
    }

    @Deprecated    // Reimplement in expectingClassification
    public ClassificationTester expectingPattern(String pattern) {

        this.pattern = pattern;
        return this;
    }


    @Deprecated    // Reimplement in expectingClassification
    public ClassificationTester expectingKeywords(String keyword) {
        this.keywords.add(keyword);
        return this;
    }


    public ClassificationTester withClassifier(ClassifierInterface classifier){

        this.classifiers.add(classifier);
        return this;
    }

    public ClassificationTester withProject(AbstractProject project, AbstractDocument document){

        this.project = project;
        this.document = document;

        return this;
    }



    public void test(){


        if(parser == null)
            throw new RuntimeException("No parser set for test");
        if(classifiers.isEmpty())
            throw new RuntimeException("No classifiers set for test");


        try {

            TextFragment f = new TextFragment(text, parser, true, 1);

            if(headline != null)
                f = f.withHeadline(headline);

            if(contextText != null)
                f = f.withContext(contextText);

            if(cell != null)
                f = f.withCellInfo(cell);

            if(project != null)
                f.withProject(project);

            if(document != null)
                f.withDocument(document);




            for(ClassifierInterface classifier : classifiers){

                 classifier.classify(f, Analyser.MAIN_PASS);

            }

            NewAnalysisOutcome outcome = f.getOutcome(Analyser.MAIN_PASS);

            System.out.println("****************************************\n* Testing Classifiers\n");
            System.out.println(f.display(2));

            if(nodeAssertions.size() == 0)
                System.out.println(" - No node assertions to test");

            for(NodeAssertion assertion : nodeAssertions){

                assertNode(f, assertion);

            }

            for(NodeAssertion notPresent : nodeAssertNots){

                assertNode(f, notPresent);  // Expecting to not find

            }


            if(classificationAssertions.size() == 0)
                System.out.println(" - No classification assertions to test");


            for(ClassificationAssertion assertion : classificationAssertions){

                assertClassification(outcome, assertion);

            }



        } catch (Exception e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            assertTrue(false);
        }

    }


    @Deprecated                         // TODO: Reimplement
    public void expectToFail(){

        String assertionDescription = "****************************************\n* Tested Extractor\n";

        if(parser == null)
            throw new RuntimeException("No parser set for test");
        if(classifiers.isEmpty())
            throw new RuntimeException("No extractor set for test");


        try {
            AnalysisOutcome o = new AnalysisOutcome();
            AnalysisFragment f = new AnalysisFragment(text, headline, parser);

            if(project != null)
                f.setProject(project);

            if(document != null)
                f.setDocument(document);

            /*

            for(FeatureExtractorInterface extractor : extractors){

                extractor.classify(f, o, tree);

            }

                */

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

    //TODO: Add testing

    public ClassificationTester withCellInfo(int row, int col, int width) {

        String defaultColour = "FFFFFF";
        int defaultWidth = -1;
        int defaultFontHeight = 0;
        TableSpan span = new TableSpan(1, 1);
        int border = 0;
        boolean wrap = false;

        cell = new CellInfo(row, col, defaultColour, defaultWidth, defaultFontHeight, span, border, wrap, width);
        return this;
    }

}
