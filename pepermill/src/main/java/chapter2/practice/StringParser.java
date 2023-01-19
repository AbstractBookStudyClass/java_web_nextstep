package chapter2.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

	private String pattern;

	public StringParser(final String pattern) {
		this.pattern = pattern;
	}

	public String findDelimiter(String source) {
		Matcher matcher = Pattern.compile(pattern).matcher(source);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return ",|:";
	}

	public String getParsedSource(String source) {
		Matcher matcher = Pattern.compile(pattern).matcher(source);
		if (matcher.find()) {
			return matcher.group(2);
		}

		return source;
	}
}
