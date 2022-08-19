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
}
