package training;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-22
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class trainTokenizer {

    public static void main(String[] par){

        /*

        try{

            TokenizerModel model;
            InputStream modelIn = new FileInputStream("en-token.bin");

            try {
              model = new TokenizerModel(modelIn);
            }
            catch (IOException e) {
              e.printStackTrace();
            }
            finally {
              if (modelIn != null) {
                try {
                  modelIn.close();
                }
                catch (IOException e) {
                }
              }
            }

            Charset charset = Charset.forName("UTF-8");
            ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream("en-sent.train"),
                charset);
            ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);

            try {
              model = TokenizerME.train("en", sampleStream, true, TrainingParameters.defaultParams());
            }
            finally {
              sampleStream.close();
            }

            OutputStream modelOut = null;
            try {
              modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
              model.serialize(modelOut);
            } finally {
              if (modelOut != null)
                 modelOut.close();
            }

        }catch(Exception e){

            e.printStackTrace();

        }

          */
    }

}
