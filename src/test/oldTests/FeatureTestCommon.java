package test.oldTests;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import document.*;
import language.English;
import language.LanguageInterface;
import language.Swedish;
import org.hamcrest.CoreMatchers;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-05
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class FeatureTestCommon {

    protected static final int ANY_NUMBER = -1;

    protected FeatureDefinition getDefinition(String text, AnalysisOutcome o, int no, int expected) {

        int matches = o.getDefinitions().size();

        if(expected == ANY_NUMBER){

            assertThat(text, matches > 0, is(true));

        }
        else
            assertThat(text, matches == expected, is(true));


        return o.getDefinitions().get(no);

    }


    protected void assertNoDefinitions(AnalysisOutcome o) {

        assertThat("Not expecting any classifications", o.getDefinitions().size(), CoreMatchers.is(0));
    }


    protected void assertNoDefinitions(String text, AnalysisOutcome o) {

        assertThat(text, o.getDefinitions().size(), CoreMatchers.is(0));
    }

    /***********************************************************************************
     *
     *              Mock documents for analysis test (definitions and references
     *
     */



    protected static AbstractDocument getMockAvtal(AbstractProject aProject) {

        final LanguageInterface language = new Swedish();

        final AbstractFragment heading1 = new AbstractFragment("1 Introduktion")
                .setStyle(StructureType.HEADING)
                ;

        final AbstractFragment heading2 = new AbstractFragment("2 Sammanfattning")
                .setStyle(StructureType.HEADING)
                ;

        AbstractDocument mockDocument = new AbstractDocument("Avtalet",
                new ArrayList<AbstractStructureItem>() {
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()).setTopElement(heading1));}           //Introduktion
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()).setTopElement(heading2));}           //Sammanfattning
                },
                new ArrayList<AbstractDefinition>() {
                    {add(new AbstractDefinition("Köparen", 0));}
                    {add(new AbstractDefinition("Säljaren", 0));}

                },
                aProject,
                language.getLanguageCode());

        return mockDocument;


    }

    protected static AbstractDocument getMockAppendix(AbstractProject aProject) {

        final LanguageInterface language = new Swedish();

        AbstractDocument mockAppendix = new AbstractDocument("Bilaga 1",
                new ArrayList<AbstractStructureItem>() {
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()));}             // First Clause
                    {add(new AbstractStructureItem().setLanguage(language.getLanguageCode()));}             // Second Clause

                },
                new ArrayList<AbstractDefinition>() {

                },
                aProject,
                language.getLanguageCode());

        return mockAppendix;
    }

}
