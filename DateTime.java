import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDateTime;

public class DateTime {

    private long advance;
    private long time;

    public DateTime(DateTime startDate, int setClockForwardInDays) {
        advance = ((setClockForwardInDays * 24L) * 60L) * 60000L;
        time = startDate.getTime() + advance;
    }

    public DateTime(int day, int month, int year) {
        // CHECK FOR VALID DATE
        if (day < LocalDateTime.now().getDayOfMonth() || month < LocalDateTime.now().getMonthValue()
                || year < LocalDateTime.now().getYear()) {
            throw new IllegalArgumentException(day + "/" + month + "/" + year + " is not a valid date.");
        }
        setDate(day, month, year);
    }

    public long getTime() {
        return time;
    }

    public String toString() {
        return getFormattedDate();
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long currentTime = getTime();
        Date gct = new Date(currentTime);

        return sdf.format(gct);
    }

    public String getEightDigitDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        long currentTime = getTime();
        Date gct = new Date(currentTime);

        return sdf.format(gct);
    }

    // returns difference in days
    public static int diffDays(DateTime startDate, DateTime endDate) {
        final long HOURS_IN_DAY = 24L;
        final int MINUTES_IN_HOUR = 60;
        final int SECONDS_IN_MINUTES = 60;
        final int MILLISECONDS_IN_SECOND = 1000;
        long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
        long gap = endDate.getTime() - startDate.getTime();
        double difference = (double) gap / (double) convertToDays;
        int round = (int) Math.round(difference);
        return round;
    }

    private void setDate(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0);

        java.util.Date date = calendar.getTime();

        time = date.getTime();
    }

}