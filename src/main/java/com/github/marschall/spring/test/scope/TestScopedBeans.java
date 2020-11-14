package com.github.marschall.spring.test.scope;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Defines names of the beans whose scope should be changed to {@value TestScope#NAME}.
 *
 * <p>Meta-annotations are supported.
 *
 * @author Philippe Marschall
 * @since 1.1
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface TestScopedBeans {

  /**
   * Returns names of the beans whose scope should be changed to {@value TestScope#NAME}.
   *
   * @return the names of the beans whose scope should be changed to {@value TestScope#NAME}.
   */
  String[] value();

}
