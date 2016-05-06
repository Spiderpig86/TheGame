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

    //Constructor
    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Perimeter 2.0?", this);

        hud = new HUD();

        Random r = new Random();

        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT /2 - 32, ID.Player, handler));
        //handler.addObject(new Player(WIDTH / 2 + 32, HEIGHT /2 + 32, ID.Player2));

        for (int i = 0; i < 2; i++) {
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy));
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
        long timer = System.currentTimeMillis();
        int frames = 0;
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
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
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

        handler.render(g);
        hud.render(g); //HUD placed under to be placed above environment.

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) { //Keeps object from going out of bounds.
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
}
