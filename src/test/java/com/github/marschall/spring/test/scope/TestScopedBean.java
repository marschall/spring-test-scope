package com.github.marschall.spring.test.scope;

public class TestScopedBean {

  private int i;

  public TestScopedBean() {
    this.i = 0;
  }

  public int incrementAndGet() {
    return ++this.i;
  }

}
