package analysis2;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-12-04
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class AnalysisException extends Exception {

    public String narration;
    public String document;

    public AnalysisException(String message){

        this.narration = message;
    }

    public AnalysisException inDocument(String document){

        this.document = document;
        return this;
    }

    public void addInformation(String additionalInfo){

        narration += "\n" + additionalInfo;
    }

}
