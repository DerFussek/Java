import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class UserInterface extends Canvas implements Runnable {
    int screenWidth = 800;
    int screenHeight = 600;

    Thread displayThread;
    volatile boolean running = false;
    final double targetFps;
    double currentFps = 0;

    // Geometrie
    Geometrie geo;
    Rect rect;
    Circle circle;

    // Bewegung für das Rechteck
    int rectDx = 3; // Geschwindigkeit X
    int rectDy = 3; // Geschwindigkeit Y

    // Bewegung für den Kreis
    int circleDx = -2; // Geschwindigkeit X
    int circleDy = 2;  // Geschwindigkeit Y

    public UserInterface() {
        this(60);
    }

    public UserInterface(double fps) {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.targetFps = fps;

        geo = new Geometrie();
        rect = geo.createRect(50, 50, 100, 60, Color.CYAN);
        circle = geo.createCircle(400, 300, 50, Color.MAGENTA);

        rect.enableShadow(Color.GRAY);
        circle.enableShadow(new Color(200, 200, 200, 120)); // Hellerer Schatten für besseren Kontrast
    }

    public synchronized void start() {
        if (running) return;
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

    @Override
    public void run() {
        long targetTime = (long) (1000 / targetFps);
        long frameCount = 0;
        long timer = System.currentTimeMillis();

        createBufferStrategy(2);
        BufferStrategy bs = getBufferStrategy();

        while (running) {
            long startTime = System.nanoTime();

            update(); // Bewegung & Kollision aktualisieren
            render(bs); // Zeichnen mit Double Buffering

            long elapsedTime = (System.nanoTime() - startTime) / 1_000_000;
            long sleepTime = targetTime - elapsedTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            frameCount++;

            if (System.currentTimeMillis() - timer >= 1000) {
                currentFps = frameCount;
                frameCount = 0;
                timer += 1000;
            }
        }
    }

    private void render(BufferStrategy bs) {
        do {
            do {
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                draw(g);
                g.dispose();
            } while (bs.contentsRestored());

            bs.show();
        } while (bs.contentsLost());
    }

    private void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight); // Hintergrund schwarz

        for (Shape shape : geo.getShapes()) {
            shape.draw(g);
        }

        // FPS-Anzeige
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("FPS: " + (int) currentFps, 10, 20);
    }

    //  **Korrigierte `update()`-Methode: Rechteck & Kreis bewegen & Kollisionsabfrage**
    private void update() {
        // Bewegung Rechteck
        rect.x += rectDx;
        rect.y += rectDy;
        if (rect.x <= 0 || rect.x >= screenWidth - rect.getWidth() - 0) {
            rectDx = -rectDx;
        }
        if (rect.y <= 0 || rect.y >= screenHeight - rect.getHeight() - 0) {
            rectDy = -rectDy;
        }

        // Bewegung Kreis
        circle.x += circleDx;
        circle.y += circleDy;
        if(circle.x - circle.getRadius() <= 0 || circle.x + circle.getRadius() >= screenWidth) {
            circleDx = -circleDx;
        }
        if(circle.y - circle.getRadius() <= 0 || circle.y + circle.getRadius() >= screenHeight) {
            circleDy = -circleDy;
        }

        // **Kollisionsabfrage zwischen Rechteck & Kreis**
        if(checkCollision(rect, circle)) {
            // Richtungen umkehren, falls Kollision
            rectDx = -rectDx;
            rectDy = -rectDy;
            circleDx = -circleDx;
            circleDy = -circleDy;
        }
    }

    // ** Methode zur Kollisionsprüfung zwischen Rechteck & Kreis**
    private boolean checkCollision(Rect r, Circle c) {
        int closestX = clamp(c.x, r.x, r.x + r.getWidth());
        int closestY = clamp(c.y, r.y, r.y + r.getHeight());

        int distanceX = c.x - closestX;
        int distanceY = c.y - closestY;

        return (distanceX * distanceX + distanceY * distanceY) < (c.getRadius() * c.getRadius());
    }

    // Hilfsmethode: Begrenze Werte auf ein Minimum & Maximum
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kollision: Rechteck & Kreis");
        UserInterface canvas = new UserInterface(165);

        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        canvas.start();
    }
}
