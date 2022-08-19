import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3; //scaling the game to x3 the game size, but with performance of 300 pixels wide.

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    public Game(){
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size); //Canvas method

        frame = new JFrame(); //creating a new instance of JFrame when creating a new Game.
    }
    //synchronized = prevents thread interferences and memory consistency errors
    public synchronized void start(){
        running = true; //game will loop without stopping.
        thread = new Thread(this, "Display");
        thread.start();
    }
    //making sure when game is closed the thread is properly shut down:
    public synchronized void stop(){
        running = false; //game loop will stop.
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace(); //exit out of the program.
        }
    }
    //run method will contain the game loop method, making sure we won't exit out of the game:
    public void run(){
        while(running){ //while running is true, game will work.
            update();
            render();
        }
    }

    /*
    -This function handles all the logic of the game and updates it in real time.
    -Restrict to 60 times per second.
    */
    public void  update(){

    }
    /*
    This function is going to display the images to the screen, as many times as the computer can per second.
    */
    public void render(){
        BufferStrategy bs = getBufferStrategy(); //retrieving the buffer-strategy of our Canvas obj 'Game'.
        //if the buffer-strategy hasn't been created yet:
        if(bs == null){
            createBufferStrategy(3); //3 buffers improves speed of frames displayed.
            return;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false); //making sure our window won't be resized. (causes graphical errors)
        game.frame.setTitle("Rain");
        game.frame.add(game); //adding a game component to our window.
        game.frame.pack(); //sets the size of the frame to the size of the component.
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the game program when 'x' is clicked in the top-right corner.
        game.frame.setLocationRelativeTo(null); //centers the frame into the screen.
        game.frame.setVisible(true); //makes sure the game window is visible and not hidden.

        game.start();

    }

}
