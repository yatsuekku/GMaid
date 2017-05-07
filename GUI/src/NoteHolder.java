import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;

/**
 * Created by Matt on 06.05.2017.
 */
public class NoteHolder<T> {
    ArrayList<NoteHolder> list;
    String name;
    T value;
    NoteHolder prev;

    NoteHolder(T newVal, String newName){
        value = newVal;
        name = newName;
        list = new ArrayList<>();
        prev = null;
    }

    NoteHolder(T newVal,String newName, NoteHolder newPrev){
        value=newVal;
        name = newName;
        list = new ArrayList<>();
        prev = newPrev;
    }

    NoteHolder goBack(){
        return prev;
    }

    String getName(){
        return name;
    }

    ArrayList<NoteHolder> getList(){
        return list;
    }
    T getValue(){
        return value;
    }

    void setName(String newName){
        name=newName;
    }
    void setValue(T newVal){
        value = newVal;
    }

    void add(T newObj, String newName){
        NoteHolder temp = new NoteHolder(newObj,newName,this);
        list.add(temp);
    }

    @Override
    public String toString() {
        return name;
    }
}
