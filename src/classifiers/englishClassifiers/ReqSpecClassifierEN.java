package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ReqSpecClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ReqSpecClassifierEN extends ReqSpecClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.DEFINITION_USAGE)
                            .pattern("(shall|should|must)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern(regex_supplierEN)
                            .pattern("(shall|should|must)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern(regex_solutionEN)
                            .pattern("(shall|should|must)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(capacity|requirement(s)*)")
                            .pattern("(of|on|for)")
                            .pattern(regex_solutionEN))
                    .withExtraction(TokenReplacer.WORD_SPAN),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(shall|should|must)")
                            .pattern("(be)")
                            .pattern("\\b((.+)ed|(.*)able|(.+)ible|done|built|held)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(shall|should|must)")
                            .pattern("(have|include)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(there|it)")
                            .pattern("(shall|should|must)")
                            .pattern("(be|exist|provide)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),


           //important to provide

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(important)")
                            .pattern("(provide)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

    };


    public ReqSpecClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "RequirementSpec";
    }


}
