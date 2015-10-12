package analysis2;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 *
 *          //TODO: Add types for semanticExtraction value
 */
public class Extraction {


    private final String syntacticExtraction;
    private String semanticExtraction;

    public Extraction(String text) {

        this.syntacticExtraction = text;

    }

    public String getSyntacticExtraction() {
        return syntacticExtraction;
    }


    public String getSemanticExtraction() {
        return semanticExtraction;
    }

    public Extraction withSemanticExtraction(String semanticExtraction) {

        this.semanticExtraction = semanticExtraction;
        return this;
    }
}
