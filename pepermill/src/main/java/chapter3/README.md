## 요구사항

### 1. http://localhost:8080/index.html 으로 접속하면 index.html 제공

### 2. GET http://localhost:8080/user/form.html 회원가입이 가능하다.

- 웹 로직과 비지니스 로직을 분리하기 위해서 reflection을 사용한다.
- 파리미터로 전달되어도 비지니스 로직은 동일하게 동작해야 한다.
- /user/form.html?name=hello&password=world

### 3. POST http://localhost:8080/user/form.html 회원가입이 가능하다.

- 바디에 저장되어도 비지니스 로직은 동일하게 동작해야 한다.

### 4. 회원가입에 성공하면 302 응답코드를 통해서 http://localhost:808/index.html 이동 가능하다.

- 사용자가 상태코드와 리다렉트 경로를 설정할 수 있다.

### 5. http://localhost:8080/user/login.html 으로 로그인이 가능하다.

- 로그인에 성공할 경우 cookie에 login=true, 실패할 경우 false를 넣는다.
- 로그인에 성공하면 index.html으로 이동한다.
- 로그인에 실패하면 login-fail.html으로 이동한다.

### 6. http://localhost:8080/user/list 에서 회원 목록을 조회할 수 있다.

- url이 문서를 지정하지 않아도 문서로 전달 가능해야 한다.

### 7. CSS를 지원한다.
