package com.github.marschall.spring.test.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;


/**
 * Opens and closes the test scope.
 *
 * <p>This listener should be picked up, instantiated and called by the
 * Spring TestContext Framework.
 *
 * @author Philippe Marschall
 */
public final class TestScopeTestExecutionListener implements TestExecutionListener, Ordered {

  @Override
  public int getOrder() {
    // We need to run before DependencyInjectionTestExecutionListener#prepareTestInstance
    // which has order 2000
    return 1950;
  }

  @Override
  public void prepareTestInstance(TestContext testContext) {
    this.openTestScope(testContext);
  }

  @Override
  public void afterTestExecution(TestContext testContext) {
    this.closeTestScope(testContext);
  }

  private void openTestScope(TestContext testContext) {
    this.getTestScope(testContext).open();
  }

  private void closeTestScope(TestContext testContext) {
    this.getTestScope(testContext).close();
  }

  private TestScope getTestScope(TestContext testContext) {
    // don't check for testContext.hasApplicationContext() because it returns false
    // even though testContext.getApplicationContext() succeeds
    ApplicationContext applicationContext = testContext.getApplicationContext();
    if (applicationContext.getAutowireCapableBeanFactory() instanceof ConfigurableBeanFactory) {
      ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
      Scope scope = configurableBeanFactory.getRegisteredScope(TestScope.NAME);
      if (scope instanceof TestScope) {
        return (TestScope) scope;
      }
    }
    throw new IllegalStateException("no test scope found in application context");
  }

}
