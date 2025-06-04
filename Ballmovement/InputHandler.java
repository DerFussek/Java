import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private boolean W_key = false;
    private boolean S_key = false;
    private boolean UP_key = false;
    private boolean DOWN_key = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            this.W_key = true;
        }
        if (key == KeyEvent.VK_S) {
            this.S_key = true;
        }
        if (key == KeyEvent.VK_UP) {
            this.UP_key = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            this.DOWN_key = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            this.W_key = false;
        }
        if (key == KeyEvent.VK_S) {
            this.S_key = false;
        }
        if (key == KeyEvent.VK_UP) {
            this.UP_key = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            this.DOWN_key = false;
        }
    }

    //GET-METHODS
    public boolean isW_key() {
        return this.W_key;
    }

    public boolean isS_key() {
        return this.S_key;
    }

    public boolean isUP_key() {
        return this.UP_key;
    }

    public boolean isDOWN_key() {
        return this.DOWN_key;
    }

}