package com.github.marschall.spring.test.scope;

import java.util.List;

import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

/**
 * Registers the {@link TestScopeContextCustomizer}.
 *
 * <p>This listener should be picked up, instantiated and called by the
 * Spring TestContext Framework.
 *
 * @author Philippe Marschall
 */
public final class TestScopeContextCustomizerFactory implements ContextCustomizerFactory {

  @Override
  public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
    return new TestScopeContextCustomizer();
  }

}
