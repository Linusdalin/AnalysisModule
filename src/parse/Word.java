package parse;

import analysis.AnalysisOutcome;
import analysis.FeatureExtractorInterface;
import language.LanguageInterface;
import openNLP.Chunk;
import textPatterns.RecursiveExtraction;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Linus
 *
 */


public class Word {

    private String text;
    private POSClassification pos;
    private Chunk chunk;
    private String semanticText = null;


    public Word(String token, POSClassification posAnalysis, Chunk currentChunk) {

        this.text = token;
        this.pos = posAnalysis;
        this.chunk = currentChunk;
    }

    public static POSClassification classify(String text, String posAnalysis) {

        if(posAnalysis.equals(".") || posAnalysis.equals(",") ||posAnalysis.equals(";") || posAnalysis.equals(":"))     //TODO: Add more..?
            return POSClassification.IP;
        if(text.equals("'") || text.equals("\""))
            return POSClassification.QT;
        if(posAnalysis.equals("-LRB-"))
            return POSClassification.LRB;
        if(posAnalysis.equals("-RRB-"))
            return POSClassification.RRB;

        for(POSClassification c : POSClassification.values()){

            if(c.name().equalsIgnoreCase(posAnalysis))
                return c;

        }
        //throw new RuntimeException("Did not understand the posAnalysis " + posAnalysis + " for " + text);
        // TODO: Log this for later analysis
        return POSClassification.UNK;
    }

    public String display() {

        String chunkString = "";

        if(chunk != null){

            // Only display chunk if there is a chunk analysis performed

            chunkString = "(" + chunk.classification + ": " + chunk.getText() + ")";

        }


        return String.format("%1$-" + 20 + "s", text) + "(" + pos.name() + "-" + pos.getDescription() + chunkString + ") ";

    }

    public String getText() {

        return text;
    }

    public POSClassification getPos() {
        return pos;
    }

    public Chunk getChunk() {

        return chunk;
    }

    public boolean isMinor() {

        //System.out.println( display() );

        if(
                        pos == POSClassification.DT
                    ||  pos == POSClassification.CC
                    ||  pos == POSClassification.CD
                    ||  pos == POSClassification.EX
                    ||  pos == POSClassification.IN
                    ||  pos == POSClassification.LS
                    ||  pos == POSClassification.MD
                    ||  pos == POSClassification.PDT
                    ||  pos == POSClassification.POS
                    ||  pos == POSClassification.PRP
                    ||  pos == POSClassification.PRP$
                    ||  pos == POSClassification.RP
                    ||  pos == POSClassification.SYM
                    ||  pos == POSClassification.TO
                    ||  pos == POSClassification.UH
                    ||  pos == POSClassification.WDT
                    ||  pos == POSClassification.WP
                    ||  pos == POSClassification.WP$
                    ||  pos == POSClassification.IP
                    ||  pos == POSClassification.QT
                    ||  pos == POSClassification.LRB
                    ||  pos == POSClassification.VBP
                    ||  pos == POSClassification.VBZ
                    ||  pos == POSClassification.RRB)
            return  true;

        return false;

    }


    /*

    public boolean matches(String s, boolean verbatim) {

        if(verbatim)
          return getText().equals(s);

        return text.toLowerCase().contains(s.toLowerCase());


    }

*/

    /***************************************************************************
     *
     *          matching text against pattern string
     *
     *
     * @param patternString - the pattern to match the word against
     * @param verbatim      - from the textPattern
     * @param caseSensitive - from the textPattern
     *
     *
     * @return
     *
     *
     *              NOTE: semanticText is used when the match is a recursive pattern
     *
     */


    public boolean matches(String patternString, boolean verbatim, boolean caseSensitive) {

        // If we are matching verbatim we only have to check a direct match

        if(verbatim)
          return getText().equals(patternString);

        //System.out.println("\n\nMatching word "+ getText()   +" patternString =  " + patternString);

        String modifiedText = getText();

        if(!caseSensitive)
            modifiedText = modifiedText.toLowerCase();



        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(modifiedText);

        if(matcher.find())
        {
            //System.out.print("matched word " + getText() + " against " + patternString);
            if(semanticText != null)
                System.out.print(" (" +  semanticText + ")" );
            System.out.println("<--");

            return true;

        }
        //else
        //    System.out.println("Could NOT match word " + modifiedText + " against " + patternString);
        return false;

    }

    private RecursiveExtraction getExtractorForClassification(String pattern, List<RecursiveExtraction> recursiveExtractions) {

        for(RecursiveExtraction recursiveExtraction : recursiveExtractions){

            if(recursiveExtraction.word.equals(pattern))
                return recursiveExtraction;

        }

        System.out.println("Not found recursive extractor for recursion pattern " + pattern);
        return null;

    }

    private boolean isRecursiveClassificationTag(String patternString) {

        System.out.println("pattern: " + patternString);

        return false;
        //return (patternString.startsWith(RECURSIVE_PATTERN));
    }


    public boolean isNoun() {

        return(getPos() == POSClassification.NN ||
               getPos() == POSClassification.NN ||
               getPos() == POSClassification.NN ||
               getPos() == POSClassification.NN );

    }

    public String toString(){

        return text;
    }

    /************************************************************************
     *
     *          Semantic text is what we really want to extract or display
     *
     *
     * @param semanticText
     */


    public void setSemanticText(String semanticText) {

        this.semanticText = semanticText;

    }

    public String getSemanticText() {

        if(semanticText == null)
            return getText();

        return semanticText;

    }


}



