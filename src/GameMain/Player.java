package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/5/2016.
 */
public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 40);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        collision(); //checks for collision
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision Code
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.white);
        else if (id == ID.Player2) g.setColor(Color.blue);

        Graphics2D g2d = (Graphics2D) g; //For collision bounds.
        g2d.draw(getBounds());

        //g.fillRect(x ,y, 32, 32);
    }
}
