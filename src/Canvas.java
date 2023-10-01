import javax.swing.*;

public class Canvas extends JFrame {


    private Mehanics mehanics = new Mehanics(this);


    Canvas(){
        setSize(2500,2000);
        setLayout(null);
        addKeyListener(mehanics);
        setVisible(true);
    }


}