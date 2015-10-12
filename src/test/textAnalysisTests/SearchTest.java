package test.textAnalysisTests;

import classifiers.classifierTests.AnalysisTest;
import language.English;
import language.Swedish;
import org.junit.BeforeClass;
import org.junit.Test;
import stemming.EnglishStemmer;
import stemming.StemmerInterface;
import stemming.SwedishStemmer;
import system.TextMatcher;

import java.io.*;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class SearchTest extends AnalysisTest {


    private static TextMatcher textMatcherEN;
    private static TextMatcher textMatcherSE;

    @BeforeClass
    public static void preamble(){

        textMatcherEN = new TextMatcher().setLanguage(new English(), "TextAnalysis/models");
        textMatcherSE = new TextMatcher().setLanguage(new Swedish(), "TextAnalysis/models");

    }



    @Test
    public void stemTest(){

        List<String> matches;

        matches = textMatcherEN.prepareSearchString("indexing").useStemMatch().getMatch("it should match index");

        System.out.println("Matches:" + matches.toString());

        assertVerbose("index", matches.contains("index"), is(true));
        matches = textMatcherEN.prepareSearchString("head").useStemMatch().getMatch("We headed in the direction");

        System.out.println("Matches:" + matches.toString());
        assertVerbose("headed", matches.contains("headed"), is(true));


        matches = textMatcherEN.prepareSearchString("collect").useStemMatch().getMatch("\"GATC\" means the Google Analytics Tracking Code, which is installed on a Property for the purpose of collecting Customer Data, together with any fixes, corrections and upgrades provided to You.");
        System.out.println("Matches:" + matches.toString());
        assertTrue(matches.contains("collecting"));
    }

    @Test
    public void synonymTest(){

        List<String> matches;

        matches = textMatcherEN.prepareSearchString("beautiful").useSynonyms().getMatch("it is a lovely weather");

        System.out.println("Matches:" + matches.toString());

        assertTrue(matches.contains("lovely"));


        matches = textMatcherEN.prepareSearchString("beautiful").getMatch("it is a lovely weather");

        assertNull(matches );

    }


    @Test
    public void synonymTestSwedish(){

        List<String> matches;

        matches = textMatcherSE.prepareSearchString("övning").useSynonyms().getMatch("Det krävs träning");

        System.out.println("Matches:" + matches.toString());

        assertTrue(matches.contains("träning"));

    }

    @Test
    public void specialCharacters(){


        List<String> matches;


        matches = textMatcherEN.prepareSearchString("Linus").getMatch("Linus,check this");
        System.out.println("Matches:" + matches.toString());
        assertThat(matches.size(), is( 1 ));

    }

    @Test
    public void basicTest(){


        List<String> matches;

        // Find two words but one is in the ignore list

        matches = textMatcherEN.prepareSearchString("this is").getMatch("this is the text from which we are to search and find");
        System.out.println("Matches:" + matches.toString());
        assertThat(matches.size(), is(2));

        matches = textMatcherEN.prepareSearchString("annotation").caseInsensitive().getMatch("This is an annotation");
        System.out.println("Matches:" + matches.toString());
        assertThat(matches.size(), is(1));

        matches =  textMatcherEN.prepareSearchString("find text").getMatch("this is the text from which we are to search and find");
        System.out.println("Matches:" + matches.toString());
        assertThat(matches.size(), is(2));

        // Search should be case insensitive

        matches =  textMatcherEN.prepareSearchString("Find TEXT").caseInsensitive().getMatch("this is the teXt from which we are to search and find");
        System.out.println("Matches:" + matches.toString());
        assertThat(matches.size(), is(2));


        matches =  textMatcherEN.prepareSearchString("search anything").getMatch("this is the text from which we are to search and find");
        assertNull(matches);

        matches = textMatcherEN.prepareSearchString("Definitions").caseInsensitive().getMatch("Definitions");
        System.out.println("Matches:" + matches.toString());
        assertThat(matches.size(), is(1));



    }


    @Test
    public void stemSE(){

        StemmerInterface stemmer = new SwedishStemmer();

        stemmer.setCurrent("spelar");
        stemmer.stem();
        assertThat(stemmer.getCurrent(), is("spel"));

    }

    @Test
    public void stemEN(){

        StemmerInterface stemmer = new EnglishStemmer();

        stemmer.setCurrent("playing");
        stemmer.stem();
        assertThat(stemmer.getCurrent(), is("play"));

    }

}
