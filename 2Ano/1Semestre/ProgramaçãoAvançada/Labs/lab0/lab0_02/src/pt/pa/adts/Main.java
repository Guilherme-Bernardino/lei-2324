package pt.pa.adts;

public class Main {
    public static void main(String[] args) {
        MyRectangle rectangle = new MyRectangle("Rect X ", 10.0, 20.0, 3.0, 4.0);
        System.out.println(rectangle);

        MyRectangle otherRectangle = new MyRectangle("Rect Y ", 3.4, 8.3);
        System.out.println(otherRectangle);

        MySquare square = new MySquare("Q-Lux", 5.0, 6.0, 10.0);
        System.out.println(square);

        MySquare otherSquare = new MySquare("Q-Dark", 7.071);
        System.out.println(otherSquare);

        MySquare perfectSquare = new MySquare("Q-Perfect", 7.0710678118654752440084436210485);
        System.out.println(perfectSquare);
    }
}
