package com.github.marschall.spring.test.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public final class TestScopeTestExecutionListener implements TestExecutionListener, Ordered {

  @Override
  public int getOrder() {
    // DependencyInjectionTestExecutionListener has order 2000
    // we need to run before DependencyInjectionTestExecutionListener
    return 2001;
  }

  @Override
  public void prepareTestInstance(TestContext testContext) {
    this.startScope(testContext);
  }

  @Override
  public void beforeTestExecution(TestContext testContext) {
    this.startScope(testContext);
  }

  @Override
  public void beforeTestMethod(TestContext testContext) {
    this.startScope(testContext);
  }

  private void startScope(TestContext testContext) {
    if (testContext.hasApplicationContext()) {
      ApplicationContext applicationContext = testContext.getApplicationContext();
      if (applicationContext.getAutowireCapableBeanFactory() instanceof ConfigurableBeanFactory) {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        Scope scope = configurableBeanFactory.getRegisteredScope("test");
        if (scope instanceof TestScope) {
          TestScope testScope = (TestScope) scope;
        }
      }
    }
  }

  @Override
  public void afterTestExecution(TestContext testContext) throws Exception {
  }

}
