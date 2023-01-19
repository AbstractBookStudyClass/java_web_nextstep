package chapter2.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

	public static void main(String[] args) {
		String input = "//*\n12*3*4";
		String pattern = "//(.)\n(.*)";
		StringCalculator calculator = new StringCalculator(
			new Calculator(),
			new StringParser(pattern),
			new StringValidator()
		);
		System.out.println(calculator.add(input));
	}
}
