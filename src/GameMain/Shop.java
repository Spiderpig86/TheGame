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
        g.drawString("Shop", Game.WIDTH /2 - 60, 80);

        //box 1
        if (canBuy(B1))
            g.setColor(Color.green);
        else
            g.setColor(Color.lightGray);
        g.setFont(new Font("Segoe UI", 0 ,12));
        g.drawString("Upgrade Health", 120, 150);
        g.drawString("Cost: " + B1 ,120, 170);
        g.drawRect(110,130,100,80);

        //box 2
        if (canBuy(B2))
            g.setColor(Color.green);
        else
            g.setColor(Color.lightGray);
        g.drawString("Upgrade Speed", 270, 150);
        g.drawString("Cost: " + B2 ,270, 170);
        g.drawRect(260,130,100,80);

        //box 3
        if (canBuy(B3))
            g.setColor(Color.green);
        else
            g.setColor(Color.lightGray);
        g.drawString("Refill Health", 420, 150);
        g.drawString("Cost: " + B3 ,420, 170);
        g.drawRect(410,130,100,80);

        g.setColor(Color.white);
        g.drawString("Score: " + hud.getScore(), Game.WIDTH /2 - 30, 300);
        g.drawString("Press Space to go back", Game.WIDTH /2 - 70, 320);
    }

    public void mousePressed(MouseEvent e) {
        int mX =e.getX();
        int mY = e.getY();

        //box 1
        if(mX >= 100 && mX <= 200) { //captures if mouse is in box 1 bounds
            if (mY >= 100 && mY <= 180) {
                if (canBuy(B1)) {
                    hud.score(hud.getScore() - B1);
                    B1 += 1000;
                    hud.extraHP += 20;
                    hud.HEALTH = (100 + (hud.extraHP));
                }
            }
        }

        //box 2
        if(mX >= 250 && mX <= 350) { //captures if mouse is in box 2 bounds
            if (mY >= 100 && mY <= 180){
                if (canBuy(B2)) {
                    hud.score(hud.getScore() - B2);
                    B2 += 1000;
                    handler.spd++;
                }
            }
        }

        //box 3
        if(mX >= 400 && mX <= 500) { //captures if mouse is in box 3 bounds
            if (mY >= 100 && mY <= 180){
                if (canBuy(B3)) {
                    hud.score(hud.getScore() - B3);
                    hud.HEALTH = (100 + hud.extraHP);
                }
            }
        }
    }

    private boolean canBuy(int i) {
        return (hud.getScore() >= i);
    }

    public void resetPurchases() {
        B1 = 1000;
        B2 = 1000;
        B3 = 1000;
        hud.extraHP = 0; //resets health
        handler.spd = 5;
    }
}
