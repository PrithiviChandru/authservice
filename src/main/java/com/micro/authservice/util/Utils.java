package com.micro.authservice.util;

import java.time.DateTimeException;
import java.time.ZoneId;

public class Utils {
    public static void validTimeZone(String timeZone) {
        String tz = timeZone != null ? timeZone : "UTC";

        try {
            ZoneId.of(tz);
        } catch (DateTimeException e) {
            throw new RuntimeException("Invalid timezone provided");
        }
    }
}
