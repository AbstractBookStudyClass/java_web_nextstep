package chapter3.webapp.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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

		// for debug
		RequestForm requestForm = new RequestForm("Test", "test");

		// 요청 정보를 가지고 경로를 설정한다.
		// controller가 늘어나면서 계속 추가해야 함. 어떻게 개선?
		// 파라미터 수도 다르기 때문에 파라미터 형식에 맞출 필요가 있다.
		Method[] methods = className.getMethods();
		for (Method method : methods) {
			Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
			for (Annotation annotation : declaredAnnotations) {
				try {
					if (annotation instanceof GetMapping
						&& url.equals(((GetMapping)annotation).value())
						&& httpMethod.equals(((GetMapping)annotation).method())
					) {
						return result = (View) method.invoke(instance, requestForm);
					} else if (annotation instanceof PostMapping
						&& url.equals(((PostMapping)annotation).value())
						&& httpMethod.equals(((PostMapping)annotation).method())
					) {
						return result = (View)method.invoke(instance, requestForm);
					}
					// } else if (annotation instanceof GetMapping
					// 	&& url.equals(((GetMapping)annotation).value())
					// 	&& httpMethod.equals(((GetMapping)annotation).method())
					// ) {
					//
					// }
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
