import java.awt.*;
import java.awt.image.BufferStrategy;

public class SpielFeld extends Canvas implements Runnable {
    
    //SCREEN-SETTINGS
    public static final int screenWidth = 800;
    public static final int screenHeight = 600;
    
    //PERFORMANCE-VARIABLES
    private Thread m_Thread;
    private volatile boolean running = false;
    private int targetFPS = 60;
    private double currentFPS = 0;
    
    //SPIELELEMENTE
    Ball ball;
    Player p1;
    Player p2;
    InputHandler inputHandler;
    
    //KONSTRUKTOR
    public SpielFeld() {
        this(60);
    }
    
    public SpielFeld(int fps) {
        setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.targetFPS = fps;
        
        //Elemente Instanzieren
        ball = new Ball(400, 300, 10);
        p1 = new Player(10, 0, 10, 75);
        p2 = new Player(this.screenWidth - 20, 0, 10, 75);
        inputHandler = new InputHandler();
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this.inputHandler);
    }
    
    
    //THREAD-LOGIK
    public synchronized void start() {
        if(this.running) return;
        this.running = true;
        
        this.m_Thread = new Thread(this);
        this.m_Thread.start();
    }
    
    public synchronized void stop() {
        this.running = false;
        
        try {
            this.m_Thread.join();
        } catch(InterruptedException e) {
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
    
    private void draw(Graphics2D g) {
        
        //Hintergrund Zeichnen
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.screenWidth, this.screenHeight);
        
        //Elemente Zeichnen
        this.ball.draw(g);
        this.p1.draw(g);
        this.p2.draw(g);
        
    }
    
    //UPDATE-LOGIK
    private void update(double d_time) {
        this.ball.move(d_time);
        
        this.ball.WallCollision();
        
        //Player 01
        if(inputHandler.isW_key()) p1.moveUp(d_time);
        if(inputHandler.isS_key()) p1.moveDown(d_time);
        this.p1.WallCollision();
        
        //Player 02
        if(inputHandler.isUP_key()) p2.moveUp(d_time);
        if(inputHandler.isDOWN_key()) p2.moveDown(d_time);
        this.p2.WallCollision();
        
        if(this.ball.intersects(this.p1) || this.ball.intersects(this.p2)) {
            this.ball.flipDirection(1);
            this.ball.addSpeed(1.02);
            this.p1.addSpeed(1.02);
            this.p2.addSpeed(1.02);
        }
    }
    
    
    //GAME-LOOP
    @Override
    public void run() {
        final long frameDuration = 1_000_000_000L / this.targetFPS;
        long nextFrameStart = System.nanoTime();
        long frameCount = 0;
        long timer = System.currentTimeMillis();
    
        createBufferStrategy(2);
        BufferStrategy bs = getBufferStrategy();
    
        while (this.running) {
            long now = System.nanoTime();
    
            if (now < nextFrameStart) {
                // Noch Zeit bis zum nächsten Frame: schlafen + busy-wait
                long sleepTime = nextFrameStart - now;
                if (sleepTime > 2_000_000) { // Nur schlafen, wenn mehr als 2ms
                    try {
                        Thread.sleep(sleepTime / 1_000_000, (int)(sleepTime % 1_000_000));
                    } catch (InterruptedException ignored) {}
                }
    
                // Feintuning
                while (System.nanoTime() < nextFrameStart) {}
            }
    
            // DeltaTime berechnen
            long actualStart = System.nanoTime();
            double deltaTime = (actualStart - now) / 1_000_000_000.0;
    
            // Logik
            update(deltaTime);
            render(bs);
    
            nextFrameStart += frameDuration;
    
            // FPS zählen
            frameCount++;
            if(System.currentTimeMillis() - timer >= 1000) {
                this.currentFPS = frameCount;
                frameCount = 0;
                timer += 1000;
                System.out.println("d_time = " + deltaTime + ", Fps = " + currentFPS);
            }
        }
    }


};
