package pt.pa.patterns;

import java.util.Map;

/**
 * Exemplo adicional.
 */
public class FirstSemesterMeanStrategy implements MeanStrategy {
    @Override
    public double calculate(StudentRecord studentRecord) {
        int count = 0;
        double sum = 0;
        for (Map.Entry<Course, Integer> tuple : studentRecord) {
            if(tuple.getKey().getSemester().equals("1S")) {
                sum += tuple.getValue();
                count++;
            }
        }
        return sum / count;
    }

    @Override
    public String record2text(StudentRecord studentRecord) {
        StringBuilder sb = new StringBuilder(String.format("Record of: %s | %s \n",
                studentRecord.getStudentId(), studentRecord.getStudentName()));

        String header = String.format("%6s | %10s | %50s | %8s | %5s \n",
                "Year",
                "Course ID",
                "Name",
                "Semester",
                "Grade");

        sb.append(header);

        for (Map.Entry<Course, Integer> entry : studentRecord) {
            Course c = entry.getKey();
            int grade = entry.getValue();

            if(!entry.getKey().getSemester().equals("1S")) continue;

            String line = String.format("%6d | %10s | %50s | %8s | %5s \n",
                    c.getYear(),
                    c.getId(),
                    c.getName(),
                    c.getSemester(),
                    grade);

            sb.append(line);
        }

        return sb.toString();
    }
}
