package chapter2.after;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

	private Calculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new Calculator();
	}

	@Test
	void add_test() {
		//given
		int a = 20;
		int b = 10;
		int expected = a+b;

		//when
		int result = calculator.add(a, b);

		//then
		assertEquals(expected, result);
	}

	@Test
	void subtract_test() {
		//given
		int a = 20;
		int b = 10;
		int expected = a-b;

		//when
		int result = calculator.subtract(a, b);

		//then
		assertEquals(expected, result);
	}

	@Test
	void multiply_test() {
		//given
		int a = 20;
		int b = 10;
		int expected = a * b;

		//when
		int result = calculator.multiply(a, b);

		//then
		assertEquals(expected, result);
	}

	@Test
	void divide_test() {
		//given
		int a = 20;
		int b = 10;
		int expected = 20 / 10;

		//when
		int result = calculator.divide(a, b);

		//then
		assertEquals(expected, result);
	}
}
