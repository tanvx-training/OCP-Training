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

1. **Recognize invalid uses of dates and times**:
- LocalDate does not contain time fields and LocalTime does not contain date fields. 
- Watch for operations being performed on the wrong type. 
- Also watch for adding or subtracting time and ignoring the result.

2. **Differentiate between Period and Duration**:
- Period is for day, month, and year. 
- It can only be used with LocalDate, LocalDateTime, and ZonedDateTime. 
- Duration is for hours, minutes, and seconds. 
- It can only be used with LocalTime, LocalDateTime, and ZonedDateTime.

3. **Perform calculations with dates**:
- Be able to perform calculations between times using UTC.
- Whether the format is -05:00, GMT-5, or UTC-5, you calculate by subtracting the offset from the 
time and then seeing how far the resulting times are.
- Also be able to perform comparisons that include daylight savings time. 
- In March, the United States springs ahead an hour, and in November, it falls back an hour

4. **Identify valid and invalid locale strings**:
- Know that the language code is lowercase and mandatory. 
- The country code is uppercase if present and follows the language code and an underscore.
- Locale.Builder is an alternate way to create a Locale, and it allows calling the
setters in either order.

5. **Determine which resource bundle Java will use to look up a key**:
- Know the order that Java uses to search for a matching resource bundle.
- Also recognize that the matching resource bundle hierarchy is searched once a matching resource bundle is found.

6. **Understand which Property value gets used as a default**:
- When calling get(), null is returned if the key is not found. 
- When calling getProperty(), there are two options. 
- The single-parameter version still returns null if the key is not found.
- The version that takes two parameters uses the second parameter as a return value if the key is not found.