package com.honestmind.storebook.utils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class TestFluent<T> {

  private T objectToTest;
  private UnaryOperator<T> when;

  public TestFluent<T> given(final T objectToTest) {
    this.objectToTest = objectToTest;
    return this;
  }

  public TestFluent<T> when(final UnaryOperator<T> when) {
    this.when = when;
    return this;
  }

  public void then(final Consumer<T> then) {
    then.accept(when.apply(objectToTest));
  }

  public void then(final BiConsumer<T, T> then) {
    then.accept(when.apply(objectToTest), objectToTest);
  }
}
