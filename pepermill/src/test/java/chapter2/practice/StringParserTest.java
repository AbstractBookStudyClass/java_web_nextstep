package chapter2.practice;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringParserTest {

	private StringParser parser;
	private static final String PATTERN = "//(.)\n(.*)";

	@BeforeEach
	void setUp() {
		parser = new StringParser(PATTERN);
	}

	@ParameterizedTest
	@MethodSource("provideStringsForDelimiter")
	void get_correct_delimiter(String input, String expected) {
		//when
		String delimiter = parser.findDelimiter(input);

		//then
		Assertions.assertEquals(expected, delimiter);
	}

	@ParameterizedTest
	@MethodSource("provideStringsForParsedSource")
	void get_correct_parsed_source(String input, String expected) {
		//when
		String parsedSource = parser.getParsedSource(input);

		//then
		Assertions.assertEquals(expected, parsedSource);
	}

	private static Stream<Arguments> provideStringsForDelimiter() {
		return Stream.of(
			Arguments.of("//*\n1*2", "*"),
			Arguments.of("//@\n1@2", "@"),
			Arguments.of("//#\n1#2", "#"),
			Arguments.of("///\n1/2", "/"),
			Arguments.of("///\n132/2", "/"),
			Arguments.of("1,2:3", ",|:")
		);
	}

	private static Stream<Arguments> provideStringsForParsedSource() {
		return Stream.of(
			Arguments.of("//*\n1*2", "1*2"),
			Arguments.of("//@\n1@2", "1@2"),
			Arguments.of("//#\n1#2", "1#2"),
			Arguments.of("///\n1/2", "1/2"),
			Arguments.of("///\n1123/2", "1123/2"),
			Arguments.of("1,2:3", "1,2:3"),
			Arguments.of("1", "1")
		);
	}
}
