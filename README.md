Spring Test Scope [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/pring-test-scope/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/pring-test-scope) [![Build Status](https://travis-ci.com/marschall/spring-test-scope.svg?branch=master)](https://travis-ci.com/marschall/spring-test-scope)
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
