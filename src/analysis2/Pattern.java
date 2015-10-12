package analysis2;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-07
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class Pattern {


    private final String text;
    private final int pos;
    private final int length;

    public Pattern(String text, int pos){

        this.text = text;
        this.pos = pos;
        this.length = (text == null ? 0 : text.length());
    }

    public String getText() {
        return text;
    }

    public int getPos() {
        return pos;
    }

    public int getLength() {
        return length;
    }
}
