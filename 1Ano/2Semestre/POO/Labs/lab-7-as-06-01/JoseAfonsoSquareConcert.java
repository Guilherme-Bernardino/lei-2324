import java.time.LocalDateTime;

public class JoseAfonsoSquareConcert implements Listable {

    private String who;
    private LocalDateTime schedule;

    private final String WHERE = "Setúbal";

    private final String WHAT = "Music at José Afonso Square";

    public JoseAfonsoSquareConcert(String who, LocalDateTime schedule) {
        this.who = who;
        this.schedule = schedule;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }


    @Override
    public String getWhat() {
        return WHAT;
    }

    @Override
    public String getWhere() {
        return WHERE;
    }

    @Override
    public String getWhen() {
        return schedule + "";
    }

    @Override
    public int getHowLong() {
        return STANDARD_HOW_LONG;
    }

    @Override
    public double getHowMuch() {
        return 0;
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Who: " + getWho() + " \n" );
        sb.append("What: " + getWhat() +  " \n");
        sb.append("Where: " + getWhere() +  " \n");
        sb.append("When: " + getWhen() +  " \n");
        sb.append("Duration: " + getHowLong() +  " \n");
        sb.append("Price: " + getHowMuch() +  " \n");

        return sb.toString();
    }
}
