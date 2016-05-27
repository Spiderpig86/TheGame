package GameMain;

import java.awt.*;

/**
 * Created by Stan on 5/5/2016.
 */
public class HUD {

    public static float HEALTH  = 100;

    //HUD color
    private float greenVal = 255;
    private float blueVal = 0;
    private float redVal = 125;

    private int score = 0;
    private int level = 1;

    //location
    private static int x = Game.WIDTH / 2 - 100;
    private static int y = Game.HEIGHT - 90;

    //size
    private static int width = 200;
    private static int height = 25;

    private Game game;

    //transparency detector
    private boolean isOver = false;

    public HUD(Game game) {
        this.game = game;
    }

    public static Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
        greenVal = Game.clamp(greenVal, 0, 255);

        greenVal = HEALTH * 2;

        score++;
    }

    public void render(Graphics g) {
        g.setColor(new Color(225,225,225,127));
        g.fillRect(x, y, 200, 32);
        if (isOver)
            g.setColor(new Color((int) redVal, (int) greenVal, (int) blueVal, 127));
         else
            g.setColor(new Color((int) redVal, (int) greenVal, (int) blueVal));
        g.fillRect(x, y, (int)(HEALTH * 2), 32);
        g.drawRect(x, y, 200, 32);

        g.setColor(new Color((int) redVal, (int) greenVal, (int) blueVal));
        g.setFont(Game.gameFont);
        g.drawString("Score: " + score, 10, 40);
        g.drawString("Level: " + level, 10, 60);
    }

    public void score(int score){
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public void setTransparency(boolean t) { isOver = t; }
}
