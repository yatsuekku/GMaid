package BasicGUI;

import FileLoader.FileLoader;
import Note.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import BasicGUI.Display;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import Note.Note;

/**
 * Created by Matt on 12.05.2017.
 */
public class App extends Application{

    Note currentNote;

    Stack<Note> recursiveStack;

    @Override
    public void start(Stage primaryStage) throws Exception {

        /// Layout Setup ////////////

        primaryStage.setTitle("BasicGUI for GMaid");

        BorderPane rootLayout = new BorderPane();

        final Canvas centerDrawingBoard = new Canvas(600,800);
        ScrollPane centerHubArea = new ScrollPane(centerDrawingBoard);
        rootLayout.setCenter(centerHubArea);

        Canvas leftDrawingBoard = new Canvas(200,500);
        ScrollPane leftHubArea = new ScrollPane(leftDrawingBoard);
        rootLayout.setLeft(leftHubArea);

        final TextField bottomInputField = new TextField();
        final TextArea bottomOutputField = new TextArea();
        bottomOutputField.setPrefHeight(100);
        bottomOutputField.setEditable(false);
        VBox bottomHubArea = new VBox(bottomOutputField,bottomInputField);
        rootLayout.setBottom(bottomHubArea);

        ////////////////////////////

        /// Data ///////////////////

        currentNote = FileLoader.loadNote("note_example.note");
        //makeExampleStructure();
        recursiveStack = new Stack<Note>();

        System.out.println(currentNote);

        final Display display = new Display(currentNote);
        System.out.println(currentNote.getClass());

        final GraphicsContext centerGraphicsContext = centerDrawingBoard.getGraphicsContext2D();

        ////////////////////////////

        /// Handling Clicking inside DrawingBoard //////////////////////

        centerDrawingBoard.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Note n = display.checkCollision(event.getX(), event.getY());
                        System.out.println(n);
                        if(n != null) {
                            if(n == currentNote){
                                if(!recursiveStack.empty()){
                                    System.out.println(recursiveStack);
                                    currentNote = recursiveStack.peek();
                                    recursiveStack.pop();
                                    display.setNote(currentNote);
                                }
                            }
                            else{
                                recursiveStack.push(currentNote);
                                currentNote = n;
                                display.setNote(currentNote);
                            }
                        }
                    }
                }
        );

        ////////////////////////////////////////////////////////////////

        /// Command Line Input Handling (Applying Parser) //////////////

        bottomInputField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bottomOutputField.setText(bottomInputField.getText());
                bottomInputField.clear();
            }
        });

        ////////////////////////////////////////////////////////////////

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // render
                centerGraphicsContext.clearRect(0, 0, centerDrawingBoard.getWidth(),centerDrawingBoard.getHeight());



                display.render(centerDrawingBoard);
            }
        }.start();

        Scene rootScene = new Scene(rootLayout, 800, 600);

        primaryStage.setScene(rootScene);
        primaryStage.show();
    }

    private void makeExampleStructure() {
        ListNote tmp = (ListNote)(currentNote);
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
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
