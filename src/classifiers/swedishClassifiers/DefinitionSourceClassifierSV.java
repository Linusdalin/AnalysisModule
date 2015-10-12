package classifiers.swedishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefinitionSourceClassifier;
import parse.POSClassification;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DefinitionSourceClassifierSV extends DefinitionSourceClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new RegExpReplacer("\"(.*?)\"\\s*(avser|avses)"),


            new RegExpReplacer("\\(\\s*(?:en|ett|den|det|nedan|tillsammans)*\\s*[\"|'](.*?)[\"|']\\s*\\)"),                         // ex: ( the "agreement") or ('agreement')

            new RegExpReplacer("\\(\\s*(?:nedan |tillsammans )(?:kalla[tde]* |benäm[ntdas]* )\\s*[\"|']*(.*?)[\"|']*\\s*\\)"),        // ex: ( nedan kallad "agreement")

            new RegExpReplacer("\\s*(?:nedan |tillsammans )(?:kalla[tde]* |benämn[ntdas]* )\\s*[\"|'](.*?)[\"|']\\s*"),                // ex:     nedan kallad "agreement")

            new RegExpReplacer("^\\s*([A-Z].*?)\\s{3,}\\w+")
                    .withCriteria(new Criteria().meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "[D|d]efinition\\w*"))),             // ex: Agreement    is this document"

            new RegExpReplacer("^\\s*([A-Z].*?)\\s*$")
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "[D|d]efinition\\w*"))
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.LEFT_COLUMN_OF_2))),                                          // ex: Agreement    in the left column in definitions chapter"

            new RegExpReplacer("^(?!(?:den|det|han|hon|vi|ni|dom|hen))([A-Z].*?) (?:menar |avser |denote[s]* )(?:en |den |det )")
                    .withExtraction( 1 ),



    };


    public DefinitionSourceClassifierSV(){

        super(LanguageSpecific);
        keywords = "";
        name = "Definition";

    }

    public void classify(TextFragment sentence, int currentPass){

        //AnalysisLogger.log(Level.DEBUG, " Definition Source: \"" + sentence.getText() + "\"");
        super.classify(sentence, currentPass);
        //AnalysisLogger.log(Level.DEBUG, " Ready:\n" + sentence.display( 0 ));


    }

}
