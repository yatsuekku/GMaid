import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by Matt on 06.05.2017.
 */
public class BasicGui extends JFrame implements ActionListener, ListSelectionListener {

    JTextField txtfCurrentNode;

    JButton backButton;
    JList listOfNodes;

    JList backtrackList;

    JTextArea txtaCurrentNoteTexr;

    static Stack<NoteHolder> backtrack;
    static NoteHolder data;
    static NoteHolder prev;

    public BasicGui() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        data = new NoteHolder<String>("Ta wiadomość jest rootem hierarchii. \nSłuży do sprawdzenia czy działa.","Testowy Trash!");
        data.add(new String("I am in subnote 1"), "Subnote 1");
        data.add(new String("I am in subnote 2"), "Subnote 2");
        data.add(new String("I am in subnote 3"), "Subnote 3");
        data.add(new String("I am in subnote 4"), "Subnote 4");
        data.add(new String("I am in subnote 5"), "Subnote 5");
        data.add(new String("I am in subnote 6"), "Subnote 6");
        prev=null;
        backtrack = new Stack<NoteHolder>();
        backtrack.push(data);

        JPanel currentObject = new JPanel(new FlowLayout());
        txtfCurrentNode = new JTextField(data.getName(),100);
        txtfCurrentNode.setEditable(false);
        txtfCurrentNode.setHorizontalAlignment(JTextField.CENTER);
        currentObject.add(txtfCurrentNode);

        JPanel nodeDisplay = new JPanel(new BorderLayout());
        backButton = new JButton("Go Back!");
        nodeDisplay.add(backButton,BorderLayout.NORTH);
        listOfNodes = new JList(data.getList().toArray());
        listOfNodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(listOfNodes);
        listScroller.setPreferredSize(new Dimension(250,300));
        nodeDisplay.add(listScroller,BorderLayout.CENTER);

        JPanel stackDisplay = new JPanel(new BorderLayout());
        stackDisplay.add(new JLabel("Tree:"),BorderLayout.NORTH);
        backtrackList = new JList(backtrack.toArray());
        listOfNodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane backtrackScroller = new JScrollPane(backtrackList);
        backtrackScroller.setPreferredSize(new Dimension(150,300));
        stackDisplay.add(backtrackScroller,BorderLayout.CENTER);

        JPanel NoteInsideDisplay = new JPanel(new FlowLayout());
        txtaCurrentNoteTexr = new JTextArea(data.getValue().toString(),8,50);
        txtaCurrentNoteTexr.setEditable(false);
        NoteInsideDisplay.add(txtaCurrentNoteTexr);


        cp.add(currentObject,BorderLayout.NORTH);
        cp.add(nodeDisplay,BorderLayout.CENTER);
        cp.add(stackDisplay,BorderLayout.WEST);
        cp.add(NoteInsideDisplay,BorderLayout.SOUTH);

        backButton.addActionListener(this);
        listOfNodes.addListSelectionListener(this);
        //addMouseListener(this);
        //addMouseMotionListener(this);
       // txtfKeyInput.addKeyListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test Run");
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        BasicGui app = new BasicGui();
    }

    //ActionListener overrides

    @Override
    public void actionPerformed(ActionEvent evt){
        Object src = evt.getSource();
        if(src == backButton){
            if(prev != null){
                data=data.goBack();
                prev=data.goBack();
                txtaCurrentNoteTexr.setText(data.getValue().toString());
                txtfCurrentNode.setText(data.getName());
                listOfNodes = new JList(data.getList().toArray());
                listOfNodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                backtrack.pop();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent evt) {
        if(evt.getValueIsAdjusting() == false) {
            if (listOfNodes.getSelectedIndex() != -1) {
                data = (NoteHolder)(listOfNodes.getSelectedValue());
                prev = data.goBack();
                txtaCurrentNoteTexr.setText(data.getValue().toString());
                txtfCurrentNode.setText(data.getName());
                listOfNodes = new JList(data.getList().toArray());
                listOfNodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                backtrack.push(data);
            }
        }
    }

    /*//MouseListener overrides

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
    @Override public void keyReleased(KeyEvent evt) { }*/
}
