package com.github.marschall.spring.test.scope;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;

/**
 * Registers the test scope.
 *
 * @author Philippe Marschall
 */
final class TestScopeContextCustomizer implements ContextCustomizer {

  @Override
  public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
    ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
    beanFactory.registerScope(TestScope.NAME, new TestScope());
  }

}
