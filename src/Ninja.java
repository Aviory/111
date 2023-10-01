import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ninja extends JPanel {
    private BaseAnimation runAnimation;
    private BaseAnimation jumpingAnimation;
    private BaseAnimation standAnimation;
    private BaseAnimation currentAnimation;

    public int getWidthOfStandingNinja() {
        return 141;
    }

    public int getHeightOfStandingNinja() {
        return 227;
    }

    public void standAnim(){
        ArrayList<AnimPoint> points = new ArrayList<>();
        points.add(new AnimPoint(-95,-70,141,227));

        standAnimation = new BaseAnimation(points,true);
        standAnimation.start();
        currentAnimation = standAnimation;
    }

    public void jumpAnim(){
        ArrayList<AnimPoint> points = new ArrayList<>();
        points.add(new AnimPoint(-89,-560,147,333));
        points.add(new AnimPoint(-243,-560,181,333));
        points.add(new AnimPoint(-435,-560,185,333));
        points.add(new AnimPoint(-631,-560,217,333));
        points.add(new AnimPoint(-845,-560,165,333));
        points.add(new AnimPoint(-1010,-560,211,333));
        points.add(new AnimPoint(-1215,-560,173,333));
        points.add(new AnimPoint(-1387,-560,171,333));
        points.add(new AnimPoint(-1555,-560,163,333));

        jumpingAnimation = new BaseAnimation(points);
        jumpingAnimation.start();
        currentAnimation = jumpingAnimation;
    }

    public void runAnim() {
        ArrayList<AnimPoint> points = new ArrayList<>();
        points.add(new AnimPoint(-99,-340, 200,203));
        points.add(new AnimPoint(-311,-340,200,203));
        points.add(new AnimPoint(-527,-340,200,203));
        points.add(new AnimPoint(-745,-340,200,203));
        points.add(new AnimPoint(-957,-340,200,203));
        points.add(new AnimPoint(-1173,-340,200,203));

        runAnimation = new BaseAnimation(points);
        runAnimation.start();
        currentAnimation = runAnimation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("src/res/black-ninja-game-sprites_7814-467.png"));
            setSize(currentAnimation.getWidth(), currentAnimation.getHeight());
            g.drawImage(image, currentAnimation.getX(), currentAnimation.getY(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class BaseAnimation extends Thread {
        private int x;
        private int y;
        private int width;
        private int height;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        private boolean isAllTime;
        private ArrayList<AnimPoint> points;

        public BaseAnimation(ArrayList<AnimPoint> points, boolean isAllTime){
            this.points = points;
            this.isAllTime = isAllTime;
        }

        public BaseAnimation(ArrayList<AnimPoint> points){
            this.points = points;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                if (i == points.size()) {
                    i = 0;
                    if (!isAllTime){
                        standAnim();
                        return;
                    }
                }

                x = points.get(i).getX();
                y = points.get(i).getY();
                width = points.get(i).getWidth();
                height = points.get(i).getHeight();

                try {
                    Thread.sleep(1000 / 8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                repaint();
                i++;
            }
        }
    }
}
