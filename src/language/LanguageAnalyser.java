package language;

import analysis2.AnalysisException;
import log.AnalysisLogger;

import java.util.ArrayList;
import java.util.List;

/**
 *      The language analyser is responsible for testing all
 *      languages and detect the correct language from the document
 */

public class LanguageAnalyser {

    // List all the languages supported in the system

    private static List<LanguageInterface> supportedLanguages = null;

    // We can also detect but not support languages
    private static LanguageInterface[] notSupportedLanguages = { };

    /*******************************************************
     *
     *          Initiate the language lists
     *
     *
     */

    public LanguageAnalyser(){

        if(supportedLanguages == null){

            supportedLanguages = new ArrayList<>();
            supportedLanguages.add(new Swedish());
            supportedLanguages.add(new English());

        }
    }


    /***************************************************************'
     *
     *          Detect the document language.
     *
     *          //TODO: Improvement: Compare different languages and get the BEST fit, not the first
     *
     *
     * @param bodyText
     * @return
     * @throws AnalysisException
     */


    public LanguageInterface getLanguage(String bodyText) throws AnalysisException {

        for(LanguageInterface language : supportedLanguages){

            if(language.isLanguage(bodyText))
                return language;
        }

        for(LanguageInterface language : notSupportedLanguages){

            if(language.isLanguage(bodyText))
                throw new AnalysisException("Not supported language " + language.getLanguageCode());
        }

        throw new AnalysisException("Unknown Language");

    }

    public LanguageInterface getLanguage(LanguageCode languageCode){

        for(LanguageInterface language : supportedLanguages){

            if(language.getLanguageCode().code.equals(languageCode.code))
                return language;
        }

        for(LanguageInterface language : notSupportedLanguages){

            if(language.getLanguageCode().code.equals(languageCode.code))
                return language;
        }

        return null;

    }

    public LanguageInterface getLanguageByName(String languageCode) throws AnalysisException {

        for(LanguageInterface language : supportedLanguages){

            if(language.getLanguageCode().code.equals(languageCode))
                return language;
        }

        for(LanguageInterface language : notSupportedLanguages){

            if(language.getLanguageCode().code.equals(languageCode))
                return language;
        }

        AnalysisLogger.log(AnalysisLogger.Level.WARNING, " No language defined for document. Using default language");

        return new English();

    }


    public boolean isSupported(LanguageInterface language) {

        LanguageCode code = language.getLanguageCode();

        for(LanguageInterface aLanguage : supportedLanguages){

            if(code.code.equals(aLanguage.getLanguageCode().code))
                return true;
        }

        return false;

    }
}
