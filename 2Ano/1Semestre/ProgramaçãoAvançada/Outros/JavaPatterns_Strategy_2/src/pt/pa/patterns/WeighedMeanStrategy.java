package pt.pa.patterns;

import java.util.Map;

public class WeighedMeanStrategy implements MeanStrategy {
    @Override
    public double calculate(StudentRecord studentRecord) {
        int count = 0;
        double sum = 0;
        for (Map.Entry<Course, Integer> tuple : studentRecord) {
            sum += tuple.getValue() * tuple.getKey().getEcts();
            count += tuple.getKey().getEcts();
        }
        return sum / count;
    }

    @Override
    public String record2text(StudentRecord studentRecord) {
        StringBuilder sb = new StringBuilder(String.format("Record of: %s | %s \n",
                studentRecord.getStudentId(), studentRecord.getStudentName()));

        String header = String.format("%6s | %10s | %50s | %8s | %8s | %5s \n",
                "Year",
                "Course ID",
                "Name",
                "Semester",
                "ECTS",
                "Grade");

        sb.append(header);

        for (Map.Entry<Course, Integer> entry : studentRecord) {
            Course c = entry.getKey();
            int grade = entry.getValue();

            String line = String.format("%6d | %10s | %50s | %8s | %8s | %5s \n",
                    c.getYear(),
                    c.getId(),
                    c.getName(),
                    c.getSemester(),
                    c.getEcts(),
                    grade);

            sb.append(line);
        }

        return sb.toString();
    }
}
