package chapter3.webapp.web;

import java.util.Map;

public class RequestMap {

	private Map<String, String> headers;
	private Map<String, String> parameters;

	public RequestMap(final Map<String, String> headers, final Map<String, String> parameters) {
		this.headers = headers;
		this.parameters = parameters;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(final Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(final Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
