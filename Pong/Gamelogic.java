import java.awt.*;
import java.awt.image.BufferStrategy;

public class Gamelogic extends Canvas implements Runnable {

    //SCREEN-SETTINGS
    static final int screenWidth = 800;
    static final int screenHeight = 600;

    //CANVAS-SETTINGS
    private Thread displayThread;
    private volatile boolean running = false;
    private final double targetFPS;
    private double currentFps = 0;

    //SPIELOBJEKTE
    Player player01;
    Player player02;
    Ball ball;
    InputHandler inputHandler;

    //KONSTRUKTOREN
    public Gamelogic() {
        this(60);
    }

    public Gamelogic(double fps) {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.targetFPS = fps;

        inputHandler = new InputHandler();
        this.player01 = new Player(20, screenHeight/2, 10, 60);
        this.player02 = new Player(screenWidth-30, screenHeight/2, 10, 60);
        this.ball = new Ball(screenWidth/2, screenHeight/2, 20);

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this.inputHandler);
    }

    //THREAD-LOGIK
    public synchronized void start() {
        if(running) return;
        running = true;
        displayThread = new Thread(this);
        displayThread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            displayThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //RENDER-LOGIK
    private void render(BufferStrategy bs) {
        do {
            do {
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                draw(g);
                g.dispose();
            } while(bs.contentsRestored());
            bs.show();
        } while(bs.contentsLost());
    }

    //DRAW-LOGIK
    private void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.screenWidth, this.screenHeight);

        player01.draw(g, Color.white);
        player02.draw(g, Color.white);
        ball.draw(g, Color.white);

        g.setColor(Color.WHITE);
        g.drawString("Fps: " + this.currentFps, screenWidth/2-30, screenHeight-10);
    }


    //RUNNING-LOGIK
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long frameCount = 0;
        long timer = System.currentTimeMillis();

        createBufferStrategy(2);
        BufferStrategy bs = getBufferStrategy();

        while (running) {
            long now = System.nanoTime();

            // deltaTime in Sekunden (z. B. 0.016 bei 60 FPS)
            double deltaTime = (now - lastTime) / 1_000_000_000.0 * 60;
            lastTime = now;

            update(deltaTime);
            render(bs);

            // FPS begrenzen
            long sleepTime = (long)(1000 / targetFPS) - ((System.nanoTime() - now) / 1_000_000);
            if (sleepTime > 0) {
                try { Thread.sleep(sleepTime); } catch (InterruptedException ignored) {}
            }

            frameCount++;
            if (System.currentTimeMillis() - timer >= 1000) {
                currentFps = frameCount;
                frameCount = 0;
                timer += 1000;
            }
        }
    }

    private void update(double dt) {

        //PLAYER01
        if(inputHandler.isW_key()) player01.moveUp(dt);
        if(inputHandler.isS_key()) player01.moveDOWN(dt);

        //PLAYER02
        if(inputHandler.isUP_key()) player02.moveUp(dt);
        if(inputHandler.isDOWN_key()) player02.moveDOWN(dt);

        //BALL
        ball.move(dt);
        ball.checkCollision(player01, player02);
    }
}
