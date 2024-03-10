# Dates, Strings and Localization

## Working with Dates and Times

### Creating Dates and Times

In Java, the `java.time` package provides classes to work with dates and times. Here are some of the commonly used classes for creating dates and times:

1. **LocalDate**: Represents a date without time zone information, such as "2024-03-08".

2. **LocalTime**: Represents a time without time zone information, such as "14:30:00".

3. **LocalDateTime**: Represents a date and time without time zone information, such as "2024-03-08T14:30:00".

4. **ZonedDateTime**: Represents a date and time with time zone information, such as "2024-03-08T14:30:00+01:00[Europe/Paris]".

Here's how you can create instances of these classes:

```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class DateTimeExample {
    public static void main(String[] args) {
        // Creating LocalDate
        LocalDate date = LocalDate.of(2024, 3, 8);
        System.out.println("LocalDate: " + date);

        // Creating LocalTime
        LocalTime time = LocalTime.of(14, 30, 0);
        System.out.println("LocalTime: " + time);

        // Creating LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 8, 14, 30, 0);
        System.out.println("LocalDateTime: " + dateTime);

        // Creating ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.of("Europe/Paris"));
        System.out.println("ZonedDateTime: " + zonedDateTime);
    }
}
```

In this example:

- We use the `of()` static methods of the respective classes to create instances.
- `ZoneId.of()` is used to specify the time zone for `ZonedDateTime`. You can use other methods like `ZoneId.systemDefault()` to get the default time zone of the system.

Remember to handle exceptions or validate inputs when creating instances, especially when dealing with user inputs or external data. Additionally, consider using `DateTimeFormatter` for parsing or formatting dates and times when interacting with users or external systems.

### Manipulating Dates and Times

**Methods in LocalDate, LocalTime, LocalDateTime, and ZonedDateTime**

|                           | Can Call on LocalDate?  | Can Call on LocalTime? | Can Call on LocalDateTime or ZonedDateTime? |
|---------------------------|-------------------------|------------------------|---------------------------------------------|
| plusYears/ minusYears     | Yes                     | No                     | Yes                                         |
| plusMonths/ minusMonths   | Yes                     | No                     | Yes                                         |
| plusWeeks/ minusWeeks     | Yes                     | No                     | Yes                                         |
| plusDays/ minusDays       | Yes                     | No                     | Yes                                         |
| plusMinutes/ minusMinutes | No                      | Yes                    | Yes                                         |
| plusSeconds/ minusSeconds | No                      | Yes                    | Yes                                         |
| plusNanos/ minusNanos     | No                      | Yes                    | Yes                                         |

```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

public class DateTimeManipulationExample {
    public static void main(String[] args) {
        // Creating instances
        LocalDate localDate = LocalDate.of(2024, 3, 8);
        LocalTime localTime = LocalTime.of(14, 30, 0);
        LocalDateTime localDateTime = LocalDateTime.of(2024, 3, 8, 14, 30, 0);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        // Manipulating LocalDate
        LocalDate plusYearsDate = localDate.plusYears(1);
        LocalDate minusYearsDate = localDate.minusYears(1);
        LocalDate plusMonthsDate = localDate.plusMonths(1);
        LocalDate minusMonthsDate = localDate.minusMonths(1);
        LocalDate plusWeeksDate = localDate.plusWeeks(1);
        LocalDate minusWeeksDate = localDate.minusWeeks(1);
        LocalDate plusDaysDate = localDate.plusDays(1);
        LocalDate minusDaysDate = localDate.minusDays(1);

        // Manipulating LocalTime, LocalDateTime, ZonedDateTime
        LocalTime plusHoursTime = localTime.plusHours(1);
        LocalTime minusHoursTime = localTime.minusHours(1);
        LocalDateTime plusHoursDateTime = localDateTime.plusHours(1);
        LocalDateTime minusHoursDateTime = localDateTime.minusHours(1);
        ZonedDateTime plusHoursZonedDateTime = zonedDateTime.plusHours(1);
        ZonedDateTime minusHoursZonedDateTime = zonedDateTime.minusHours(1);

        // Print results
        System.out.println("Manipulating LocalDate:");
        System.out.println("plusYearsDate: " + plusYearsDate);
        System.out.println("minusYearsDate: " + minusYearsDate);
        System.out.println("plusMonthsDate: " + plusMonthsDate);
        System.out.println("minusMonthsDate: " + minusMonthsDate);
        System.out.println("plusWeeksDate: " + plusWeeksDate);
        System.out.println("minusWeeksDate: " + minusWeeksDate);
        System.out.println("plusDaysDate: " + plusDaysDate);
        System.out.println("minusDaysDate: " + minusDaysDate);

        System.out.println("\nManipulating LocalTime, LocalDateTime, ZonedDateTime:");
        System.out.println("plusHoursTime: " + plusHoursTime);
        System.out.println("minusHoursTime: " + minusHoursTime);
        System.out.println("plusHoursDateTime: " + plusHoursDateTime);
        System.out.println("minusHoursDateTime: " + minusHoursDateTime);
        System.out.println("plusHoursZonedDateTime: " + plusHoursZonedDateTime);
        System.out.println("minusHoursZonedDateTime: " + minusHoursZonedDateTime);
    }
}
```

### Working with Periods

In Java, the `java.time.Period` class represents a period of time, typically used for expressing the difference between two dates in terms of years, months, and days. It's a convenient way to work with durations in terms of human-readable units.

Here's how you can work with `Period` in Java:

1. **Creating Periods**: You can create instances of `Period` using the `of()` method, specifying the number of years, months, and days.

   ```java
   import java.time.Period;

   public class PeriodExample {
       public static void main(String[] args) {
           Period period = Period.of(1, 6, 10); // 1 year, 6 months, 10 days
           System.out.println("Period: " + period);
       }
   }
   ```

2. **Manipulating Dates**: You can use `Period` to add or subtract periods from dates using the `plus()` and `minus()` methods.

   ```java
   import java.time.LocalDate;
   import java.time.Period;

   public class PeriodManipulationExample {
       public static void main(String[] args) {
           LocalDate currentDate = LocalDate.now();
           Period periodToAdd = Period.ofMonths(2);

           LocalDate futureDate = currentDate.plus(periodToAdd);
           System.out.println("Future date: " + futureDate);
       }
   }
   ```

3. **Getting Individual Components**: You can extract individual components (years, months, days) from a `Period` using the `getYears()`, `getMonths()`, and `getDays()` methods.

   ```java
   import java.time.Period;

   public class PeriodComponentsExample {
       public static void main(String[] args) {
           Period period = Period.of(1, 6, 10); // 1 year, 6 months, 10 days
           int years = period.getYears();
           int months = period.getMonths();
           int days = period.getDays();
           System.out.println("Years: " + years + ", Months: " + months + ", Days: " + days);
       }
   }
   ```

Periods are useful for expressing differences between dates in a human-readable format and for performing date arithmetic while considering the varying lengths of months and years. They're commonly used in conjunction with `LocalDate` to perform date calculations.

**Period format**

The P always starts out the String to show it is a period measure. Then come the number of years, 
number of months, and number of days. If any of these are zero, they are omitted.

### Working with Durations

In Java, the `java.time.Duration` class represents a duration of time in terms of seconds and nanoseconds. Unlike `Period`, which represents durations in terms of years, months, and days, `Duration` represents durations at a finer granularity, suitable for measuring time intervals.

Here's how you can work with `Duration` in Java:

1. **Creating Durations**: You can create instances of `Duration` using the `ofSeconds()` or `of()` methods, specifying the total number of seconds and optional nanoseconds.


|                   | Duration Constructor 1 | Duration Constructor 2               |
|-------------------|------------------------|--------------------------------------|
| Daily             | Duration.ofDays(1)     | Duration.of(1, ChronoUnit.DAYS)      |
| Every Hours       | Duration.ofHours(1)    | Duration.of(1, ChronoUnit.HOURS)     |
| Every Minute      | Duration.ofMinutes(1)  | Duration.of(1, ChronoUnit.MINUTES)   |
| Every Seconds     | Duration.ofSeconds(1)  | Duration.of(1, ChronoUnit.SECONDS)   |
| Every Millisecond | Duration.ofMillis(1)   | Duration.of(1, ChronoUnit.MILLIS)    |
| Every Nanosecond  | Duration.ofNanos(1)    | Duration.of(1, ChronoUnit.NANOS)     |


   ```java
   import java.time.Duration;

   public class DurationExample {
       public static void main(String[] args) {
           Duration duration = Duration.ofSeconds(3600); // 1 hour
           System.out.println("Duration: " + duration);
       }
   }
   ```

2. **Manipulating Durations**: You can perform arithmetic operations on `Duration` objects, such as adding or subtracting durations.

   ```java
   import java.time.Duration;

   public class DurationManipulationExample {
       public static void main(String[] args) {
           Duration duration = Duration.ofHours(1);
           Duration plusDuration = duration.plusMinutes(30);
           Duration minusDuration = duration.minusSeconds(10);

           System.out.println("Plus duration: " + plusDuration);
           System.out.println("Minus duration: " + minusDuration);
       }
   }
   ```

3. **Getting Components**: You can extract the number of seconds and nanoseconds from a `Duration` using the `getSeconds()` and `getNano()` methods.

   ```java
   import java.time.Duration;

   public class DurationComponentsExample {
       public static void main(String[] args) {
           Duration duration = Duration.ofMinutes(2).plusSeconds(30);
           long seconds = duration.getSeconds();
           int nanos = duration.getNano();
           System.out.println("Seconds: " + seconds + ", Nanoseconds: " + nanos);
       }
   }
   ```

`Duration` is commonly used for measuring time intervals, performing time calculations, and representing time-based durations. It's particularly useful when working with time-sensitive operations and measurements.

**Where to use Duration and Period**

|               | Can Use with Period? | Can Use with Duration? |
|---------------|----------------------|------------------------|
| LocalDate     | Yes                  | No                     |
| LocalDateTime | Yes                  | Yes                    |
| LocalTime     | No                   | Yes                    |
| ZonedDateTime | Yes                  | Yes                    |

**Working with Instants**

In Java, the `java.time.Instant` class represents an instantaneous point in time on the time-line, typically represented as a number of seconds and nanoseconds since the epoch (midnight of January 1, 1970 UTC). `Instant` is useful for representing machine-readable points in time and for performing calculations involving time zones.

Here's how you can work with `Instant` in Java:

1. **Creating Instants**: You can create instances of `Instant` using the `now()` method to get the current instant, or using the `ofEpochSecond()` method to specify the number of seconds since the epoch.

   ```java
   import java.time.Instant;

   public class InstantExample {
       public static void main(String[] args) {
           // Current instant
           Instant now = Instant.now();
           System.out.println("Current instant: " + now);

           // Instant from epoch seconds
           Instant epoch = Instant.ofEpochSecond(0);
           System.out.println("Epoch instant: " + epoch);
       }
   }
   ```

2. **Manipulating Instants**: Since `Instant` represents an instantaneous point in time, there's no concept of adding or subtracting time directly. However, you can adjust an instant by adding or subtracting seconds or nanoseconds using the `plusSeconds()` and `minusSeconds()` methods.

   ```java
   import java.time.Instant;

   public class InstantManipulationExample {
       public static void main(String[] args) {
           Instant now = Instant.now();
           Instant later = now.plusSeconds(60); // Add 60 seconds
           Instant earlier = now.minusSeconds(60); // Subtract 60 seconds

           System.out.println("Now: " + now);
           System.out.println("Later: " + later);
           System.out.println("Earlier: " + earlier);
       }
   }
   ```

3. **Converting Instants**: You can convert `Instant` to other types such as `LocalDateTime`, `ZonedDateTime`, or `OffsetDateTime` using the `atZone()` method to provide a time zone.

   ```java
   import java.time.Instant;
   import java.time.ZoneId;
   import java.time.ZonedDateTime;

   public class InstantConversionExample {
       public static void main(String[] args) {
           Instant instant = Instant.now();
           ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

           System.out.println("Zoned DateTime: " + zonedDateTime);
       }
   }
   ```

`Instant` is particularly useful for recording timestamps and performing time calculations in a timezone-independent manner, such as measuring time intervals or comparing events.

### Accounting for Daylight Savings Time

When working with time in Java, especially when dealing with time zones that observe daylight saving time (DST), it's essential to be aware of how Java handles DST transitions. Java's time API provides robust support for handling daylight saving time through the `java.time.ZoneId`, `java.time.ZonedDateTime`, and `java.time.OffsetDateTime` classes.

Here are some key points to consider when accounting for daylight saving time in Java:

1. **Use Time Zone Information**: Always use time zone information (`ZoneId`) when working with dates and times to ensure that DST transitions are handled correctly.

2. **Consider Time Zone Rules**: Time zone rules, including the start and end of daylight saving time, can change over time. Java's time API uses the IANA time zone database, which is regularly updated to reflect changes in time zone rules.

3. **Handle DST Transitions**: Be aware that when transitioning into or out of daylight saving time, the local time may be ambiguous or skipped altogether. Java's time API provides methods to handle these scenarios, such as `ZonedDateTime#withLaterOffsetAtOverlap()` and `ZonedDateTime#withEarlierOffsetAtOverlap()`.

4. **Use OffsetDateTime**: If you need to work with a fixed offset from UTC without considering DST, use `OffsetDateTime` instead of `ZonedDateTime`. `OffsetDateTime` represents a date-time with an offset from UTC, but without time zone rules.

5. **Update JDK**: Ensure that you are using an up-to-date version of the Java Development Kit (JDK) to benefit from improvements and bug fixes related to time zone handling.

By following these practices and leveraging Java's robust time API, you can accurately account for daylight saving time and handle time-related operations effectively in your Java applications.

## Reviewing the String class

**Comparing String, StringBuilder, and StringBuffer**

| Characteristic   | String        | StringBuilder | StringBuffer |
|------------------|---------------|---------------|--------------|
| Immutable?       | Yes           | No            | No           |
| Pooled?          | Yes           | No            | No           |
| Thread-safe?     | Yes           | No            | Yes          |
| Can change size? | No            | Yes           | Yes          |

## Adding Internationalization and Localization