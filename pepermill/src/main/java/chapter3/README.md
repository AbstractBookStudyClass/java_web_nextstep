## 요구사항

### 1. http://localhost:8080/index.html 으로 접속하면 index.html 제공

### 2. GET http://localhost:8080/user/form.html 회원가입이 가능하다.

- 웹 로직과 비지니스 로직을 분리하기 위해서 reflection을 사용한다.
- 파리미터로 전달되어도 비지니스 로직은 동일하게 동작해야 한다.
- /user/form.html?name=hello&password=world

### 3. POST http://localhost:8080/user/form.html 회원가입이 가능하다.

- 바디에 저장되어도 비지니스 로직은 동일하게 동작해야 한다.

### 4. 회원가입에 성공하면 302 응답코드를 통해서 http://localhost:8080/index.html 이동 가능하다.

- 사용자가 상태코드와 리다이렉트 경로를 설정할 수 있다.

### 5. http://localhost:8080/user/login.html 으로 로그인이 가능하다.

- 로그인에 성공할 경우 cookie에 login=true, 실패할 경우 false를 넣는다.
- 로그인에 성공하면 index.html으로 이동한다.
- 로그인에 실패하면 login-fail.html으로 이동한다.
- 리다이렉트가 되어도 쿠키는 유지가 되어야 한다.

### 6. http://localhost:8080/user/list 에서 회원 목록을 조회할 수 있다.

- 가입된 회원 정보를 전체 조회할 수 있다.
- 객체로 받아도 문자열로 바꿔 기록할 수 있어야 한다.
  - 받은 객체를 리플렉션으로 가져와서 문자열로 변환하는가?
  - 객체가 toString을 오버라이딩 했다고 가정하고 toString을 사용하는가?
- 바디에 회원 list를 전달한다.

### 7. CSS를 지원한다.

- html 문서와 함께 css 파일을 전달한다.
