package parse;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import document.AbstractDocument;
import featureTypes.FeatureTypeInterface;
import featureTypes.FeatureTypeTree;
import language.English;
import document.AbstractProject;
import openNLP.Chunk;
import openNLP.NLParser;
import textPatterns.RecursiveExtraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 */

public class AnalysisFragment {

    private String text;         // Original Text
    private String[] tokens;     // Token list after the initial tokenization
    private List<Word> words;
    private List<Chunk> chunks;
    private String heading;
    private int column = -1;
    private AbstractProject project;
    private AbstractDocument document;
    private boolean isHeadline = false;
    private NLParser parser;

    // Todo: This is just to avoid redoing all test cases. Remove this and fix all Fragment() in test

    public AnalysisFragment(String text){

        this(text, "", new NLParser(new English(), "TextAnalysis/models"));
    }


    public AnalysisFragment(String text, String heading, NLParser parser) {

        this.text = text;
        this.heading = heading;
        this.parser = parser;

        System.out.println("Heading:  " + heading);
        System.out.println("Fragment: " + text);

        if(heading != null && !heading.equals("") && text.endsWith(heading))    // Allowing for chapter numbering
            isHeadline = true;
        else
            isHeadline = false;

        //System.out.println("isHeadline = " + isHeadline);

        chunks = new ArrayList<>();
        //System.out.println("Before:        " + new Date().getTime() % 10000);
        tokens = parser.tokenize(text);
        //System.out.println("After tokenize:" + new Date().getTime() % 10000);
        String[] posAnalysis = parser.posAnalysis(tokens);
        //System.out.println("After pos:     " +new Date().getTime() % 10000);
        //String[] chunkAnalysis = parser.chunk(tokens);
        String[] chunkAnalysis = new String[0];
        //System.out.println("After chunk:   " + new Date().getTime() % 10000);
        words = new ArrayList<>();

        Chunk currentChunk = null;
        boolean isInQuote = false;
        String textInCurrentChunk = "";              // TODO: Refactor this into sequential adding of text in the chunk
        boolean inBracket = false;

        //TODO: Add check that they are of equal length (should be, but this is external libs... :-/ )

        for(int i = 0; i < tokens.length; i++){

            POSClassification classification = Word.classify(tokens[i], posAnalysis[i]);


            if(classification == POSClassification.RRB)
                inBracket = false;

            // Sometimes (some language) there are no chunk analysis,
            // so we cant add chunks to the word representation

            if(chunkAnalysis.length > i){



                if(isNewChunk(chunkAnalysis[i], tokens[i], isInQuote)){

                    if(currentChunk != null){
                        //currentChunk.setText(textInCurrentChunk);
                        chunks.add(currentChunk);
                    }

                    //currentChunk = new Chunk(classification, inBracket);
                    currentChunk = new Chunk(chunkAnalysis[i], inBracket);
                    textInCurrentChunk = "";
                }
            }

            if(classification == POSClassification.QT)
                isInQuote = !isInQuote;


            if(classification == POSClassification.LRB)
                inBracket = true;


            Word word = new Word(tokens[i], classification, currentChunk );
            if(currentChunk != null ){
                currentChunk.count(classification);
                currentChunk.addWord(word);
            }
            words.add(word);


        }

        // Close the final chunk

        if(currentChunk != null){
            //currentChunk.setText(textInCurrentChunk);
            chunks.add(currentChunk);
        }

        //System.out.println("Created analysis fragment!");

    }


    public AnalysisFragment setColumn(int column){


        this.column = column;
        return this;
    }



    public AnalysisFragment setProject(AbstractProject project){


        this.project = project;
        return this;
    }

    public AnalysisFragment setDocument(AbstractDocument document){

        this.document = document;
        return this;
    }


    /****************************************************************************''
     *
     *      Checks if this is start of a chunk or not
     *
     * @param chunkAnalysis
     * @return
     *
     *          This is based on the notation in the Open NLP that says that:
     *
     *              B- is a new chunk
     *              I- is the continuation of a chunk
     */

    private boolean isNewChunk(String chunkAnalysis, String text, boolean isInQuote) {


        if(text.equalsIgnoreCase("("))
            return true;

        if(text.equalsIgnoreCase("\"") && !isInQuote)
            return true;

        if(text.equalsIgnoreCase("\"") && isInQuote)
            return false;


        if(chunkAnalysis.startsWith("B-"))
            return true;


        return false;
    }

    public String display() {

        String output = "[\n";

        for(Word w : words){

            output += w.display() + "\n";
        }

        output +="]";
        return output;
    }

    public List<Chunk> getChunks() {

        return chunks;
    }

    public List<Word> getWords() {

        return words;
    }

    public String getText() {
        return text;
    }

    public String getClauseHeadline() {

        return heading;

    }

    public int getColumn() {

        return column;
    }

    public AbstractProject getProject() {

        return project;
    }

    public AbstractDocument getDocument() {

        return document;
    }

    public boolean isHeadline() {
        return isHeadline;
    }

    public String getHeading() {
        return heading;
    }

    /*************************************************************************************
     *
     *          Apply extractors and replace the corresponding tokens with
     *
     *
     * @param extractors
     */


    public AnalysisFragment recursiveReplace(List<RecursiveExtraction> extractors, FeatureTypeTree tree) {

        AnalysisOutcome recursiveOutcome = new AnalysisOutcome();

        // Apply all extractors defined in this rule

        for(RecursiveExtraction extractor : extractors ){

            extractor.extractor.classify(this, recursiveOutcome, tree);

        }

        // We are for now only allowing one pattern to be replaced.
        // For any subsequent pattern we don't know the position in the
        // recursively changing fragment text

        AnalysisFragment newFragment = this;

        /*

        for(FeatureDefinition definition : recursiveOutcome.getDefinitions()){

            newFragment = replaceInWordList(definition, newFragment);

        }

        */

        if(recursiveOutcome.getDefinitions().size() > 0)
            newFragment = replaceInWordList(recursiveOutcome.getDefinitions().get(0), newFragment);

        return newFragment;


    }

    private AnalysisFragment replaceInWordList(FeatureDefinition definition, AnalysisFragment analysisFragment) {

        AnalysisFragment newFragment;

        try{

            String pattern = definition.getPattern();
            FeatureTypeInterface type = definition.getType();
            String tag = "#" + type.getName().toLowerCase();

            System.out.println("*** In replace. Looking to replace " + pattern + " with " + tag + "\nin fragment " + analysisFragment.getText());

            //System.out.println("definition.getPos()" + definition.getPos());
            //System.out.println("definition.getLength()" + definition.getLength());


            String newText = analysisFragment.getText();
            newText = analysisFragment.getText().substring(0, definition.getPos()) + tag + " " + analysisFragment.getText().substring(definition.getPos() + definition.getLength());

            System.out.println("*** In replace. New fragment is: " + newText);


            newFragment = new AnalysisFragment(newText, this.heading, this.parser);

            setSemanticValue(tag, pattern, newFragment);

        }catch(Exception e){


            newFragment = analysisFragment;
        }

        return newFragment;

    }

    /********************************************************************************
     *
     *              Put a semantic value to a word in the fragment.
     *              After the replacement of an extraction with a #tag we need to
     *              store the original text for later extraction
     *
     *
     * @param tag
     * @param pattern
     * @param newFragment
     */

    private void setSemanticValue(String tag, String pattern, AnalysisFragment newFragment) {

        for(Word word : newFragment.getWords()){

            if(word.getText().equals(tag)){
                word.setSemanticText(pattern);
                System.out.println("Setting semantic text " + pattern + " for " + tag);
                return;
            }

        }

        System.out.println("Error: Did not find the tag " +  tag + " in the text " + newFragment.getWords().toString());


    }
}
