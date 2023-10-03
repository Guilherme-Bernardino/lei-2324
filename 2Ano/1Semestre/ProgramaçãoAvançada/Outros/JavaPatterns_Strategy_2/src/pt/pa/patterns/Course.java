package pt.pa.patterns;

public class Course {
    private String id;
    private int year;
    private String name;
    private String semester;
    private int ects;

    public Course(String id, int year, String name, String semester, int ects) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.semester = semester;
        this.ects = ects;
    }

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public int getEcts() {
        return ects;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", semester='" + semester + '\'' +
                ", ects=" + ects +
                '}';
    }
}
