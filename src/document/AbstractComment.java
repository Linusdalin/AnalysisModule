package document;

/****************************************************************************
 *
 *              This is the representation of a comment from a word document
 *
 */
public class AbstractComment {

    private String type;
    private final String anchor;
    private final String comment;
    private final int fragmentId;
    private String key;
    private int start;
    private int length;

    public AbstractComment(String type, String anchor, String comment, int fragmentId, int start, int length){

        this.type = type;
        this.anchor = anchor;
        this.comment = comment;
        this.fragmentId = fragmentId;
        this.start = start;
        this.length = length;
    }

    public String getAnchor() {
        return anchor;
    }

    public String getComment() {
        return comment;
    }

    public int getFragmentId() {
        return fragmentId;
    }

    public String getType() {
        return type;

    }

    /************************************'
     *
     *      Store the key in an abstract format
     *
     * @param key     - storing the key when parsing to be able to create cross references
     * @return        - self
     */

    public AbstractComment setKey(String key) {

        this.key = key;
        return this;
    }

    public String getKey() {

        return key;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }
}
