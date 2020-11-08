package com.github.marschall.spring.test.scope;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(
        classes = TestScopeConfiguration.class,
        initializers = TestScopeInitializer.class)
@TestExecutionListeners(listeners = TestScopeTestExecutionListener.class, mergeMode = MERGE_WITH_DEFAULTS)
class TestScopeTests {

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

}
