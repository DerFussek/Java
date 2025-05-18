import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class NwtProjekt extends Canvas implements Runnable {
    private final int screenWidth = 256;
    private final int screenHeight = 320;

    Thread mainThread;
    private volatile boolean running = false;
    private final double targetFps;
    private double currentFps = 0;

    public NwtProjekt(double fps) {
        this.targetFps = fps;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    //START - STOPP METHODEN
    public synchronized void start() {
        if(running) return;
        running = true;
        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //RUN METHODE und UPDATE REIHENFOLGE
    @Override
    public void run() {
        long targetTime = (long) (1000/this.targetFps);
        long frameCount = 0;
        long timer = System.currentTimeMillis();

        createBufferStrategy(2);
        BufferStrategy bs = getBufferStrategy();

        while(running) {
            long startTime = System.nanoTime();

            update();
            render(bs);

            long elapsedTime = (System.nanoTime() - startTime) / 1_000_000;
            long sleepTime = targetTime - elapsedTime;

            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            frameCount++;

            if(System.currentTimeMillis() - timer >= 1000) {
                currentFps = frameCount;
                frameCount = 0;
                timer += 1000;
                System.out.println(currentFps);
            }
        }
    }

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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.screenWidth, this.screenHeight);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 96));
        FontMetrics fm = g.getFontMetrics();

        g.setColor(new Color(41, 73, 20));
        g.fillOval(24, 36, 80, 80);
        g.setColor(Color.BLACK);
        g.fillOval(24 + 6, 36 + 6, 68, 68);

        g.setColor(new Color(165, 255, 0));
        g.fillOval(24, 36, 80, 80);
        g.setColor(Color.BLACK);
        g.fillOval(24 + 6, 36 + 6, 68, 68);


        g.setColor(new Color(50, 25, 0));
        g.fillOval(152, 36, 80, 80);
        g.setColor(Color.BLACK);
        g.fillOval(152 + 6, 36 + 6, 68, 68);

        g.setColor(new Color(255, 128, 0));
        g.fillOval(152, 36, 80, 80);
        g.setColor(Color.BLACK);
        g.fillOval(152 + 6, 36 + 6, 68, 68);

        g.setColor(new Color(165, 255, 0));

        LocalTime time = LocalTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String fTime = myFormatObj.format(time);

        int timeWidth = fm.stringWidth(fTime);
        int timeX = (this.screenWidth - timeWidth) /2;

        g.drawString(fTime, timeX, 240);
    }

    private void update() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tft-Screen");
        NwtProjekt nwt = new NwtProjekt(24);

        frame.add(nwt);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        nwt.start();
    }
}