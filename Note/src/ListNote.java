import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Dedalus on 2017-05-06.
 */
public class ListNote implements Note {
    private ArrayList<Note> content;

    @Override
    public List<Note> getSubnotes() {
        return content;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(Note i : content){
            ret.append('>');
            ret.append(i.toString());
        }
        return ret.toString();
    }
}
