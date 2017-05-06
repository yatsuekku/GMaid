import java.awt.*;
import java.awt.event.*;

/**
 * Created by Matt on 06.05.2017.
 */
public class BasicGui extends Frame implements ActionListener, WindowListener, MouseListener, MouseMotionListener, KeyListener{

    private Label labelCount;
    private Button buttonCount;
    private TextField txtfCount;
    private int count = 0;

    private TextField txtfMouseClickX;
    private TextField txtfMouseClickY;

    private TextField txtfMousePosX;
    private TextField txtfMousePosY;

    private TextField txtfKeyInput;
    private TextArea txtaKeyOutput;

    public BasicGui(){
        setLayout(new FlowLayout());

        labelCount = new Label("Counter");
        add(labelCount);

        txtfCount = new TextField("0",10);
        txtfCount.setEditable(false);
        add(txtfCount);

        buttonCount = new Button("Count");
        add(buttonCount);

        add(new Label("X Click"));
        txtfMouseClickX = new TextField(10);
        txtfMouseClickX.setEditable(false);
        add(txtfMouseClickX);

        add(new Label("Y Click"));
        txtfMouseClickY = new TextField(10);
        txtfMouseClickY.setEditable(false);
        add(txtfMouseClickY);

        add(new Label("X Pos"));
        txtfMousePosX = new TextField(10);
        txtfMousePosX.setEditable(false);
        add(txtfMousePosX);

        add(new Label("Y Pos"));
        txtfMousePosY = new TextField(10);
        txtfMousePosY.setEditable(false);
        add(txtfMousePosY);

        add(new Label("Enter text, please: "));
        txtfKeyInput = new TextField(10);
        add(txtfKeyInput);

        txtaKeyOutput = new TextArea(5,40);
        add(txtaKeyOutput);

        buttonCount.addActionListener(this);
        addWindowListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        txtfKeyInput.addKeyListener(this);

        setTitle("Test Run");
        setSize(900,200);
        setVisible(true);

        System.out.println(this);
        System.out.println(labelCount);
        System.out.println(txtfCount);
        System.out.println(buttonCount);
    }

    public static void main(String[] args) {
        BasicGui app = new BasicGui();
    }

    //ActionListener overrides

    @Override
    public void actionPerformed(ActionEvent evt){
        count++;
        txtfCount.setText(count + "");
    }

    // WindowListener overrides

    @Override
    public void windowClosing(WindowEvent evt){
        System.exit(0);
    }
    @Override public void windowOpened(WindowEvent evt) { }
    @Override public void windowClosed(WindowEvent evt) { }
    @Override public void windowIconified(WindowEvent evt) { }
    @Override public void windowDeiconified(WindowEvent evt) { }
    @Override public void windowActivated(WindowEvent evt) { }
    @Override public void windowDeactivated(WindowEvent evt) { }

    //MouseListener overrides

    @Override
    public void mouseClicked(MouseEvent evt) {
        txtfMouseClickX.setText(evt.getX() + "");
        txtfMouseClickY.setText(evt.getY() + "");
    }
    @Override public void mousePressed(MouseEvent evt) { }
    @Override public void mouseReleased(MouseEvent evt) { }
    @Override public void mouseEntered(MouseEvent evt) { }
    @Override public void mouseExited(MouseEvent evt) { }

    //MouseMotionListener overrides


    @Override
    public void mouseMoved(MouseEvent evt) {
        txtfMousePosX.setText(evt.getX() + "");
        txtfMousePosY.setText(evt.getY() + "");
    }
    @Override public void mouseDragged(MouseEvent evt) { }

    //KeyListener overrides


    @Override
    public void keyTyped(KeyEvent evt) {
        if(evt.getKeyChar() == 8)
            txtaKeyOutput.setText("");
        else
            txtaKeyOutput.append(evt.getKeyChar() + "");
        txtfKeyInput.setText((char)(0) + "");
    }
    @Override public void keyPressed(KeyEvent evt) { }
    @Override public void keyReleased(KeyEvent evt) { }
}
