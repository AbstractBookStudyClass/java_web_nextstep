package example;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

public class ExampleTest {

	Consumer<String> function = text -> System.out.println(text);

	@Test
	void test() {
		function.accept("hi");
	}

}
