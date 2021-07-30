package com.project.real_estate_1.controller.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetNow {
    public static String getTime(){
        String value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return value;
    }
}
