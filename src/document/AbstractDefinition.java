package document;

import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2015-03-17
 * Time: 09:44
 * To change this template use File | Settings | File Templates.
 */

public class AbstractDefinition {

    ;

    public final String term;
    public String definition = null;
    private ReplacerInterface correctDefinitionRule = null;
    private ReplacerInterface incorrectDefinitionRule = null;
    private ReplacerInterface repetedDefinitionRule = null;
    private int fragmentNo;
    private final DefinitionType type;

    public AbstractDefinition(String term, int fragmentNo){

        this(term, fragmentNo, DefinitionType.REGULAR);
    }

    public AbstractDefinition(String term, int fragmentNo, DefinitionType type){

        this.term = term;
        this.fragmentNo = fragmentNo;
        this.type = type;
    }


    public AbstractDefinition withDefinition(String definition){

        this.definition = definition;
        return this;
    }



    public ReplacerInterface getCorrectDefinitionRule(){

        if(correctDefinitionRule == null){

            String regEx = "\\b(" + strip(term) +")(s|et|en|er)*\\b";
            correctDefinitionRule = new RegExpReplacer(regEx, RegExpReplacer.CASE_SENSITIVE)
                    .withExtraction(1)
                    .withTag("Correct");

        }

        return correctDefinitionRule;

    }

    public ReplacerInterface getRepeatedDefinitionRule() {

        if(repetedDefinitionRule == null && definition != null){

            String regEx = "(" + strip(definition) +")\\b";
            repetedDefinitionRule = new RegExpReplacer(regEx, RegExpReplacer.CASE_SENSITIVE)
                    .withExtraction(1)
            ;

        }
        return repetedDefinitionRule;
    }


    public ReplacerInterface getIncorrectDefinitionRule(){

        if(incorrectDefinitionRule == null){

            String regEx = "\\b(" + strip(term) +")(s|et|en|er)*\\b";
            this.incorrectDefinitionRule = new RegExpReplacer(regEx)
                    .withExtraction(1)
                    .withTag("Incorrect");

        }

        return incorrectDefinitionRule;

    }



    public int getFragmentNo(){

        return fragmentNo;
    }


    private String strip(String x){

        return x.replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
    }


    public DefinitionType getType() {
        return type;
    }
}
