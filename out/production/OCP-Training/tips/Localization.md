**Resource Bundle in Java:**

- **Definition**: A resource bundle file can be either a properties file or a class file.
- **Abstract Class**: ResourceBundle is an abstract class with two subclasses: PropertyResourceBundle and ListResourceBundle.

**PropertyResourceBundle:**
- **Backed By**: It's backed by a properties file, a plain-text file containing translatable text.
- **Limitation**: Properties files can only contain values for String objects, not other types.

**ListResourceBundle:**
- **Backed By**: It's backed by a class file.
- **Management**: Manages resources with a list, allowing storage of any locale-specific object.
- **Adding Locale Support**: To support additional locales, you create another source file compiled into a class file.

**Flexibility of ResourceBundle**:
- You can start with String objects in a PropertyResourceBundle and later switch to ListResourceBundle without impacting your code.
- For instance, the `getBundle` method retrieves a ResourceBundle for the appropriate Locale regardless of whether it's backed by a class or a properties file.

**Example**:
```java
ResourceBundle introLabels = ResourceBundle.getBundle("ButtonLabel", currentLocale);
```

This call fetches the ResourceBundle for the specified Locale, whether it's backed by a class or a properties file named "ButtonLabel".
