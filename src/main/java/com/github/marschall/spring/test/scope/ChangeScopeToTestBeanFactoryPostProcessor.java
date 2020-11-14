package com.github.marschall.spring.test.scope;

import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.Assert;

/**
 * Changes the scope of a given set of beans to {@value TestScope#NAME}.
 *
 * @author Philippe Marschall
 * @since 1.1
 */
final class ChangeScopeToTestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  private final Set<String> testScopedBeanNames;

  ChangeScopeToTestBeanFactoryPostProcessor(Set<String> testScopedBeanNames) {
    Assert.notNull(testScopedBeanNames, "testScopedBeanNames");
    this.testScopedBeanNames = testScopedBeanNames;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    for (String beanName : this.testScopedBeanNames) {
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
      beanDefinition.setScope(TestScope.NAME);
    }
  }

}
