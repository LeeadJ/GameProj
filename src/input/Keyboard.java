package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {


    private boolean[] keys = new boolean[250]; //array representing the characters. Each one has 2 states (pressed and not)
    public boolean up, down, left, right;

    /*
    -This function will check every cycle if a particular key is pressed or not.
    */
    public void update(){
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

    }
    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true; //sets the id of the key the got pressed to true.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false; //sets the id of the key the got released to false.
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


}
