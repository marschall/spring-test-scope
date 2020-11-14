package com.github.marschall.spring.test.scope;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig
@TestScopedBeans("testScopedBean")
class TestScopedBeansTests {

  @Autowired
  private TestScopedBean testScopedBean;

  @Test
  void test1() {
    assertNotNull(this.testScopedBean);
    assertEquals(1, this.testScopedBean.incrementAndGet());
  }

  @Test
  void test2() {
    assertNotNull(this.testScopedBean);
    assertEquals(1, this.testScopedBean.incrementAndGet());
  }

  @Configuration
  static class TestScopeConfiguration {

    @Bean
    public TestScopedBean testScopedBean() {
      return new TestScopedBean();
    }

  }

}
