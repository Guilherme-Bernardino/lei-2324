package pt.pa.adts;
//nivel 4
public class PrintJob implements Comparable<PrintJob> {

    public enum PrintJobPriority {LOW, NORMAL, HIGH}

    private String name;
    private int numberPages;
    private PrintJobPriority priority;

    public PrintJob(String name, int numberPages, PrintJobPriority priority) {
        this.name = name;
        this.numberPages = numberPages;
        this.priority = priority;
    }

    @Override
    public int compareTo(PrintJob o) {
        return this.priority.ordinal() - o.priority.ordinal();
    }

    @Override
    public String toString() {
        return "PrintJob{" +
                "name='" + name + '\'' +
                ", numberPages=" + numberPages +
                ", priority=" + priority +
                '}';
    }
}
