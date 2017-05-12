import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Stefan Dedalus on 2017-05-06.
 */
public class ListNote extends Note {
    private ArrayList<Note> content;

    public ListNote(Collection<Note> a){
        content = new ArrayList<>(a);
    }
    public ListNote(){
        content = new ArrayList<>();
    }
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

    public void append(Note a){
        content.add(a);
    }
}
