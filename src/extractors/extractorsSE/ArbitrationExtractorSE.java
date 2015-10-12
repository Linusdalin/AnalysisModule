package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.ArbitrationExtractor;
import extractors.CompensationExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to payment and invoicing
 *
 *
 *
 */

public class ArbitrationExtractorSE extends ArbitrationExtractor implements FeatureExtractorInterface {

        /*

    	9 Tvistelösning, tillämplig lag	Annotations: 0
	9.1 Svensk lag ska äga tillämpning på detta avtal. 	Annotations: 0
	9.2 Tvist i anledning av detta avtal ska slutligt avgöras genom skiljedom enligt Skiljedomsregler för Stockholms Handelskammares Skiljedomsinstitut. Skiljeförfarandets säte ska vara Stockholm och skiljeförfarandet ska hållas på svenska. 	Annotations: 0
	9.3 Institutets Regler för Förenklat Skiljeförfarande ska gälla om inte Institutet med beaktande av målets svårighetsgrad, tvisteföremålets värde och övriga omständligheter bestämmer att Regler för Stockholms Handelskammares Skiljedomsinstitut ska tillämpas på förfarandet. I sistnämnda fall ska Institutet också bestämma om skiljenämnden ska bestå av en eller tre skiljemän.	Annotations: 0
	9.4 Skiljenämnden, Parterna, deras ombud samt andra som deltar i skiljeförfarandet ska iaktta sekretess avseende förfarandet och vad som förevarit där.	Annotations: 0
	9.5 Part äger, med undantag av det sagda, föra fullgörelsetalan vid allmän domstol, med Stockholms tingsrätt som första instans, om klar och förfallen fordran enligt Avtalet.

     */



    public ArbitrationExtractorSE(){

        super();
        patterns = new ArrayList<TextPattern>(){{


                add(new TextPattern(new String[] {"[T|t]vistelösning", "[S|s]kiljeförfarande", "[S|s]kiljedom", "[S|s]kiljenämnd", "[S|s]kiljedomsinstitut", }).anyOf().extractWordSpan());
                add(new TextPattern(new String[] {"tillämplig", "lag"}).close().extractWordSpan());
                add(new TextPattern(new String[] {"\\blag\\b", "(?:tillämpas|tillämpning)", "(?:avtal|kontrakt)"}).allPresent().extractWordSpan());
                add(new TextPattern(new String[] {"\\b[T|t]vist", "skiljedom"}).someOf().extractWordSpan());
                add(new TextPattern(new String[] {"\\b[P|p]art\\b", "(?:domstol|tingsrätt|hovrätt|kammarätt)"}).allPresent().extractWordSpan());




            }};

        name = "Tvist";
        searchKeywords = new String[] {"Domstol", "tvist"};


    }

}
