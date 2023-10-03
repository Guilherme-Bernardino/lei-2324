public class MySquare extends MyRectangle {

    public MySquare(double width, String name) {
        super(width, width,name);
    }

    public MySquare(double x, double y, double width, String name) {
        super(x, y, width, width, name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
