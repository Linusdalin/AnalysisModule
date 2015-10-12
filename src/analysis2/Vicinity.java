package analysis2;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-04
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */
public enum Vicinity {

    ADJACENT,
    VERY_CLOSE,             // Accepting only minor words
    CLOSE,                  // Accepting one major ond some minor words
    PRETTY_CLOSE,           // Accepting some major words (typically in the same sentence
    ALL_PRESENT,
    NONE,
    ANY_OF,
}
