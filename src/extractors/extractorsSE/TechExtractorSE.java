package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.TechExtractor;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class TechExtractorSE extends TechExtractor implements FeatureExtractorInterface {


    public TechExtractorSE(){

        super();

        regExpList.add(new TextPattern("(mjukvara|Microsoft|Windows|Mac|Apple|PC|vm[- ]*Ware|VM[- ]*ware)").withTag("System"));
        regExpList.add(new TextPattern("(data[ ]*hall|drift[s ]*hall|UPS|rack)").withTag("Miljö"));
        regExpList.add(new TextPattern("(operativ[ ]*system|patchnivå|Microsoft|Windows|Mac|Apple|PC|Linux|Redhat|HP-ux|Solaris)").withTag("Operativsystem"));
        regExpList.add(new TextPattern("(MySQL|Oracle|MS-SQL|databas)").withTag("Databas"));

        regExpList.add(new TextPattern("((applikation[s]*|databas|server|system)[- ]*(drift|förvaltning|förvaltare))").withTag("Organisation"));
        regExpList.add(new TextPattern("((service|drift[s]*|applikation[s]*|support|IT)[- ]*(organisation|ansvar|support))").withTag("Organisation"));
        regExpList.add(new TextPattern("(test|produktion[s]*)[- ]*(miljö|system)").withTag("System"));

        regExpList.add(new TextPattern("(hårdvara|firmware|BIOS|bladserver|bladchassi|cpu|\\bMB\\b)").withTag("Hårdvara"));
        regExpList.add(new TextPattern("(virtuell|virtuella|virtualiserade|fysisk[a]*) (server|servrar)").withTag("virtualisering"));
        regExpList.add(new TextPattern("(tjänsteägare|incidenthantering|ändringshantering|change control)").withTag("Process"));

        name = "Teknisk Specifikation";
        searchKeywords = new String[] {"spec", "tech operation", "specifikation"};



    }


}
