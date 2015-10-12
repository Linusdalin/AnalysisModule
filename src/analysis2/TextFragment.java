package analysis2;

import classifiers.Classification;
import document.AbstractDocument;
import document.AbstractProject;
import document.CellInfo;
import log.AnalysisLogger;
import openNLP.NLParser;
import openNLP.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *              Text fragment is the top node of the analysis
 */


public class TextFragment extends ParseNode implements ParseNodeInterface {

    private NLParser parser;
    private AbstractProject project;
    private AbstractDocument document;
    private int fragmentNo;


    public TextFragment(String text, NLParser parser, boolean usePOS, int fragmentNo){

        super(text, 0);
        this.text = parser.getLanguage().wash(text);

        nodeClass = new NodeClass(NodeClass.Type.FRAGMENT);
        parse(this.text, parser, usePOS);
        this.parser = parser;
        this.fragmentNo = fragmentNo;

    }

    public TextFragment withHeadline(String headline){

        this.metaData.headline = headline;
        return this;
    }

    public boolean isHeadline(){

        return this.metaData.headline.equalsIgnoreCase(this.text);
    }

    public TextFragment withContext(String context){

        this.metaData.context = context;
        return this;
    }

    public TextFragment withCellInfo(CellInfo cellInfo){

        this.metaData.cellInfo = cellInfo;
        return this;
    }

    /****************************************************
     *
     *              Textual parsing of the input text
     *
     *
     * @param text
     * @param parser
     * @param usePOS
     *
     *          //TODO: Calculate textpos better here by looking in the rest of the string.
     *
     */

    private void parse(String text, NLParser parser, boolean usePOS){

        // Test replacing the pos analysis with home written
        //String[] tokens = parser.tokenize(text);
        Tokenizer tokenizer = new Tokenizer();
        String[] tokens = tokenizer.tokenize(text);
        String[] posTags;

        if(usePOS){

            try{

                posTags = parser.posAnalysis(tokens);

            }catch(Exception e){

                AnalysisLogger.log( e, "Failing Token list: " + Arrays.toString(tokens) );
                posTags = new String[tokens.length];

            }

        }
        else
            posTags = new String[tokens.length];

        //Span[] tokenSpans = parser.getSpan(text);           // Get the token span to be able to extract the text position
        String[] stems = parser.wordStem(tokens);

        try{

            Integer[] positions = tokenizer.getPositions();

            for(int i = 0; i < tokens.length; i++){

                addChild(new WordNode(tokens[i], stems[i], positions[i], posTags[i]));

            }

        }catch(Exception e){

            // There is an error or mismatch in the parsing. Log this for further analysis

            String errorMessage = "Error parsing \""+text+"\". " +
                    " Tokens: " + Arrays.asList(tokens).toString() +
                    " stems:  " + Arrays.asList(stems).toString() +
                    " posTags:" + Arrays.asList(posTags).toString() + " ";

            AnalysisLogger.log( e , errorMessage);
        }



    }


    /**************************************************************************
     *
     *      Build the outcome from the classificaitons
     *
     *
     * @return
     */


    public NewAnalysisOutcome getOutcome(int phase){

        NewAnalysisOutcome outcome = new NewAnalysisOutcome(this);

        //Create empty lists to fill by the extractor method
        List<Classification> classifications = new ArrayList<>();
        List<String> comments = new ArrayList<>();

        extractClassifications(classifications, comments, phase);
        outcome.setClassifications(classifications);
        outcome.setComments(comments);

        return outcome;
    }



    public String display(int indentation){

        StringBuilder display = new StringBuilder();
        display.append(indent("Text{\n", indentation));
        display.append(displayChildren(indentation + 3));
        display.append(indent("}\n", indentation));

        return display.toString();
    }

    @Override
    public ParseNodeInterface createPrefixNode(int textLength) {

        return  new TextFragment(this.text.substring(0, textLength), this.parser, false, fragmentNo).withHeadline(this.metaData.headline).withContext(this.metaData.context);
    }


    public TextFragment getCopy() {

        TextFragment copy = new TextFragment(this.text, this.parser, false, fragmentNo).withHeadline(this.metaData.headline).withContext(this.metaData.context);
        copy.nodeClass = this.nodeClass;
        copy.extraction = this.extraction;

        copy.children = new ArrayList<>();

        for(ParseNodeInterface child : children){
            copy.children.add(child);
        }

        return copy;
    }

    public NLParser getParser() {

        return parser;
    }

    public AbstractProject getProject(){

        return project;
    }

    public TextFragment withProject(AbstractProject project){

        this.project = project;
        return this;
    }

    public AbstractDocument getDocument() {

        return document;
    }

    public TextFragment withDocument(AbstractDocument document){

        this.document = document;
        return this;
    }


    public int getFragmentNo() {
        return fragmentNo;
    }
}

