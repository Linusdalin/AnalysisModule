package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureActionType;
import analysis.FeatureDefinition;
import extractors.extractorsEN.RiskExtractorEN;
import extractors.extractorsSE.RiskExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.Test;
import parse.AnalysisFragment;
import test.oldTests.FeatureTestCommon;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*********************************************************************
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 */
 



public class RiskExtractorTest extends FeatureTestCommon{


    private static final NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");
    private static final NLParser swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @Test
    public void testPenaltyRisk(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;


        f = new AnalysisFragment("If no protective order is obtained and the receiving party has not received a waiver hereunder before one (1) business day prior to the time the recipient must disclose Confidential Information or else stand liable for contempt or suffer other sanction or penalty, the receiving Party may disclose to the minimum extent legally required the requested Confidential Information.",
                "", englishParser);

        new RiskExtractorEN().classify( f, o, tree);

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Penalty Risk"));

        assertThat("Position of word \"Penalty\"", d.getPos(), is(256));
        assertThat("Length of word \"Penalty\"", d.getLength(), is(7));

    }


    @Test
    public void exclusivityRiskEN(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;


        f = new AnalysisFragment("Provide dedicated resources",
                "", englishParser);

        new RiskExtractorEN().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Exclusivity"));


    }


    @Test
    public void exclusivityRiskSE(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;


        f = new AnalysisFragment("Tillhandahålla ett dedikerat arbetsteam",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Exklusivitet"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));


    }

    @Test
    public void scopeRiskEN(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;



        f = new AnalysisFragment("the fastest support on the market",
                "", englishParser);

        new RiskExtractorEN().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));


    }



    @Test
    public void scopeRiskSE(){

        AnalysisFragment f;
        FeatureDefinition d;



        AnalysisOutcome o = new AnalysisOutcome();
        f = new AnalysisFragment("ta helhetsansvar för respektive Uppdrag och har således ansvar för samtliga moment",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o, 0, 2);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));


        AnalysisOutcome o2 = new AnalysisOutcome();
        f = new AnalysisFragment("Leverantören ansvarar för att snarast åtgärda de brister och/eller avvikelser som påtalats av Chigago och som föranlett underkännande. " +
                "Leverantören ska därefter överlämna reviderat Resultat till Chigago för nytt godkännande. Om åtgärdandet av de brister och/eller avvikelser som påtalats" +
                " medför att Uppdrag inte kan genomföras i tid ska Leverantören utbetala vite enligt punkt 22 nedan.",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o2, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o2, 0, 2);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Straffavgift"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));

        d = getDefinition("Additional hit\"", o2, 1, 2);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Respons"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));


        AnalysisOutcome o3 = new AnalysisOutcome();
        f = new AnalysisFragment("Sedan reklamation enligt denna bestämmelse skett ska Leverantören utan dröjsmål avhjälpa felet eller bristen",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o3, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o3, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Respons"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));



        AnalysisOutcome o4 = new AnalysisOutcome();
        f = new AnalysisFragment("Även för överskjutande delar",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o4, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o4, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));


        AnalysisOutcome o5 = new AnalysisOutcome();
        f = new AnalysisFragment("utföra Uppdrag i överensstämmelse med sådana standarder som används i branschen för liknande uppgifter",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o5, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o5, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));
        assertThat(d.getPattern(), is("standarder som används i branschen"));



        AnalysisOutcome o6 = new AnalysisOutcome();
        f = new AnalysisFragment("I den mån det är förenligt med LOU äger Chigago rätt att, efter skriftligt meddelande och med rimlig framförhållning, under hand ändra ett Uppdrag, " +
                "dock ska vid väsentlig förändring Leverantörens medgivande först inhämtas. Leverantören ska inte oskäligen och/eller utan att ange sakliga skäl underlåta att lämna sådant medgivande",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o6, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o6, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));
        assertThat(d.getPattern(), is("äger Chigago rätt att"));            // TODO: This is too short, when extractWordSpan() together with .and() is fixed this will be longer




        AnalysisOutcome o7 = new AnalysisOutcome();
        f = new AnalysisFragment("Oaktat vad som anges ovan, ska Leverantören alltid utföra mindre ändringar i ett Uppdrag såsom exempelvis men inte uteslutande stavfel, buggar m.m., på Chigagos anmodan, utan tillkommande kostnad.",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o7, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o7, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));
        assertThat(d.getPattern(), is("m.m"));




        AnalysisOutcome o8 = new AnalysisOutcome();
        f = new AnalysisFragment("Chigago ska utan oskäligt uppehåll efter sådant överlämnande skriftligen godkänna eller underkänna Uppdrags utförande. " +
                "Uppdrag ska godkännas i de fall Resultatet motsvarar den mellan Parterna överenskomna specifikationen i aktuellt Avropsavtal och Uppdraget i övrigt har utförts i enlighet med Avtalet.",
                "", swedishParser);

        new RiskExtractorSE().classify( f, o8, tree);
        d = getDefinition("Expecting hit for \"" + f.getText() +"\"", o8, 0, 1);

        assertThat(d.getType().getName(), is( "Risk"));
        assertThat(d.getTag(), is("Scope"));
        assertThat(d.getAction(), is(FeatureActionType.CREATE_RISK));
        assertThat(d.getPattern(), is("godkännas i de fall Resultatet motsvarar den mellan Parterna överenskomna specifikationen"));




    }

}
