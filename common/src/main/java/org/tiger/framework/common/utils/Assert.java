package org.tiger.framework.common.utils;

import org.springframework.util.StringUtils;
import org.tiger.framework.common.exception.AppException;
import org.tiger.framework.common.exception.Code;

/**
 * Assertion utility class that assists in validating arguments.
 * Useful for identifying programmer errors early and clearly at runtime.
 *
 * <p>For example, if the contract of a public method states it does not
 * allow {@code null} arguments, Assert can be used to validate that
 * contract. Doing this clearly indicates a contract violation when it
 * occurs and protects the class's invariants.
 *
 * <p>Typically used to validate method arguments rather than configuration
 * properties, to check for cases that are usually programmer errors rather than
 * configuration errors. In contrast to config initialization code, there is
 * usally no point in falling back to defaults in such methods.
 *
 * <p>This class is similar to JUnit's assertion library. If an argument value is
 * deemed invalid, an {@link IllegalArgumentException} is thrown (typically).
 * For example:
 *
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i > 0, "The value must be greater than zero");</pre>
 *
 * Mainly for internal use within the framework; consider Jakarta's Commons Lang
 * >= 2.0 for a more comprehensive suite of assertion utilities.
 *
 * @author Keith Donald
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 * @author Rob Harrop
 * @since 1.1.2
 */
public abstract class Assert {

	/**
	 * Assert a boolean expression, throwing {@code IllegalArgumentException}
	 * if the test result is {@code false}.
	 * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if expression is {@code false}
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new AppException(message);
		}
	}
	
	/**
     *  Assert a boolean expression, throwing {@code IllegalArgumentException}
     * if the test result is {@code false}.
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @param errorCode
     * @throws AppException if expression is {@code false}
     */
    public static void isTrue(boolean expression, Code errorCode) {
        if (!expression) {
            throw new AppException(errorCode);
        }
    }
    
    /**
     *  Assert a boolean expression, throwing {@code IllegalArgumentException}
     * if the test result is {@code false}.
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @param errorCode
     * @throws AppException if expression is {@code false}
     */
    public static void isTrue(boolean expression, Code errorCode,Object ... arguments) {
        if (!expression) {
            throw new AppException(errorCode, arguments);
        }
    }

	

	/**
	 * Assert that an object is {@code null} .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is not {@code null}
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is not {@code null} .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is {@code null}
	 */
	public static void notNull(Object object, Code errorCode) {
		if (object == null) {
			throw new AppException(errorCode);
		}
	}
	
	/**
     * Assert that an object is not {@code null} .
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object, Code errorCode,Object ... arguments) {
        if (object == null) {
            throw new AppException(errorCode,arguments);
        }
    }

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be {@code null} and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text, String message) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
     * 是否有内容
     * @param text
     * @param message
     * @param errorCode
     */
    public static void hasText(String text, Code errorCode, Object ... arguments) {
        if (!StringUtils.hasText(text)) {
            throw new AppException(errorCode, arguments);
        }
    }
    
    /**
     * 是否有内容
     * @param text
     * @param message
     * @param errorCode
     */
    public static void hasText(Integer text, Code errorCode, Object ... arguments) {
        if (StringUtils.isEmpty(text) || text < 0) {
            throw new AppException(errorCode, arguments);
        }
    }
    
    /**
     * 是否有内容
     * @param text
     * @param message
     * @param errorCode
     */
    public static void hasText(String text, Code errorCode) {
        if (!StringUtils.hasText(text)) {
            throw new AppException(errorCode);
        }
    }
	

}

