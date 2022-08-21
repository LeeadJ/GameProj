package graphics;

import java.util.Random;

public class Screen {
    //variables:
    private int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; //array of 4096 tiles that keeps track of the data in each one.

    private Random random = new Random();

    //Constructor:
    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        //randomly generating colors to put in the tiles array from black to white:
        for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++){
            tiles[i] = random.nextInt(0xffffff);
            tiles[0] = 0; //setting tiles[0] black for testing purposes.
        }


    }
    /*
    -This function sets the pixel array to 0 (black), creating the effect of the screen being cleared.
    */
    public void clear(){
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }

    /*
    -This function updates the pixels of the image on the screen.
    */
    public void render(int xOffset, int yOffset){
        for(int y = 0; y < height; y++){
            int yy = y + yOffset;
//            if(yy<0 || yy>=height) break;
            for(int x = 0; x < width; x++){
                int xx = x + xOffset;
//                if(xx<0 || xx>width) break;
                int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE; //equation for finding the correct tile for the screen(using bitwise).
                pixels[x + (y * width)] = tiles[tileIndex];
            }
        }
    }
}
