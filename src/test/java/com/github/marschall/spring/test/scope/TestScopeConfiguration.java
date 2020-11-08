package com.github.marschall.spring.test.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestScopeConfiguration {

  @Bean
  @Scope("test")
  public TestScopedBean testScopedBean() {
    return new TestScopedBean();
  }

}
