Spring Test Scope
=================

A simple test scope for Spring.

Sometimes you have some stateful beans, eg. due to caching, that need to be recreated for every test. Using `@DirtiesContext` would throw the whole application context away, possibly slowing down test execution by a lot. This project introduces a test scope allows for only certain beans to be recreated while the rest of the application context can be reused.

Usage
-----

Add the dependency

```xml
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>spring-test-scope</artifactId>
  <version>1.0.0</version>
  <scope>test</scope>
</dependency>
```

and define your beans as `@TestScoped`

```java
@Bean
@TestScoped
public TestScopedBean testScopedBean() {
  return new TestScopedBean();
}
```

Future Ideas
------------

Allow changing the scope of certain beans on a per-class level.

