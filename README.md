Spring Test Scope [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/pring-test-scope/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/pring-test-scope) [![Build Status](https://travis-ci.com/marschall/spring-test-scope.svg?branch=master)](https://travis-ci.com/marschall/spring-test-scope) [![javadoc](https://javadoc.io/badge2/com.github.marschall/spring-test-scope/javadoc.svg)](https://javadoc.io/doc/com.github.marschall/spring-test-scope)
=================

A simple test scope for Spring.

Sometimes you have some stateful beans, eg. due to caching, that need to be recreated for every test. Using `@DirtiesContext` would throw the entire application context away, possibly slowing down test execution by a lot. This project introduces a test scope allows for only certain beans to be recreated while the rest of the application context can be reused.

Usage
-----

Add the dependency

```xml
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>spring-test-scope</artifactId>
  <version>1.1.0</version>
  <scope>test</scope>
</dependency>
```

Define your beans as `@TestScoped`

```java
@Bean
@TestScoped
public TestScopedBean testScopedBean() {
  return new TestScopedBean();
}
```

If you don't want to (re)define a bean you can instead use `@TestScopedBeans` on a test to redefine some beans as test scoped.

```java
@SpringJUnitConfig
@TestScopedBeans("testScopedBean")
class MyTests {

  @Autowired
  private TestScopedBean testScopedBean;

```

Requirements
------------

- The code has only been tested with Spring 5.3.
- Java 11 is required.
- The code has only been tested with JUnit 5, in theory JUnit 4 and TestNG should work as well

Caveats
-------

The current implementation has some limitations:

- The presence of a `ContextCustomizerFactory` may prevent sharing of an application context across test classes.
- The test scope is opened in `#prepareTestInstance` and closed in `#afterTestExecution`. Before and after these methods in the same thread there is not test scope available and accessing test scoped beans will result in an exception. This works fine if a bean is injected into a test but may fail if:
  - a test scoped bean is referenced by an other bean in the application context
  - test instances are reused but injection is not repeated

