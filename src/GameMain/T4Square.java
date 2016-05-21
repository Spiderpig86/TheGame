package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/6/2016.
 */

/*T4 Square Stats:
    Attack: 5 HP
    Speed: 1 - 5
    Special: Sweeping Motion
 */

public class T4Square extends GameObject{


    private Handler handler;
    private GameObject player;
    
    int startX;
    int startY;
    
    boolean isUp;

    public T4Square(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        
        startX = x;
        startY = y;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }

        Random r = new Random();

        velX = r.nextInt(5) + 5;
        velY = 0;
        
        isUp = (r.nextInt(2) == 1) ? true : false; 
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        
        if (y <= 0) {
            isUp = false;
        } else if (y >= Game.HEIGHT - 32) {
            isUp = true;
        }; //Changes increment to up or down.
        if (x <= 0 || x >= Game.WIDTH - 32) {
            velX *= -1;
            if (isUp) {
                y-=32;
            } else {
                y+=32;
            }
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.decode("#7E3900"), 24, 24, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.decode("#7E3900"));
        g.fillRect((int)x, (int)y, 24, 24);
    }
}
