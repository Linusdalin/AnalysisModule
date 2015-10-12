package test.oldTests;

import parse.TestClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-03-10
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class UnitTestExample {


    @Test
    public void tc1(){


        int res = new TestClass().add( 10 );
        assertThat(res, is( 11 ));
    }
}
