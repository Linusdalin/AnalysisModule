package analysis2;

import classifiers.classifierTests.AnalysisTest;
import document.DocumentManager;
import log.AnalysisLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2015-01-13
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public class TestXML extends AnalysisTest {


    private static final String TestFileDirectory = "testFiles\\tempTest\\";

    public static void main(String[] arg){

        //String files[] = new String[] { "Anbudsförfrågan IT-drift 2014.docx", "Huvudavtal_EHM_IT_Drift.docx"};

        String path = "TextAnalysis" + "//" + TestFileDirectory;
        File directory = new File(path);
        List<String> files = listFilesForFolder(directory);

        System.out.println(files);


        String fullPath = "";
        InputStream is;

        AnalysisLogger.setLogLevel(AnalysisLogger.Level.DEBUG);

            Scanner input = new Scanner(System.in);

            for(String fileName : files){

                DocumentManager.FileType fileType = getFileTypeForDocument(fileName);

                if(fileType == DocumentManager.FileType.XML){

                    System.out.println("Parsing file " + fileName + "\n>");

                    String dontCare = input.nextLine();

                    fullPath = "TextAnalysis" + "//" + TestFileDirectory + fileName;

                    File f = new File(fullPath);

                    parseXML( f );

                }
                else{

                    System.out.println("Ignoring file " + fileName + " with unknown type\n>");
                    String dontCare = input.nextLine();

                }

            }

    }

    private static void parseXML(File file) {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Configure the factory object
            factory.setValidating(false);
            factory.setNamespaceAware(false);


                   DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                   Document doc = builder.parse(file);

                   DocumentTraversal traversal = (DocumentTraversal) doc;

                   NodeIterator iterator = traversal.createNodeIterator(
                     doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);

                   for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
                       //System.out.println("Element: " + ((Element) n).getTagName());
                       String tagname = ((Element) n).getTagName();

                       NamedNodeMap map = ((Element)n).getAttributes();

                       System.out.println("Tag: \"" + tagname + "\"=\"" + ((Element)n).getTextContent().trim() + "\"");


                       if(map.getLength() > 0) {

                           for(int i=0; i<map.getLength(); i++) {
                               Node node = map.item(i);
                               System.out.println("  Attr:\"" + node.getNodeName() + "\"=\"" + node.getNodeValue().trim() + "\"");
                           }
                       }
                   }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static DocumentManager.FileType getFileTypeForDocument(String fileName) {


        if(fileName.startsWith("~$"))
            return DocumentManager.FileType.UNKNOWN;   // This is a copy of an open file
        if(fileName.endsWith(".docx"))
            return DocumentManager.FileType.WORD;
        if(fileName.endsWith(".xlsx"))
            return DocumentManager.FileType.EXCEL;
        if(fileName.endsWith(".xml"))
            return DocumentManager.FileType.XML;

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


}
