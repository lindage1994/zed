package com.example.zed.localdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * @Author: zed
 * @Date: 2019/9/28 14:05
 * @Description: Java 8 LocalDate使用示例
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        // 打印当前日期
        System.out.println("today's date is :" + today);
        // 打印当前年月日
        System.out.printf("Year : %d  Month : %d  Day  : %d \n", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        // 构造某个日期
        LocalDate localDate = LocalDate.of(2019, 9, 28);
        // 比较两个日期是否相等
        if (today.equals(localDate)) {
            System.out.printf("Today %s and date %s are same date \n", today, localDate);
        }
        // 获取一周后的日期
        LocalDate dateAfterAweek = today.plus(1, ChronoUnit.WEEKS);
        System.out.printf("The date after a week is  %s \n", dateAfterAweek);
        // 获取一年前的日期
        LocalDate dateBeforeAyear = today.minusYears(1);
        System.out.printf("The date before one year is  %s \n", dateBeforeAyear);
        // 检查每年重复的日期
        LocalDate dateOfBirth = LocalDate.of(2010, 1, 14);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if(currentMonthDay.equals(birthday)){
            System.out.println("Many Many happy returns of the day !!");
        }else{
            System.out.println("Sorry, today is not your birthday");
        }
        // YearMonth组合 如检查信用卡过期时间
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());  // 当前YearMonth实例有多少天
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
        // 检查是否闰年
        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("This year is not a Leap year");
        }
        // 判断日期先后
        if (today.isAfter(dateOfBirth)) {
            System.out.println("Congratulations! you have born");
        }
        if (today.isBefore(dateOfBirth)) {
            System.out.println("Sorry! you dont exist");
        }
        // 两个日期之间有多少天、多少个月 java.time.Period类
        LocalDate java8Release = LocalDate.of(2014, Month.MARCH, 14);
        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths() );

        // 当前时间
        LocalTime time = LocalTime.now();
        System.out.printf("local time now is : %s \n", time);
        // 增加LocalTime对象的小时
        LocalTime afterTwoHours = time.plusHours(2);
        System.out.printf("Two hours later time is : %s \n", afterTwoHours);


        // 使用时钟
        // java 8 使用Clock类来替代System.currentTimeInMillis()与 TimeZone.getDefault()
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // Returns time based on system clock zone Clock defaultClock =
        Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);
        System.out.printf("now's timestamp is : %s \n", clock.millis());
        // an other timestamp
        Instant instant = Instant.now();
        System.out.printf("now's timestamp is : %s \n", instant);

        // 不同时区 ZoneId
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);


        // 时区偏移量 ZoneOffset
        LocalDateTime datetime = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);

        // string to Date
        String dayAfterTomorrow = "20140116";
        LocalDate formatted = LocalDate.parse(dayAfterTomorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTomorrow, formatted);
        String goodFriday = "六月 18 2014";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);
        } catch (DateTimeParseException ex) {
            System.out.printf("%s is not parsable!%n", goodFriday);
            ex.printStackTrace();
        }
        // Date to String
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at : %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }

    }

}
