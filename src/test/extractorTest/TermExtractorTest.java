package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsEN.TermExtractorEN;
import extractors.extractorsSE.TermExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.Test;
import parse.AnalysisFragment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*********************************************************************
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 */
 



public class TermExtractorTest {

    private static final NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");
    private static final NLParser swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @Test
    public void testTermsExtractorEN(){

        AnalysisFragment f;
        FeatureDefinition d;

        AnalysisOutcome o = new AnalysisOutcome();
        f = new AnalysisFragment("This Agreement shall become effective on the Effective Date and remain in full force and effect for two (2) years " +
                "from the Commencement Date (the “original Term”). Six (6) months prior to the Expiration Date, the Vendor shall propose terms to Buyer for renewing the Agreement."
                , "", englishParser);
        new TermExtractorEN().classify( f, o, tree );

        assertThat("Expecting one featureDefinition", o.getDefinitions().size(), is(1));

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Term"));
        assertThat(d.getTag(), is("Period"));



        AnalysisOutcome o2 = new AnalysisOutcome();
        f = new AnalysisFragment("If the Parties are unable to agree upon the terms and conditions for the renewal of the Agreement ninety (90) days prior to the Expiration Date, Buyer may elect to extend the Term for a period of time designated by Buyer of up to twelve (12) months from the expiration date at the then-current terms and conditions."
                , "", englishParser);
        new TermExtractorEN().classify( f, o2, tree );

        d= o2.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Term"));
        assertThat(d.getTag(), is("Extension Term"));



        AnalysisOutcome o3 = new AnalysisOutcome();
        f = new AnalysisFragment("Buyer will have the right to extend the Agreement for up to two (2) additional successive periods of up to one (1) year each at the then current terms and conditions."
                , "", englishParser);
        new TermExtractorEN().classify( f, o3, tree );

        d= o2.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Term"));
        assertThat(d.getTag(), is("Extension Term"));


    }

    @Test
    public void testTermsExtractorSE(){


        new ExtractorTesterOld("Detta Ramavtal gäller under två (2) år från och med dess undertecknande (Initial Avtalsperiod). [##Part] har rätt att förlänga Avtalsperioden " +
                        "två gånger om vardera ett (1) år, varför Avtalsperioden som längst kan uppgå till totalt fyra (4) år. Om [##Part] önskar förlänga Avtalsperioden ska [##Part] skriftligen " +
                        "meddela Leverantören detta senast tre (3) månader innan Avtalsperiodens utgång. Om ingen förlängning sker löper Avtalet ut vid Avtalsperiodens utgång utan föregående uppsägning.")
                .withParser(swedishParser)
                .withExtractor(new TermExtractorSE( ))
                .expectingType(FeatureTypeTree.TERM)
                .expectingPattern("gäller under")
                .expectingTag("Period")
                .expectingSignificance(ExtractorTesterOld.ExpectedSignificance.HIGH)
                .test(0, 2);


        new ExtractorTesterOld("Under avtalsperioden")
                .withParser(swedishParser)
                .withExtractor(new TermExtractorSE( ))
                .expectingType(FeatureTypeTree.TERM)
                .expectingPattern("avtalsperioden")
                .expectingTag("Period")
                .expectingSignificance(ExtractorTesterOld.ExpectedSignificance.LOW)
                .test(0,1);



        /*


        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("Detta Ramavtal gäller under två (2) år från och med dess undertecknande (”Initial Avtalsperiod”). [##Part] har rätt att förlänga Avtalsperioden " +
                "två gånger om vardera ett (1) år, varför Avtalsperioden som längst kan uppgå till totalt fyra (4) år. Om [##Part] önskar förlänga Avtalsperioden ska [##Part] skriftligen " +
                "meddela Leverantören detta senast tre (3) månader innan Avtalsperiodens utgång. Om ingen förlängning sker löper Avtalet ut vid Avtalsperiodens utgång utan föregående uppsägning."
                , "", englishParser);
        new TermExtractorSE().classify( f, o, tree );

        assertThat("Expecting one featureDefinition", o.getDefinitions().size(), is(1));

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Term"));
        assertThat(d.getTag(), is("Period"));

        */

    }


}
