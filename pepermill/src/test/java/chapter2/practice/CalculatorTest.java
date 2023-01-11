package chapter2.practice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chapter2.after.Calculator;

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
}
