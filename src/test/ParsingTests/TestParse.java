package test.ParsingTests;

import analysis2.AnalysisException;
import classifiers.classifierTests.AnalysisTest;
import document.AbstractFragment;
import document.AbstractStructureItem;
import document.DocumentManager;
import language.LanguageCode;
import log.AnalysisLogger;
import org.junit.BeforeClass;
import org.junit.Test;
import system.Analyser;

import java.io.*;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 *          Simple test runs
 *
 */

public class TestParse extends AnalysisTest {


    private static final String TestFileDirectory = "testFiles";


    @BeforeClass
    public static void preAmble(){

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);
    }



    @Test
    public void parseTestDocument(){

        String fileName = "Test document.docx";

        try {

            String fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;

            InputStream is = new FileInputStream(fullPath);
            AnalysisLogger.log(AnalysisLogger.Level.INFO, "Splitting doc in abstract document component");
            DocumentManager manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("EN"), DocumentManager.FileType.WORD, 25, 93, 1);

            //fragmentDocument(document.getFile(), version, docXManager);

        } catch (FileNotFoundException e) {

            assertThat("Document " + fileName + " should exist in " + System.getProperty("user.dir") + "\\" + TestFileDirectory, false, is(true));

        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }
    }


    /***************************************************************************************************''
     *
     *          Check the outcomes from the parsing process
     *
     * @param filename - the name, only for display purposes
     * @param manager  - the document manager, holding the result of the parsing
     * @param expectedLanguage - the expected language of the document
     * @param expectedFileType - the expected type of the document
     * @param expectedClauses
     * @param expectedFragments
     * @param expectedComments
     */


    //TODO: Refactor: Remove structure item from this

    private void assertParsingOutcome(String filename, DocumentManager manager, LanguageCode expectedLanguage, DocumentManager.FileType expectedFileType, int expectedClauses, int expectedFragments, int expectedComments) {

        try{
            int noClauses    = manager.getStructureItems().size();
            int noFragments  = manager.getFragments().size();
            int noComments   = manager.getComments().size();
            DocumentManager.FileType fileType = manager.getFileType();

            int i = 0;
            for(AbstractFragment f : manager.getFragments()){

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "* Fragment: " + f.getBody());

                AbstractStructureItem structureItem = f.getStructureItem();

                assertNotNullVerbose("There is a fragment", f);
                assertNotNullVerbose("There is a type", f.getStyle());
                assertNotNullVerbose("Fragment should have structure Item", structureItem);

                System.out.println("fragment " + i++ + ": ("+ f.getIndentation() + " " + f.getStyle().name()+")" + f.getBody() +"\n      (" + structureItem.getIndentation() + ": " +structureItem.getStructureType().name() + ":" +
                        (structureItem.getTopElement() != null ? structureItem.getTopElement().getBody() : "--") +")" );
            }



            if(expectedClauses >= 0)
                assertVerbose("Number of structure items in document " + filename, noClauses, is(expectedClauses));
            if(expectedFragments >= 0)
                assertVerbose("Number of fragments in document " + filename, noFragments, is(expectedFragments));
            if(expectedComments >= 0)
                assertVerbose("Number of comments in document " + filename, noComments, is(expectedComments));

            LanguageCode languageCode = Analyser.detectLanguage(filename, manager.getBody());
            System.out.println("Language: " + languageCode.code);
            assertVerbose("Language for document " + filename, languageCode.code, is(expectedLanguage.code));

            assertVerbose("File type for " + filename, fileType, is(expectedFileType));

            // Check the references to structure items.

            for(AbstractFragment aFragment : manager.getFragments()){

                assertNotNull("There must be a structure reference", aFragment.getStructureItem());
                assertNotNull("There must be a body", aFragment.getBody());
                assertNotNull("There must be a type", aFragment.getStyle());

            }



        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }

    }



    @Test
    public void parseExampleDocuments(){

        String fileName = null;
        String fullPath;
        InputStream is;
        DocumentManager manager;
        try {

            fileName = "Bilaga 2, Pris-Utvarderingsmall.docx";
            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;

            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("SV"), DocumentManager.FileType.WORD, 55, 207, 0);



            fileName = "Bilaga 1a SLL.docx";
            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;

            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("SV"), DocumentManager.FileType.WORD, 6, 80, 0);

            fileName = "Forfragningsunderlag for Interaktiva utbildningar.docx";
            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;


            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("SV"), DocumentManager.FileType.WORD, 171, 479, 0);


            fileName = "Encl3.xlsx";
            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;

            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("EN"), DocumentManager.FileType.EXCEL, 1, 312, 0);





        } catch (FileNotFoundException e) {

            assertThat("Document " + fileName + " should exist in " + System.getProperty("user.dir") + "\\" + TestFileDirectory, false, is(true));

        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }
    }



    @Test
    public void parseAdHoc(){

        String fileName = null;
        String fullPath;
        InputStream is;
        DocumentManager manager;

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);

        try {



            //fileName = "Encl 2 - Delivery agreement concept.docx";
            //fileName = "Encl 3 - Price form.xlsx";
            //fileName = "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx";

            fileName = "Anbudsförfrågan IT-drift 2014.docx";

            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//adHoc//" + fileName;

            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("SV"), DocumentManager.FileType.WORD, -1, -1, -1);




        } catch (FileNotFoundException e) {

            assertThat("Document " + fileName + " should exist in " + System.getProperty("user.dir") + "\\" + TestFileDirectory, false, is(true));

        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }
    }


    @Test
    public void parseExcel(){

        String fileName = null;
        String fullPath;
        InputStream is;
        DocumentManager manager;
        try {

            fileName = "Excel Test.xlsx";
            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;

            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("EN"), DocumentManager.FileType.EXCEL, 2, 22, 0);


        } catch (FileNotFoundException e) {

            assertThat("Document " + fileName + " should exist in " + System.getProperty("user.dir") + "\\" + TestFileDirectory, false, is(true));

        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }
    }


    @Test
    public void parseTable(){

        String fileName = null;
        String fullPath;
        InputStream is;
        DocumentManager manager;
        try {

            fileName = "long tables.docx";
            fullPath = "TextAnalysis" + "//" + TestFileDirectory + "//" + fileName;

            is = new FileInputStream(fullPath);
            manager = new DocumentManager(fileName, is);

            assertParsingOutcome(fileName, manager, new LanguageCode("SV"), DocumentManager.FileType.WORD, 12, 45, 0);


        } catch (FileNotFoundException e) {

            assertThat("Document " + fileName + " should exist in " + System.getProperty("user.dir") + "\\" + TestFileDirectory, false, is(true));

        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }
    }





}
