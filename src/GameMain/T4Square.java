package src.GameMain;

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

public class T4Square extends GameObject{


    private Handler handler;
    private GameObject player;
    
    int startX;
    int startY;

    public T4Square(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        
        startX = x;
        startY = y;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }

        Random r = new Random();

        velX = r.nextInt(5) + 1;
        velY = r.nextInt(5) + 1;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        
        if (x == startX + 5) {
            velX = 0;
            velY = 5;
        } else if (y == startY + 5) {
            velX = -5;
            velY = 0;
        }  else if (x == startX + 1) {
            velX = 0;
            velY = 0;
        }  else if (y == startY + 5) {
            velX = -5;
            velY = 0;
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.decode("#7E3900"), 24, 24, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.decode("#7E3900"));
        g.fillRect((int)x, (int)y, 24, 24);
    }
}
