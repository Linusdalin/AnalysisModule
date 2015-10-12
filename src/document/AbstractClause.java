package document;


/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-05-13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 *
 *          /TODO: (Future) Not implemented indentation levels on clauses
 *
 */

public class AbstractClause {

    private String text;
    private int clauseNumber;
    private static final int NO_PARENT = -1;
    private int parent = NO_PARENT;

    private String key;

    private String style=null;
    private String styleId=null;


    public AbstractClause(String text) {

        this.text = text;
    }

    public String getBody() {

        return text;
    }

    public AbstractClause setId(int id) {

        this.clauseNumber = id;
        return this;
    }

    public int getID() {
        return clauseNumber + 1;
    }

    public AbstractClause setStyle(String styleID) {
        this.style = style;
        this.styleId = styleID;
        return this;
    }

    public String getStyle() {

        return style;
    }
    public String getStyleId() {

        return styleId;
    }

    /************************************'
     *
     *      Store the key in an abstract format
     *
     * @param key
     * @return
     */

    public AbstractClause setKey(String key) {

        this.key = key;
        return this;
    }

    public String getKey() {

        return key;
    }

}
