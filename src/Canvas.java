import javax.swing.*;
import java.awt.event.*;

public class Canvas extends JFrame implements KeyListener {

    private int x = 50;
    private int y = 50;

    private Ninja ninja = new Ninja();
    private FireBall fireBall = new FireBall();
    private Monster monster1;
    private Monster monster2;
    private EndWindowGame endWindowGame = new EndWindowGame();

    private boolean monstersSpawned;

    Canvas(){
        endWindowGame.setHp(100);
        endWindowGame.setScore(0);
        setSize(2500,2000);
        setLayout(null);
        addKeyListener(this);
        setVisible(true);

        ninja.setBounds(ninja.getX(),ninja.getY(),ninja.getWidthOfStandingNinja(), ninja.getHeightOfStandingNinja());
        add(ninja);
        ninja.standAnim();

        endWindowGame.setHp(100);
        endWindowGame.setScore(0);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                ninja.runAnim();
                break;
            case KeyEvent.VK_SPACE:
                ninja.jumpAnim();
                break;
            case KeyEvent.VK_ESCAPE:
                 new EndWindowGame();
                break;
            case KeyEvent.VK_Z:
                monstersSpawned = true;

                monster1 = new Monster(MonsterType.LARRY,1700,50);
                monster1.setBounds(monster1.getXPosition(),monster1.getYPosition(), monster1.getWidth(), monster1.getHeight());

                monster2 = new Monster(MonsterType.SLIME, 1700,250);
                monster2.setBounds(monster2.getXPosition(), monster2.getYPosition(), monster2.getWidth(), monster2.getHeight());

                add(monster1);
                add(monster2);
                break;
            case KeyEvent.VK_ENTER:
                fireBall.setVisible(true);

                fireBall.setBounds(ninja.getX() + 100, ninja.getY(), fireBall.getWidth(), fireBall.getHeight());
                add(fireBall);

                fireBall.FireBallAttack();
                break;
            case KeyEvent.VK_M:
                if (monstersSpawned)
                    monster1.MoveMonsters();
                    monster2.MoveMonsters();
                break;
            default:
                ninja.standAnim();
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}