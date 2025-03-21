import javax.swing.*;

public class Pong extends JFrame {
    private Gamelogic gamelogic;

    public Pong(String name, double fps) {
        super(name);
        gamelogic = new Gamelogic(fps);
        this.add(gamelogic);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        gamelogic.start();
    }
}
