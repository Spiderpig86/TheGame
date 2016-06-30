package GameMain;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Stan on 6/30/2016.
 */
public class Shop extends MouseAdapter {

    Handler handler; //handler needed to change elements in game
    HUD hud;

    private int B1 = 1000;
    private int B2 = 1000;
    private int B3 = 1000;

    public Shop(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void render(Graphics g) { //draws shop layout
        g.setColor(Color.white);
        g.setFont(new Font("Segoe UI" ,0,48));
        g.drawString("Shop", Game.WIDTH /2 - 80, 50);

        //box 1
        g.setFont(new Font("Segoe UI", 0 ,12));
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + B1 ,110, 140);
        g.drawRect(100,100,100,80);

        //box 2
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + B2 ,260, 140);
        g.drawRect(250,100,100,80);

        //box 3
        g.drawString("Upgrade Health", 410, 120);
        g.drawString("Cost: " + B3 ,410, 140);
        g.drawRect(400,100,100,80);

        g.drawString("Score: " + hud.getScore(), Game.WIDTH /2 - 50, 300);
        g.drawString("Press Space to go back", Game.WIDTH /2 - 50, 320);
    }

    public void mousePressed(MouseEvent e) {
        int mX =e.getX();
        int mY = e.getY();

        //box 1
        if(mX >= 100 && mX <= 200) { //captures if mouse is in box 1 bounds
            if (mY >= 100 && mY <= 180) {
                if (canBuy(B1)) {
                    B1 += 1000;
                    hud.score(hud.getScore() - B1);
                }
            }
        }

        //box 2
        if(mX >= 250 && mX <= 350) { //captures if mouse is in box 1 bounds
            if (mY >= 100 && mY <= 180){

            }
        }

        //box 3
        if(mX >= 400 && mX <= 500) { //captures if mouse is in box 1 bounds
            if (mY >= 100 && mY <= 180){

            }
        }
    }

    private boolean canBuy(int i) {
        return (hud.getScore() >= i);
    }
}
