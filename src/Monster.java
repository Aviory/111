import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monster extends JPanel {
    private int monsterX;
    private int monsterY;

    private int xPosition;
    private int yPosition;

    private int currentX;//0

    private MonsterType monsterType;
    private MovingMonsters movingMonsters;



    public Monster(MonsterType monsterType, int xPos, int yPos) {
        this.monsterType = monsterType;
        this.xPosition = xPos;
        this.currentX = xPos;
        this.yPosition = yPos;

        this.monsterX = monsterType.getPoint().getX();
        this.monsterY = monsterType.getPoint().getY();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    @Override
    public int getWidth() {
        return monsterType.getPoint().getWidth();
    }

    @Override
    public int getHeight() {
        return monsterType.getPoint().getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("src/res/Free-Monster-2D-Game-Objects3.png"));
            g.drawImage(image, monsterX, monsterY, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MoveMonsters(){
        movingMonsters = new MovingMonsters();
        movingMonsters.start();
    }

    class MovingMonsters extends Thread{
        private Ninja ninja = new Ninja();

        @Override
        public void run() {
            int step = 50;

            while (true){
                if (currentX <= ninja.getX()){
                    currentX = xPosition;//0

                }

                currentX -= step;
                setBounds(currentX,yPosition,getWidth(),getHeight());



                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

enum MonsterType {
    LARRY(new AnimPoint(-245, -140, 147, 98)), SLIME(new AnimPoint(-450, -120, 138, 120));

    private final AnimPoint point;

    public AnimPoint getPoint() {
        return point;
    }

    MonsterType(AnimPoint point) {
        this.point = point;
    }
}
