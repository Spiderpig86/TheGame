package GameMain;

import java.awt.*;

/**
 * Created by Stan on 5/5/2016.
 */
public class Trail extends GameObject {

    private float alpha = 1; //for opacity
    private Handler handler;
    private Color color = Color.green;

    private int width, height = 16;

    private float life; //life = 0.001 - 0.1

    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick() {
        if (alpha >life) {
            alpha -= life - 0.01f;
        } else {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);

        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) { //Method that renders the fade out for trail.
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public Rectangle getBounds() {
        return null;
    }

}
