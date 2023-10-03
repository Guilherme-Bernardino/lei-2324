package pt.pa.patterns;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
This classe was made "iterable", but not a necessary requirement
for this problem. It simply allows us to iterate over all the courses/grades
without exposing the map (attribute) reference to the outside.
 */
public class StudentRecord
        implements Iterable<Map.Entry<Course, Integer>> {

    private String studentId;
    private String studentName;

    private Map<Course, Integer> record;

    private MeanStrategy strategy = new ArithmeticMeanStrategy();

    public StudentRecord(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        this.record = new HashMap<>();
    }

    public StudentRecord(String studentId, String studentName, MeanStrategy strategy) {
        this(studentId, studentName);
        this.strategy = strategy;
    }

    public void setStrategy(MeanStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double computeAverage() {
        return strategy.calculate(this);
    }

    @Override
    public String toString() {
        return strategy.record2text(this);
    }

    public void importFromFile(String filename) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            /* discard header*/
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int year = Integer.valueOf( values[0] );
                String name = values[1].trim();
                String id = values[2].trim();
                String semester = values[3].trim();
                int ects = Integer.valueOf( values[4] );

                int grade = Integer.valueOf( values[5] );

                Course c = new Course(id, year, name, semester, ects);

                record.put(c, grade);

            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Iterator<Map.Entry<Course, Integer>> iterator() {
        /* Simply return the existing iterator; no need
        * to create one ourselves. */
        return record.entrySet().iterator();
    }
}
