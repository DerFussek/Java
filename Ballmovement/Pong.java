import javax.swing.*;

public class Pong extends JFrame {
    
    SpielFeld spielFeld;
    
    public Pong(int fps) {
        super("Pong");
        spielFeld = new SpielFeld(fps);
        this.add(this.spielFeld);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        this.spielFeld.start();
        
    }
}