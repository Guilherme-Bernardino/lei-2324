/**
 * StartApp
 */
public class StartApp {

    public static void main(String[] args) {
        MyRectangle rectangle = new MyRectangle(10.0, 20.0, 3.0, 4.0, "Rect X ");
        System.out.println(rectangle);
        MyRectangle otherRectangle = new MyRectangle(3.4, 8.3, "Rect Y ");
        System.out.println(otherRectangle);
        MySquare square = new MySquare(5.0, 6.0, 10.0, "Q-Lux");
        System.out.println(square);
        MySquare otherSquare = new MySquare(7.071, "Q-Dark");
        System.out.println(otherSquare);
        MySquare perfectSquare = new MySquare(7.0710678118654752440084436210485, "Q-Perfect");
        System.out.println(perfectSquare);
    }
}
