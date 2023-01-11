package chapter2.practice;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StringCalculatorTest {

	private StringCalculator stringCalculator;
	private static final String PATTERN = "//(.)\n(.*)";

	@BeforeEach
	void setUp() {
		stringCalculator = new StringCalculator(
			new Calculator(),
			new StringParser(PATTERN),
			new StringValidator()
		);
	}

	@ParameterizedTest
	@MethodSource("provideStringsForCalculation")
	void calculate(String input, int expected) {
		//when
		int result = stringCalculator.add(input);

		//then
		Assertions.assertEquals(expected, result);
	}

	private static Stream<Arguments> provideStringsForCalculation() {
		return Stream.of(
			Arguments.of("//;\n1;2;1", 4),
			Arguments.of("//;\n123;1;1;1", 126),
			Arguments.of("//;\n100;1", 101)
		);
	}
}
