public class Game implements Runnable{
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3; //scaling the game to x3 the game size, but with performance of 300 pixels wide.

    private Thread thread;
    private boolean running = false;

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

        }
    }

}
