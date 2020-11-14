package com.github.marschall.spring.test.scope;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

/**
 * {@link Scope} that is only active during the execution of a test.
 *
 * <p>This scope does not support accessing a test scoped bean from multiple threads.
 *
 * @author Philippe Marschall
 */
final class TestScope implements Scope {

  public static final String NAME = "test";

  private static final Log LOGGER = LogFactory.getLog(TestScope.class);

  TestScope() {
    this.scopeContentHolder = new NamedThreadLocal<>("TestScope");
  }

  private ScopeContent getScopeContent() {
    ScopeContent scopeContent = this.scopeContentHolder.get();
    if (scopeContent == null) {
      throw new IllegalStateException("test scope is not active");
    }
    return scopeContent;
  }

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("accessing bean: " + name);
    }
    return this.getScopeContent().get(name, objectFactory);
  }

  @Override
  public Object remove(String name) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("removing bean: " + name);
    }
    return this.getScopeContent().remove(name);
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("registering destruction callback for bean: " + name);
    }
    this.getScopeContent().registerDestructionCallback(name, callback);
  }

  void open() {
    LOGGER.debug("opening scope");
    ScopeContent current = this.scopeContentHolder.get();
    if (current != null) {
      throw new IllegalStateException("test scope is already active");
    }
    this.scopeContentHolder.set(new ScopeContent());
  }

  void close() {
    LOGGER.debug("closing scope");
    this.getScopeContent().close();
    this.scopeContentHolder.remove();
  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }

  /**
   * {@link ThreadLocal} holding on the the content of this scope. Not {@code static}
   * so different application contexts don't interfere with each other.
   */
  private final ThreadLocal<ScopeContent> scopeContentHolder;

  static final class ScopeContent {

    private final Map<String, Object> scopedObjects;
    private final Map<String, Runnable> destructionCallbacks;

    ScopeContent() {
      this.scopedObjects = new HashMap<>();
      this.destructionCallbacks = new HashMap<>();
    }

    Object get(String name, ObjectFactory<?> objectFactory) {
      return this.scopedObjects.computeIfAbsent(name, (ignored) -> objectFactory.getObject());
    }

    Object remove(String name) {
      return this.scopedObjects.remove(name);
    }

    void registerDestructionCallback(String name, Runnable callback) {
      Runnable previous = this.destructionCallbacks.put(name, callback);
      if (previous != null) {
        LOGGER.warn("overwriting previous destruction callback of: " + name);
      }
    }

    void close() {
      this.scopedObjects.clear();
      for (Runnable callback : this.destructionCallbacks.values()) {
        callback.run();
      }

    }
  }

}
