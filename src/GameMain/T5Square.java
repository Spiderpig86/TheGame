package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/24/2016.
 */

/*T5 Square Stats:
    Attack: 3 HP
    Speed: 1 - 10
    Special: Random Speed Fluctuations
 */

public class T5Square extends GameObject{


    private Handler handler;
    private GameObject player;
    private Random r;

    int startX;
    int startY;

    boolean isUp;

    public T5Square(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        r = new Random();

        startX = x;
        startY = y;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }

        this.handler = handler;

        velX = r.nextInt(5) + 1;
        velY = r.nextInt(5) + 1;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) { if (velY < 0) velY = -(r.nextInt(10) + 1) * -1; else velY = (r.nextInt(10) + 1) * -1; }
        if (x <= 0 || x >= Game.WIDTH - 32) { if (velX < 0) velX = -(r.nextInt(10) + 1) * -1; else velX = (r.nextInt(10) + 1) * -1;}

        handler.addObject(new Trail(x, y, ID.Trail, Color.decode("#C3C3C3"), 24, 24, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.decode("#C3C3C3"));
        g.fillRect((int)x, (int)y, 24, 24);
    }
}
