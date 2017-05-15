package main.java.BasicGUI;

import main.java.Note.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Created by Matt on 14.05.2017.
 */
public class Sprite {
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    Note object;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        width = 300;
        height = 50;
        object = null;
    }

    public Sprite(Note n)
    {
        positionX = 0;
        positionY = 0;
        width = 300;
        height = 50;
        object = n;
    }

    public Note getNote(){
        return object;
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }
    public void setSize (double x, double y)
    {
        width = x;
        height = y;
    }

    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }

    public void render(GraphicsContext gc)
    {
        gc.setFill(Paint.valueOf("lime"));
        //gc.fillRoundRect(positionX,positionY,width,height,20,20);
        if(object.getClass() == ListNote.class){
            gc.setStroke(Paint.valueOf("red"));
        }
        else if(object.getClass() == PlainTextNote.class){
            gc.setStroke(Paint.valueOf("blue"));
        }
        else if(object.getClass() == NoteGraph.class){
            gc.setStroke(Paint.valueOf("orange"));
        }
        gc.strokeRoundRect(positionX+1,positionY+1,width-2,height-2,20,20);
        gc.setStroke(Paint.valueOf("black"));
        if(object != null){
            gc.fillText(object.getName(),positionX + 20, positionY + 35, 260);
        }
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }
    public boolean isInside(double x, double y){
        if((x >= positionX && positionX + width >= x) && (y >= positionY && positionY + height >= y))
            return true;
        return false;
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]";
    }
}
