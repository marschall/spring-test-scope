package com.github.marschall.spring.test.scope;

import java.util.Set;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;

/**
 * Registers a {@link BeanFactoryPostProcessor} that changes the scope of a given set of beans to {@value TestScope#NAME}.
 *
 * @author Philippe Marschall
 * @since 1.1
 */
final class ChangeToTestScopedContextCustomizer implements ContextCustomizer {

  private final String[] testScopedBeanNames;

  ChangeToTestScopedContextCustomizer(String[] testScopedBeanNames) {
    this.testScopedBeanNames = testScopedBeanNames;
  }

  @Override
  public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
    context.addBeanFactoryPostProcessor(new ChangeScopeToTestBeanFactoryPostProcessor(Set.of(this.testScopedBeanNames)));
  }

}
