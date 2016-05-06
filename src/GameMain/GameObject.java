package GameMain;

import java.awt.*;

/**
 * Created by Stan on 5/5/2016.
 */

//Base class for all game entities.

public abstract class GameObject {

    protected int x, y; //Position
    protected ID id;
    protected int velX, velY; //Velocity

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void getY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velY;
    }
}
