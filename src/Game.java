import graphics.Screen;
import input.Keyboard;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3; //scaling the game to x3 the game size, but with performance of 300 pixels wide.
    public static String title = "THE GAME";

    private Thread thread;
    private JFrame frame;
    private Keyboard key;

    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //main image object, helps handle all the pixel data.
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //getting the images Rasters(array of pixels) Data Buffer, and casting it to int.

    //Constructor:
    public Game(){
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size); //Canvas method

        screen = new Screen(width, height); //initializing the screen object;
        frame = new JFrame(); //creating a new instance of JFrame when creating a new Game.
        key = new Keyboard(); //initializing the keyboard object;
        addKeyListener(key);
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
    /*
    -Run method will contain the game loop method, making sure we won't exit out of the game.
    -Game implements Runnable so the run function will be called automatically when thread is initialized.
    */
    public void run(){
        long lastTime = System.nanoTime(); //time variable to measure system time (used to keep track of render)
        long timer = System.currentTimeMillis();
        final double nanoSec = 1000000000.0 / 60.0;
        double delta = 0; //measures the delta between start of run and before the while loop.
        int frames = 0; //going to count how many frames we have time to render every second.
        int updates = 0; //measures how many times the update method is called every second.
        while(running){ //while running=true, game will work.
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSec;
            lastTime = now;
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            render();
            frames++; //counts while loops.

            if(System.currentTimeMillis() - timer > 1000){ //If the difference is greater than 1 second.
                timer += 1000;
                frame.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /*
    -This function handles all the logic of the game and updates it in real time.
    -Restrict to 60 times per second.
    */
    int x=0; int y=0;
    public void  update(){
        key.update();
        //moving the map according to the keys being pressed.
        if(key.up) y--;
        if(key.down) y++;
        if(key.right) x++;
        if(key.left) x-- ;
    }
    /*
    -This function is going to display the images to the screen, as many times as the computer can per second.
    */

    public void render(){
        BufferStrategy bs = getBufferStrategy(); //retrieving the buffer-strategy of our Canvas obj 'Game'.
        //if the buffer-strategy hasn't been created yet:
        if(bs == null){
            createBufferStrategy(3); //3 buffers improves speed of frames displayed.
            return;
        }
        screen.clear();
        screen.render(x,y);

        for(int i=0; i<pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics(); //linking our drawn graphics to the buffer.
//        g.setColor(Color.BLACK); //setting the graphical color to black.
//        g.fillRect(0,0, getWidth(), getHeight()); //filling the screen with a rectangle. (0,0) - starting coordinates
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); //setting the image pixels pink.
        g.dispose(); //removing the graphics from the buffer after being used.
        bs.show(); //shows the next buffer that is being calculated.
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false); //making sure our window won't be resized. (causes graphical errors)
        game.frame.setTitle(Game.title);
        game.frame.add(game); //adding a game component to our window.
        game.frame.pack(); //sets the size of the frame to the size of the component.
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the game program when 'x' is clicked in the top-right corner.
        game.frame.setLocationRelativeTo(null); //centers the frame into the screen.
        game.frame.setVisible(true); //makes sure the game window is visible and not hidden.

        game.start();

    }

}
