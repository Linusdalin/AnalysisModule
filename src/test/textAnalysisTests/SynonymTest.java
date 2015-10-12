package test.textAnalysisTests;

import analysis.AnalysisOutcome;
import extractors.extractorsEN.RiskExtractorEN;
import language.English;
import language.Swedish;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;
import parse.POSClassification;
import synonyms.SynonymList;
import synonyms.SynonymManager;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class SynonymTest {

    private static SynonymManager synonymManager;

    @BeforeClass
    public static void preamble(){

        synonymManager = new SynonymManager();

    }

    @Test
    public void basicTest(){

        SynonymList swedishSynonyms = synonymManager.getSynonymList(new Swedish(), "TextAnalysis/models" );

        assertTrue(swedishSynonyms.getSynonym("abakus").contains("kulram"));
        assertThat(swedishSynonyms.getSynonym("abdikera").size(), is(2));

        //System.out.println(swedishSynonyms.getSynonym("abdikera").toString());

    }

}
