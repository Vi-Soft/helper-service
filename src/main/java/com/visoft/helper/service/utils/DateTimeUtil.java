package com.visoft.helper.service.utils;

import io.swagger.models.auth.In;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class DateTimeUtil {

    private static final String TIME_ZONE = "Asia/Jerusalem";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd-HH_mm";

    public static String asDateAndTime() {
        DateFormat timeFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
        timeFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return timeFormat.format(Date.from(Instant.now()));
    }
}
