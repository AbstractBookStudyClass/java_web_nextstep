package org.falsystack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CaculatorTest {

  private Calculator calc;

  @BeforeEach
  void beforeEach() {
    calc = new Calculator();
  }

  @Test
  @DisplayName("두 인자를 더한 값을 반환해야 한다.")
  void add() {
    int result = calc.add(1, 2);
    assertThat(result).isEqualTo(3);
  }

  @Test
  @DisplayName("두 인자를 뺸 값을 반환해야 한다.")
  void substract() {
    int result = calc.substract(3, 2);
    assertThat(result).isEqualTo(1);
  }

  @Test
  @DisplayName("두 인자를 나눈 값을 반환해야 한다.")
  void divide() {
    int result = calc.divide(6, 2);
    assertThat(result).isEqualTo(3);
  }

  @Test
  @DisplayName("두 인자를 곱한 값을 반환해야 한다.")
  void multiply() {
    int result = calc.multiply(3, 3);
    assertThat(result).isEqualTo(9);
  }
}