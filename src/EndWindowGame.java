import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndWindowGame extends JFrame implements ActionListener {
    private int hp = 100;
    private int score;

    public EndWindowGame() throws HeadlessException {
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        CreateTextAreas();
        CreateButtons();
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void CreateTextAreas() {
        CreateTextArea(150,10," hp = ", hp);
        CreateTextArea(150,60," score = ", score);
    }

    public void CreateTextArea(int x, int y, String name, int number) {
        JTextField txt = new JTextField(name);
        txt.setBounds(x,y,50,25);
        txt.setEditable(false);

        JTextField textWithVariable = new JTextField(String.valueOf(number));
        textWithVariable.setBounds(x + 60, y, 25,25);
        textWithVariable.setEditable(false);

        add(txt);
        add(textWithVariable);
    }

    public void CreateButtons() {
        CreateButton(10, 10, "Exit");
    }

    public void CreateButton(int x, int y, String name) {
        JButton button = new JButton(name);
        button.setBounds(x, y, 100, 50);
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Exit":
                setVisible(false);
        }
    }
}