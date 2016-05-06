package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/6/2016.
 */

/*T1 Square Stats:
    Attack: 1 HP
    Speed: 1 - 5
 */

public class GodProjectile extends GameObject {

    private Handler handler;
    private Color color;

    public GodProjectile(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        Random r = new Random();

        color = Color.decode("#00278D");

        this.handler = handler;

        velX = r.nextInt(5 - -5) + -5;
        velY = 5;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        /*if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //Keeps entity in bound.
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;*/

        if (y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
