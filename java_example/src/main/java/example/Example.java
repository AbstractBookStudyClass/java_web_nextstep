package example;

import java.util.concurrent.RecursiveTask;

public class Example {
	public static void main(String[] args) {
		FibonacciOld fibonacciOld = new FibonacciOld(20);
		Fibonacci fibonacci = new Fibonacci(20);

		long start = System.currentTimeMillis();
		System.out.println("Fib old : " + fibonacciOld.compute());
		long end = System.currentTimeMillis();
		System.out.println((end-start));

		long start = System.currentTimeMillis();
		System.out.println("Fib : " + fibonacci.compute());
		long end = System.currentTimeMillis();
		System.out.println((end-start));
	}

}

class FibonacciOld {
	private final int n;

	public FibonacciOld(final int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public Integer compute() {
		System.out.println(Thread.currentThread().getName());
		if (n <= 1)
			return n;
		FibonacciOld f1 = new FibonacciOld(n - 1);
		FibonacciOld f2 = new FibonacciOld(n - 2);
		return f2.compute() + f1.compute();
	}
}

class Fibonacci extends RecursiveTask<Integer> {
	private final int n;

	public Fibonacci(final int n) {
		this.n = n;
	}

	@Override
	public Integer compute() {
		System.out.println(Thread.currentThread().getName());
		if (n <= 1)
			return n;
		Fibonacci f1 = new Fibonacci(n - 1);
		f1.fork();
		Fibonacci f2 = new Fibonacci(n - 2);
		f2.fork();
		return f2.join() + f1.join();
	}
}
