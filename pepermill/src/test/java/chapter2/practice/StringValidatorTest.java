package chapter2.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringValidatorTest {

	private StringValidator validator;

	@BeforeEach
	void setUp() {
		validator = new StringValidator();
	}

	@Test
	void can_not_calculate_minus() {
		//given
		String input = "//;\\n-1;2;-3";

		//when, then
		Assertions.assertThrows(
			RuntimeException.class,
			() -> validator.validateMinus(input)
		);
	}

	@Test
	void return_true_when_no_minus() {
		//given
		String input = "//;\\n1;2;3";

		//when
		boolean result = validator.validateMinus(input);

		//then
		Assertions.assertEquals(true, result);
	}

	@Test
	void return_false_when_string_is_empty() {
		//given
		String input = "";

		//when
		boolean result = validator.validateEmpty(input);

		//then
		Assertions.assertEquals(true, result);
	}

	@Test
	void return_true_when_string_is_not_empty() {
		//given
		String input = "//;\\n1;2;3";

		//when
		boolean result = validator.validateEmpty(input);

		//then
		Assertions.assertEquals(false, result);
	}
}
