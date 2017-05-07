import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Stefan Dedalus on 2017-05-07.
 */
public class NoteGraph implements Note {

    private TreeMap<Pair<Note,Note>,ListNote> content; //Pair(a,b) in map represents edge a~b. Pair(a,a) represents vertex a;

    public NoteGraph(){
        content = new TreeMap<>();
    }

    @Override

    public List<Note> getSubnotes() {
        return null;
    }

    public void insertEdge(Note a,Note b,Note c){
        if(a == b) throw  new RuntimeException();
        else {
            ListNote temp = content.get(new Pair<>(a, b));
            if(temp == null){
                ListNote toInsert = new ListNote();
                toInsert.append(c);
                content.put(new Pair<>(a, b),toInsert);
            }else{
                temp.append(c);
                content.put(new Pair<>(a, b),temp);
            }
        }
    }

    public void insertVertex(Note a,Note c){
        ListNote temp = content.get(new Pair<>(a, a));
        if(temp == null){
            ListNote toInsert = new ListNote();
            toInsert.append(c);
            content.put(new Pair<>(a, a),toInsert);
        }else{
            temp.append(c);
            content.put(new Pair<>(a, a),temp);
        }
    }
    public ListNote eraseVertex(Note a){
        ListNote ret = new ListNote();
        for(Map.Entry<Pair<Note, Note>, ListNote> i : content.entrySet()){
            if( (i.getKey().getKey() == a) || (i.getKey().getValue() == a) ){
                ret.append(i.getValue());
                content.remove(i.getKey());
            }
        }
        return ret;
    }


}
