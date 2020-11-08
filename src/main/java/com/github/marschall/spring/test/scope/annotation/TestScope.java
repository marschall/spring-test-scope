package com.github.marschall.spring.test.scope.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;

@Scope("test")
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Documented
public @interface TestScope {

}
