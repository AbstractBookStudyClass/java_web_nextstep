## 요구사항

### 1. 서블릿에서 지원하는 HttpSession API의 일부를 구현한다.
- 구현할 메소드 목록
  - getId() : 현재 세션에 할당되어 있는 고유한 세션 아이디를 반환
  - setAttribute(String name, Object value) : 현재 세션에 value 인자로 전달되는 객체를 name 인자 이름으로 저장
  - getAttribute(String name) : 현재 세션에 name 인자로 저장되어 있는 객체 값을 찾아 반환 
  - removeAttribute(String name) : 현재 세션에 name 인자로 저장되어 있는 객체 값을 삭제
  - invalidate() : 현재 세션에 저장되어 있는 모든 값을 삭제



- id를 저장할 때는 uuid를 사용한다.
- 쿠키를 세션에 저장한다.
- 세션을 관리하는 클래스를 구현한다.