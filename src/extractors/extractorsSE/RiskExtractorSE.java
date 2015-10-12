package extractors.extractorsSE;

import analysis.*;
import extractors.baseExtractors.RiskExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to risk by looking for payment related words and combinations
 *
 *
 * //TODO: Risk extractor has to be elaborated. Too weak...
 */

@Deprecated
public class RiskExtractorSE extends RiskExtractor implements FeatureExtractorInterface {


    public RiskExtractorSE(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"\\b(?:[S|s]traffavgift|vite)\\b"}).extractWordSpan().withTag("Straffavgift"));
                add(new TextPattern(new String[] {"\\bdedikera[d|t]", "(?:[.]*team|grupp)" }).veryClose().extractWordSpan().withTag("Exklusivitet"));

            // "helhetsansvar för respektive Uppdrag och har således ansvar för samtliga moment"

            add(new TextPattern(new String[] {"(?:helhetsansvar|totalansvar)"}).anyOf().extractWordSpan().withTag("Scope"));
            add(new TextPattern(new String[] {"ansvar", "samtliga", "(?:delar|moment)"}).close().extractWordSpan().withTag("Scope"));
            //snarast åtgärda

            add(new TextPattern(new String[] {"(?:snarast|omedelbart)", "åtgärda", }).veryClose().extractWordSpan().withTag("Respons"));
            add(new TextPattern(new String[] {"utan", "(?:dröjsmål|dröjesmål)" }).veryClose().extractWordSpan().withTag("Respons"));

            add(new TextPattern(new String[] {"(?:inte|ej)", "begränsa[d|t]" }).veryClose().extractWordSpan().withTag("Scope"));

            add(new TextPattern(new String[] {"(?:[Ä|ä]ven)", "överskjutande" }).close().extractWordSpan().withTag("Scope"));

            add(new TextPattern(new String[] {"[S|s]tandard(er)*", "bransch(en)*" }).allPresent().extractWordSpan().withTag("Scope"));
            add(new TextPattern(new String[] {"[B|b]branchstandard" }).close().extractWordSpan().withTag("Scope"));

            add(new TextPattern(new String[] {"(?:äger|Äga|har)", "rätt(ighet)*", "att" }).close()
                    .and(new TextPattern(new String[] {"(ändra|förändra|utöka)" }).close())
                    .extractWordSpan().withTag("Scope"));

            add(new TextPattern(new String[] {"(godkännas|godkännande|godkänt)", "specifikation" }).allPresent().extractWordSpan().withTag("Scope"));

            }};

        regExpList = new ArrayList<TextPattern>(){{

                        add(new TextPattern("\\b(m\\.m[\\.]*)\\b").withTag("Scope"));                        // m.m

        }};

        name = "Granska Risk";
        searchKeywords = new String[] {"Varning"};
        actionType = FeatureActionType.CREATE_RISK;


    }

}
