package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
* This Class is in charge of managing any SpriteSheet and caching them to memory.
*/
public class SpriteSheet {

    private String path;
    private final int SIZE;
    private int[] pixels;//array representing the pixels of the SpriteSheet.

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    /*This function loads the physical image file to Java. */
    public void load(){
        try{
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            //breaking down the image into pixels and placing into the pixel array:
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
