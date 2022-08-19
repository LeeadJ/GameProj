package graphics;

public class Screen {
    //variables:
    private int width, height;
    public int[] pixels;

    //Constructor:
    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    /*
    -This function updates the pixels of the image on the screen.
    */
    public void render(){
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                pixels[x + (y * width)] = 0xff00ff; //adding hexadecimal (prefix: 0x)
            }
        }
    }
}
