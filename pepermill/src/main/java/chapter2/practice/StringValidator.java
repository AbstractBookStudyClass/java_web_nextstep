package chapter2.practice;

public class StringValidator {

	public boolean validateMinus(String input) {
		for (char c : input.toCharArray()) {
			if (c == '-') {
				throw new RuntimeException("음수는 포함할 수 없습니다.");
			}
		}
		return true;
	}

	public boolean validateEmpty(String input) {
		if (!input.isEmpty()) {
			return false;
		}
		return true;
	}
}
