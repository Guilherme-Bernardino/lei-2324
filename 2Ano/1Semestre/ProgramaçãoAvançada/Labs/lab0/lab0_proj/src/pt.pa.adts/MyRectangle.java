import java.lang.Math;

/**
 * MyRectangle
 */
public class MyRectangle implements Shapeable {

    private double x;
    private double y;
    private double width;
    private double height;
    private String name;
    
    /**
     * 
     * @param width
     * @param height
     * @param name
     */
    public MyRectangle(double width, double height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public MyRectangle(double x, double y, double width, double height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getArea() {
        return (width * height);
    }

    @Override
    public double getHypotenuse() {
        return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }

    @Override
    public double getPerimeter() {
        return (width * 2 + height * 2);
    }

    @Override
    public boolean isPerfect() {
        if (getHypotenuse() == (int)getHypotenuse()) {
            return true;
        } return false;
    }

    @Override
    public String toString() {
        return "MyRectangle [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", name=" + name + "]";
    }
   
}
