package GameMain;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class T1Kill extends GameObject
{
    // instance variables - replace the example below with your own
    private Handler handler;
    Timer timer = new Timer();
    
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

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
        public void run() {
                isYellow = !isYellow;
            }
        },  250, 250); //params measured in milliseconds (delay, period)
    }
    
     public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        //stationary does not do update
        /*for (int i = 0; i < 1000; i++)
        if (i > 500) isYellow = true; else { isYellow = false; }*/

    }

    public void render(Graphics g) {
        if (isYellow) {
        g.setColor(Color.YELLOW);
    } else {
          g.setColor(Color.BLUE);
    }
        g.drawRect((int)x, (int)y, 16, 16);
    }
}
