package pt.pa.refactoring.C_identify_codesmells;

public class Main {
    public static void main(String[] args) {
        Figure figure = new Figure();

        // Thought: you could also only require the point values and relieve the
        // user of the burder to create other objects.
        figure.addLine(new Line(new Point(1,2),new Point(0,0)));
        figure.addLine(new Line(new Point(0,0),new Point(-1,-2)));
        figure.addLine(new Line(new Point(0,0),new Point(7,3)));

        System.out.println("Figure: "+ figure);

        figure.move(5, 2);

        System.out.println("Figure: "+ figure);
    }
}
