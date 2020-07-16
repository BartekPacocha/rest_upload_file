package pl.bpacocha.rest.storage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Timestamp ConvertStringToTimestamp(String timestampString, String pattern) {

        if (pattern == null || pattern.isEmpty())
            pattern = "yyyy-MM-dd HH:mm:ss";

        Timestamp timestamp = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            Date parsedDate = dateFormat.parse(timestampString);
            timestamp = new Timestamp(parsedDate.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return timestamp;

    }



//    public static boolean CompareTimestamp(Timestamp from, Timestamp to, Timestamp target) {
//
//    }

}
