package pt.pa.adts;

public class MySquare extends MyRectangle{
    public MySquare(String name, double x, double y, double size) {
        super(name, x, y, size, size);
    }

    public MySquare(String name, double size) {
        this(name, 0,0, size);
    }

    @Override
    public boolean isPerfect() {
        double hypotenuse = getHypotenuse();
        return Math.abs(hypotenuse - Math.floor(hypotenuse))<=0.00000001;
    }

    @Override
    public String toString() {
        return "MySquare{" +
                "name='" + getName() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", size=" + getWidth() +
                ", area=" + String.format("%.2f", getArea()) +
                ", perimeter=" + String.format("%.2f", getPerimeter()) +
                ", hypotenuse=" + String.format("%.8f", getHypotenuse()) +
                ", isPerfect=" + (isPerfect() ? "YES" : "NO") +
                '}';
    }
}
