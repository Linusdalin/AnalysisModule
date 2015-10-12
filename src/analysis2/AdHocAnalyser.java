package analysis2;

import classifiers.classifierTests.AnalysisTest;
import document.AbstractFragment;
import document.AbstractStructureItem;
import document.DocumentManager;
import language.LanguageCode;
import log.AnalysisLogger;
import system.Analyser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *          Analysing all documents in the TestFileDirectory.
 *          StandAlone execution for manual inspection
 *
 */

public class AdHocAnalyser extends AnalysisTest {


    private static final String TestFileDirectory = "testFiles\\tempTest\\";

    public static void main(String[] arg){

        //String files[] = new String[] { "Anbudsförfrågan IT-drift 2014.docx", "Huvudavtal_EHM_IT_Drift.docx"};

        String path = "TextAnalysis" + "//" + TestFileDirectory;
        File directory = new File(path);
        List<String> files = listFilesForFolder(directory);

        System.out.println(files);


        String fullPath = "";
        InputStream is;
        DocumentManager manager;

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);

        try {

            Scanner input = new Scanner(System.in);

            for(String fileName : files){

                DocumentManager.FileType fileType = getFileTypeForDocument(fileName);

                if(fileType != DocumentManager.FileType.UNKNOWN){

                    System.out.println("Parsing file " + fileName + "\n>");

                    //String dontCare = input.nextLine();

                    fullPath = "TextAnalysis" + "//" + TestFileDirectory + fileName;

                    is = new FileInputStream(fullPath);
                    manager = new DocumentManager(fileName, is);


                    LanguageCode languageCode = Analyser.detectLanguage(fileName, manager.getBody());
                    System.out.println("Language: " + languageCode.code);

                    assertParsingOutcome(fileName, manager, fileType);
                }
                else{

                    System.out.println("Ignoring file " + fileName + " with unknown type\n>");
                    //String dontCare = input.nextLine();

                }

            }


            //fileName = "Encl 2 - Delivery agreement concept.docx";
            //fileName = "Encl 3 - Price form.xlsx";
            //fileName = "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx";






        } catch (FileNotFoundException e) {

            assertThat("Document " + fullPath + " should exist in " + System.getProperty("user.dir") + "\\" + TestFileDirectory, false, is(true));

        } catch (AnalysisException e) {

            e.printStackTrace();
            assertTrue(false);
        }
    }

    private static DocumentManager.FileType getFileTypeForDocument(String fileName) {


        if(fileName.startsWith("~$"))
            return DocumentManager.FileType.UNKNOWN;   // This is a copy of an open file
        if(fileName.endsWith(".docx"))
            return DocumentManager.FileType.WORD;
        if(fileName.endsWith(".xlsx"))
            return DocumentManager.FileType.EXCEL;

        return DocumentManager.FileType.UNKNOWN;

    }


    private static List<String> listFilesForFolder(File folder) {

        List<String> files = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory()) {
                files.addAll(listFilesForFolder(fileEntry));
            } else {
                files.add(fileEntry.getName());
            }
        }

        return files;
    }

    private static void assertParsingOutcome(String filename, DocumentManager manager, DocumentManager.FileType expectedFileType) {

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



            assertVerbose("File type for " + filename, fileType, is(expectedFileType));

            // Check the references to structure items.

            for(AbstractFragment aFragment : manager.getFragments()){

                assertNotNull("There must be a structure reference", aFragment.getStructureItem());
                assertNotNull("There must be a body", aFragment.getBody());
                assertNotNull("There must be a type", aFragment.getStyle());

            }

        System.out.println("Number of clauses " + noClauses);
        System.out.println("Number of fragments " + noFragments);
        System.out.println("Number of comments " + noComments);



    }



}
