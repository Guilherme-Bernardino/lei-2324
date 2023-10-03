package pt.pa.patterns;

public interface MeanStrategy {

    /**
     *
     * @param studentRecord
     * @return
     */
    double calculate(StudentRecord studentRecord);

    String record2text(StudentRecord studentRecord);
}
