package com.ale;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateUtil {

    static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .toFormatter();
    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    static DecimalFormat df = new DecimalFormat("#0.00");
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatteryyyyMM = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat formatter3 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat formatter5 = new SimpleDateFormat("yyyyMMdd");
    /**
     * yyyy-MM-dd 或者 yyyy-M-dd
     **/
    private static String DATE_REGEX = "^([1-9]\\d{3}-)(([0]{0,1}[1-9]-)|([1][0-2]-))(([0-3]{0,1}[1-9]))$";

    private DateUtil() {
    }

    public static final String dayAfterDay(Date day, Integer dayStep) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(day);
        instance.add(Calendar.DAY_OF_YEAR, dayStep);
        return formatter.format(instance.getTime());
    }

    public static final String dayAfterDayYMD(Date day, Integer dayStep) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(day);
        instance.add(Calendar.DAY_OF_YEAR, dayStep);
        return formatter2.format(instance.getTime());
    }

    public static final Date getDayAfterDay(Date day, Integer dayStep) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(day);
        instance.add(Calendar.DAY_OF_YEAR, dayStep);
        return instance.getTime();
    }

    public static final Date getDayBeforeDay(Date day, Integer dayStep) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(day);
        instance.add(Calendar.DAY_OF_YEAR, -dayStep);
        return instance.getTime();
    }

    public static Date getDate() {
        return new Date();
    }

    public static Date getDate2() {
        try {
            String format = formatter2.format(new Date());
            return formatter2.parse(format);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static String getDate3(Date date) {
        String format = formatter2.format(date);
        return format;

    }

    public static final String currentTime() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        return formatter.format(instance.getTime());
    }

    public static final String getStr() {
        return formatter1.format(new Date());
    }

    public static final Date getDate(String str) throws ParseException {
        return formatter.parse(str);
    }

    public static String getDateStr(Date date) {
        if (null == date)
            return "";
        return formatter.format(date);
    }

    public static String getDateYMDStr(Date date) {
        if (null == date)
            return "";
        return formatter2.format(date);
    }

    public static Date getDateYMD(String str) {
        try {
            return formatter2.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateYM(String str) {
        try {
            return formatteryyyyMM.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateYMD() {
        LocalDate today = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = today.atStartOfDay().atZone(zoneId).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static Date getThreadSafetyDateYMD(String str) {
        LocalDate localDate = LocalDate.parse(str, DATE_TIME_FORMATTER);
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int afterDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal1.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
        cal1.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
        cal1.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
        cal1.set(Calendar.MILLISECOND, cal2.get(Calendar.MILLISECOND));

        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (day1 == day2 && year1 == year2) {
            return 0;
        }
        if (cal1.after(cal2)) {
            return 0;
        }
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else { // 不是闰年
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else { // 不同年
            return day2 - day1;
        }
    }

    public static String getDate(Date date) {
        return format.format(date);
    }

    public static Date getLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        return calendar.getTime();
    }

    public static Date nextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        return calendar.getTime();
    }

    public static List<String> getBetweenDates(String start, String end) {

        List<String> result = new ArrayList<String>();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date start_date = sdf.parse(start);

            Date end_date = sdf.parse(end);

            Calendar tempStart = Calendar.getInstance();

            tempStart.setTime(start_date);

            Calendar tempEnd = Calendar.getInstance();

            tempEnd.setTime(end_date);

            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {

                result.add(sdf.format(tempStart.getTime()));

                tempStart.add(Calendar.DAY_OF_YEAR, 1);

            }

        } catch (ParseException e) {

            e.printStackTrace();

        }

        Collections.reverse(result);

        return result;

    }

    public static void main(String[] arges) {
        try {
            String date = getDayString("2020-05-13 11:00:00", 0);
            System.out.println(date);
        } catch (Exception e) {

        }

    }

    public static String format(Integer value, BigDecimal num) {
        return value == null ? "--" : doFormat(new BigDecimal(value).divide(num, 4, BigDecimal.ROUND_DOWN).doubleValue() * 100);
    }

    static String 获得商(int 分子, Integer 分母) {
        if (分母 == null || 分母 <= 0) {
            return ("0");
        } else {
            float 商 = 分子 * 100 / 分母;
            return (df.format(商) + "%");
        }
    }

    static String doFormat(double value) {
        String[] valueStrs = String.valueOf(value).split("\\.");
        return valueStrs[0] + "." + valueStrs[1].substring(0, Math.min(valueStrs[1].length(), 2)) + "%";
    }

    /**
     * 返回指定时间
     * getDay
     *
     * @param time 时间
     * @param flag 0 返回yyyy-MM-dd 00:00:00日期<br>
     *             1 返回yyyy-MM-dd 23:59:59日期
     * @return
     * @throws ParseException String
     */
    public static Date getDayDate(Date time, int flag) {
        Calendar cal = Calendar.getInstance();
        if (null == time) return null;
        try {
            cal.setTime(time);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            cal.add(Calendar.DATE, 0);
            //时分秒（毫秒数）
            long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
            //凌晨00:00:00
            cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
            if (flag == 0) {
                //设置毫秒数，避免产生进位
                cal.set(Calendar.MILLISECOND, 0);
                return cal.getTime();
            } else if (flag == 1) {
                //凌晨23:59:59
                cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
            }
            //设置毫秒数，避免产生进位
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getDateHhMmStr(Date date) {
        if (null == date)
            return "";
        return formatter3.format(date);
    }

    public static Date getHhMmDate(String date) {
        try {
            if (null == date)
                return null;
            return formatter3.parse(date);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 如果时间在8:21:00---21:33:00 之间  flag为true   否则为false
     *
     * @param nowTime   当前时间
     * @param beginTime 开始时间
     * @param endTime   结果时间
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String day1 = formatter2.format(calendar.getTime());
        return day1;
    }

    public static String getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        String day2 = formatter2.format(calendar.getTime());
        return day2;
    }

    public static final Date getDateHHMM(String str) throws ParseException {
        return formatter4.parse(str);
    }

    public static String getDateHHMMStr(Date date) {
        if (null == date)
            return "";
        return formatter4.format(date);
    }

    /**
     * 时间日期转换
     *
     * @param strDate 字符串yyyyMMddHHmmss
     * @return 字符串yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDateLong(String strDate) {
        Date date = new Date();
        try {
            date = formatter1.parse(strDate);//先按照原格式转换为时间
            String str = formatter1.format(date);//再将时间转换为对应格式字符串
            return getDate(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToDateFormat(String date) {
        try {
            formatter5.setLenient(false);
            Date newDate = formatter5.parse(date);
            return newDate;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 返回指定时间
     * getDayString
     *
     * @param strDate 时间
     * @param flag    0 返回yyyy-MM-dd 00:00:00日期<br>
     *                1 返回yyyy-MM-dd 23:59:59日期
     * @return
     * @throws ParseException String
     */
    public static String getDayString(String strDate, int flag) {
        if (StringUtils.isEmpty(strDate)) return null;
        Calendar cal = Calendar.getInstance();
        Date time;
        try {
            time = getDate(strDate);
            cal.setTime(time);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            cal.add(Calendar.DATE, 0);
            //设置毫秒数，避免产生进位
            cal.set(Calendar.MILLISECOND, 0);
            //时分秒（毫秒数）
            long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
            //凌晨00:00:00
            cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
            if (flag == 0) {
                return getDateStr(cal.getTime());
            } else if (flag == 1) {
                //凌晨23:59:59
                cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
            }
            return getDateStr(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 根据出生日期 计算年龄
     *
     * @param birthDay
     * @return
     * @throws ParseException
     */
    public static int getAgeByBirth(Date birthDay) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            return 0;
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 根据出生日期 计算年龄
     *
     * @param birthDay
     * @return
     * @throws ParseException
     */
    public static int getAgeByBirthStr(String birthDay) {
        if (StringUtils.isEmpty(birthDay)) {
            return 0;
        }
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            return 0;
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(getDateYMD(birthDay));
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 获取GMT8时间
     *
     * @return 将当前时间转换为GMT8时区后的Date
     */
    public static String getGMT8Time() {
        String gmt8 = formatter1.format(new Date());
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.CHINESE);
            Calendar day = Calendar.getInstance();
            day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
            day.set(Calendar.DATE, cal.get(Calendar.DATE));
            day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
            day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
            day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
            gmt8 = formatter1.format(day.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            gmt8 = null;
        }
        return gmt8;
    }


    /***
     * @desc 校验日期的格式，yyyy-MM-dd，无法校验dd的完整性，
     * 就是可能出现 2020-2-32，2020-1-33这样的天数，可以通过设置日期的严禁性来转成日期，若报错则日期不正确
     * @author fanzhen
     * @date 2020-05-28 18:50
     * @param
     * @param datestr：日期，格式：yyyy-MM-dd
     * @return boolean
     */
    public static boolean validDateEffecitive(String datestr) {
        boolean matches = Pattern.matches(DATE_REGEX, datestr);
        if (!matches) {
            return matches;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //设置日期格式转的严谨性
        sdf.setLenient(false);
        try {
            sdf.parse(datestr);
        } catch (ParseException e) {
            return false;
        }
        return matches;
    }


    public static Boolean checkDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return Boolean.FALSE;
        }
        try {
            String[] dates = date.split("-");
            String yearStr = dates[0];
            String monthStr = dates[1];
            String dayStr = dates[2];
            if (StringUtils.isEmpty(yearStr) || StringUtils.isEmpty(monthStr) || StringUtils.isEmpty(dayStr)) {
                return Boolean.FALSE;
            }
            Integer year = Integer.parseInt(yearStr);
            Integer month = Integer.parseInt(monthStr);
            Integer day = Integer.parseInt(dayStr);
            if (year == 0 || year < 1920) {
                return Boolean.FALSE;
            }

            if (month == 0 || month > 12) {
                return Boolean.FALSE;
            }
            if (day == 0 || day > 31) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * 日月年
     *
     * @param date
     * @return
     */
    public static Boolean thcashCheckDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return Boolean.FALSE;
        }
        try {
            String[] dates = date.split("-");
            String yearStr = dates[2];
            String monthStr = dates[1];
            String dayStr = dates[0];
            if (StringUtils.isEmpty(yearStr) || StringUtils.isEmpty(monthStr) || StringUtils.isEmpty(dayStr)) {
                return Boolean.FALSE;
            }
            Integer year = Integer.parseInt(yearStr);
            Integer month = Integer.parseInt(monthStr);
            Integer day = Integer.parseInt(dayStr);
            if (year == 0 || year < 1920) {
                return Boolean.FALSE;
            }

            if (month == 0 || month > 12) {
                return Boolean.FALSE;
            }
            if (day == 0 || day > 31) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * 获取GMT8时间
     *
     * @return 将当前时间转换为GMT8时区后的Date
     */
    public static Date getGMT8TimeStamp() {
        Date gmt8 = new Date();
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.CHINESE);
            Calendar day = Calendar.getInstance();
            day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
            day.set(Calendar.DATE, cal.get(Calendar.DATE));
            day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
            day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
            day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
            gmt8 = day.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gmt8;
    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        //cal.add(Calendar.HOUR_OF_DAY,8);
        return cal.getTime();
    }

    //获取时间加一天
    public static Date DateAdd(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();

    }

    public static Date changeDate(Date date) throws ParseException {
        final SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateformat1.format(date);
        return dateformat1.parse(format);
    }
}
