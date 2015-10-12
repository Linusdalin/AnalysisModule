package openNLP;

import parse.POSClassification;
import parse.SimpleClassification;
import parse.Word;
import textPatterns.TextPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *          A chunk is a POS concept which contains of a number of words
 *          that together make up a meaning
 *
 *          The POS analysis gives it a classification
 *
 *          Created by Linus
 *
 */

public class Chunk {

    public final String classification;
    private List<Word> words;
    boolean isBracket = false;      // Store if the chunk is within brackets
    int size = 0;


    /*********************************************************************'
     *
     *
     * @param classification - the classification string
     * @param inBracket - is this chunc found in a bracket or not
     */

    public Chunk(String classification, boolean inBracket) {

        this.classification = striped(classification);
        isBracket = inBracket;
        words = new ArrayList<>();
    }

    private String striped(String classification) {

        if(classification.contains("-"))
            return classification.substring(classification.indexOf("-")+1);
        return classification;
    }

    public void addWord(Word textInCurrentChunk) {

        words.add(textInCurrentChunk);
    }

    public String getText(){

        String output = "";

        for(Word w : words){

            output += w.getText() + " ";
        }

        return output.substring(0, output.length()-1); // Remove the last " "
    }

    public void setBracket(){

        isBracket = true;
    }

    public boolean isBracket(){

        return isBracket;
    }

    public void count(POSClassification classification){

        if(classification.isWord())
            size++;
    }

    public int getSize() {

        return size;
    }

    public List<Word> getWords(){
        return  words;
    }

    public boolean isQuoted(){

        int quotesFound = 0;

        for(Word w : words){

            if(w.getText().contains("\""))
                quotesFound++;
        }
        return (quotesFound > 1);
    }

    //TODO: Look for multiple quotes in the same chunk

    public List<String> getQuotes() {

        List<String> res = new ArrayList<String>();
        String text = getText();

        int first = text.indexOf("\"")+1;             // Remove "
        int second = text.indexOf("\"", first );

        if(first < 0 || second < 0){

            System.out.println("Brk");
        }

        String quote = text.substring(first, second).trim();

        res.add(quote);

        return res;
    }

}
