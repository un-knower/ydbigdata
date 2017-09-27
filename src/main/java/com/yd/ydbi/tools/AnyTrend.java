package com.yd.ydbi.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * 计算趋势工具类
 *
 * @author Administrator
 */
public class AnyTrend {
    private static final Calendar c = Calendar.getInstance();
    private static final FastDateFormat d = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";
    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天

    /**
     * 月日期转换
     *
     * @param paramsMap
     * @param i
     */
    public static void SeasonTransform(Map<String, String> paramsMap, int i) {
        if (i == 0) {
            String date_time = paramsMap.get("date_time");
            String today = formatDate(new Date());
            String fday = AnyTrend.firstDayOfSeason(date_time);
            //paramsMap.put("date_first", fday);
            //paramsMap.put("date_final", date_time);
            if (today.equals(date_time)) {
                paramsMap.put("date_first", fday);
                paramsMap.put("date_final", date_time);
            } else if (parseDate(fday).getTime() <= parseDate(today).getTime()) {
                paramsMap.put("date_first", fday);
                paramsMap.put("date_final", today);
            } else if (parseDate(fday).getTime() > parseDate(today).getTime()) {
                String[] strs = AnyTrend.lastSeasonBetweenDate(date_time);
                paramsMap.put("date_first", strs[0]);
                paramsMap.put("date_final", strs[1]);
            }
        } else {
            if (StringUtils.isNotEmpty(paramsMap.get("date_time"))) {
                String date_time = paramsMap.get("date_time");
                String[] strs = AnyTrend.lastSeasonBetweenDate(date_time);
                paramsMap.put("date_first", strs[0]);
                paramsMap.put("date_final", strs[1]);
                paramsMap.put("date_time", strs[0]);
            }
        }
    }

    /**
     * 月日期转换
     *
     * @param paramsMap
     * @param i
     */
    public static void MonTransform(Map<String, String> paramsMap, int i) {
        if (i == 0) {
            String date_time = paramsMap.get("date_time");
            String today = formatDate(new Date());
            String fday = AnyTrend.firstDayOfMonth(date_time);
            if (today.equals(date_time)) {
                paramsMap.put("date_first", fday);
                paramsMap.put("date_final", date_time);
            } else if (parseDate(fday).getTime() <= parseDate(today).getTime()) {
                paramsMap.put("date_first", fday);
                paramsMap.put("date_final", today);
            } else if (parseDate(fday).getTime() > parseDate(today).getTime()) {
                String[] strs = AnyTrend.lastMonthBetweenDate(date_time);
                paramsMap.put("date_first", strs[0]);
                paramsMap.put("date_final", strs[1]);
            }
        } else {
            if (StringUtils.isNotEmpty(paramsMap.get("date_time"))) {
                String date_time = paramsMap.get("date_time");
                String[] strs = AnyTrend.lastMonthBetweenDate(date_time);
                paramsMap.put("date_first", strs[0]);
                paramsMap.put("date_final", strs[1]);
                paramsMap.put("date_time", strs[0]);

            }
        }
    }

    /**
     * 周日起转换
     *
     * @param paramsMap
     * @param i
     */
    public static void WeekTransform(Map<String, String> paramsMap, int i) {
        if (i == 0) {
            String date_time = paramsMap.get("date_time");
            String today = formatDate(new Date());
            String fday = AnyTrend.firstDayOfWeek(date_time);
//            paramsMap.put("date_first", fday);
//            paramsMap.put("date_final", date_time);
            if (today.equals(date_time)) {
                paramsMap.put("date_first", fday);
                paramsMap.put("date_final", date_time);
            } else if (parseDate(fday).getTime() <= parseDate(today).getTime()) {
                paramsMap.put("date_first", fday);
                paramsMap.put("date_final", today);
            } else if (parseDate(fday).getTime() > parseDate(today).getTime()) {
                String[] strs = AnyTrend.lastWeekBetweenDate(date_time);
                paramsMap.put("date_first", strs[0]);
                paramsMap.put("date_final", strs[1]);
            }
        } else {
            String date_time = paramsMap.get("date_time");
            String[] strs = AnyTrend.lastWeekBetweenDate(date_time);
            paramsMap.put("date_first", strs[0]);
            paramsMap.put("date_final", strs[1]);
            paramsMap.put("date_time", strs[0]);
        }
    }

    /**
     * 日期减去一天
     *
     * @param dateStr
     * @return
     */
    public static String getDateDec1(String dateStr, Integer num) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            c.setTime(d.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.DATE, c.get(Calendar.DATE) - num);
        String t = d.format(c.getTime());
        return t;
    }

    /**
     * 日期减去一周
     *
     * @param dateStr
     * @return
     */
    public static String getDateDec1w(String dateStr, Integer num) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            c.setTime(d.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) - num);
        String t = d.format(c.getTime());
        return t;
    }

    /**
     * 日期减去一月
     *
     * @param dateStr
     * @return
     */
    public static String getDateDec1m(String dateStr, Integer num) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            c.setTime(d.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - num);
        String t = d.format(c.getTime());
        return t;
    }

    /**
     * 求百分数保留n位小数(乘了100)
     *
     * @param fz
     * @param fm
     * @param xiaoshu
     * @return
     */
    public static String getRate(String fz, String fm, int xiaoshu) {
        float fenzi = Float.parseFloat(fz);
        float fenmu = Float.parseFloat(fm);
        if (fenmu == 0) {
            return "0";
        }
        BigDecimal bg = new BigDecimal(fenzi / fenmu);
        bg = bg.multiply(new BigDecimal("100"));
        double result2 = bg.setScale(xiaoshu, BigDecimal.ROUND_HALF_UP).doubleValue();
        String rt = String.valueOf(result2);
        return rt;
    }

    /**
     * 分子除以分母
     *
     * @param fz
     * @param fm
     * @param xiaoshu
     * @return
     */
    public static String getRate2(String fz, String fm, int xiaoshu) {
        float fenzi = Float.parseFloat(fz);
        float fenmu = Float.parseFloat(fm);
        if (fenmu == 0) {
            return "0";
        }
        BigDecimal bg = new BigDecimal(fenzi / fenmu);
        double result2 = bg.setScale(xiaoshu, BigDecimal.ROUND_HALF_UP).doubleValue();
        String rt = String.valueOf(result2);
        return rt;
    }

    /**
     * 传入日期到当月1日的天数
     *
     * @param strDate
     * @return
     */
    public static int strDayOfDays(String strDate) {
        Calendar dayc1 = new GregorianCalendar();
        Date daystart = null;
        try {
            daystart = d.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayc1.setTime(daystart); // 得到的dayc1就是你需要的calendar了
        dayc1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        Date time = dayc1.getTime();
        int daysOfTwo = daysOfTwo(daystart, time);
        return daysOfTwo + 1;
    }

    /**
     * 两个日期相差天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwo(Date fDate, Date oDate) {
        c.setTime(fDate);
        int day1 = c.get(Calendar.DAY_OF_YEAR);
        c.setTime(oDate);
        int day2 = c.get(Calendar.DAY_OF_YEAR);
        return Math.abs(day2 - day1);
    }

    /**
     * 返回当月第一天
     *
     * @param
     * @return
     */
    public static String firstDayOfMonth(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            c.setTime(d.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String t = d.format(c.getTime());
        return t;
    }

    /**
     * 获取上个月的第一天和最后一天
     *
     * @param dateStr
     * @return
     */
    public static String[] lastMonthBetweenDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            c.setTime(d.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = d.format(c.getTime());
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String finalDay = d.format(c.getTime());
        return new String[]{firstDay, finalDay};
    }

    /**
     * 获取传入日期季度第一天
     *
     * @param dateStr
     * @return
     */
    public static String firstDayOfSeason(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = parseDate(dateStr);
        Date firstDateOfSeason = getFirstDateOfSeason(date);
        return d.format(firstDateOfSeason);
    }

    /**
     * 获取上个季度的第一天和最后一天
     *
     * @param dateStr
     * @return
     */
    public static String[] lastSeasonBetweenDate(String dateStr) {
        String s = firstDayOfSeason(dateStr);
        String dateDec1 = getDateDec1(s, 33);
        String firstDate = firstDayOfSeason(dateDec1);
        Date lastDateOfSeason = getLastDateOfSeason(parseDate(firstDate));
        String finalDate = d.format(lastDateOfSeason);
        return new String[]{firstDate, finalDate};
    }

    /**
     * 获取传入日期星期的第一天
     *
     * @param dateStr
     * @return
     */
    public static String firstDayOfWeek(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = parseDate(dateStr);
        Date mondayOfWeek = getMondayOfWeek(date);
        return d.format(mondayOfWeek);
    }

    /**
     * 获取传入日期上个星期的第一天和最后一天
     *
     * @param dateStr
     * @return
     */
    public static String[] lastWeekBetweenDate(String dateStr) {
        String s = firstDayOfWeek(dateStr);
        String dateDec1 = getDateDec1(s, 7);
        String firstDate = firstDayOfWeek(dateDec1);
        Date sundayOfWeek = getSundayOfWeek(parseDate(dateDec1));
        String finalDate = d.format(sundayOfWeek);
        return new String[]{firstDate, finalDate};
    }


    /**
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, null);
    }

    /**
     * parseDate
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDate(String strDate, String pattern) {
        Date date = null;
        try {
            if (pattern == null) {
                pattern = YYYYMMDD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, null);
    }

    /**
     * format date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = YYYYMMDD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 取得日期：年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得日期：年
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 取得日期：年
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int da = c.get(Calendar.DAY_OF_MONTH);
        return da;
    }

    /**
     * 取得当天日期是周几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.DAY_OF_WEEK);
        return week_of_year - 1;
    }

    /**
     * 取得一年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }

    /**
     * getWeekBeginAndEndDate
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getWeekBeginAndEndDate(Date date, String pattern) {
        Date monday = getMondayOfWeek(date);
        Date sunday = getSundayOfWeek(date);
        return formatDate(monday, pattern) + " - "
                + formatDate(sunday, pattern);
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    /**
     * 根据日期取得对应周周日日期
     *
     * @param date
     * @return
     */
    public static Date getSundayOfWeek(Date date) {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sunday.getTime();
    }

    /**
     * 取得月的剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfMonth(Date date) {
        int dayOfMonth = getDayOfMonth(date);
        int day = getPassDayOfMonth(date);
        return dayOfMonth - day;
    }

    /**
     * 取得月已经过的天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfSeason(Date date) {
        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    /**
     * 取得季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfSeason(Date date) {
        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    /**
     * 取得季度天数
     *
     * @param date
     * @return
     */
    public static int getDayOfSeason(Date date) {
        int day = 0;
        Date[] seasonDates = getSeasonDate(date);
        for (Date date2 : seasonDates) {
            day += getDayOfMonth(date2);
        }
        return day;
    }

    /**
     * 取得季度剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfSeason(Date date) {
        return getDayOfSeason(date) - getPassDayOfSeason(date);
    }

    /**
     * 取得季度已过天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfSeason(Date date) {
        int day = 0;

        Date[] seasonDates = getSeasonDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);

        if (month == Calendar.JANUARY || month == Calendar.APRIL
                || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
            day = getPassDayOfMonth(seasonDates[0]);
        } else if (month == Calendar.FEBRUARY || month == Calendar.MAY
                || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
            day = getDayOfMonth(seasonDates[0])
                    + getPassDayOfMonth(seasonDates[1]);
        } else if (month == Calendar.MARCH || month == Calendar.JUNE
                || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
            day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1])
                    + getPassDayOfMonth(seasonDates[2]);
        }
        return day;
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    public static void main(String[] args) {

        String strDate = "2017-07-23";

        Date date = parseDate(strDate);

        System.out.println(strDate + " 今天是哪一年？" + getYear(date));
        System.out.println(strDate + " 今天是哪个月？" + getMonth(date));
        System.out.println(strDate + " 今天是几号？" + getDay(date));
        System.out.println(strDate + " 今天是周几？" + getWeekDay(date));
        System.out.println(strDate + " 是一年的第几周？" + getWeekOfYear(date));
        System.out.println(strDate + " 所在周起始结束日期？"
                + getWeekBeginAndEndDate(date, "yyyy年MM月dd日"));
        System.out.println(strDate + " 所在周周一是？"
                + formatDate(getMondayOfWeek(date)));
        System.out.println(strDate + " 所在周周日是？"
                + formatDate(getSundayOfWeek(date)));

        System.out.println(strDate + " 当月第一天日期？"
                + formatDate(getFirstDateOfMonth(date)));
        System.out.println(strDate + " 当月最后一天日期？"
                + formatDate(getLastDateOfMonth(date)));
        System.out.println(strDate + " 当月天数？" + getDayOfMonth(date));
        System.out.println(strDate + " 当月已过多少天？" + getPassDayOfMonth(date));
        System.out.println(strDate + " 当月剩余多少天？" + getRemainDayOfMonth(date));

        System.out.println(strDate + " 所在季度第一天日期？"
                + formatDate(getFirstDateOfSeason(date)));
        System.out.println(strDate + " 所在季度最后一天日期？"
                + formatDate(getLastDateOfSeason(date)));
        System.out.println(strDate + " 所在季度天数？" + getDayOfSeason(date));
        System.out.println(strDate + " 所在季度已过多少天？" + getPassDayOfSeason(date));
        System.out
                .println(strDate + " 所在季度剩余多少天？" + getRemainDayOfSeason(date));
        System.out.println(strDate + " 是第几季度？" + getSeason(date));
        System.out.println(strDate + " 所在季度月份？"
                + formatDate(getSeasonDate(date)[0], "yyyy年MM月") + "/"
                + formatDate(getSeasonDate(date)[1], "yyyy年MM月") + "/"
                + formatDate(getSeasonDate(date)[2], "yyyy年MM月"));

        System.out.println(firstDayOfSeason("2017-08-23"));
        System.out.println(lastSeasonBetweenDate("2017-08-23")[0]);
        System.out.println(lastSeasonBetweenDate("2017-08-23")[1]);
        System.out.println(lastWeekBetweenDate("2017-08-23")[0]);
        System.out.println(lastWeekBetweenDate("2017-08-23")[1]);


        System.out.println("当天时间：" + formatDate(new Date()));

    }

}
