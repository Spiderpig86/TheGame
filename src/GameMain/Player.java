package GameMain;

import java.awt.*;
import java.util.Random;

/**
 * Created by Stan on 5/5/2016.
 */
public class Player extends GameObject {

    Random r = new Random();
    Handler handler;
    Color color = Color.white;
    HUD hud;

    public Player(int x, int y, ID id, Color color, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.hud = handler.getHUD();
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 40);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, color, 34, 34, 0.05f, handler));

        collision(); //checks for collision
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.T1Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision Code
                    HUD.HEALTH -= 1;
                }
            } else if (tempObject.getID() == ID.T2Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision Code
                    HUD.HEALTH -= 2;
                }
            } else if (tempObject.getID() == ID.T5Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision Code
                    HUD.HEALTH -= 3;
                }
            } else if (tempObject.getID() == ID.T3Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision Code
                    HUD.HEALTH -= 5;
                }
            } else if (tempObject.getID() == ID.Health) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //Collision Code
                        HUD.HEALTH += 100;
                             handler.removeObject(tempObject);
                    }
            } else if (tempObject.getID() == ID.T1Kill) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //Collision Code
                        for (int j = 0; j < handler.object.size(); j++) {
                            GameObject cube = handler.object.get(j);
                            if (cube.getID() == ID.T1Enemy) {
                                handler.removeObject(cube);
                            }
                            }
                                handler.removeObject(tempObject);
                    }
            }
            
        }

        //for HUD transparency
        if (getBounds().intersects(hud.getBounds()))
            hud.setTransparency(true);
        else
            hud.setTransparency(false);

    }

    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(color);
        else if (id == ID.Player2) g.setColor(Color.blue);

        Graphics2D g2d = (Graphics2D) g; //For collision bounds.
        g2d.draw(getBounds());

        //g.fillRect((int)x ,(int)y, 32, 32);
    }

    public Color getColor() {
        return color;
    }
}
