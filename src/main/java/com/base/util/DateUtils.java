package com.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间格式化工具类
 *
 * @author zhangqiao
 * @since 2018-12-22 14:00
 */
public class DateUtils {

    /**
     * 根据 <code>weekDay</code> 返回之后的那一天的日期
     *
     * @param weekDay   星期几 1 - 7
     * @param basisDate 基准的时间,基准参数所在的星期
     * @return Date日期
     */
    public static Date getNextDateForWeekDay(int weekDay, Date basisDate) {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(basisDate);
        int basisWeekDay = currentCalendar.get(Calendar.DAY_OF_WEEK);
        if (basisWeekDay - 1 < weekDay) {
            currentCalendar.add(Calendar.DATE, weekDay + 1 - basisWeekDay);
        } else {
            currentCalendar.add(Calendar.DATE, weekDay + 8 - basisWeekDay);
        }
        return currentCalendar.getTime();
    }

    /**
     * 根据 <code>weekDay</code> 返回那一天的日期
     *
     * @param weekDay   星期几 1 - 7
     * @param basisDate 基准的时间,基准参数所在的星期
     * @return Date日期
     */
    public static Date getDateForWeekDay(int weekDay, Date basisDate) {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(basisDate);
        int basisWeekDay = currentCalendar.get(Calendar.DAY_OF_WEEK);
        currentCalendar.add(Calendar.DATE, weekDay + 1 - basisWeekDay);
        return currentCalendar.getTime();
    }

    /**
     * 自定义格式化日期输出
     *
     * @param date
     * @param format
     * @return
     */
    public static String doFormatDate(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    /**
     * 自定义格式化日期输出
     *
     * @param date
     * @param format
     * @return
     */
    public static Date doFormatDate(String date, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 功能描述:获取当前时间的小时分钟 格式为：hh:mm
     *
     * @return
     * @author yangliu 2013-7-25 上午09:22:41
     */
    public static String getCurrentHoursMin() {
        return doFormatDate(new Date(), "HH:mm");
    }

    /**
     * 功能描述: 获取当前日期
     *
     * @param format
     * @return
     * @author yangliu 2013-7-25 下午07:43:42
     */
    public static String getCurrentDay(String format) {
        return doFormatDate(new Date(), format);
    }

    /**
     * 功能描述:获取两个日期之间相差多少小时(firstDate要大于secondDate)
     *
     * @param firstDate
     * @param secondDate
     * @return
     * @author L H T 2014-5-21 下午03:25:30
     */
    public static long getBetweenHour(Date firstDate, Date secondDate) {
        if (EmptyUtils.isEmpty(firstDate) || EmptyUtils.isEmpty(secondDate)) {
            return 0;
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long hour = 0;
        try {
            // 获得两个时间的毫秒时间差异
            long betweenNs = sd.parse(sd.format(firstDate)).getTime()
                    - sd.parse(sd.format(secondDate)).getTime();

            hour = betweenNs % (1000 * 24 * 60 * 60) / (1000 * 60 * 60);// 计算差多少小时

            // 输出结果
            System.out.println("时间相差：" + hour + "小时");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour;
    }

    // 测试方法
    public static int getTest(Date firstDate, Date secondDate) {
        if (EmptyUtils.isEmpty(firstDate) || EmptyUtils.isEmpty(secondDate)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse("2014-05-22 00:00:00").getTime()
                    - sd.parse(sd.format(secondDate)).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟"
                    + sec + "秒。");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 给一个日期加上N月后或减去N月后得到的一个新日期
     *
     * @param startDate 需要增加的日期时间
     * @param addnos    添加的月数，可以是正数也可以是负数
     * @return 操作后的日期
     */
    public static Date addMonth(Date startDate, int addnos) {
        if (startDate == null)
            return null;
        Calendar cc = Calendar.getInstance();
        cc.setTime(startDate);
        cc.add(Calendar.MONTH, addnos);
        return cc.getTime();

    }

    /**
     * 功能描述: 给日期加或减天
     *
     * @param startDate 开始时间
     * @param addnos    添加天数
     * @return
     * @author yangliu 2016年9月13日 下午2:40:28
     */
    public static Date addDay(Date startDate, int addnos) {
        if (startDate == null)
            return null;
        Calendar cc = Calendar.getInstance();
        cc.setTime(startDate);
        cc.add(Calendar.DATE, addnos);
        return cc.getTime();

    }

    /**
     * 功能描述:获取当前时间-1970年的秒数
     *
     * @return
     * @author L H T 2014-5-5 下午04:26:36
     */
    public static Long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 功能描述: 获取给定日期的所在月份的第一天
     *
     * @param date
     * @return
     * @author yangliu 2016年9月1日 下午3:24:37
     */
    public static Date getMonthOfDayFirst(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 功能描述:获取给定日期的所在月份的最后一天
     *
     * @param date
     * @return
     * @author yangliu 2016年9月1日 下午3:24:58
     */
    public static Date getMonthOfDayLast(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 功能描述:获取给定日期的所在月份的第一天00:00:00时间戳
     *
     * @return 时间戳
     * @author ChenJin
     */
    public static long getTimeMonthOfDayFirst(Date date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 功能描述:获取给定日期的所在月份的最后一天00:00:00时间戳
     *
     * @return 时间戳
     * @author ChenJin
     */
    public static long getTimeMonthOfDayLast(Date date) {
        Calendar ca = Calendar.getInstance();
        //下一月第一天-1毫秒
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
        //将小时至0
        ca.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        ca.set(Calendar.MINUTE, 0);
        //将秒至0
        ca.set(Calendar.SECOND, 0);
        //将毫秒至-1
        ca.set(Calendar.MILLISECOND, -1);
        // 获取本月最后一天的时间戳
        return ca.getTimeInMillis();
    }

    public static void main(String[] args) {
//		String format = "yyyyMMdd";
//		Date str = DateUtils.doFormatDate("20150207", format);
//		String firstDate = DateUtils.doFormatDate(getMonthOfDayFirst(str),
//				format);
//		String lastDate = DateUtils
//				.doFormatDate(getMonthOfDayLast(str), format);
//		System.out.println(firstDate);
//		System.out.println(lastDate);
        System.out.println(DateUtils.doFormatDate(DateUtils.getDateForWeekDay(7, new Date()), "yyyy-MM-dd"));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws java.text.ParseException
     */
    public static int getDaysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param d1 较大的日期
     * @param d2 较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int getDaysBetween(String smdate, String bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 功能描述:两日期比较
     *
     * @param DATE1
     * @param DATE2
     * @param format 格式化 			yyyy-MM-dd HH:mm:ss
     * @return DATE1>= DATE2 为true 否则为false
     * @author wsp 2016-9-29 下午07:23:22
     */
    public static boolean compareDate(String DATE1, String DATE2, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 功能描述:判断是否过期
     *
     * @param DATE1
     * @param format 过期为 false  未过期为true
     * @return
     * @author wsp  2017-1-5 上午10:02:46
     */
    public static boolean whetherExpired(String DATE1, String format) {
        String DATE2 = getCurrentDay(format);
        Boolean flag = compareDate(DATE1, DATE2, format);
        return flag;
    }

    /**
     * 功能描述:判断是否过期
     *
     * @param DATE1
     * @param format 过期为 false  未过期为true
     * @return
     * @author wsp  2017-1-5 上午10:02:46
     */
    public static boolean whetherExpired(Date DATE1, Date DATE2, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String now = getCurrentDay(format);
        Boolean flag1 = compareDate(now, df.format(DATE1), format);
        Boolean flag2 = compareDate(df.format(DATE2), now, format);
        if (flag1 && flag2) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 功能描述:根据日期获取一年的第几周
     *
     * @param date
     * @return
     * @author yanzy  2016-11-8 下午12:06:56
     */
    public static int weekOfYear(Date date) {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        int week = cl.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 功能描述:获取当前是第几周    yyyy-WW
     *
     * @param date
     * @return 2016-40
     * @author wsp  2016-11-17 上午09:11:42
     */
    public static String weekofyear(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        int week = cl.get(Calendar.WEEK_OF_YEAR);
        int year = cl.get(Calendar.YEAR);
        return year + "-" + week;

    }

    /**
     * 根据当前日期获得所在周的周一日期
     *
     * @return
     * @throws ParseException
     */
    public static Date getWeekBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 根据当前日期获得所在周的周日日期
     *
     * @return
     * @throws ParseException
     */
    public static Date getWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 6);
        return cal.getTime();
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        if (!beginDate.equals(endDate)) {
            lDate.add(endDate);// 把结束时间加入集合
        }
        return lDate;
    }

    /**
     * 获取2个日期之间日期的集合（日）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<Date> dateDayList(String startTime, String endTime) {
        List<Date> dateList = new ArrayList<>();
        Date startDate = DateUtils.doFormatDate(startTime, "yyyy-MM-dd");//开始
        Date endDate = DateUtils.doFormatDate(endTime, "yyyy-MM-dd");//结束
        int date = DateUtils.getDaysBetween(startDate, endDate);
        for (int i = 0; i < date + 1; i++) {
            if (i == 0) {
                dateList.add(startDate);
            } else {
                dateList.add(DateUtils.addDay(startDate, i));
            }
        }
        return dateList;
    }

    /**
     * 获取2个日期之间日期的集合(月)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<Date> dateMonthList(String startTime, String endTime) {
        List<Date> dateList = new ArrayList<>();
        Date startDate = DateUtils.doFormatDate(startTime, "yyyy-MM");//开始
        Date endDate = DateUtils.doFormatDate(endTime, "yyyy-MM");//结束
        int month = DateUtils.getMonthDiff(endDate, startDate);
        for (int i = 0; i < month + 1; i++) {
            if (i == 0) {
                dateList.add(startDate);
            } else {
                dateList.add(DateUtils.addMonth(startDate, i));
            }
        }
        return dateList;
    }
}











