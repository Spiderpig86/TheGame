package GameMain;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Stan on 5/5/2016.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    long timer;
    int frames = 0;
    int fpsFinal = 0;

    //Constructor
    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Perimeter 2.0?", this);

        hud = new HUD(this);
        spawner = new Spawn(handler, hud);

        Random r = new Random();

        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT /2 - 32, ID.Player, Color.white, handler));
        //handler.addObject(new Player(WIDTH / 2 + 32, HEIGHT /2 + 32, ID.Player2));

        for (int i = 0; i < 2; i++) {
            //handler.addObject(new T1Square(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.T1Enemy, handler));
        }

        handler.addObject(new God((WIDTH / 2) - 48, -96, ID.God, handler));
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
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3); //Recommended buffer to lower FPS
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black); //Stops screen flicker
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.white);
        g.drawString("FPS: " + fpsFinal, 10, 20);

        handler.render(g);
        hud.render(g); //HUD placed under to be placed above environment.

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
}
