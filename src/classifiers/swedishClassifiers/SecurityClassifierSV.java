package classifiers.swedishClassifiers;

import analysis.FeatureActionType;
import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SecurityClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SecurityClassifierSV extends SecurityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("((ss\\-)*iso(\\/iec)*)")            // SS-ISO/IEC is the official name
                            //.pattern("iso")            // SS-ISO/IEC is the official name
                            .pattern("270[01][0-9]"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("datainspektionen")
                            .pattern("dnr"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(personuppgiftslag(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(PUL)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(risk|hotbilds)(analys(en)*|bedöming(en)*|nivå(er|n)*|hantering(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("risk"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(hotbild(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("risk"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(behörighets|tillträdes|access|åtkomst)(begränsning(en)*|tilldelning(en)*|kontroll(er|en)*|återkallande(n|t)*|återkallelse(r|n)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("behörig")
                            .pattern("(personal|access)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(åtkomst(en)*|access(en)*)")
                            .pattern("(it-utrustning(en)*|information(en)*|system(et|en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(tilldela|bort|dela)")
                            .pattern("behörighet(er|en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("fysisk(a)*")
                            .pattern("säkerhet(en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(skapa(nde)*|återsälla(nde)*)")
                            .pattern("säkerhetskopi(a|or)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(kontinuitetsplan(er|ering)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("disaster")
                           .pattern("recovery(\\-)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),



            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("autentiser(a|ing)*")
                            .pattern("användare(en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(personlig(a)*|rollbaserad(e)*)")
                            .pattern("inloggning(suppgifter)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("användarnamn(et)*")
                            .pattern("lösenord(et)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("sekretess(markering(en)*|klassning(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("säkerhets(åtgärd(en|er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("aktivitet"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(informations|it-)*säkerhet(s)*(råd(et)*|revision(en|er)*|chef(er|en)*|process(en|er)*|nivå(ner)*|policy(n)*|strategi|granska|granskning(en)*|krav(et|en)*|klassining(en|ar)*|rutin(en|er)*|instruktion(en|er)*|analys(en|er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(information(s)|it(\\-)*)*(klassning(en|ar)*|säkerhet(en|s)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("personuppgift(er)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("systemsäkerhet(sanalys)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("system"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(brandvägg(ar)*|virusskydd(et)*|antivirus)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("system"),




            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(informations)*säkerhets(arbete(t)*|bestämmelse(r)*|fråg(a|or)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(säkerhets)*incident(en|er)*")
                            .pattern("åtgärda(d|s|ts)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("incident"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("obehörig")
                            .pattern("tillgång"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("intrång"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("information")
                            .pattern("security")
                            .pattern("management"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("tillträdesskydd"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("access"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(säkerhets)*incident(en|er)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("incident"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(säkerhets)*incident(hantering(en)*|rutin(er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("incident"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(trusted|untrusted|semi(\\-)*trusted)(zon)*")
                            .pattern("(nät|zon|system|zone|terminering|(proxy|web)*serv(er|rar))"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("systemarkitektur"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(burton(modell(en)*)*|DMZ)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("systemarkitektur"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(ddos(\\-)*(skydd)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),


            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .pattern("(antivirus|virusskydd(et)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("process"),



    };





    public SecurityClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Säkerhet";
    }


}
