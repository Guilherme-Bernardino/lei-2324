import java.time.LocalDateTime;

public class GuidedTour extends Tour{

    private String tag;
    private String what;
    private String where;
    private LocalDateTime schedule;

    private static int guided_number = 100;

    protected GuidedTour(String tag, String what, String where, LocalDateTime schedule) {
        super("Guided Tour " + guided_number++);
        this.tag = tag;
        this.what = what;
        this.where = where;
        this.schedule = schedule;
    }

    @Override
    public String getWhat() {
        return what;
    }

    @Override
    public String getWhere() {
        return where;
    }

    @Override
    public String getWhen() {
        return schedule + "";
    }

    @Override
    protected String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("ID: " + ID +  " \n");
        sb.append("Tag: " + getTag() +  " \n");
        sb.append("What: " + getWhat() +  " \n");
        sb.append("Where: " + getWhere() +  " \n");
        sb.append("When: " + getWhen() +  " \n");
        sb.append("Duration: " + getHowLong() +  " \n");
        sb.append("Price: " + getHowMuch() +  " \n");

        return sb.toString();
    }
}
