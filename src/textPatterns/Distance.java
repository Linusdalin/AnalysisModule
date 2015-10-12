package textPatterns;

import parse.AnalysisFragment;
import parse.Word;

import java.util.List;

/**
 * Created by Linus
 */

public class Distance {

    public static final int INFINITE = 99999999;
    private boolean hit[][];
    private List<Word> words;
    private String[] patterns;
    private List<RecursiveExtraction> recursiveExtractions;

    /*************************************************************
     *
     *
     *
     * @param patterns
     * @param recursiveExtractions
     * @param fragment - for recursion
     *
     *
     *          will pay
     *          he will immediately pay the bill or pay fine
     *
     *          Distance method populates the matrix pattern/word with Trues at hit.
     */


    public Distance(String[] patterns, List<RecursiveExtraction> recursiveExtractions, AnalysisFragment fragment, boolean verbatim, boolean caseSensitive) {

        List<Word> words = fragment.getWords();

        this.recursiveExtractions = recursiveExtractions;

        hit = new boolean[patterns.length][words.size()];
        this.words = words;
        this.patterns = patterns;

        for(int p = 0; p < patterns.length; p++){

            for(int w = 0; w < words.size(); w++){

                if(words.get(w).matches(patterns[p], verbatim, caseSensitive))

                    hit[p][w] = true;


            }
        }

        //display();
    }



    private void display() {

        for(int p = 0; p < hit.length; p++){

            System.out.print("p=" + p + " ");


            for(int w = 0; w < hit[0].length; w++){

                System.out.print(hit[p][w] + ", ");
            }
            System.out.println("");
        }

    }





    /*********************************************************************
     *
     *
     * @return the distance as 10x words in between
     *
     *
     *
     *      // TODO: Count different words differently in the distance calculation.
     *       already done with calculateDistance method. Todo could be deleted.
     *      getDistance should be renamed to getMinDistance since it really does that through the Math.min statement.
     *
     *
     *
     */

    public int getDistance(AnalysisFragment fragment) {

        int distance = INFINITE;    // Big number

        for(int w = 0; w < hit[0].length; w++){

            if(hit[0][w]){

                //System.out.println("found word " + w );
                if(hit.length == 1)
                    distance = 0;        // If there is only one pattern, and we found it, distance = 0!
                else{

                    // Start from n+x where x indicates the last word

                    for(int w2 = 0; w2 < hit[0].length; w2++){

                        // Now look at the last pattern word.

                        if(hit[hit.length-1][w2]){

                            //System.out.println("found hit for last word on " + w2);
                            int foundDistance = calculateDistance(w, w2, fragment, patterns);
                            //System.out.println(" Found Distance " + foundDistance);

                            distance = Math.min(distance, foundDistance);

                        }
                    }
                }
            }
        }

        //System.out.println("Distance: " + distance);
        return distance;
    }


    /*********************************************************************
     *
     *      Calculating the actual distance between two words. This is done the following way:
     *
     *       - Disregard all words in the pattern, we only count the words between
     *        - Add 1 for minor words (like the, a, on etc.)
     *        - Add 10 for major words (typically verbs, nouns and adjectives)
     *
     *
     *
     * @param w  - first word
     * @param w2 - last word
     * @param fragment - for recursion
     * @param patterns
     * @return
     */


    private int calculateDistance(int w, int w2, AnalysisFragment fragment, String[] patterns) {

        List<Word> words = fragment.getWords();

        int distance = 0;

        //Statement to make pattern sequence irrelevant;
        if(w2<w){
            int temp=w;
            w=w2;
            w2=temp;
        }

        for(int i = w+1; i < w2; i++){

            Word word = words.get(i);

            //System.out.println(word.display());
            if(!isOneOf(word, fragment, patterns))
                distance += (word.isMinor() ? 1 : 10);
        }

        return distance;
    }

    private boolean isOneOf(Word word, AnalysisFragment fragment, String[] patterns) {

        for(String p : patterns){

            if(word.matches(p, false, false))
                return true;
        }

        return false;
    }
}
