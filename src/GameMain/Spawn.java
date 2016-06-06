package GameMain;

import java.util.Random;

/**
 * Created by Stan on 5/6/2016.
 */
public class Spawn { //Spawns enemies, keeps track of score.

    private Handler handler;
    private HUD hud;
    private Game game;
    Random r;

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
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
            }else if(hud.getLevel() % 4 == 0 && hud.getLevel() >= 4) {
                handler.addObject(new Health(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Health, handler));
            }else if(hud.getLevel() % 5 == 0 && hud.getLevel() >= 5) {
                handler.addObject(new T1Kill(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Kill, handler));
                handler.addObject(new T3Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T3Enemy, handler));
            }else if(hud.getLevel() % 6 == 0 && hud.getLevel() >= 6) {
                handler.addObject(new T4Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T4Enemy, handler));
            } else if (hud.getLevel() == 2) {
                handler.clearEnemies();
                handler.addObject(new God((Game.WIDTH / 2) - 48, -106, ID.God, handler));
            }
        }
    }
}
