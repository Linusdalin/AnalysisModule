package document;

import language.LanguageCode;
import language.LanguageInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-06
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
public class AbstractDocument {

    public String name;
    public List<AbstractStructureItem> chapters;
    private List<AbstractDefinition> definitions;
    public AbstractProject project;
    public LanguageCode documentLanguage;

    public AbstractDocument(String name, List<AbstractStructureItem>chapters, List<AbstractDefinition>definitions, AbstractProject project, LanguageCode language){

        this.name = name;
        this.chapters = chapters;
        this.definitions = definitions;
        this.project = project;
        this.documentLanguage = language;
    }

    public void addDefinition(AbstractDefinition definition){

        if(definitions == null)
            definitions = new ArrayList<AbstractDefinition>();

        definitions.add(definition);
    }

    public List<AbstractDefinition> getDefinitions(){

        return definitions;
    }

}
