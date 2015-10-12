package synonyms;

import language.English;
import language.LanguageCode;
import language.LanguageInterface;
import language.Swedish;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-19
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
public class SynonymManager {

    private static SynonymList synonymsSE = null;
    private static SynonymList synonymsEN = null;

    public SynonymManager(){

    }


    public SynonymList getSynonymList(LanguageInterface language, String models){

        System.out.println("Creating new synonym list for language " + language.getLanguageCode().code);

        if(language.getLanguageCode().code.equals(new Swedish().getLanguageCode().code)){

            if(synonymsSE == null){
                synonymsSE = new SynonymList(language, models);

                System.out.println("Creating new synonym list for language " + language.getLanguageCode().code + "("+ synonymsSE.synonyms.size()+")");
            }
            return synonymsSE;
        }

        if(language.getLanguageCode().code.equals(new English().getLanguageCode().code)){

            if(synonymsEN == null){
                synonymsEN = new SynonymList(language, models);

                System.out.println("Creating new synonym list for language " + language.getLanguageCode().code + "("+ synonymsEN.synonyms.size()+")");
            }
            return synonymsEN;
        }


        // Not supported synonym list

        System.out.println("Not supported synonyms for language " + language.getLanguageCode().code);
        return null;


    }
}
