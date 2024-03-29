package parse;

import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hdgf.extractor.VisioTextExtractor;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class DocXReader {

    public static void main(String[] args) {

        /*

        try{

            FileInputStream fis = new FileInputStream("c:\\Users\\Linus\\Documents\\test.doc");
            POIFSFileSystem fileSystem = new POIFSFileSystem(fis);
            // Firstly, get an extractor for the Workbook
            POIOLE2TextExtractor oleTextExtractor =
               ExtractorFactory.createExtractor(fileSystem);
            // Then a List of extractors for any embedded Excel, Word, PowerPoint
            // or Visio objects embedded into it.
            POITextExtractor[] embeddedExtractors =
               ExtractorFactory.getEmbededDocsTextExtractors(oleTextExtractor);
            for (POITextExtractor textExtractor : embeddedExtractors) {
               // If the embedded object was an Excel spreadsheet.
               if (textExtractor instanceof ExcelExtractor) {
                  ExcelExtractor excelExtractor = (ExcelExtractor) textExtractor;
                  System.out.println(excelExtractor.getText());
               }
               // A Word Document
               else if (textExtractor instanceof WordExtractor) {
                  WordExtractor wordExtractor = (WordExtractor) textExtractor;
                  String[] paragraphText = wordExtractor.getParagraphText();
                  for (String paragraph : paragraphText) {
                     System.out.println(paragraph);
                  }
                  // Display the document's header and footer text
                  System.out.println("Footer text: " + wordExtractor.getFooterText());
                  System.out.println("Header text: " + wordExtractor.getHeaderText());
               }
               // PowerPoint Presentation.
               else if (textExtractor instanceof PowerPointExtractor) {
                  PowerPointExtractor powerPointExtractor =
                     (PowerPointExtractor) textExtractor;
                  System.out.println("Text: " + powerPointExtractor.getText());
                  System.out.println("Notes: " + powerPointExtractor.getNotes());
               }
               // Visio Drawing
               else if (textExtractor instanceof VisioTextExtractor) {
                  VisioTextExtractor visioTextExtractor =
                     (VisioTextExtractor) textExtractor;
                  System.out.println("Text: " + visioTextExtractor.getText());
               }
            }
        }catch(Exception e){

            e.printStackTrace();
        }
               */

    }//end of main()
}//end of class