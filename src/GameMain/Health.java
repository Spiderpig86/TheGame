package GameMain;

import java.awt.*;
import java.util.Random;

public class Health extends GameObject
{
    // instance variables - replace the example below with your own
    private Handler handler;

    /**
     * Constructor for objects of class Health
     */
    public Health(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        
        velX = 0;
        velY = 0;
        
        this.handler = handler;
    }
    
     public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        //stationary does not do update
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect((int)x, (int)y, 16, 16);
    }
}
