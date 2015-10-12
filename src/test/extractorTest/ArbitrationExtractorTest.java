package test.extractorTest;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsSE.ArbitrationExtractorSE;
import extractors.extractorsSE.LegalEntityExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import parse.AnalysisFragment;
import test.oldTests.FeatureTestCommon;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class ArbitrationExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    @Test
    public void basicTest(){

        new ExtractorTesterOld("Tvistelösning, tillämplig lag")
                .withParser(swedishParser)
                .withExtractor(new ArbitrationExtractorSE())
                .expectingType(FeatureTypeTree.ARBITRATION)
                .expectingPattern("tillämplig lag")
                .expectingTag("")
                .test(1, 3);


        new ExtractorTesterOld("Svensk lag ska äga tillämpning på detta avtal")
                .withParser(swedishParser)
                .withExtractor(new ArbitrationExtractorSE())
                .expectingType(FeatureTypeTree.ARBITRATION)
                .expectingPattern("lag ska äga tillämpning på detta avtal")
                .expectingTag("")
                .test(0, 1);


        new ExtractorTesterOld("Tvist i anledning av detta avtal ska slutligt avgöras genom skiljedom enligt Skiljedomsregler för Stockholms Handelskammares Skiljedomsinstitut. Skiljeförfarandets säte ska vara Stockholm och skiljeförfarandet ska hållas på svenska.")
                .withParser(swedishParser)
                .withExtractor(new ArbitrationExtractorSE())
                .expectingType(FeatureTypeTree.ARBITRATION)
                .expectingPattern("Tvist i anledning av detta avtal ska slutligt avgöras genom skiljedom")
                .expectingTag("")
                .test(1, 2);


        new ExtractorTesterOld("Institutets Regler för Förenklat Skiljeförfarande ska gälla om inte Institutet med beaktande av målets svårighetsgrad, tvisteföremålets värde och övriga omständligheter bestämmer att Regler för Stockholms Handelskammares Skiljedomsinstitut ska tillämpas på förfarandet. I sistnämnda fall ska Institutet också bestämma om skiljenämnden ska bestå av en eller tre skiljemän")
                .withParser(swedishParser)
                .withExtractor(new ArbitrationExtractorSE())
                .expectingType(FeatureTypeTree.ARBITRATION)
                .expectingPattern("")
                .expectingTag("")
                .test(1, 2);


        new ExtractorTesterOld("Skiljenämnden, Parterna, deras ombud samt andra som deltar i skiljeförfarandet ska iaktta sekretess avseende förfarandet och vad som förevarit där.")
                .withParser(swedishParser)
                .withExtractor(new ArbitrationExtractorSE())
                .expectingType(FeatureTypeTree.ARBITRATION)
                .expectingPattern("")
                .expectingTag("")
                .test(0, 1);


        new ExtractorTesterOld("Part äger, med undantag av det sagda, föra fullgörelsetalan vid allmän domstol, med Stockholms tingsrätt som första instans, om klar och förfallen fordran enligt Avtalet")
                .withParser(swedishParser)
                .withExtractor(new ArbitrationExtractorSE())
                .expectingType(FeatureTypeTree.ARBITRATION)
                .expectingPattern("Part äger , med undantag av det sagda , föra fullgörelsetalan vid allmän domstol")
                .expectingTag("")
                .test(0, 1);




    }
    @Test
    public void failTest(){

        AnalysisFragment f;
        FeatureDefinition d;

        String[] figureExamples = {

                "Det kan uppstå tvister som skall lösas",
                "Till detta Avropsavtal hör följande bilagor, vilka ska anses utgöra en integrerad del av Avropsavtalet",
        };

        for(String example : figureExamples){


            AnalysisOutcome o = new AnalysisOutcome();
            f = new AnalysisFragment(example, "", swedishParser);
            System.out.println("Fragment: \"" + f.getText() + "\"\n" + f.display());

            new LegalEntityExtractorSE().classify( f, o, tree );
            assertNoDefinitions("Expecting no hit for \""+ example+"\"", o );


        }
    }

}
