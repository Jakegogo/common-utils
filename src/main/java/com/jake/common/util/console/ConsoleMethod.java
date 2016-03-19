/**
 * 
 */
package com.jake.common.util.console;

import java.lang.annotation.*;

/**
 * @author fansth
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ConsoleMethod {

	String name();
	
	String description() default "";
}
