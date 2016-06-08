package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 6/7/2016.
 */

/*H2 Square Stats:
    Attack: 2 HP
    Speed: 15
    Special: Moves side to side.
 */

public class H2Square extends GameObject{


    private Handler handler;

    public H2Square(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        Random r = new Random();

        if (r.nextInt(2) == 1) {
            velX = 15;
        } else {
            velX = -15;
        }
        velY = 0;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //Keeps entity in bound.
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.decode("#00BCCD"), 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.decode("#00BCCD"));
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
