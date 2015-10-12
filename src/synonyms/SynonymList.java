package synonyms;

import language.LanguageInterface;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-19
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class SynonymList {

    public Map<String, ArrayList<String>> synonyms = new HashMap<>();

    public SynonymList(LanguageInterface language, String path) {

        String filename = path + "/" + language.getModelPrefix() + "-synonym.txt";

        try {


            BufferedReader in = new BufferedReader(
           		   new InputStreamReader(
                                 new FileInputStream(filename), "ISO-8859-1"));
            String line;

            while ((line = in.readLine()) != null) {

               // process the line.

                String[] items = line.split(", ");
                String key = items[1];
                String synonym = items[2];

                ArrayList<String> existingEntries = synonyms.get(key);
                if(existingEntries == null)
                    existingEntries = new ArrayList<>();

                existingEntries.add(synonym);
                synonyms.put(key, existingEntries);


                //System.out.println("Line: " + line + " Adding synonym <" + key + "->" + synonym + ">");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }






    }

    public List<String> getSynonym(String word){

        List<String> list = synonyms.get(word);
        if(list != null && list.size() > 0)
            System.out.println("Found synonyms for " + word + list.toString());

        return synonyms.get(word);


    }

}
