package graphics;

public class Screen {
    //variables:
    private int width, height;
    public int[] pixels;

    int time = 0;
    int counter=0;
    //Constructor:
    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }
    /*
    -This function sets the pixel array to 0 (black), creating the effect of the screen being cleared.
    */
    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i] = 0;
        }
    }

    /*
    -This function updates the pixels of the image on the screen.
    */
    public void render(){
        counter++;
        if(counter %100 ==0){
            time++;
        }
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                pixels[time + (time * width)] = 0xff00ff; //adding hexadecimal (prefix: 0x)
            }
        }
    }
}
