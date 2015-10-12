package parse;

import java.io.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

/****************************************************************************
 *
 *          This example reads an old .doc word format and splits it in paragraphs
 *
 */


public class DocReader {

    public static void main(String[] args) {

        File file = null;
        WordExtractor extractor = null ;

        try {

           file = new File("c:\\Users\\Linus\\Documents\\test.doc");
           FileInputStream fis=new FileInputStream(file.getAbsolutePath());
           HWPFDocument document=new HWPFDocument(fis);
           extractor = new WordExtractor(document);
           String [] fileData = extractor.getParagraphText();
           for(int i=0;i<fileData.length;i++){
             if(fileData[i] != null)
               System.out.println(fileData[i]);
           }
        }
        catch(Exception exep){

            exep.printStackTrace();
        }

    }
}