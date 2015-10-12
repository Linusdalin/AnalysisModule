package classifiers.englishClassifiers;

import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import analysis2.TextFragment;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ReferenceClassifier;

/******************************************************************************
 *
 *
 *          Reference classifier
 *
 *          NOTE: This has a overridden classify method that loops over documents and chapters in the project
 *
 */



public class ReferenceClassifierEN extends ReferenceClassifier implements ClassifierInterface {

    private static final String documentParts = "(?:document[s]* |chapter[s]* |item[s]* |section[s]* |attachment[s]* |appendix |appendicies |paragraph[s]* |clause[s]* |schedule[s]* |exhibit[s]* )";


    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new RegExpReplacer("((?:see also |according to |provisions of (?:this )*|pursuant to |in accordance with |under )(?:the )*"+ documentParts+"*(\\d+[\\.\\d+]*))")
                            .withExtraction(1)
                            .withSemanticExtraction(2)
                            .withTag("Chapter"),

            // For shorter descriptions (e.g. prepositions) we require a document part. Otherwise fragments like "in 1" would match.

            new RegExpReplacer("((?:in |under |as |to |for |see )(?:the )*"+ documentParts+"(\\d+[\\.\\d+]*))")
                            .withExtraction(1)
                            .withSemanticExtraction(2)
                            .withTag("Chapter"),


    };


    public ReferenceClassifierEN(){

        super(LanguageSpecificRuleList);
    }

    //((?:see also |\bsee |according to |in accordance with| \bin )(?:chapter |item |section |appendix |paragraph )*(Definitions)[\,|i |in ]* Google Analytics))


    final String chapterRef = "(?:see also |\\bsee |according to |in accordance with| \\bin )(?:chapter |item |section |appendix |paragraph )*";


    public void classify(TextFragment sentence, int currentPass){


        super.classify(sentence, chapterRef, "Chapter", "Document", currentPass);
        name = "#reference";

    }

}
