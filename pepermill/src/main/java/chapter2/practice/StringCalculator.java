package chapter2.practice;

public class StringCalculator {

	private Calculator calculator;
	private StringParser parser;
	private StringValidator validator;

	public StringCalculator(final Calculator calculator, final StringParser parser, final StringValidator validator) {
		this.calculator = calculator;
		this.parser = parser;
		this.validator = validator;
	}

	public int add(String input) {
		validator.validateMinus(input);
		if (validator.validateEmpty(input)) {
			return 0;
		}

		String delimiter = parser.findDelimiter(input);
		String source = parser.getParsedSource(input);

		return calculator.add(delimiter, source);
	}
}
