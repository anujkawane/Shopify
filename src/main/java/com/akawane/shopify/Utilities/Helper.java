package com.akawane.shopify.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

    private static DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("MMM-dd-yyy HH:mm:ss");
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

}

