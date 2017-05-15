package FileLoader;
import com.google.gson.*;
import Note.PlainTextNote;
import java.util.ArrayList;

/**
 * Created by jacek on 2017-05-15.
 */
public class FileLoader {
    public static void main(String s[]){
        Gson a = new Gson();
        String myJson = a.toJson(new PlainTextNote("To jest przykladowa notatka"));
        System.out.println(myJson);
    }
}
