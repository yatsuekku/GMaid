package FileLoader;
import Note.ListNote;
import com.google.gson.*;
import Note.PlainTextNote;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jacek on 2017-05-15.
 */
public class FileLoader {

    //TODO -create custom parser declarations for Note types.

    private static ListNote ParseListNote(JsonObject in){
        ArrayList<Note.Note> temp = new ArrayList<>();
        for(JsonElement i : (JsonArray)in.get("content")){
            if(((JsonObject)i).get("type").getAsString().equals("ListNote")){
                temp.add(0,ParseListNote((JsonObject)i));
            }else if(((JsonObject)i).get("type").getAsString().equals("PlainTextNote")){
                temp.add(0,ParsePlainTextNote((JsonObject)i));
            }
        }
        ListNote ret = new ListNote(temp);
        ret.setName(in.get("name").getAsString());
        return ret;
    }
    private static PlainTextNote ParsePlainTextNote(JsonObject in){
        PlainTextNote ret = new PlainTextNote(in.get("content").getAsString());
        ret.setName(in.get("name").getAsString());
        return ret;
    }

    static public ListNote loadNote(String path){
        File file = new File(path);
        try {
            FileInputStream FIS = new FileInputStream(file);
            InputStreamReader ISR = new InputStreamReader(FIS);
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject temp = (JsonObject)parser.parse(ISR);

            return ParseListNote(temp);
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.toString() + " not found!");
            return null;
        }
    }
    static public void saveNote(String path,ListNote data){
        File file = new File(path);
        try {
            FileOutputStream FOS = new FileOutputStream(file);
            OutputStreamWriter OSW = new OutputStreamWriter(FOS);
            Gson gson = new Gson();
            String myJson = gson.toJson(data);
            System.out.println(myJson);
            OSW.write(myJson);
            OSW.close();
        } catch (FileNotFoundException e) {
            System.out.println("Something's broken ; - ;");
        } catch (IOException e) {
            System.out.print("Could not write properly!");
        }
    }
    public static void main(String s[]){
        ListNote tmp = new ListNote();
        tmp.setName("Root");

        ListNote world = new ListNote();
        world.setName("World");
        PlainTextNote w1 = new PlainTextNote("Note 1");
        w1.setName("World Sub1");
        world.append(w1);
        PlainTextNote w2 = new PlainTextNote("Note 2");
        w2.setName("World Sub2");
        world.append(w2);
        PlainTextNote w3 = new PlainTextNote("Note 3");
        w3.setName("World Sub3");
        world.append(w3);
        PlainTextNote w4 = new PlainTextNote("Note 4");
        w4.setName("World Sub4");
        world.append(w4);
        tmp.append(world);

        ListNote players = new ListNote();
        players.setName("Players");
        PlainTextNote p1 = new PlainTextNote("Annie");
        p1.setName("Player Annie");
        players.append(p1);
        PlainTextNote p2 = new PlainTextNote("Celine");
        p2.setName("Player Celine");
        players.append(p2);
        tmp.append(players);

        PlainTextNote t1 = new PlainTextNote("Text 1");
        t1.setName("Random Sub1");
        tmp.append(t1);
        PlainTextNote t2 = new PlainTextNote("Text 2");
        t2.setName("Random Sub2");
        tmp.append(t2);

        //saveNote("note_example.note",tmp);
        //System.out.println(loadNote("note_example.note"));
    }
}
