package openNLP;

import language.LanguageAnalyser;
import language.LanguageInterface;
import opennlp.tools.chunker.Chunker;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;
import stemming.StemmerInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/****************************************************************************
 *
 *      NL Parser
 *
 *

 1. 	CC 	Coordinating conjunction
 2. 	CD 	Cardinal number
 3. 	DT 	Determiner
 4. 	EX 	Existential there
 5. 	FW 	Foreign word
 6. 	IN 	Preposition or subordinating conjunction
 7. 	JJ 	Adjective
 8. 	JJR 	Adjective, comparative
 9. 	JJS 	Adjective, superlative
 10. 	LS 	List item marker
 11. 	MD 	Modal
 12. 	NN 	Noun, singular or mass
 13. 	NNS 	Noun, plural
 14. 	NNP 	Proper noun, singular
 15. 	NNPS 	Proper noun, plural
 16. 	PDT 	Predeterminer
 17. 	POS 	Possessive ending
 18. 	PRP 	Personal pronoun
 19. 	PRP$ 	Possessive pronoun
 20. 	RB 	Adverb
 21. 	RBR 	Adverb, comparative
 22. 	RBS 	Adverb, superlative
 23. 	RP 	Particle
 24. 	SYM 	Symbol
 25. 	TO 	to
 26. 	UH 	Interjection
 27. 	VB 	Verb, base form
 28. 	VBD 	Verb, past tense
 29. 	VBG 	Verb, gerund or present participle
 30. 	VBN 	Verb, past participle
 31. 	VBP 	Verb, non-3rd person singular present
 32. 	VBZ 	Verb, 3rd person singular present
 33. 	WDT 	Wh-determiner
 34. 	WP 	Wh-pronoun
 35. 	WP$ 	Possessive wh-pronoun
 36. 	WRB 	Wh-adverb


        //TODO: Add check language so that it exists
 */

public class NLParser {


    private static String modelDirectory;
    private final LanguageInterface language;
    private Tokenizer tokenizer = null;
    private POSTaggerME tagger = null;

    /****************************************************************
     *
     *          Create a new parser given the language
     *
     * @param language
     * @param modelDirectory
     */

    public NLParser(LanguageInterface language, String modelDirectory){

        this.language = language;
        this.modelDirectory = modelDirectory;
        InputStream modelIn = null;

        // Create the models

        try {

            /*
            modelIn = new FileInputStream(modelDirectory + "/"+ language.getModelPrefix()+"-sent.bin");
            SentenceModel sentenceModel = new SentenceModel(modelIn);
            sentenceDetector = new SentenceDetectorME(sentenceModel);

            */

            //TODO: Check which tokenizer to use

            // Simple tokenizar detects 100kr but is deprecated. Maybe we need training for the tokenizer model

            //modelIn = new FileInputStream(modelDirectory + "/"+ language.getModelPrefix()+"-token.bin");
            //TokenizerModel tokenizerModel = new TokenizerModel(modelIn);
            //tokenizer = new TokenizerME(tokenizerModel);

            //tokenizer = new SimpleTokenizer();

            modelIn = new FileInputStream(modelDirectory + "/"+ language.getModelPrefix()+"-pos-maxent.bin");
            POSModel posModel = new POSModel(modelIn);
            tagger = new POSTaggerME(posModel);


        }
        catch (IOException e) {
          //handle the exception

            e.printStackTrace();
        }
        finally {
          if (null != modelIn) {
            try {
              modelIn.close();
            }
            catch (IOException e) {

                e.printStackTrace();
            }
          }
        }



    }

    public void initTokenizer(){


        InputStream modelIn = null;

        try{

            modelIn = new FileInputStream(modelDirectory + "/"+ language.getModelPrefix()+"-token.bin");
            TokenizerModel tokenizerModel = new TokenizerModel(modelIn);
            tokenizer = new TokenizerME(tokenizerModel);

        }catch (IOException e) {
              //handle the exception

                e.printStackTrace();
            }
            finally {
              if (null != modelIn) {
                try {
                  modelIn.close();
                }
                catch (IOException e) {

                    e.printStackTrace();
                }
              }
            }

    }


    /******************************************************************
     *
     *              Tokeize a text in english
     *
     * @param text - a complete text, sentence etc
     * @return list of tokens
     *
     *
     *      //TODO: Add exception handling if the tokenizer is null
     */


    public String[] tokenize(String text){


        return tokenizer.tokenize(text);


    }

    public Span[] getSpan(String text){

        return tokenizer.tokenizePos(text);
    }


    /****************************************************************
     *
     *      Returns the POS analysis names for the tokens
     *
     * @param text
     * @return
     */


    public String[] posAnalysis(String text){

        return posAnalysis(tokenize(text));

    }

    public String[] posAnalysis(String[] tokens){

        testSupportedLanguages(language);
        String[] posTags = tagger.tag(tokens);

        language.enhancePOS(tokens, posTags);
        return posTags;

    }

    private void testSupportedLanguages(LanguageInterface language) {

        LanguageAnalyser analyser = new LanguageAnalyser();

        if(!analyser.isSupported(language))
            throw new RuntimeException("Language " + language.getLanguageCode().code + " is not supported");
    }


    /****************************************************************************''
     *
     *  Tag 	Description 	            Words 	            Example
         NP 	noun phrase  	            DT+RB+JJ+NN + PR 	the strange bird
         PP 	prepositional phrase 	    TO+IN 	            in between
         VP  	verb phrase  	            RB+MD+VB  	        was looking
         ADVP 	adverb phrase 	            RB 	                also
         ADJP 	adjective phrase  	        CC+RB+JJ 	        warm and cosy
         SBAR 	subordinating conjunction  	IN 	                whether or not
         PRT 	particle 	                RP 	                up the stairs
         INTJ 	interjection 	            UH 	                hello
             0

         The IOB prefix marks whether a word is inside or outside of a chunk.

         Tag 	Description
         I- 	inside the chunk
         B- 	inside the chunk, preceding word is part of a different chunk
         O 	    not part of a chunk
         *
     *
     *
     *
     *
     * @param tokens
     * @return
     */


    public String[] chunk(String[] tokens){

        InputStream modelIn = null;
        ChunkerModel model;

        String[] pos = posAnalysis(tokens);

        try {

            modelIn = new FileInputStream(modelDirectory + "/" + language.getModelPrefix() + "-chunker.bin");
            model = new ChunkerModel(modelIn);

            Chunker chunker = new ChunkerME(model);
            String output[] = chunker.chunk(tokens, pos);

            return output;
        } catch (FileNotFoundException e) {


            System.out.println("Warning. No chunk analysis file found for language "+ language.getModelPrefix()+". Working Directory = " +
                          System.getProperty("user.dir"));

            return new String[0];

        } catch (InvalidFormatException e) {
            //handle the exception

            e.printStackTrace();
        } catch (IOException e) {
            //handle the exception

            e.printStackTrace();
        } finally {
            if (null != modelIn) {
                try {
                    modelIn.close();
                } catch (IOException e) {
                }
            }
        }

        return null;

    }


    public LanguageInterface getLanguage() {

        return language;
    }


    public String[] wordStem(String[] wordList) {

        if(language == null)
            throw new RuntimeException("No language defined");

        StemmerInterface stemmer = language.getStemmer();

        String[] stemmedList = new String[wordList.length];

        for(int i = 0; i < wordList.length; i++){

            stemmer.setCurrent(wordList[i]);
            stemmer.stem();
            stemmedList[i] = stemmer.getCurrent();
        }

        return stemmedList;
    }


}
