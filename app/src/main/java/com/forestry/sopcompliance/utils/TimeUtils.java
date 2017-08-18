package com.forestry.sopcompliance.utils;

import android.app.Activity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fimansya on 6/7/2017.
 */

public class TimeUtils {

    public static void getTimeDiff() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
        Date Date1 = format.parse("08:00:12 pm");
        Date Date2 = format.parse("05:30:12 pm");
        long mills = Date1.getTime() - Date2.getTime();
        int Hours = (int) (mills/(1000 * 60 * 60));
        int Mins = (int) (mills % (1000*60*60));
    }


    public static void timeDiff(Activity activity) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-ddzHH:mm:ss");
        Date dateOne = df.parse("2017-05-15T00:00:00");
        Date dateTwo = df.parse("2017-05-13T00:00:00");
        long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
        Toast.makeText(activity, String.valueOf(timeDiff), Toast.LENGTH_LONG).show();
    }

    public static String hotspotTimeDiff(String hotspot){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = df.format(Calendar.getInstance().getTime());

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = df.parse(hotspot);
            d2 = df.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        String timeDiff = String.valueOf(diffDays);

        return timeDiff;
    }


}
