import java.util.List;
/**
 * Created by Stefan Dedalus on 2017-05-06.
 */
//Core interface of whole project, almost everything is a note.
public interface Note {

    List<Note> getSubnotes(); //if note contains subnotes returnslist of them, else returns null

    //in progress
}
