package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class StandardComplianceClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

                        //TODO: Add relevant URLs to specific standards


            new RegExpReplacer("\\b(emas)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">EMAS</a>"),

            new RegExpReplacer("\\b(sni[-\\s]*2007[\\s]*(kod .)*)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">SNI 2007</a>"),          //TODO: Add correct URL

            new RegExpReplacer("\\b(sepa-fast)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">SEPA-FAST</a>"),

            new RegExpReplacer("\\b(Card Payment Terminal Management Message)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">Card Payment Terminal Management Message</a>"),


            new RegExpReplacer("\\b(pci[-\\s]dss)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">PCI-DSS</a>"),

            new RegExpReplacer("\\b(pci)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">PCI</a>"),


            new RegExpReplacer("\\b([PBD]ER)\\b", RegExpReplacer.CASE_SENSITIVE)      // encoding
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">BER</a>"),



            new RegExpReplacer("\\b(emv[\\s]*2000)\\b")
                    .withSemanticExtraction(1)
                    .withTag("a href=\"#\">emv2000</a>"),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("((ss\\-)*iso(\\/iec)*)")            // SS-ISO/IEC is the official name
                            .pattern("(270[01][0-9])"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("Security"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("((?:ss\\-)*iso(?:\\/iec)*(270[01][0-9]))"))            // SS-ISO/IEC is the official name
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("Security"),



            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("((ss\\-)*iso(\\/iec)*)")            // SS-ISO/IEC is the official name
                            //.pattern("iso")            // SS-ISO/IEC is the official name
                            .pattern("1400[0-9]"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("environment"),

            new RegExpReplacer("((?:ss\\-)*iso(?:\\/iec)*\\s*(1400[0-9]))")
                    .withSemanticExtraction(2)
                    .withTag("environment"),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("((ss\\-)*iso(\\/iec)*)")            // SS-ISO/IEC is the official name
                            //.pattern("iso")            // SS-ISO/IEC is the official name
                            .pattern("900[0-9]"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("quality"),

            new RegExpReplacer("((?:ss\\-)*iso(?:\\/iec)*\\s*(900[0-9]))")
                    .withSemanticExtraction(2)
                    .withTag("quality"),




                         //\b((?:itu)*[\s]*x[\s. ]*(\d{3}))\b
            new RegExpReplacer("\\b((?:itu)*[\\s]*x[\\. ]*(\\d{3}))\\b")    //ITU x.nnn
                    .withSemanticExtraction(2)
                    .withTag(""),



                  /*

                      new RegExpReplacer("\\b(iso(?:/iec)*[\\s]*((\\d+)(-\\d)*))\\b")
                    .withSemanticExtraction(2)
                    .withTag("xxx"),

           */



    };

    protected StandardComplianceClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.TECHNICAL_TERM, FeatureTypeTree.STANDARDS_COMPLIANCE);
    }

}
