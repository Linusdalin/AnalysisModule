package test.oldTests;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-08-05
 * Time: 12:43
 * To change this template use File | Settings | File Templates.
 */
public class RegExpTest {


    @Test
    public void manual(){

        String regExp = "[enligt|framgår av|i enlighet med] [stycke |kapitel |paragraf ]*(Introduktion)[\\,| i]* Avtalet";
        String text = "som framgår av kapitel Introduktion, Avtalet";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find())
        {
            System.out.println("matched text " + text + " against " + regExp);
            System.out.println("Extracting " + matcher.group(1));

        }


    }
}
