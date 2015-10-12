package document;


import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import language.LanguageCode;
import language.LanguageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-05-13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 *
 *
 */

public class AbstractStructureItem {


    private AbstractFragment topElement;
    private ReplacerInterface classifierRuleInternal = null;
    private ReplacerInterface classifierRuleExternal = null;

    static final int NO_INDENTATION = -1;

    private int indentationLevel = NO_INDENTATION;

    private String key;
    private String styleId=null;
    private int structureItemNumber;
    private StructureType structureType = StructureType.UNKNOWN;

    private LanguageCode documentLanguage;


    final String chapterRefEn = "(?:see also |\\bsee |according to |in accordance with| \\bin )(?:chapter |item |section |appendix |paragraph )*";
    final String chapterRefSv = "(?:enligt |framgår av |motsvaras av |per |enlighet med |\\bi )(?:punkt(?:en)* |kapitel | kapitlet |avsnitt(?:et)* |stycke(?:t)* |appendix(?:et)* |paragraf(?:en)* )*";


    public AbstractStructureItem() {

    }

    public AbstractStructureItem setLanguage(LanguageCode language) {

        this.documentLanguage = language;
        return this;
    }


    public ReplacerInterface getClassifierRuleOtherDocument(String documentName){

        if(classifierRuleExternal == null){

            String body = strip(topElement.getBody());

            String regex = "(" + getChapterRefForLanguage(documentLanguage) + "(" + body + ")" + "(\\,|i |in )* "+documentName + ")";

            classifierRuleExternal = new RegExpReplacer(regex)
                    .withExtraction(1)
                    .withSemanticExtraction(2);

        }

        return classifierRuleExternal;

    }

    public ReplacerInterface getClassifierRuleThisDocument(){

        if(classifierRuleInternal == null){

            String body = strip(trimNumbering(topElement.getBody()));

            String regex = "(" + getChapterRefForLanguage(documentLanguage) + "(" + body + "))";

            classifierRuleInternal = new RegExpReplacer(regex)
                    .withExtraction(1)
                    .withSemanticExtraction(2)
            ;
        }
        return classifierRuleInternal;
    }

    private String getChapterRefForLanguage(LanguageCode documentLanguage) {

        if(documentLanguage.code.equals("EN"))
            return chapterRefEn;
        if(documentLanguage.code.equals("SV"))
            return chapterRefSv;

        return chapterRefEn;

    }


    public AbstractStructureItem setId(int id) {

        this.structureItemNumber = id;
        return this;
    }

    public int getID() {
        return structureItemNumber;
    }

    public AbstractStructureItem setStyle(StructureType type, String styleID) {

        this.structureType = type;
        this.styleId = styleID;
        return this;
    }

    public String getStyle() {

        return styleId;
    }

    /************************************'
     *
     *      Store the key in an abstract format
     *
     * @param key
     * @return
     */

    public AbstractStructureItem setKey(String key) {

        this.key = key;
        return this;
    }

    public String getKey() {

        return key;
    }


    public AbstractStructureItem setIndentation(int indentation) {

        this.indentationLevel = indentation;
        return this;
    }

    public int getIndentation() {

        return indentationLevel;
    }


    public StructureType getStructureType() {

        return structureType;
    }

    /*******************************************************************************
     *
     *          Top element is the fragment element that corresponds to a structure element.
     *
     *          e.g. the actual text of the headline
     *
     * @param fragment
     * @return
     */

    public AbstractStructureItem setTopElement(AbstractFragment fragment) {

        topElement = fragment;
        return this;
    }


    public AbstractFragment getTopElement(){

        return topElement;
    }


    private String trimNumbering(String text) {

        int len = text.length();
        int st = 0;
        char[] val = text.toCharArray();

        while ((st < len) && (val[st] == '.' || (val[st] >= '0' && val[st] <= '9'))){
            st++;
        }
        return ((st > 0) ? text.substring(st).trim() : text);
    }


    protected String strip(String x){

        return x.replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\(", "")
                .replaceAll("/", "")
                .replaceAll("\\)", "");
    }


}
