package chapter3.webapp.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter3.webapp.web.annotation.GetMapping;
import chapter3.webapp.web.annotation.PostMapping;

public class MethodHandler {

	private static final Logger log = LoggerFactory.getLogger(MethodHandler.class);

	private final Class<?> className;
	private final Object instance;

	public MethodHandler(final Class<?> className, final Object instance) {
		this.className = className;
		this.instance = instance;
	}

	public View createResponseView(Map<String, String> requestMap) {
		String httpMethod = requestMap.get("Method");
		String url = requestMap.get("Url");
		View result = null;

		// js, css는 도메인 로직이 아니기 때문에 도메인으로 넘겨서는 안된다.
		if (url.contains(".css")) {
			result = new View();
			result.setHttpStatusCode(HttpStatusCode.OK.getHttpStatusCode());
			result.setHttpStatusDescription(HttpStatusCode.OK.getHttpStatusDescription());
			result.setLocation("/css/styles.css");
			result.setContentType("text/css");

			return result;
		}

		// 만약 파라미터가 있다면, 해당 파라미터 form을 만든다.
		// 편의를 위해서 정해진 form만 생성
		RequestForm requestForm = new RequestForm();
		if (requestMap.containsKey("Parameters")) {
			String parameters = requestMap.get("Parameters");
			String[] split = parameters.split("&");
			Map<String, String> map = Arrays.stream(split)
				.map(e -> e.split("=", 2))
				.collect(Collectors.toMap(s -> s[0], s -> s[1]));
			requestForm.setName(map.get("name"));
			requestForm.setPassword(map.get("password"));
		}

		// 요청 정보를 가지고 경로를 설정한다.
		// controller가 늘어나면서 계속 추가해야 함. 어떻게 개선?
		// 파라미터 수도 다르기 때문에 파라미터 형식에 맞출 필요가 있다.
		Method[] methods = className.getMethods();
		for (Method method : methods) {
			Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
			for (Annotation annotation : declaredAnnotations) {
				try {
					if (annotation instanceof GetMapping // get 파라미터가 있을 때, 호출
						&& url.equals(((GetMapping)annotation).value())
						&& httpMethod.equals(((GetMapping)annotation).method())
						&& requestMap.containsKey("Parameters")
					) {
						return result = (View) method.invoke(instance, requestForm);
					} else if (annotation instanceof PostMapping // post 파라미터가 있을 때, 호출
						&& url.equals(((PostMapping)annotation).value())
						&& httpMethod.equals(((PostMapping)annotation).method())
						&& requestMap.containsKey("Parameters")
					) {
						return result = (View) method.invoke(instance, requestForm);
					} else if (annotation instanceof GetMapping // get 파라미터 없을 때
						&& url.equals(((GetMapping)annotation).value())
						&& httpMethod.equals(((GetMapping)annotation).method())
					) {
						log.debug("GET - no parameters");
						return result = (View) method.invoke(instance);
					}
				} catch (InvocationTargetException e) {
					log.error("호출 대상 메소드가 없습니다.");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					log.error("접근 불가 메소드입니다.");
					e.printStackTrace();
				}
			}
		}
		// 받은 결과를 반환한다.
		return result;
	}
}
