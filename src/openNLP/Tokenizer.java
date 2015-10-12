package openNLP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*********************************************************************'
 *
 *      Local implementation of a tokenizer.
 *
 *
 *      Sideeffect: The tokenization stores an array of the positions of the tokens in the target string
 *
 */


public class Tokenizer {

    private Integer[] positions = null;

    public String[] tokenize(String text){

        List<String> tokens = new ArrayList<>();
        List<Integer> positionsList = new ArrayList<>();

        int i, wordStart = 0;

        for(i = 0; i< text.length(); i++){

            char c = text.charAt( i );

            // Token terminating characters

            if(c == ' ' || c == '\t' || c == '\n' ){

                if(wordStart < i){
                    tokens.add(text.substring(wordStart, i));
                    positionsList.add(wordStart);
                }
                wordStart = i+1;

            }

            // Single character tokens

            if(     c == '.' || c == ',' || c == ';'  || c == '!' || c == '?' || c == ';' || c == ':'|| c == '-' ||
                    c == '%' || c == '#' || c == '\"'  ||  c == '´' ||
                    c == '>' || c == '<' || c == '|'  || c == '=' || c == '/' || c == '\\' ||
                    c == '(' || c == ')' || c == '[' || c == ']' ||c == '{' || c == '}'

                    ){

                if(wordStart < i){
                    tokens.add(text.substring(wordStart, i));
                    positionsList.add(wordStart);
                }

                tokens.add(Character.toString(c));
                positionsList.add(i);

                wordStart = i+1;

            }

            // Associate right

            if(     c == '\''

                    ){

                if(wordStart < i){
                    positionsList.add(wordStart);
                    tokens.add(text.substring(wordStart, i));

                }

                wordStart = i;

            }



        }

        if(wordStart < i){
            tokens.add(text.substring(wordStart, i));
            positionsList.add(wordStart);
        }

        positions = positionsList.toArray(new Integer[tokens.size()]);
        return tokens.toArray(new String[tokens.size()]);

    }

    public Integer[] getPositions(){

        return positions;
    }

}
