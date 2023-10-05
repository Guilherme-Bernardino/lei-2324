import java.time.LocalDate;

public class Task implements Orderable, Comparable<Task>{

    private String description;
    private int priority;
    private LocalDate date;

    public Task(String description, int priority, LocalDate date) {
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    public String getHeader(){
        return "\n |--------------------------------------------------------|"
                + "\n | Priority | Task name                      | Due date   |"
                + "\n |----------|--------------------------------|------------|";
    }

    @Override
    public String toString(){
        return String.format("\n |    %02d    | %-30s | %10s |"
              , priority, description, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Task))
            return false;
        Task x = (Task)obj;
        if (this.date.equals(x.getDate()) && this.description.equalsIgnoreCase(x.getDescription()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result= 20;
        if (date != null)
            result = 23 + result + date.hashCode();
        if (description != null)
            result = 23 + result + description.hashCode();
        return result;
    }

    @Override
    public int compareTo(Task o) {
        return this.priority == o.getPriority() ? 0 :
                this.priority < o.getPriority() ? -1 : 1;
    }
}
