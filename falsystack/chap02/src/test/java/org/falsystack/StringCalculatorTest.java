package org.falsystack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

  private StringCalculator calc;

  @BeforeEach
  void beforeEach() {
    calc = new StringCalculator();
  }

  @Test
  @DisplayName("Null || '' 입력하면 0을 반환해야 한다.")
  void addNullOrEmptyString() {
    assertThat(calc.add(null)).isEqualTo(0);
    assertThat(calc.add("")).isEqualTo(0);
  }

  @Test
  @DisplayName("숫자 하나를 문자열로 입력할 경우 숫자 하나를 반환한다.")
  void addOneNumber() {
    assertThat(calc.add("5")).isEqualTo(5);
    assertThat(calc.add("3")).isEqualTo(3);
  }

  @Test
  @DisplayName("숫자 두개를 쉼표 구분자로 입력할 경우 두 숫자의 합을 반환한다.")
  void addComma() {
    assertThat(calc.add("2,0")).isEqualTo(2);
    assertThat(calc.add("3,7")).isEqualTo(10);
  }

  @Test
  @DisplayName("쉼표 이외에 콜론을 사용할 수 있다.")
  void addCommaAndColon() {
    assertThat(calc.add("2,0")).isEqualTo(2);
    assertThat(calc.add("3:7")).isEqualTo(10);
  }

  @Test
  @DisplayName("//와 \n 문자 사이에 커스텀 구분자를 지정할 수 있다.")
  void addOnlyCustomDelimiter() {
    assertThat(calc.add("//;\n1;2;3")).isEqualTo(6);
  }

  @Test
  @DisplayName("음수를 전달하는 경우 RuntimeException 을 던진다.")
  void addMinusNumber() {
    assertThrows(RuntimeException.class, () -> calc.add("-1,2,3"));
  }
}