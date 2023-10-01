import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FireBall extends JPanel {
    private int startX;
    private int startY;
    private int currentX;
    private int currentY;

    private final int countOfSprites = 6;

    private MonsterAttack monsterAttack;
    private Monster monster;

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    @Override
    public int getHeight() {
        return 85;
    }

    @Override
    public int getWidth() {
        return 82;
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("src/res/PC Computer - BIONICLE The Legend of Mata Nui Prototype - Fireball.png"));
            setSize(getWidth(),getHeight());
            g.drawImage(image, monsterAttack.getX(), monsterAttack.getY(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FireBallDestruction(){
//        monsterAttack = new MonsterAttack();
//        if (monsterAttack.isAlive())
//            monsterAttack.interrupt();
    }

    public void FireBallAttack(){
        ArrayList<AnimPoint> points = new ArrayList<>();
        points.add(new AnimPoint(-27,-31,82,85));
        points.add(new AnimPoint(-157,-31,82,85));
        points.add(new AnimPoint(-289,-31,82,85));
        points.add(new AnimPoint(-422,-31,82,85));
        points.add(new AnimPoint(-555,-31,82,85));
        points.add(new AnimPoint(-688,-31,82,85));

        monsterAttack = new MonsterAttack(points);
        monsterAttack.start();
    }

    class MonsterAttack extends Thread{
        private int x;
        private int y;
        private int width;
        private int height;

        private ArrayList<AnimPoint> points;

        MonsterAttack(ArrayList<AnimPoint> points){
            this.points = points;
        }

        MonsterAttack(){

        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public void run() {
            int i = 0;
            int step = 50;


            while (true){
                if (currentX >= 1700){
                    currentX = startX;
                    setVisible(false);
                    return;
//                    FireBallDestruction();

                }

                if (i == countOfSprites){
                    i = 0;
                }

                x = points.get(i).getX();
                y = points.get(i).getY();
                width = points.get(i).getWidth();
                height = points.get(i).getHeight();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                currentX += step;
                setBounds(currentX,startY,getWidth(),getHeight());

                repaint();
                i++;
            }
        }
    }
}
