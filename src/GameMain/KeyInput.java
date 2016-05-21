package GameMain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Stan on 5/5/2016.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4]; //Tells us which keys are pressed. To fix sticky key glitch.

    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;

        keyDown[0] = false; //W
        keyDown[1] = false; //A
        keyDown[2] = false; //S
        keyDown[3] = false; //D
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //Gets ASCII value of key pressed.

        for (int i = 0; i < handler.object.size(); i++) { //Loops through all player entities.
            GameObject tempObject = handler.object.get(i); //Gets object from list called object in handler class.

            if (tempObject.getID() == ID.Player) {
                //key events for player 1

                if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
                if(key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[1] = true; }
                if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[2] = true; }
                if(key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[3] = true; }

            }

            /*if (tempObject.getID() == ID.Player2) {
                //key events for player 2

                if(key == KeyEvent.VK_UP) tempObject.setVelY(-5);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(5);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);

            }*/
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) { //Loops through all player entities.
            GameObject tempObject = handler.object.get(i); //Gets object from list called object in handler class.

            if (tempObject.getID() == ID.Player) {
                //key events for player 1

                if(key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) keyDown[1] = false; //tempObject.setVelX(0);
                if(key == KeyEvent.VK_S) keyDown[2] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_D) keyDown[3] = false; //tempObject.setVelX(0);

                //vertical movement
                if (!keyDown[0] && !keyDown[2]) tempObject.setVelY(0);

                //horizontal movement
                if (!keyDown[1] && !keyDown[3]) tempObject.setVelX(0);
            }

            /*if (tempObject.getID() == ID.Player2) {
                //key events for player 2

                if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);

            }*/
        }

        if (key == KeyEvent.VK_P) {
            if (game.gameState == Game.STATE.Game) {
                if (Game.paused) Game.paused = false;
                else Game.paused = true;
            }
        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(0);

    }
}
