package openNLP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linus
 *
 */


public class ChunkList {

    public List<Chunk> content;
    private String tag;

    public ChunkList(){

        content = new ArrayList<Chunk>();
        tag = "";
    }

    public void add(Chunk c, String tag){

        content.add(c);
        if(tag != null && !this.tag.contains(tag)){

           this.tag += (this.tag.length() > 0 ? " " : "") + tag;
        }
    }

    public int size() {
        return content.size();
    }

    /*****************************************
     *
     *          From the list of Chunks, extract the content between "  "
     *
     * @return a list of strings - this will not be modifiable any more
     */


    public List<String> extractQuote() {

        List<String> words = new ArrayList<>();

        for(Chunk chunk : content ){

            if(chunk.isQuoted())
                words.addAll(chunk.getQuotes());

        }

        return words;
    }

    public String getTag() {
        return tag;
    }
}
