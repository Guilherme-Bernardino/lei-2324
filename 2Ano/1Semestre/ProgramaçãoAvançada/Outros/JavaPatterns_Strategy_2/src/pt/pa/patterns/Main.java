package pt.pa.patterns;

import java.io.FileNotFoundException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        StudentRecord record = new StudentRecord("2018", "João Meireles");
        try {
            record.importFromFile("record.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        /* Print record */
        //System.out.println(record);

        //percorrer todas as notas do aluno?
        //for (Map.Entry<Course, Integer> tuple : record) {
        //    System.out.println(tuple.getKey() + " - " + tuple.getValue());
        //}

        /* Compute average */
        System.out.println("## Arithmetic mean selection ##");
        record.setStrategy(new ArithmeticMeanStrategy());
        System.out.println(record);
        double average = record.computeAverage();
        System.out.println(String.format("Grade average: %.2f", average));

        System.out.println("## Weighed mean selection ##");
        record.setStrategy(new WeighedMeanStrategy());
        System.out.println(record);
        average = record.computeAverage();
        System.out.println(String.format("Grade average: %.2f", average));

        System.out.println("## First semester mean selection ##");
        record.setStrategy(new FirstSemesterMeanStrategy());
        System.out.println(record);
        average = record.computeAverage();
        System.out.println(String.format("Grade average: %.2f", average));
    }
}
