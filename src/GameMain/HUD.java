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

    private Game game;

    public HUD(Game game) {
        this.game = game;
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
        greenVal = Game.clamp(greenVal, 0, 255);

        greenVal = HEALTH * 2;

        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 90, 200, 32);
        g.setColor(new Color((int)redVal, (int)greenVal, (int)blueVal));
        g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 90, (int)(HEALTH * 2), 32);
        g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 90, 200, 32);

        g.drawString("Score: " + score, 10, 40);
        g.drawString("Level: " + level, 10, 60);

        /*if (System.currentTimeMillis() - game.getTimer() > 1000) {
            g.setColor(Color.white);
            g.drawString("FPS: " + 4, 10, 10);
        }*/
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
}
