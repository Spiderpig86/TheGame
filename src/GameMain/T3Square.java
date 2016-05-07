package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/6/2016.
 */

/*T3 Square Stats:
    Attack: 5 HP
    Speed: 1 - 5
    Special: Smarter AI
 */

public class T3Square extends GameObject{


    private Handler handler;
    private GameObject player;

    public T3Square(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }

        Random r = new Random();

        //velX = r.nextInt(5) + 1;
        //velY = r.nextInt(5) + 1;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt(((x - player.getX()) * (x - player.getX())) + ((y - player.getY()) * (y - player.getY()))); //distance formula. could use pow();

        velX = (float) ((-1.0 / distance) * diffX);
        velY = (float) ((-1.0 / distance) * diffY);

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //Keeps entity in bound.
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.decode("#CACC16"), 24, 24, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.decode("#CACC16"));
        g.fillRect((int)x, (int)y, 24, 24);
    }
}
