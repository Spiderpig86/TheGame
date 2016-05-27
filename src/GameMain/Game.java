package GameMain;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Stan on 5/5/2016.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    public static final Font gameFont = new Font("segoe ui", 0, 14);

    private Thread thread;
    private boolean running = false;

    public static boolean paused = false;
    public int diff = 0;
    // 0 - normal
    // 1 - hard

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    long timer;
    int frames = 0;
    int fpsFinal = 0;
    
    //BACKGROUND FADE
    int red = 10;
    int green = 10;
    int blue = 10;
    
    int incR = 1;
    int incG = 1;
    int incB = 1;

    public enum STATE {
        Menu,
        Select,
        Help,
        Game,
        End
    };

    public static STATE gameState = STATE.Menu;

    //Constructor
    public Game() {
        hud = new HUD(this);
        handler = new Handler(hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        AudioPlayer.init();
        AudioPlayer.getMusic("music").loop();

        new Window(WIDTH, HEIGHT, "Perimeter 2.0?", this);

        spawner = new Spawn(handler, hud);

        Random r = new Random();
        //handler.addObject(new Player(WIDTH / 2 + 32, HEIGHT /2 + 32, ID.Player2));

        if (gameState == STATE.Game) {

        } else {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    public synchronized void start() { //Starts thread
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() { //Stops thread
       try {
           thread.join(); //Kills thread
           running = false;
       } catch(Exception e){
           e.printStackTrace();
       }
    }

    public void run() { //Game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        timer = System.currentTimeMillis();
        frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                fpsFinal = frames;
                frames = 0;

                Random r = new Random();

                incR = r.nextInt(5) + 1;
                incG = r.nextInt(5) + 1;
                incB = r.nextInt(5) + 1;

                //System.out.println(red + " " +green + " " +blue );

            }
        }
        stop();
    }

    private void tick() {

        //color update
        if (red < 10 || red > 200) incR *= -1;
        if (green < 10 || green > 200) incG *= -1;
        if (blue < 10 || blue > 200) incB *= -1;

        red += incR;
        green += incG;
        blue += incB;

        if (!paused) {
                handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
            spawner.tick();

            if (hud.HEALTH <= 0) {
                hud.HEALTH = 100;
                gameState = STATE.End;
                handler.clearEnemies();
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
            menu.tick();
        }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3); //Recommended buffer to lower FPS
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        /*if (red <= 0 || red >= 255) incR *= -1; 
        if (green <= 0 || green >= 255) incG *= -1;
        if (blue <= 0 || blue >= 255) incB *= -1;

        red += incR;
        green += incG;
        blue += incB;*/

        g.setColor(new Color(Math.abs(red), Math.abs(green),Math.abs( blue))); //Stops screen flicker
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setFont(gameFont);
        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString("FPS: " + fpsFinal, 10, 20);

        handler.render(g);

        if (paused) {
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }

        if (gameState == STATE.Game) {
            hud.render(g); //HUD placed under to be placed above environment.
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) { //Keeps object from going out of bounds.
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String args[]) {
        new Game(); //Needed to instantiate game object.
    }

    public long getTimer() {
        return timer;
    }

    public int getFrames() {
        return frames;
    }

    public HUD getHUD() { return hud; }
}
