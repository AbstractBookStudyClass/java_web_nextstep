package chapter2.before;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public int subtract(int a, int b) {
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator();

		int number1 = 20;
		int number2 = 10;

		System.out.println(calculator.add(number1, number2));
		System.out.println(calculator.subtract(number1, number2));
		System.out.println(calculator.multiply(number1, number2));
		System.out.println(calculator.divide(number1, number2));
	}
}
