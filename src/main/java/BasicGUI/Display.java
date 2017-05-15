package main.java.BasicGUI;

import main.java.Note.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 14.05.2017.
 */
public class Display {
    ArrayList<Sprite> container;
    Note object;
    double potentialCanvasWidth;
    double potentialCanvasHeight;

    Display(){
        container = null;
        object = null;
    }

    Display (Note ln){
        object = ln;
        if(object.getClass() == ListNote.class){
            ListNote tmpNote = (ListNote)object;
            List<Note> tmpList = tmpNote.getSubnotes();
            container = new ArrayList<>();
            double height = 50;

            for(Note i : tmpList){
                Sprite tmpSprite = new Sprite(i);
                tmpSprite.setPosition(0,height);
                height += tmpSprite.getHeight();
                container.add(tmpSprite);
            }
            potentialCanvasHeight = height;
        }
        else if(object.getClass() == PlainTextNote.class){
            container = null;
            potentialCanvasHeight = 100;
        }
    }

    public void setNote(Note ln){
        System.out.println(ln);
        object = ln;
        if(object.getClass() == ListNote.class){
            ListNote tmpNote = (ListNote)object;
            List<Note> tmpList = tmpNote.getSubnotes();
            container = new ArrayList<>();
            double height = 50;

            for(Note i : tmpList){
                Sprite tmpSprite = new Sprite(i);
                tmpSprite.setPosition(0,height);
                height += tmpSprite.getHeight();
                container.add(tmpSprite);
            }
            potentialCanvasHeight = height;
        }
        else if(object.getClass() == PlainTextNote.class){
            container = null;
            potentialCanvasHeight = 100;
        }
    }

    public void render(Canvas can){
        can.setHeight(potentialCanvasHeight);
        GraphicsContext gc =can.getGraphicsContext2D();

        gc.setTextAlign(TextAlignment.LEFT);
        Font f = new Font(30);
        gc.setFont(f);
        gc.strokeRoundRect(0,0,100,50,20,20);
        gc.fillText("Back",20, 35);

        gc.fillText(object.getName(),120, 35);

        if(object.getClass() == ListNote.class){
            for(Sprite i : container){
                i.render(gc);
                //System.out.println(i);
            }
        }
        else if(object.getClass() == PlainTextNote.class){
            PlainTextNote tmp = (PlainTextNote) object;
            gc.setFill(Paint.valueOf("red"));
            gc.fillText(tmp.toString(), 20,85);
            gc.setFill(Paint.valueOf("lime"));
        }

    }

    public Note checkCollision(double x, double y) {
        if((x >= 0 && 100 >= x) && (y >= 0 && 50 >= y))
        {
            return object;
        }
        if(object.getClass() == ListNote.class){
            for(Sprite i : container){
                if(i.isInside(x,y))
                    return i.getNote();
                //System.out.println(i);
            }
        }
        return null;
    }
}
