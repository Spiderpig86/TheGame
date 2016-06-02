package GameMain;

import java.awt.*;
import java.util.Random;

public class T1Kill extends GameObject
{
    // instance variables - replace the example below with your own
    private Handler handler;
    
    boolean isYellow = false;

    /**
     * Constructor for objects of class Health
     */
    public T1Kill(int x, int y, ID id, Handler handler)
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
        for (int i = 0; i < 1000; i++)
            isYellow = !isYellow;
    }

    public void render(Graphics g) {
        if (isYellow) {
            System.out.println("test");
        g.setColor(Color.YELLOW);
    } else {
          g.setColor(Color.BLUE);
    }
        g.drawRect((int)x, (int)y, 16, 16);
    }
}
