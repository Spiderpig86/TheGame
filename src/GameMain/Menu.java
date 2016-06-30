package GameMain;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by Stan on 5/20/2016.
 */
public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    //Button bounds
    private Rectangle r1 = new Rectangle(215, 150, 200, 64);
    private Rectangle r2 = new Rectangle(215, 250, 200, 64);
    private Rectangle r3 = new Rectangle(215, 350, 200, 64);

    public Menu(Game g, Handler handler, HUD hud) {
        game = g;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX(); //temp vars for mouse location
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {

            //play
            if (mouseOver(mx, my, r1)) {
                /*game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, Color.white, handler));
                handler.clearEnemies();
                for (int i = 0; i < 2; i++) {
                    handler.addObject(new T1Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Enemy, handler));
                }*/
                game.gameState = Game.STATE.Select;
            }

            //help
            if (mouseOver(mx, my, r2)) {
                game.gameState = Game.STATE.Help;
            }

            //quit
            if (mouseOver(mx, my, r3)) {
                System.exit(1);
            }
        } else if (game.gameState == Game.STATE.Select) {

            //normal
            if (mouseOver(mx, my, r1)) {
                game.gameState = Game.STATE.Game;

                game.diff = 0;
                
                game.setFade(false);

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, Color.white, handler));
                handler.clearEnemies();
                 for (int i = 0; i < 2; i++) {
                    handler.addObject(new T1Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Enemy, handler));
                }
            }

            //hard
            if (mouseOver(mx, my, r2)) {
                game.gameState = Game.STATE.Game;

                game.diff = 1;
                
                game.setFade(false);

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, Color.white, handler));
                handler.clearEnemies();
                for (int i = 0; i < 5; i++) {
                    handler.addObject(new T1Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Enemy, handler));
                }
            }

            //back
            if (mouseOver(mx, my, r3)) {
                game.gameState = Game.STATE.Menu;
            }
        }

        //help back
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, r3)) {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //restart
        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, r3)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.score(0);
                game.getShop().resetPurchases();
                game.getSpawner().resetScoreKeep();
                game.getSpawner().resetHardSquareInc();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, Color.white, handler));
                handler.clearEnemies();
                int j = (game.diff == 1) ? 4 : 2;
                for (int i = 0; i < j; i++) {
                    handler.addObject(new T1Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Enemy, handler));
                }
                return;
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, Rectangle rect) { //checks if mouse pos is in bounds of button
        if (mx > rect.x && mx < rect.x + rect.width) {
            if (my > rect.y && my < rect.y + rect.height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) { //draws menu
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("segoe ui", 1, 50);
            Font fnt2 = new Font("segoe ui", 1, 25);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Perimeter", 200, 70);

            g.setFont(fnt2);

            //Play
            g.drawRect(r1.x, r1.y, r1.width, r1.height);
            g.drawString("Play", 290, 190);

            //Help
            g.drawRect(r2.x, r2.y, r2.width, r2.height);
            g.drawString("Help", 290, 290);

            //Quit
            g.drawRect(r3.x, r3.y, r3.width, r3.height);
            g.drawString("Quit", 290, 390);
        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("segoe ui", 1, 50);
            Font fnt2 = new Font("segoe ui", 1, 25);
            Font fnt3 = new Font("segoe ui", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 250, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player to dodge enemies.", 75, 200);

            g.setFont(fnt2);
            g.drawRect(r3.x, r3.y, r3.width, r3.height);
            g.drawString("Back", 290, 390);
        } else if (game.gameState == Game.STATE.End) {
            Font fnt = new Font("segoe ui", 1, 50);
            Font fnt2 = new Font("segoe ui", 1, 25);
            Font fnt3 = new Font("segoe ui", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Darn.", 250, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(), 170, 200);

            g.setFont(fnt2);
            g.drawRect(r3.x, r3.y, r3.width, r3.height);
            g.drawString("Restart", 270, 390);
        } else if (game.gameState == Game.STATE.Select) {
            Font fnt = new Font("segoe ui", 1, 50);
            Font fnt2 = new Font("segoe ui", 1, 25);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select Difficulty", 150, 70);

            g.setFont(fnt2);

            //Play
            g.drawRect(r1.x, r1.y, r1.width, r1.height);
            g.drawString("Normal", 270, 190);

            //Help
            g.drawRect(r2.x, r2.y, r2.width, r2.height);
            g.drawString("Hard", 285, 290);

            //Quit
            g.drawRect(r3.x, r3.y, r3.width, r3.height);
            g.drawString("Back", 285, 390);
        }
    }
}