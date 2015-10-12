package test.extractorTest;


import classifiers.classifierTests.AnalysisTest;
import language.English;
import language.LanguageInterface;
import language.Swedish;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/*********************************************************************
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 */
 



public class LanguageTest extends AnalysisTest {



    @Test
    public void keywordsTestSE(){

        LanguageInterface swedish = new Swedish();
        String[] keywords = swedish.getClassifierKeywords();
        assertVerbose("Found keywords for classifications", keywords.length, is(14));

    }

    @Test
    public void keywordsTestEN(){

        LanguageInterface english = new English();
        String[] keywords = english.getClassifierKeywords();
        assertVerbose("Found keywords for classifications", keywords.length, is(14));

    }


}
