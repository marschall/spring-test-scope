package com.github.marschall.spring.test.scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public final class TestScope implements Scope {

  private static final Log LOGGER = LogFactory.getLog(TestScope.class);

  // an argument could be made that these should be thread local
  private final Map<String, Object> scopedObjects;
  private final Map<String, Runnable> destructionCallbacks;

  public TestScope() {
    this.scopedObjects = Collections.synchronizedMap(new HashMap<>());
    this.destructionCallbacks = Collections.synchronizedMap(new HashMap<>());
  }

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    return this.scopedObjects.computeIfAbsent(name, (ignored) -> objectFactory.getObject());
  }

  @Override
  public Object remove(String name) {
    Object removed = this.scopedObjects.remove(name);
    if (removed != null) {
      Runnable callback = this.destructionCallbacks.remove(removed);
      callback.run();
    }
    return removed;
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    Runnable previous = this.destructionCallbacks.put(name, callback);
    if (previous != null) {
      LOGGER.warn("overwriting previous destruction callback of: " + name);
    }
  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }

}
