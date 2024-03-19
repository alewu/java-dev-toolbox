package com.ale;

import sun.util.resources.LocaleData;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

/**
 * @author Administrator
 * @date 2023/9/12
 */
public class DateFormatUtil {
    public static void main(String[] args) {
        String date = "2023-09-12T10:10:17.000Z";

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        DateTimeFormatter dateTimeFormatter = builder.append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral('T')
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2).
                appendFraction(ChronoField.MILLI_OF_SECOND, 3, 3, true)
                .appendZoneId().toFormatter(Locale.ENGLISH);

        TemporalAccessor parse = dateTimeFormatter.parse(date);

        System.out.println(LocalDateTime.parse(date, dateTimeFormatter));

        System.out.println(LocalDateTime.from(parse));

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS' 'Z");

//        TemporalAccessor parse1 = dateTimeFormatter1.parse(date);
//        System.out.println(LocalDateTime.from(parse1));


        System.out.println( LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
