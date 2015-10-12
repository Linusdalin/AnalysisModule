package classifiers.englishClassifiers;

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



public class DefinitionSourceClassifierEN extends DefinitionSourceClassifier implements ClassifierInterface {

    private static final String capitalizedWords = "(([A-Z][\\w-]*)+)";

    private static final ReplacerInterface[] LanguageSpecific = {



            new RegExpReplacer("\\(\\s*(?:a|an|the|this|below|together)*\\s*[\"|'](.*?)[\"|']\\s*\\)"),                       // ex: ( the "agreement") or ('agreement')

            new RegExpReplacer("^\\s*([A-Z].*?)\\s{3,}\\w+")
                    .withCriteria(new Criteria().meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "[D|d]efinition\\w*"))),             // ex: Agreement    is this document

            new RegExpReplacer("^\\s*([A-Z].*?)\\s*$")
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "[D|d]efinition\\w*"))
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.LEFT_COLUMN_OF_2))),                                          // ex: Agreement    in the left column in definitions chapter"

            //new RegExpReplacer("^(?!(?:This))"+ capitalizedWords+" (?:is |are |define[s]* |denote[s]* )(?:the |an |a )")
            //        .caseSensitive()
            //        .withExtraction( 1 ),



    };


    public DefinitionSourceClassifierEN(){

        super(LanguageSpecific);
        keywords = "";
        name = "Definition";

    }



}
