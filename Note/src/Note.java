import java.util.List;
/**
 * Created by Stefan Dedalus on 2017-05-06.
 */
//Core interface of whole project, almost everything is a note.
public abstract class Note {

    abstract List<Note> getSubnotes(); //if note contains subnotes returns list of them, else returns null
    private String name;
    String getName(){
        return name;
    }
    public void setName(String nw){
        name = nw;
    }
    //in progress
}
