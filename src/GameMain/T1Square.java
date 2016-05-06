package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/5/2016.
 */

/*T1 Square Stats:
    Attack: 1 HP
    Speed: 1 - 5
 */

public class T1Square extends GameObject {

    private Handler handler;
    private Color color;

    public T1Square(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        Random r = new Random();

        int rColor = r.nextInt(3);

        switch (rColor) {
            case 0:
                this.color = Color.decode("#12C900");
                break;
            case 1:
                this.color = Color.decode("#7C0000");
                break;
            case 2:
                this.color = Color.decode("#8F18CF");
                break;
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

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //Keeps entity in bound.
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
