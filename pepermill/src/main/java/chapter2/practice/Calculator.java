package chapter2.practice;

import java.util.LinkedList;
import java.util.List;

public class Calculator {

	private final List<Integer> stack = new LinkedList<>();

	public int add(String delimiter, String input) {
		String[] numbers = input.split("\\"+delimiter);
		for (String number : numbers) {
			if (stack.size() == 2) {
				int b = pop();
				int a = pop();
				push(a+b);
			}
			push(Integer.parseInt(number));
		}
		return pop() + pop();
	}

	private int pop() {
		int lastIndex = stack.size()-1;
		int poppedValue = stack.get(lastIndex);
		stack.remove(lastIndex);
		return poppedValue;
	}

	private void push(int number) {
		stack.add(number);
	}
}
