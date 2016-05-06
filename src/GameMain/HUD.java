package GameMain;

import java.awt.*;

/**
 * Created by Stan on 5/5/2016.
 */
public class HUD {

    public static int HEALTH  = 100;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 90, 200, 32);
        g.setColor(Color.green);
        g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 90, HEALTH * 2, 32);
        g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT - 90, 200, 32);
    }
}
