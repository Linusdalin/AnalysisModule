package classifiers.englishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DeadlineClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DeadlineClassifierEN extends DeadlineClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(after|before|latest)")
                            .node(NodeClass.Type.DATE))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("stipulated date"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(no|not)")
                            .pattern("(later)")
                            .pattern("(than|then)")
                            .node(NodeClass.Type.DATE))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("stipulated date"),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(closing)")
                            .pattern("(date)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(before|within)")
                            .pattern("(minute|hour|day|week|month|year)[s]*")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Reaktion"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(milestone|tollgate)(s)*")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("milestone"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(more|less)")
                            .pattern("(than|then)")   //sic
                            .node(NodeClass.Type.NUMBER)
                            .pattern("(minute|hour|day|week|month|year)[s]*")
                            .pattern("(before|prior)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Reaktion"),


    };


    public DeadlineClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "Deadline";


    }

}
