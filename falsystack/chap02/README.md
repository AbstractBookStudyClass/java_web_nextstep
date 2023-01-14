# 문자열 계산기 구현을 통한 테스트와 리팩토링

- 테스트는 한 번에 메소드 하나에만 집중해야 한다.
- JUnit은 매 테스트마다 인스턴스를 새로 생성해주는것을 선호한다.
    - 상태 값이 변경되어 다음 테스트에 영향을 주는것을 방지하기 위함이다.
```java
class CalculatorTest {
  private Calculator calc;

  @BeforeEach
  void beforeEach() {
    calc = new Calculator();
  } 
}
```