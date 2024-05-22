While creating your own exception classes, you need to remember points:

1. Throwable is the top most class in exception hierarchy and is almost never used directly. Its two subclasses - Error and Exception represent two categories of exception, from which your exception class should extend.

2. Error is used for a serious system errors from which there can be no recovery. Exceptions for application programs almost never extend from this class.

3. Exception can be used to create new exception classes for reporting business logic failure. For example, a LowBalanceException that extends exception would be appropriate if the code finds that the funds in the account are not enough for the withdrawal.

4. RuntimeException, which extends Exception, should be used for situations that are unexpected for a piece of code. For example, if you get an array as a parameter and your application logic may expect that all elements are non-null and if you do get a null, you may throw a NullPointerException. There is usually no need for creating your own RuntimeException because Java API provides several RuntimeExceptions suitable for various situations.