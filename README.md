Spring Test Scope
=================

A simple test scope for Spring.

Usage
-----

Add the dependency

```xml
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>spring-test-scope</artifactId>
  <version>0.1.0-SNAPSHOT</version>
  <scope>test</scope>
</dependency>
```

and then define your beans as `@TestScoped`

```java
@Bean
@TestScoped
public TestScopedBean testScopedBean() {
  return new TestScopedBean();
}
```

