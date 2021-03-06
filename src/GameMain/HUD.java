package GameMain;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Stan on 5/5/2016.
 */
public class HUD {

    public int extraHP = 0;
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

    Timer t = new Timer();

    public HUD(Game game) {
        this.game = game;

        if (game.diff == 1) {
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (getLevel() == 5) {
                        game.isColor = !game.isColor;
                    }
                }
            }, 100, 250); //params measured in milliseconds (delay, period)
        }

    }

    public static Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100 + extraHP);

        greenVal = (int)(HEALTH / (100 + extraHP) * 255); //gets green value in proportion to total hp
        greenVal = Game.clamp(greenVal, 0, 255);

        score++;
    }

    public void render(Graphics g) {
        g.setColor(new Color(225,225,225,127));
        g.fillRect(x, y, 200, 32);
        if (isOver)
            g.setColor(new Color((int) redVal, (int) greenVal, (int) blueVal, 127));
         else
            g.setColor(new Color((int) redVal, (int) greenVal, (int) blueVal));
        g.fillRect(x, y, (int)((HEALTH * 200) / (100 + extraHP)), 32);
        g.drawRect(x, y, 200, 32);

        g.setColor(new Color((int) redVal, (int) greenVal, (int) blueVal));
        g.setFont(Game.gameFont);
        g.drawString("Score: " + score, 10, 40);
        g.drawString("Level: " + level, 10, 60);
        g.drawString("Press space for shop", 10,80);
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

    public void incLevel() {
        this.level += 1;
    }

}
