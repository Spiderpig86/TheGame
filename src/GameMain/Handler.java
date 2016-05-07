package GameMain;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Stan on 5/5/2016.
 */

//Class handles and processes each object in game. Loops through all objects and update/renders them.
public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>(); //List of all game objects.

    public void tick() {
        for (int i = 0; i < object.size(); i++) { //Loops through all objects and updates them.
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) { //Loops through all objects and updates them.
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }

    }

    public void clearEnemies() {
        for (GameObject g: object) {
            if (g.getID() == ID.Player) {
                Player p = (Player)g;
                object.clear();
                addObject(new Player((int)p.getX(), (int)p.getY(), ID.Player, p.getColor(), this));
            }
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
