package classifiers.classifierTests;

import analysis2.NewAnalysisOutcome;
import analysis2.NodeClass;
import analysis2.ParseNodeInterface;
import analysis2.TextFragment;
import classifiers.Classification;
import document.*;
import featureTypes.FeatureTypeInterface;
import language.English;
import language.LanguageInterface;
import language.Swedish;
import log.AnalysisLogger;
import openNLP.NLParser;
import org.hamcrest.Matcher;

import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/***************************************************************************''
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-05
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 *
 */


public class AnalysisTest {

    protected static NLParser englishParser;
    protected static NLParser swedishParser;


    protected static AbstractProject mockProject;
    protected static AbstractDocument mockDocument;

    protected static void init(){

        try{

            mockProject = new AbstractProject();

            mockProject
                    .addDocument(getMockAvtal(mockProject))
                    .addDocument(getMockAppendix(mockProject));

            mockDocument = mockProject.documents.get(0);

            mockDocument.addDefinition(new AbstractDefinition("Köparen", 0, DefinitionType.ACTOR));
            mockDocument.addDefinition(new AbstractDefinition("Säljaren", 0, DefinitionType.ACTOR));
            mockDocument.addDefinition(new AbstractDefinition("Produkten", 0, DefinitionType.OBJECT));
            mockDocument.addDefinition(new AbstractDefinition("Dokumentet", 0));
            mockDocument.addDefinition(new AbstractDefinition("Avtalet", 0));

            englishParser = new NLParser(new English(), "TextAnalysis/models");
            swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

            AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);


        }catch(Exception e){

            e.printStackTrace();
        }

    }



    protected void assertNode(TextFragment classifiedSentence, NodeAssertion expectedType) {

        NodeClass expected = new NodeClass(expectedType.nodeType);

        assertVerbose("Expecting to find a node of type " + expectedType.nodeType.name(), lookupNode(classifiedSentence, expected, expectedType.nodeExtraction) > 0, is(true));

    }



    protected void assertClassification(NewAnalysisOutcome outcome, ClassificationAssertion assertion) {

        Classification classification = lookupClassification(outcome, assertion.featureType, assertion.count);

        if(assertion.count > 0)
            assertVerbose("Expecting to find classification of type " + assertion.featureType.getName(), classification != null, is(true));
        else
            assertVerbose("NOT Expecting to find classification of type " + assertion.featureType.getName(), classification != null, is(false));

        if(assertion.tag != null)
            assertVerbose("Expecting to find tag " + assertion.tag, classification.getTag(), is(assertion.tag));

        //String classificationPattern = (classification.getPattern() != null ? classification.getPattern().getText() : "");

        if(assertion.pattern != null){

            assertNotNullVerbose("Expected pattern, but classification is null", classification);
            assertVerbose("Expecting to find pattern " + assertion.pattern, classification.getPattern().getText(), is(assertion.pattern));
        }
        else
        if(classification != null)
            System.out.println("   ! Found extracted pattern \"" + classification.getPattern().getText() + "\". Consider adding assertion...");

        if(assertion.semanticExtraction != null)
            assertVerbose("Expecting to find extraction " + assertion.semanticExtraction, classification.getExtraction().getSemanticExtraction(), is(assertion.semanticExtraction));


        if(assertion.keywords != null)
            assertVerbose("Expecting to find keyword " + assertion.keywords + " in \"" + classification.getKeywords() + "\"",  classification.getKeywords().contains(assertion.keywords), is(true));


    }

    private Classification lookupClassification(NewAnalysisOutcome outcome, FeatureTypeInterface expectedType, int number) {

        for( Classification classification : outcome.getClassifications()){

            if(classification.getType().getName().equals(expectedType.getName() )){

                if(number == 0)
                    return classification;

                if(number == 1)
                    return classification;
                else
                    number--;
            }


        }

        return null;

    }


    protected static <T> void assertVerbose(String explanation, T result, Matcher<T> matcher){

        assertThat(explanation, result, matcher);
        System.out.println(" - Test Pass: " + explanation);
    }

    protected static <T> void assertNotNullVerbose(String explanation, T result){

        assertNotNull(explanation, result);
        System.out.println(" - Test Pass: " + explanation);
    }

    /********************************************************************************
     *
     *              Lookup a specific node and check the semantic extraction
     *
     *
     * @param node
     * @param expected
     * @param expectedExtraction
     * @return
     *
     *          //TODO: No check for attribute here
     */


    protected int lookupNode(ParseNodeInterface node, NodeClass expected, String expectedExtraction) {

        int nodeCount = 0;


        if(node.getNodeClass().isA(expected.getType())){

            System.out.println(" -- Found node " + nodeCount + " of type " + node.getNodeClass() + "(Text: " + node.getText() + ") Extraction: (" + node.getExtraction().getSemanticExtraction() + ")");

            if(expectedExtraction == null ||
                    (node.getExtraction().getSemanticExtraction() != null && node.getExtraction().getSemanticExtraction().equals(expectedExtraction)))

                nodeCount++;
            else
                System.out.println("    -- Required semantic extraction \""+ expectedExtraction+"\"not found");
        }

        for(ParseNodeInterface child : node.getChildren()){

            nodeCount += lookupNode(child, expected, expectedExtraction);

        }
        return nodeCount;

    }


    /***********************************************************************************
     *
     *              Mock documents for analysis test (definitions and references
     *
     */



    protected static AbstractDocument getMockAvtal(AbstractProject aProject) {

        final LanguageInterface language = new Swedish();

        final AbstractFragment heading1 = new AbstractFragment("1 Introduktion")
                .setStyle(StructureType.HEADING);

        final AbstractFragment heading2 = new AbstractFragment("2 Sammanfattning")
                .setStyle(StructureType.HEADING);


        AbstractDocument mockDocument = new AbstractDocument("Avtalet",
                new ArrayList<AbstractStructureItem>() {
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()).setTopElement(heading1).setKey("dummyKey1"));}           //Introduktion
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()).setTopElement(heading2).setKey("dummyKey2"));}           //Sammanfattning
                },
                new ArrayList<AbstractDefinition>() {
                    {add(new AbstractDefinition("Köparen", 0));}
                    {add(new AbstractDefinition("Säljaren", 0));}

                },
                aProject,
                language.getLanguageCode());

        return mockDocument;


    }

    protected static AbstractDocument getMockAppendix(AbstractProject aProject) {

        final LanguageInterface language = new Swedish();

        AbstractDocument mockAppendix = new AbstractDocument("Bilaga 1, prislista",
                new ArrayList<AbstractStructureItem>() {
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()));}             // First Clause
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()));}             // Second Clause

                },
                new ArrayList<AbstractDefinition>() {

                },
                aProject,
                language.getLanguageCode()
                );

        return mockAppendix;
    }


}
