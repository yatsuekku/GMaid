package Note;

import java.util.List;

/**
 * Created by Stefan Dedalus on 2017-05-06.
 */

//represents simple text notes - kind of a primitive type in Notes hierarchy.
public class PlainTextNote extends Note {

    private String type = "PlainTextNote";

    public PlainTextNote(){
        content = new String();
    }

    public PlainTextNote(String a){
        content = a;
    }

    private String content;

    public String toString(){
        return content;
    }

    public void setContent(String a){
        content = a;
    }

    @Override
    public List<Note> getSubnotes() {
        return null;
    }

}