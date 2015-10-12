package document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-05
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class AbstractProject {

    public List<AbstractDocument> documents = new ArrayList<>();

    public AbstractProject(){

    }

    public AbstractProject addDocument(AbstractDocument doc){

        documents.add(doc);
        return this;
    }

}
