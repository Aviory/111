import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Mehanics implements KeyListener {

    private Ninja ninja = new Ninja();
    private FireBall fireBall = new FireBall();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private Canvas canvas;
    private EndWindowGame endWindowGame = new EndWindowGame();
    private boolean monstersSpawned;


    public Mehanics(Canvas canvas) {
        this.canvas = canvas;
        endWindowGame.setHp(100);
        endWindowGame.setScore(0);

        ninja.setBounds(ninja.getX(),ninja.getY(),ninja.getWidthOfStandingNinja(), ninja.getHeightOfStandingNinja());
        canvas.add(ninja);
        ninja.standAnim();

    }

    @Override
    public void keyTyped(KeyEvent e) {

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

                Monster monster1 = new Monster(MonsterType.LARRY,1700,50);
                monster1.setBounds(monster1.getXPosition(),monster1.getYPosition(), monster1.getWidth(), monster1.getHeight());
                monsters.add(monster1);
                Monster monster2 = new Monster(MonsterType.SLIME, 1700,250);
                monster2.setBounds(monster2.getXPosition(), monster2.getYPosition(), monster2.getWidth(), monster2.getHeight());
                monsters.add(monster2);
                canvas.add(monster1);
                canvas.add(monster2);
                break;
            case KeyEvent.VK_ENTER:
                fireBall.setVisible(true);

                fireBall.setBounds(ninja.getX() + 100, ninja.getY(), fireBall.getWidth(), fireBall.getHeight());
                canvas.add(fireBall);

                fireBall.FireBallAttack();
                break;
            case KeyEvent.VK_M:
                if (monstersSpawned)
                    startMoveMonsters();
                break;
            default:
                ninja.standAnim();
                break;
        }
        canvas.repaint();
    }
    private void startMoveMonsters() {
        for (Monster monster :monsters) {
            monster.moveMonsters();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
