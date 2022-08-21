package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {


    private boolean[] keys = new boolean[120]; //array representing the characters. Each one has 2 states (pressed and not)
    public boolean up, down, left, right;

    /*
    -This function will check every cycle if a particular key is pressed or not.
    */
    public void update(){
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

        //printing which key was pressed for testing purposes.
        for(int i = 0; i < keys.length; i++){
            if(keys[i]){
                switch(i){
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        System.out.println("KEY: UP");
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        System.out.println("KEY: DOWN");
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        System.out.println("KEY: LEFT");
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        System.out.println("KEY: RIGHT");
                        break;
                }

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true; //sets the id of the key the got pressed to true.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false; //sets the id of the key the got released to false.
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


}
