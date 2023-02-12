package chapter5.webapp.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter5.webapp.web.annotation.GetMapping;
import chapter5.webapp.web.annotation.PostMapping;
import chapter5.webapp.web.http.HttpRequest;
import chapter5.webapp.web.http.HttpResponse;

public class MethodHandler {

	private static final Logger log = LoggerFactory.getLogger(MethodHandler.class);

	private final Class<?> className;
	// 인스턴스를 하드 코딩해야 함. 어떻게 개선해야 하는가?
	private final Object instance;

	public MethodHandler(final Class<?> className, final Object instance) {
		this.className = className;
		this.instance = instance;
	}

	/**
	 * HttpRequest, HttpResponse를 컨트롤러에게 처리를 넘기는 메서드
	 * 모든 컨트롤러는 request와 response를 가진다.
	 *
	 * @param request
	 * @param response
	 */
	public void invokeMethod(HttpRequest request, HttpResponse response) {
		String url = request.getUrl();

		// 복잡한 코드. 개선이 필요
		Method[] methods = className.getMethods();
		for (Method method : methods) {
			Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
			for (Annotation annotation : declaredAnnotations) {
				try {
					if (annotation instanceof GetMapping && // GET Method
						url.equals(((GetMapping)annotation).value()) // GET url
					) {
						method.invoke(instance, request, response);
					} else if (annotation instanceof PostMapping && // POST Method
						url.equals(((PostMapping)annotation).value()) // POST url
					) {
						method.invoke(instance, request, response);
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
	}
}
