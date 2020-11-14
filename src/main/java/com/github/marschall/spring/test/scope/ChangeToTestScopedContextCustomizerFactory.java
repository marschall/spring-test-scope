package com.github.marschall.spring.test.scope;

import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

/**
 * Registers a {@link ChangeToTestScopedContextCustomizer} if {@link TestScopedBeans} is present on
 * a test class.
 *
 * <p>This class should be picked up, instantiated and called by the
 * Spring TestContext Framework.
 *
 * @author Philippe Marschall
 * @since 1.1
 */
public final class ChangeToTestScopedContextCustomizerFactory implements ContextCustomizerFactory {

  @Override
  public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
    TestScopedBeans testScopedBeans = AnnotationUtils.findAnnotation(testClass, TestScopedBeans.class);
    if (testScopedBeans != null) {
      return new ChangeToTestScopedContextCustomizer(testScopedBeans.value());
    }
    return null;
  }

}
