import java.time.LocalDateTime;
import java.util.Date;

public class MonitoredData {
    LocalDateTime start_time;
    LocalDateTime end_time;
    String activity_label;

    public MonitoredData(LocalDateTime start_time, LocalDateTime end_time, String activity)
    {
        this.start_time=start_time;
        this.end_time=end_time;
        this.activity_label=activity;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public String getActivity_label() {
        return activity_label;
    }

    public int getDistinctDay(int month, int day)
    {
        return month*31+day;
    }

    public String toString()
    {
        return "Activitate: "+this.getActivity_label()+ "  Ora de inceput: "+this.getStart_time()+"  Ora de final: "+this.getEnd_time();
    }
}
