package stemming;

/**
 *
 *    Generic interface for Snowball stemmer. This is not part of the automatic
 *    Snowball generation so the classes have to me manually copied from the
 *    package and the interface added.
 */

public interface StemmerInterface {


    // Set the word to be stemmed
    void setCurrent(String word);

    //Execute
    boolean stem();

    // Get the word
    String getCurrent();


}
