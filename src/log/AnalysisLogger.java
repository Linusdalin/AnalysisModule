package log;


import analysis2.AnalysisException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 *          Simple log wrapper.
 *
 *          //TODO: Implement Log4J here
 *          //TODO: Implement log filter level
 *
 */
public class AnalysisLogger {

    public enum Level { DEBUG(10), INFO(20), WARNING(25), ACTION(30), MAJOR_EVENT(40), ERROR(40), FATAL(50);
        private int levelId;

        Level(int i) {

            levelId = i;
        }
        public int getOrdinal(){

            return levelId;
        }
    }
    private static Level filterLevel = Level.INFO;

    public static void log(Level level, String message){

        log(level, message, null);

    }

    private static void log(Level level, String message, String info){

        if(level.getOrdinal() >= filterLevel.getOrdinal()){

            System.out.println(level + ": " + message);
            if(info != null)
                System.out.println(info);

        }


    }


    public static void log(Exception e){

        String info = getMessage( e );

        log(Level.FATAL, "Exception Stacktrace:", info);


    }

    public static void log(Exception e, String message){

        String info = getMessage( e );

        log(Level.FATAL, message + "\nException Stacktrace:", info);


    }


    public static void log(AnalysisException e){

        String info = getMessage( e );

        log(Level.FATAL, e.narration +  "\nException Stacktrace:", info);


    }


    public static String getMessage(Exception e){

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        return writer.toString();


    }


    public static void setLogLevel(Level lvl){

        filterLevel = lvl;
    }

}
