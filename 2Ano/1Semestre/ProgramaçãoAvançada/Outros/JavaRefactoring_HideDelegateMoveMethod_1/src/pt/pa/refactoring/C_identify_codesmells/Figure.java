package pt.pa.refactoring.C_identify_codesmells;

import java.util.ArrayList;
import java.util.List;

public class Figure {

    private final List<Line> lines;

    public Figure() {
        lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void move(int dx, int dy) {
        for (Line l : lines) {

            // message chain detected previously

            l.move(dx, dy);
        }
    }

    public boolean contains(Point point) {
        for (Line l : lines) {

            // message chain detected previously

            if(l.contains(point))
               return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "<" + lines + '>';
    }
}
