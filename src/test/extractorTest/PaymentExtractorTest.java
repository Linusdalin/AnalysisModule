package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsEN.CompensationExtractorEN;
import extractors.extractorsSE.CompensationExtractorSE;
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
 



public class PaymentExtractorTest extends FeatureTestCommon {

    private static final NLParser englishParser = new NLParser(new English(), "TextAnalysis/models");
    private static final NLParser swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @Test
    public void testInvoiceTermsExtractor(){

        AnalysisFragment f;
        FeatureDefinition d;

        try{

            new ExtractorTesterOld(" invoices shall be in a form approved by .")
                    .withParser(englishParser)
                    .withExtractor(new CompensationExtractorEN())
                    .expectingType(FeatureTypeTree.COMPENSATION)
                    .expectingTag("Invoice Term")
                    .test(0, 1);

            new ExtractorTesterOld(" The Vendor will invoice Buyer monthly in accordance with ")
                    .withParser(englishParser)
                    .withExtractor(new CompensationExtractorEN())
                    .expectingType(FeatureTypeTree.COMPENSATION)
                    .expectingTag("Invoice Term")
                    .test(0, 1);

            new ExtractorTesterOld("Vendor shall not invoice Buyer, and Buyer will not be obligated to pay, any charges that are not properly invoiced within three months after the end of the month to which the charges correspond.")
                    .withParser(englishParser)
                    .withExtractor(new CompensationExtractorEN())
                    .expectingType(FeatureTypeTree.COMPENSATION)
                    .expectingTag("Invoice Term")
                    .test(0, 1);

            new ExtractorTesterOld("Except in the case of a delay in issuing invoices owing to a dispute between the Parties regarding Services provided, or any other default on the part of Buyer, Seller shall not be entitled to issue invoices to Buyer in respect of Services (and taxes associated with such Services) provided by Buyer more than three months previously or such other period separately agreed between the parties.")
                    .withParser(englishParser)
                    .withExtractor(new CompensationExtractorEN())
                    .expectingType(FeatureTypeTree.COMPENSATION)
                    .expectingTag("Invoice Term")
                    .test(0, 1);



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void testPriceSwedishExtractor(){

        AnalysisFragment f;
        FeatureDefinition d;

        try{

            AnalysisOutcome o = new AnalysisOutcome();
            f = new AnalysisFragment("Timarvode per kompetens. Det pris som anbudsgivaren offererar för respektive kompetens utgör ett takpris för det pris som offereras i samband med varje uppdrag.",
                    "", swedishParser);
            new CompensationExtractorSE().classify( f, o, tree );

            d = getDefinition("Expecting hit for \"" + f.getText() + "\"", o, 0, 3);
            assertThat(d.getType().getName(), is( "Compensation"));
            assertThat(d.getTag(), is("Ersättning"));


            AnalysisOutcome o2 = new AnalysisOutcome();
            f = new AnalysisFragment("Fast månadspris för hosting hos leverantören per utbildning enligt villkoren i Kravspecifikationens punkt 2.2.1.6.",
                    "", swedishParser);
            new CompensationExtractorSE().classify( f, o2, tree );

            d = getDefinition("Expecting hit for \"" + f.getText() + "\"", o2, 0, 2);
            assertThat(d.getType().getName(), is( "Compensation"));
            assertThat(d.getTag(), is("Ersättning"));


            AnalysisOutcome o3 = new AnalysisOutcome();
            f = new AnalysisFragment("Tjänster som efterfrågas i denna upphandling ska kunna avropas som pris per timma, per dag, per vecka, per månad eller som fast pris. Vilken prissättning som är bäst lämpad för det specifika uppdraget ",
                    "", swedishParser);
            new CompensationExtractorSE().classify( f, o3, tree );

            d = getDefinition("Expecting hit for \"" + f.getText() + "\"", o3, 0, 1);
            assertThat(d.getType().getName(), is( "Compensation"));
            assertThat(d.getTag(), is("Ersättning"));

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }



    }

    @Test
    public void failTest(){

        AnalysisFragment f;

        String[] figureExamples = {

                "avgift inte månad",            //TODO: Fix this. Is it close that is wrong or the match of "per"?
        };

        try{
            for(String example : figureExamples){

                AnalysisOutcome o = new AnalysisOutcome();

                f = new AnalysisFragment(example, "", englishParser);
                System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

                new CompensationExtractorSE().classify( f, o, tree );
                assertNoDefinitions("Expecting no hit for \""+ example+"\"", o );


            }

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
