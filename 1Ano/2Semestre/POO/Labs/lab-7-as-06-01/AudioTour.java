import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;

public class AudioTour extends Tour{

    String tag;
    String what;
    private static int audio_number = 99;

    protected final String WHERE = "Setubal";

    protected final String WHEN =  "Todos os dias, das 9:00 às 20:00";

    protected AudioTour(String tag, String what) {
        super("Audio Tour " + ++ audio_number);
        this.tag = tag;
        this.what = what;

    }

    @Override
    public String getWhat() {
        return what;
    }

    @Override
    public String getWhere() {
        return WHERE;
    }

    @Override
    public String getWhen() {
        return WHEN + "";
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
