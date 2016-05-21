package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/20/2016.
 */
public class MenuParticle extends GameObject {
    private Handler handler;
    private Color color;

    Random r = new Random();

    int red = r.nextInt(255);
    int green = r.nextInt(255);
    int blue = r.nextInt(255);

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        Random r = new Random();
        this.handler = handler;

        this.color = new Color(red, green, blue);

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
