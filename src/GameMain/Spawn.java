package GameMain;

import java.util.Random;

/**
 * Created by Stan on 5/6/2016.
 */
public class Spawn { //Spawns enemies, keeps track of score.

    private Handler handler;
    private HUD hud;
    Random r;

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        r = new Random();
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 1000) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            //Add T1 Square each level.

            handler.addObject(new T1Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Enemy, handler));

            if(hud.getLevel() % 3 == 0 && hud.getLevel() >= 3) {
                handler.addObject(new T2Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T2Enemy, handler));
                System.out.println("T2");
            }else if(hud.getLevel() % 5 == 0 && hud.getLevel() >= 5) {
                handler.addObject(new T3Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T3Enemy, handler));
                System.out.println("T3");
            } else if (hud.getLevel() == 20) {
                handler.clearEnemies();
                handler.addObject(new God((Game.WIDTH / 2) - 48, -96, ID.God, handler));
            }
        }
    }
}
