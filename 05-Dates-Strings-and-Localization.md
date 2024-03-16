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

### Picking a Locale

The `java.util.Locale` class in Java represents a specific geographical, political, or cultural region. It's commonly used for internationalization and localization in Java applications to tailor the user interface and behavior to different languages, regions, and cultural conventions.

Here's an overview of some of the commonly used methods and features of the `Locale` class:

1. **Constructors**: `Locale` provides several constructors for creating `Locale` instances, including constructors that take language codes, language and country codes, and language, country, and variant codes.

   ```java
   Locale english = new Locale("en");
   Locale usEnglish = new Locale("en", "US");
   Locale frenchCanada = new Locale("fr", "CA");
   ```

2. **Constants**: `Locale` defines a set of constants for commonly used locales, such as `Locale.ENGLISH`, `Locale.FRENCH`, `Locale.GERMAN`, `Locale.US`, `Locale.UK`, etc.

3. **Getters**: You can use various getter methods to retrieve information from a `Locale` instance, such as the language, country, variant, and display name.

   ```java
   Locale locale = Locale.getDefault();
   String language = locale.getLanguage();
   String country = locale.getCountry();
   ```

4. **Display Names**: `Locale` provides methods for obtaining display names of locales in different languages. These methods are useful for presenting locale names to users in a localized format.

   ```java
   String displayName = locale.getDisplayName();
   String displayNameInFrench = locale.getDisplayName(Locale.FRENCH);
   ```

5. **Language and Country Codes**: `Locale` provides methods for retrieving language and country codes from a locale.

   ```java
   String languageCode = locale.getLanguage();
   String countryCode = locale.getCountry();
   ```

6. **Builder**: Starting from Java 7, `Locale.Builder` allows you to create custom locales using a fluent interface.

   ```java
   Locale customLocale = new Locale.Builder().setLanguage("en").setRegion("GB").build();
   ```

`Locale` is an essential class for internationalizing Java applications, allowing developers to adapt their software to different languages, regions, and cultural conventions to provide a better user experience.

### Using a Resource Bundle 

#### Creating a Property File Resource Bundle

To create a property file resource bundle in Java, you can follow these steps:

1. **Create Property Files**: Create one or more property files (`.properties`) containing key-value pairs for the localized messages or resources. Each property file corresponds to a specific locale.

   For example, you can create `messages_en.properties` for English messages and `messages_fr.properties` for French messages.

   ```properties
   # messages_en.properties
   greeting=Hello
   farewell=Goodbye

   # messages_fr.properties
   greeting=Bonjour
   farewell=Au revoir
   ```

2. **Load Resource Bundles**: Use the `ResourceBundle` class to load the appropriate resource bundle based on the user's locale. You can load the resource bundle using the `getBundle()` method, specifying the base name and the locale.

   ```java
   import java.util.Locale;
   import java.util.ResourceBundle;

   public class ResourceBundleExample {
       public static void main(String[] args) {
           // Specify the base name of the property files (without the locale suffix)
           String baseName = "messages";

           // Load resource bundle for English locale
           ResourceBundle bundleEn = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
           System.out.println("English Greeting: " + bundleEn.getString("greeting"));
           System.out.println("English Farewell: " + bundleEn.getString("farewell"));

           // Load resource bundle for French locale
           ResourceBundle bundleFr = ResourceBundle.getBundle(baseName, Locale.FRENCH);
           System.out.println("French Greeting: " + bundleFr.getString("greeting"));
           System.out.println("French Farewell: " + bundleFr.getString("farewell"));
       }
   }
   ```

   Running this code will display the appropriate greeting and farewell messages based on the specified locales.

3. **Accessing Messages**: Access the messages or resources in your code by using the keys defined in the property files and the `getString()` method of the `ResourceBundle` instance.

   ```java
   String greeting = bundle.getString("greeting");
   String farewell = bundle.getString("farewell");
   ```

By following these steps, you can create property file resource bundles for internationalizing your Java applications and provide localized messages or resources for different locales.

#### Creating a Java Class Resource Bundle

To create a Java class resource bundle in Java, you need to create a class that extends `java.util.ListResourceBundle` and override the `getContents()` method to provide the key-value pairs for the localized messages or resources.

Here's a step-by-step guide to creating a Java class resource bundle:

1. **Create a Java Class**: Create a Java class that extends `ListResourceBundle`. This class will contain the key-value pairs for the localized messages.

   ```java
   import java.util.ListResourceBundle;

   public class MyResourceBundle extends ListResourceBundle {
       @Override
       protected Object[][] getContents() {
           return new Object[][] {
               {"greeting", "Hello"},
               {"farewell", "Goodbye"}
           };
       }
   }
   ```

2. **Define Key-Value Pairs**: Override the `getContents()` method to return a two-dimensional array of objects. Each row in the array represents a key-value pair, where the first element is the key and the second element is the corresponding value.

3. **Load Resource Bundle**: Load the resource bundle in your Java code using the `ResourceBundle` class and the fully qualified class name of your resource bundle.

   ```java
   import java.util.Locale;
   import java.util.ResourceBundle;

   public class ResourceBundleExample {
       public static void main(String[] args) {
           // Load resource bundle
           ResourceBundle bundle = ResourceBundle.getBundle("MyResourceBundle", Locale.ENGLISH);

           // Access messages using keys
           String greeting = bundle.getString("greeting");
           String farewell = bundle.getString("farewell");

           System.out.println("Greeting: " + greeting);
           System.out.println("Farewell: " + farewell);
       }
   }
   ```

   Ensure that your resource bundle class (`MyResourceBundle` in this example) is in the classpath.

4. **Accessing Messages**: Access the messages or resources in your code by using the keys defined in the resource bundle and the `getString()` method of the `ResourceBundle` instance.

   ```java
   String greeting = bundle.getString("greeting");
   String farewell = bundle.getString("farewell");
   ```

By following these steps, you can create a Java class resource bundle for internationalizing your Java applications and provide localized messages or resources for different locales.

#### Determining Which Resource Bundle to Use

To determine which resource bundle to use based on the user's locale, you typically follow these steps:

1. **Obtain the User's Locale**: Retrieve the user's locale from the environment. This could be based on user preferences, browser settings, or any other mechanism available in your application.

2. **Determine the Base Name**: Decide on a base name for your resource bundles. This base name is the prefix of the properties file names (without the locale suffix).

3. **Load the Resource Bundle**: Use the `ResourceBundle.getBundle()` method to load the appropriate resource bundle based on the user's locale. Pass the base name and the user's locale as arguments to this method.

4. **Fallback Mechanism**: Implement a fallback mechanism to handle cases where a resource bundle for the user's locale is not available. You can provide a default locale or a generic resource bundle to ensure that there's always a fallback option.

Here's an example Java code snippet demonstrating these steps:

```java
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) {
        // 1. Obtain the user's locale (for demonstration purposes, using English)
        Locale userLocale = Locale.ENGLISH;

        // 2. Determine the base name of the resource bundles
        String baseName = "messages";

        // 3. Load the resource bundle for the user's locale
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, userLocale);

        // 4. Access messages from the resource bundle
        String greeting = bundle.getString("greeting");
        String farewell = bundle.getString("farewell");

        System.out.println("Greeting: " + greeting);
        System.out.println("Farewell: " + farewell);
    }
}
```

In this example, the `messages` resource bundle will be loaded based on the user's locale. If a specific resource bundle for the user's locale is not found, Java will fall back to the default locale (which is English in this case).

By following these steps, you can dynamically determine which resource bundle to use based on the user's locale, allowing you to provide localized messages and resources in your Java applications.

### Formatting Numbers

#### Format and Parse Numbers and Currency 

**Factory methods to get a NumberFormat**

| Description                          | Using Default Locale and a Specified Locale   |
|--------------------------------------|------------------------------------------------|
| A general purpose formatter          | `NumberFormat.getInstance()`                   |
|                                      | `NumberFormat.getInstance(locale)`             |
| Same as `getInstance`                | `NumberFormat.getNumberInstance()`             |
|                                      | `NumberFormat.getNumberInstance(locale)`       |
| For formatting monetary amounts      | `NumberFormat.getCurrencyInstance()`           |
|                                      | `NumberFormat.getCurrencyInstance(locale)`     |
| For formatting percentages           | `NumberFormat.getPercentInstance()`            |
|                                      | `NumberFormat.getPercentInstance(locale)`      |
| Rounds decimal values before         | `NumberFormat.getIntegerInstance()`            |
| displaying (not on the exam)         | `NumberFormat.getIntegerInstance(locale)`      |

**Code example**

Here's a Java code example demonstrating how to use various `NumberFormat` methods with both the default locale and a specified locale:

```java
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExample {
    public static void main(String[] args) {
        // Default Locale
        Locale defaultLocale = Locale.getDefault();

        // Specified Locale (French)
        Locale frenchLocale = Locale.FRENCH;

        // A general purpose formatter
        NumberFormat generalFormatterDefault = NumberFormat.getInstance();
        NumberFormat generalFormatterFrench = NumberFormat.getInstance(frenchLocale);

        // Same as getInstance
        NumberFormat numberFormatterDefault = NumberFormat.getNumberInstance();
        NumberFormat numberFormatterFrench = NumberFormat.getNumberInstance(frenchLocale);

        // For formatting monetary amounts
        NumberFormat currencyFormatterDefault = NumberFormat.getCurrencyInstance();
        NumberFormat currencyFormatterFrench = NumberFormat.getCurrencyInstance(frenchLocale);

        // For formatting percentages
        NumberFormat percentFormatterDefault = NumberFormat.getPercentInstance();
        NumberFormat percentFormatterFrench = NumberFormat.getPercentInstance(frenchLocale);

        // Rounds decimal values before displaying
        NumberFormat integerFormatterDefault = NumberFormat.getIntegerInstance();
        NumberFormat integerFormatterFrench = NumberFormat.getIntegerInstance(frenchLocale);

        // Display formatted numbers
        double number = 1234567.89;

        System.out.println("Default Locale:");
        displayFormattedNumbers(number, generalFormatterDefault, numberFormatterDefault,
                                currencyFormatterDefault, percentFormatterDefault, integerFormatterDefault);

        System.out.println("\nFrench Locale:");
        displayFormattedNumbers(number, generalFormatterFrench, numberFormatterFrench,
                                currencyFormatterFrench, percentFormatterFrench, integerFormatterFrench);
    }

    private static void displayFormattedNumbers(double number, NumberFormat... formatters) {
        for (NumberFormat formatter : formatters) {
            System.out.println(formatter.format(number));
        }
    }
}
```

In this example, we use various `NumberFormat` methods with both the default locale and the French locale (`Locale.FRENCH`). We then format a sample number (`1234567.89`) using each formatter and display the formatted numbers for both locales.

### Formatting Dates and Times

| Pattern | Description                                                   |
|---------|---------------------------------------------------------------|
| M       | Represents the month (1 for January, 2 for February, etc.)    |
| MM      | Represents the month with leading zero (01 for January, etc.) |
| MMM     | Represents the abbreviated month (Jan, Feb, etc.)             |
| MMMM    | Represents the full month name (January, February, etc.)      |
| dd      | Represents the day of the month with leading zero             |
| d       | Represents the day of the month                               |
| ,       | Outputs a comma                                               |
| yyyy    | Represents the year (four-digit year)                         |
| yy      | Represents the year (two-digit year)                          |
| hh      | Represents the hour with leading zero (01-12)                 |
| h       | Represents the hour (1-12)                                    |
| :       | Outputs a colon                                               |
| mm      | Represents the minute with leading zero (00-59)               |
| m       | Represents the minute (0-59)                                  |

# Summary

Exactly! Here's a summary of the key classes for working with dates, times, and time zones in Java:

1. **LocalDate**:
   - Represents a date without a time zone.
   - Created using methods like `LocalDate.now()` or `LocalDate.of()`.

2. **LocalTime**:
   - Represents a time without a date or time zone.
   - Created using methods like `LocalTime.now()` or `LocalTime.of()`.

3. **LocalDateTime**:
   - Represents a date and time without a time zone.
   - Created using methods like `LocalDateTime.now()` or `LocalDateTime.of()`.

4. **ZonedDateTime**:
   - Represents a date, time, and time zone.
   - Created from a LocalDateTime and a time zone ID using methods like `LocalDateTime.atZone()`.

5. **Instant**:
   - Represents a moment in time, typically in UTC.
   - Created using methods like `Instant.now()` or `Instant.ofEpochSecond()`.

### Example:

```java
import java.time.*;

public class DateTimeExample {
    public static void main(String[] args) {
        // Creating LocalDate
        LocalDate date = LocalDate.now();
        System.out.println("LocalDate: " + date);

        // Creating LocalTime
        LocalTime time = LocalTime.of(12, 30);
        System.out.println("LocalTime: " + time);

        // Creating LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println("LocalDateTime: " + dateTime);

        // Creating ZonedDateTime
        ZoneId zoneId = ZoneId.of("America/New_York");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, zoneId);
        System.out.println("ZonedDateTime: " + zonedDateTime);

        // Creating Instant
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);
    }
}
```

In this example:
- We create instances of LocalDate, LocalTime, LocalDateTime, ZonedDateTime, and Instant using various factory methods provided by each class.
- Each class represents a different aspect of date, time, or both, allowing for precise handling of various temporal concepts in Java.

Exactly! Here's an overview of manipulating dates and times in Java using the `plus()` and `minus()` methods, as well as the `Period` and `Duration` classes:

1. **Manipulating Dates and Times with `plus()` and `minus()` Methods**:
   - The `plus()` and `minus()` methods are available on instances of `LocalDate`, `LocalTime`, `LocalDateTime`, and `ZonedDateTime`.
   - They allow adding or subtracting values to date and time objects.
   - For example:
     ```java
     LocalDate tomorrow = LocalDate.now().plusDays(1);
     LocalTime twoHoursLater = LocalTime.now().plusHours(2);
     ```

2. **Period Class**:
   - Represents a period of time defined in terms of years, months, and days.
   - Used to add or subtract a period of time from `LocalDate`, `LocalDateTime`, or `ZonedDateTime`.
   - For example:
     ```java
     LocalDate nextWeek = LocalDate.now().plus(Period.ofWeeks(1));
     ```

3. **Duration Class**:
   - Represents a duration of time defined in terms of hours, minutes, and seconds.
   - Used to add or subtract a duration of time from `LocalTime`, `LocalDateTime`, or `ZonedDateTime`.
   - For example:
     ```java
     LocalTime twoHoursLater = LocalTime.now().plus(Duration.ofHours(2));
     ```

### Example:

```java
import java.time.*;

public class DateTimeManipulationExample {
    public static void main(String[] args) {
        // Manipulating dates using plus() and minus() methods
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("Tomorrow: " + tomorrow);

        // Manipulating times using plus() and minus() methods
        LocalTime twoHoursLater = LocalTime.now().plusHours(2);
        System.out.println("Two hours later: " + twoHoursLater);

        // Using Period to add weeks to a date
        LocalDate nextWeek = LocalDate.now().plus(Period.ofWeeks(1));
        System.out.println("Next week: " + nextWeek);

        // Using Duration to add hours to a time
        LocalTime twoHoursFromNow = LocalTime.now().plus(Duration.ofHours(2));
        System.out.println("Two hours from now: " + twoHoursFromNow);
    }
}
```

In this example:
- We use the `plus()` and `minus()` methods to manipulate dates and times by adding or subtracting values.
- We use the `Period` class to add a week to the current date.
- We use the `Duration` class to add two hours to the current time.

These classes and methods provide convenient ways to perform date and time arithmetic in Java.

Absolutely, UTC (Coordinated Universal Time) represents the time zone offset from zero and does not observe daylight saving time (DST). Daylight saving time is observed in various regions, including the United States and many other countries, by advancing the clocks by one hour in the spring (to start DST) and moving them back by one hour in the fall (to end DST). Java automatically adjusts time and the UTC offset to account for daylight saving time changes in the respective time zones.

When working with Java's date and time classes, such as `ZonedDateTime`, Java handles daylight saving time transitions internally based on the time zone information provided. This means that if you specify a time zone that observes daylight saving time, Java will automatically adjust the time and the UTC offset accordingly when converting between different time zones or performing date and time calculations.

For example, if you have a `ZonedDateTime` object representing a time in a time zone that observes daylight saving time, and you perform arithmetic operations such as adding or subtracting durations, Java will correctly handle any transitions between standard time and daylight saving time, adjusting the time and the UTC offset as necessary.

It's essential to use appropriate time zone information when working with dates and times to ensure accurate handling of daylight saving time transitions and other time zone-related adjustments. Java's date and time API provides comprehensive support for handling time zones and daylight saving time changes, making it easier to work with dates and times in various regions of the world.

Absolutely! Let's break down the concept of creating a `Locale` and using `ResourceBundle` in Java:

### Locale Class:

- **Creating a Locale**:
   - You can create a `Locale` object using the constructor `Locale(String language)` or `Locale(String language, String country)`.
   - The language parameter is a two-letter lowercase code representing the language, and the country parameter is a two-letter uppercase code representing the country.
   - Examples: `Locale english = new Locale("en")` for English and `Locale usEnglish = new Locale("en", "US")` for US English.

### ResourceBundle:

- **Specifying Key/Value Pairs**:
   - `ResourceBundle` allows specifying key/value pairs in a property file (`.properties`) or in a Java class.
   - Keys are strings that uniquely identify the values.
   - Values can be any object.

- **Searching for Resource Bundles**:
   - Java searches for resource bundles in a hierarchy from the most specific to the most general to find a match.
   - If no matches are found for the requested locale, Java switches to the default locale and then finally the default resource bundle.

- **Java Class vs. Property File**:
   - Java looks at the equivalent Java class before the property file for each locale.
   - Once a matching resource bundle is found, Java only looks in the hierarchy of that resource bundle to find keys.

### Example:

```java
import java.util.*;

public class ResourceBundleExample {
    public static void main(String[] args) {
        // Creating a Locale
        Locale usEnglish = new Locale("en", "US");

        // Loading ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", usEnglish);

        // Accessing keys and values
        String greeting = bundle.getString("greeting");
        String farewell = bundle.getString("farewell");

        System.out.println("Greeting: " + greeting);
        System.out.println("Farewell: " + farewell);
    }
}
```

In this example:
- We create a `Locale` object representing US English.
- We load a resource bundle named "Messages" for the specified locale.
- We access keys such as "greeting" and "farewell" from the resource bundle to retrieve their corresponding values.
- Java searches for the appropriate resource bundle based on the locale and retrieves the values for the requested keys. If a matching resource bundle is not found, it falls back to the default locale and default resource bundle.

Exactly! Here's an explanation of using `NumberFormat` and `DateTimeFormatter` in Java:

### NumberFormat:

- **Retrieving Formatter**:
   - `NumberFormat` class provides static methods to retrieve instances of `NumberFormat` for various types of formatting, such as currency, percentage, and general numbers.
   - Example: `NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();`

### DateTimeFormatter:

- **Outputting Dates and Times**:
   - `DateTimeFormatter` is used to output dates and times in the desired format.
   - Provides methods to format date-time objects into strings and parse strings into date-time objects.
   - Supports patterns for formatting and parsing, such as "yyyy-MM-dd" for date-only format and "HH:mm:ss" for time-only format.
   - Example: `DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");`

### Immutability of Date and Time Classes:

- **Immutable Classes**:
   - All date and time classes in Java's date and time API are immutable.
   - Operations on these classes return a new instance with the modified values rather than modifying the original instance.
   - This ensures thread safety and prevents unintended modifications of date and time objects.
   - Example: `LocalDate tomorrow = LocalDate.now().plusDays(1);`

### Example:

```java
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatExample {
    public static void main(String[] args) {
        // Number formatting
        double amount = 1234.56;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedCurrency = currencyFormatter.format(amount);
        System.out.println("Formatted Currency: " + formattedCurrency);

        // Date formatting
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dateFormatter.format(date);
        System.out.println("Formatted Date: " + formattedDate);
    }
}
```

In this example:
- We use `NumberFormat` to format a currency amount.
- We use `DateTimeFormatter` to format the current date.
- Both formatters are retrieved using static methods (`getCurrencyInstance()` and `ofPattern()`) and are used to format the respective values into strings.
- Since date and time classes are immutable, the return value of the formatting operations must be used to capture the formatted result.

# Exam Essentials

**Recognize invalid uses of dates and times.**

Absolutely, it's crucial to be mindful of the valid uses of dates and times in Java to avoid unintended errors. Here are some common mistakes to watch out for:

### Invalid Uses of Dates and Times:

1. **Mixing Date and Time Types**:
   - `LocalDate` does not contain time fields, and `LocalTime` does not contain date fields. Attempting operations that cross these boundaries will result in errors or unexpected behavior.
   - Example: Trying to add a time duration to a date or perform date-specific operations on a `LocalTime` object.

2. **Ignoring Results of Operations**:
   - All date and time classes in Java's date and time API are immutable. Operations on these classes return new instances with the modified values rather than modifying the original instance. Ignoring the result of these operations will discard the changes.
   - Example: `LocalDate.now().plusDays(1);` without capturing the result in a variable will not update the original date.

3. **Incorrect Parsing and Formatting**:
   - Incorrectly parsing or formatting date-time strings can lead to parsing errors or incorrect results.
   - Always ensure that the pattern used for parsing matches the format of the input string, and vice versa for formatting.

### Example:

```java
import java.time.*;

public class InvalidDateTimeUsageExample {
    public static void main(String[] args) {
        // Invalid mixing of date and time types
        LocalTime time = LocalTime.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1); // Incorrect: Mixing LocalDate and LocalTime
        // LocalDate tomorrow = LocalDate.now().plusDays(1); // Correct: Using LocalDate for date-specific operations

        // Ignoring result of operations
        LocalTime ignoredTime = LocalTime.now().plusHours(1); // Incorrect: Ignoring result
        System.out.println("Ignored Time: " + ignoredTime); // This will still print the original time

        // Incorrect parsing and formatting
        String invalidDateString = "2022-06-30T10:00:00"; // Incorrect format for LocalDate
        // LocalDate parsedDate = LocalDate.parse(invalidDateString); // Parsing will fail
        // String formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Incorrect format for LocalTime
    }
}
```

In this example:
- There's an attempt to mix `LocalDate` and `LocalTime` objects incorrectly.
- Ignoring the result of the `plusHours()` operation on `LocalTime` will not update the original time.
- Incorrect parsing or formatting of date-time strings can lead to parsing errors or incorrect results.

**Differentiate between Period and Duration.**

Absolutely! Here's a clear differentiation between `Period` and `Duration` in Java's date and time API:

### Period:

- **Definition**:
   - Represents a duration of time defined in terms of years, months, and days.
   - Used to represent a period of time between two dates or to add/subtract a period of time to `LocalDate`, `LocalDateTime`, or `ZonedDateTime`.

- **Units**:
   - Represents years, months, and days.

- **Usage**:
   - Typically used for date-based calculations, such as calculating the difference between two dates or adding/subtracting years, months, or days to a date.
   - Example: `Period.ofYears(1)` represents a period of one year.

- **Usage Context**:
   - Can only be used with date-related classes like `LocalDate`, `LocalDateTime`, and `ZonedDateTime`.

### Duration:

- **Definition**:
   - Represents a duration of time defined in terms of hours, minutes, and seconds.
   - Used to represent a duration of time between two times or to add/subtract a duration of time to `LocalTime`, `LocalDateTime`, or `ZonedDateTime`.

- **Units**:
   - Represents hours, minutes, and seconds.

- **Usage**:
   - Typically used for time-based calculations, such as calculating the difference between two times or adding/subtracting hours, minutes, or seconds to a time.
   - Example: `Duration.ofHours(1)` represents a duration of one hour.

- **Usage Context**:
   - Can only be used with time-related classes like `LocalTime`, `LocalDateTime`, and `ZonedDateTime`.

### Example:

```java
import java.time.*;

public class PeriodAndDurationExample {
    public static void main(String[] args) {
        // Period for date-based calculations
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 1);
        Period period = Period.between(startDate, endDate);
        System.out.println("Period between dates: " + period);

        // Duration for time-based calculations
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(11, 0);
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Duration between times: " + duration);
    }
}
```

In this example:
- We use `Period.between()` to calculate the period between two dates.
- We use `Duration.between()` to calculate the duration between two times.
- Period is used for date-based calculations, while Duration is used for time-based calculations.

**Perform calculations with dates.**

Performing calculations with dates and times, especially when dealing with time zones and daylight saving time (DST), requires careful consideration. Here's how you can perform calculations with dates and times, including comparisons that involve daylight saving time changes:

### Calculations with Dates and Times:

1. **Performing Calculations between Times using UTC**:
   - When dealing with time zones expressed as offsets like `-05:00`, `GMT-5`, or `UTC-5`, you can calculate by subtracting the offset from the time.
   - For example, to convert a local time to UTC, subtract the offset from the local time.
   - Ensure to consider the sign of the offset when performing calculations.

2. **Performing Comparisons with Daylight Saving Time**:
   - During daylight saving time changes, the offset from UTC changes, affecting time comparisons.
   - In regions observing DST, clocks are typically moved forward by one hour in spring (March) and moved backward by one hour in fall (November).
   - When comparing times across these transitions, consider the actual local time and its associated offset to UTC.

### Example:

```java
import java.time.*;

public class DateAndTimeCalculationsExample {
    public static void main(String[] args) {
        // Calculate time difference between two time zones with different offsets
        ZoneOffset offset1 = ZoneOffset.ofHours(-5); // Offset from UTC for time zone 1 (-05:00)
        ZoneOffset offset2 = ZoneOffset.ofHours(-8); // Offset from UTC for time zone 2 (-08:00)
        
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 3, 10, 12, 0); // Time in time zone 1
        LocalDateTime dateTime2 = LocalDateTime.of(2024, 3, 10, 12, 0); // Time in time zone 2

        // Calculate difference between two times
        Duration timeDifference = Duration.between(dateTime1.toInstant(offset1), dateTime2.toInstant(offset2));
        System.out.println("Time difference between time zone 1 and time zone 2: " + timeDifference);

        // Perform comparisons across DST transitions
        LocalDateTime springTime = LocalDateTime.of(2024, 3, 10, 1, 0); // 1:00 AM on the day DST starts (spring forward)
        LocalDateTime fallTime = LocalDateTime.of(2024, 11, 3, 1, 0); // 1:00 AM on the day DST ends (fall back)
        
        System.out.println("Is springTime before fallTime? " + springTime.isBefore(fallTime)); // true
        System.out.println("Is fallTime after springTime? " + fallTime.isAfter(springTime)); // true
    }
}
```

In this example:
- We calculate the time difference between two time zones with different offsets from UTC.
- We perform comparisons between times across DST transitions (spring forward and fall back) to demonstrate how to handle DST changes when comparing times.

**Identify valid and invalid locale strings.**

Absolutely! Here's a breakdown of valid and invalid locale strings and how to create a `Locale` using `Locale.Builder`:

### Valid Locale Strings:

- **Format**:
   - Language code (mandatory, lowercase) followed by an optional country code (uppercase) separated by an underscore.

- **Examples**:
   - Valid: `"en"`, `"en_US"`, `"fr"`, `"fr_FR"`, `"de"`, `"de_DE"`.
   - Language-only locales are valid, such as `"en"`, `"fr"`, `"de"`.
   - Language-country locales are valid, such as `"en_US"`, `"fr_FR"`, `"de_DE"`.

### Invalid Locale Strings:

- **Examples**:
   - Invalid: `"EN_us"`, `"en_us"`, `"EN_US"`, `"en_us123"`, `"en_123"`, `"us"`, `"FR_FR"`.

### Creating Locale Using Locale.Builder:

- **Locale.Builder**:
   - An alternate way to create a `Locale` in Java.
   - Allows calling the setters in any order.
   - Provides flexibility in building locales without strict adherence to the language-country format.

### Example:

```java
import java.util.Locale;

public class LocaleExample {
    public static void main(String[] args) {
        // Valid locale strings
        Locale validLocale1 = new Locale("en");
        Locale validLocale2 = new Locale("en", "US");
        
        // Invalid locale strings
        // Locale invalidLocale1 = new Locale("EN_us"); // Invalid: Language code should be lowercase
        // Locale invalidLocale2 = new Locale("en_us"); // Invalid: Country code should be uppercase
        // Locale invalidLocale3 = new Locale("en_us123"); // Invalid: Country code should not contain digits
        // Locale invalidLocale4 = new Locale("en_123"); // Invalid: Country code should not contain digits
        // Locale invalidLocale5 = new Locale("us"); // Invalid: Language code is mandatory
        // Locale invalidLocale6 = new Locale("FR_FR"); // Invalid: Language code should be lowercase
        
        // Creating locale using Locale.Builder
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguage("en").setRegion("US"); // Setters can be called in any order
        Locale localeFromBuilder = builder.build();
        System.out.println("Locale from builder: " + localeFromBuilder);
    }
}
```

In this example:
- We create `Locale` objects using both valid and invalid locale strings.
- We demonstrate how to use `Locale.Builder` to create a `Locale` object, allowing setters to be called in any order.

**Determine which resource bundle Java will use to look up a key.**

Absolutely! Java follows a specific order when searching for a matching resource bundle. Here's how Java determines which resource bundle to use to look up a key:

### Resource Bundle Lookup Order:

1. **Exact Locale Match**:
   - Java first attempts to find an exact match for the requested locale. If a resource bundle exists for the requested locale, Java uses it.
   - For example, if the requested locale is `"en_US"`, Java looks for a resource bundle with the same locale.

2. **Language Match**:
   - If an exact match for the requested locale is not found, Java tries to find a resource bundle with a match for the language (ignoring the country).
   - For example, if the requested locale is `"en_US"` and an exact match is not found, Java looks for a resource bundle with the language `"en"`.

3. **Default Locale**:
   - If neither an exact match nor a language match is found, Java falls back to the default locale and attempts to find a resource bundle for it.
   - The default locale is typically the JVM's default locale, which can be set using the `-Duser.language` and `-Duser.country` system properties.

4. **Default Resource Bundle**:
   - If no matches are found for any of the above steps, Java switches to the default resource bundle.
   - The default resource bundle is often a general-purpose resource bundle that provides fallback values for keys that are not found in more specific bundles.

### Matching Resource Bundle Hierarchy:

- Once a matching resource bundle is found, Java only looks in the hierarchy of that resource bundle to find keys.
- Java does not search through the hierarchy of other resource bundles if a matching one is already found.
- This ensures that Java efficiently retrieves keys from the most specific resource bundle available without unnecessary searching.

### Example:

```java
import java.util.*;

public class ResourceBundleLookupExample {
    public static void main(String[] args) {
        // Requested locale
        Locale requestedLocale = new Locale("en", "US");

        // Loading ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", requestedLocale);

        // Accessing keys and values
        String greeting = bundle.getString("greeting");
        String farewell = bundle.getString("farewell");

        System.out.println("Greeting: " + greeting);
        System.out.println("Farewell: " + farewell);
    }
}
```

In this example:
- We request a resource bundle named `"Messages"` for the locale `"en_US"`.
- Java searches for an exact match for `"en_US"` first. If not found, it looks for a language match (`"en"`), then falls back to the default locale, and finally to the default resource bundle.
- Once a matching resource bundle is found, Java only looks in the hierarchy of that bundle to find keys, providing efficient lookup.

**Understand which Property value gets used as a default.**

Absolutely, understanding how default values work in Java's properties is crucial. Here's how it works:

### Default Values in Properties:

- **Using `get()` Method**:
   - When using the `get()` method to retrieve a property value from a `Properties` object, `null` is returned if the key is not found in the properties file.

- **Using `getProperty()` Method**:
   - There are two versions of the `getProperty()` method:
      1. **Single-parameter version**:
         - This version behaves similarly to `get()`. It returns `null` if the key is not found in the properties file.
         - Syntax: `String getProperty(String key)`.
      2. **Two-parameter version**:
         - This version allows specifying a default value to return if the key is not found in the properties file.
         - Syntax: `String getProperty(String key, String defaultValue)`.
         - If the key is found, the corresponding value is returned. If the key is not found, the specified `defaultValue` is returned.

### Example:

```java
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) {
        // Creating a Properties object
        Properties properties = new Properties();

        // Adding properties
        properties.setProperty("key1", "value1");
        properties.setProperty("key2", "value2");

        // Retrieving property values
        String value1 = properties.getProperty("key1"); // Key found, returns "value1"
        String value2 = properties.getProperty("key2", "defaultValue"); // Key found, returns "value2"
        String value3 = properties.getProperty("key3"); // Key not found, returns null
        String value4 = properties.getProperty("key4", "defaultFallbackValue"); // Key not found, returns "defaultFallbackValue"

        // Displaying property values
        System.out.println("Value1: " + value1);
        System.out.println("Value2: " + value2);
        System.out.println("Value3: " + value3);
        System.out.println("Value4: " + value4);
    }
}
```

In this example:
- We add properties `"key1"` and `"key2"` to a `Properties` object.
- We retrieve values using both versions of the `getProperty()` method.
- If a key is found, the corresponding value is returned; otherwise, `null` or the specified default value is returned, depending on the method used.