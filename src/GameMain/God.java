package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/6/2016.
 */

/*T3 Square Stats:
    Attack: 2 HP
    Speed: 5 - 10
    Special: Smarter AI
 */

public class God extends GameObject{

    private Handler handler;
    Random r = new Random();

    private int timer = 80;
    private int timer2 = 50;

    public God(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        Random r = new Random();

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 96, 96);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer <= 0)  velY = 0; //stops God from going down.
        else timer--;

        if (timer <= 0)  timer2--; //boss moves horizontally
        if (timer2 <= 0) {
            if (velX == 0) velX = 2;

            if (velX > 0) //speed up
                velX += 0.01;
            else if(velX < 0)
                velX -= 0.01;

            int spawn = r.nextInt(10); //boss spawns projectiles
            if (spawn < 2) handler.addObject(new GodProjectile((int)x + 48, (int)y, ID.T1Enemy, handler));
        }

        //if (y <= 0 || y >= Game.HEIGHT - 96) velY *= -1; //Keeps entity in bound.
        if (x <= 0 || x >= Game.WIDTH - 96) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.decode("#00278D"), 96, 96, 0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.decode("#00278D"));
        g.fillRect((int)x, (int)y, 96, 96);
    }
}
