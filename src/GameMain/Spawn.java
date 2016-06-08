 

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Stan on 5/6/2016.
 */
public class Spawn { //Spawns enemies, keeps track of score.

    private Handler handler;
    private HUD hud;
    private Game game;
    Random r;
    Timer t = new Timer();

    int count = 0;

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
            hud.incLevel();

            //Add T1 Square each level.
            if (game.diff == 0) {
                handler.addObject(new T1Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Enemy, handler));

                if (hud.getLevel() % 3 == 0 && hud.getLevel() >= 3) {
                    handler.addObject(new T2Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T2Enemy, handler));
                } else if (hud.getLevel() % 4 == 0 && hud.getLevel() >= 4) {
                    handler.addObject(new Health(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Health, handler));
                } else if (hud.getLevel() % 5 == 0 && hud.getLevel() >= 5) {
                    handler.addObject(new T1Kill(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T1Kill, handler));
                    handler.addObject(new T3Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T3Enemy, handler));
                } else if (hud.getLevel() % 6 == 0 && hud.getLevel() >= 6) {
                    handler.addObject(new T4Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T4Enemy, handler));
                } else if (hud.getLevel() % 8 == 0 && hud.getLevel() >= 6) {
                    handler.addObject(new T5Square(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.T5Enemy, handler));
                } else if (hud.getLevel() == 20) {
                    handler.clearEnemies();
                    handler.addObject(new God((Game.WIDTH / 2) - 48, -106, ID.God, handler));
                }
            } else { //hard mode good shit
                handler.clearEnemies();
                if (hud.getLevel() % 2 == 0) {
                    count = 0;
                    t.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (count < 6) {
                                handler.addObject(new H1Square(10 + (80 * count / hud.getLevel()), Game.HEIGHT / 2, ID.T1Enemy, handler));
                                count++;
                            } else { //causes crash when restarting game after loss
                                /*t.cancel();
                                t.purge();*/
                            }
                        }
                    },  250, 250); //params measured in milliseconds (delay, period)
                } else if (hud.getLevel() % 3 == 0) {
                    for (int i = 1; i < 15; i++) {
                        handler.addObject(new H2Square(Game.WIDTH / 2, (48 * i), ID.T2Enemy, handler));
                    }
                    
                 } else if (hud.getLevel() % 3 == 0) {
                    for (int i = 1; i < 9; i++) {
                        handler.addObject(new H2Square(Game.WIDTH / 2, (32 * i), ID.T2Enemy, handler));
                    }
                    
                } else if (hud.getLevel() % 5 == 0) {
                    handler.addObject(new H3Square(Game.WIDTH / 2, Game.HEIGHT / 2, 10, -10, ID.T2Enemy, handler));
                }
            }
        }
    }

    public void resetScoreKeep() { this.scoreKeep = 0; }

}
