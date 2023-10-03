package pt.pa.adts;

public class MyRectangle implements Shapeable{
    private String name;
    private double x, y, width, height;

    public MyRectangle(String name, double x, double y, double width, double height)
            throws InvalidSizeException, IllegalArgumentException{
        if (name==null)
            throw new IllegalArgumentException();

        this.name = name.toUpperCase();
        this.x = x;
        this.y = y;

        if (width<=0)
            throw new InvalidSizeException();
        this.width = width;

        if (height<=0)
            throw new InvalidSizeException();
        this.height = height;
    }

    public MyRectangle(String name, double width, double height) {
        this(name, 0,0, width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name==null)
            throw new IllegalArgumentException();
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
        if (width<=0)
            throw new InvalidSizeException();
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height<=0)
            throw new InvalidSizeException();
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width+height);
    }

    @Override
    public double getHypotenuse() {
        return Math.sqrt(Math.pow(width,2.0)+Math.pow(height,2.0));
    }

    @Override
    public boolean isPerfect() {
        double minSize = Math.min(width, height);
        double maxSize = Math.max(width, height);
        return minSize>=maxSize/2;
    }

    @Override
    public String toString() {
        return "MyRectangle{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", area=" + String.format("%.2f", getArea()) +
                ", perimeter=" + String.format("%.2f", getPerimeter()) +
                ", hypotenuse=" + String.format("%.8f", getHypotenuse()) +
                ", isPerfect=" + (isPerfect() ? "YES" : "NO") +
                '}';
    }

}
