package chapter5.webapp.web;

public class View {

	private static final String END_WITH = "\r\n";
	private static final String HTTP_1_1 = "HTTP/1.1";
	private static final String TEXT_HTML = "text/html";
	private static final String CONTENT_TYPE = "Content-Type:";
	private static final String CONTENT_LENGTH = "Content-Length:";
	private static final String LOCATION = "Location:";
	private static final String HOST = "http://127.0.0.1:8080";
	private static final String COOKIE = "Set-Cookie:";
	private static final String CONNECTION = "Connection:";
	private static final String KEEP_ALIVE = "Keep-Alive:";

	private String httpVersion;
	private String httpStatusCode;
	private String httpStatusDescription;
	private String location;
	private String contentType;
	private String url;
	private String cookie;
	private String connection;
	private String keepAlive;
	private String body;
	private int lengthOfBodyContent;
	private byte[] header;

	public View() {
		this.httpVersion = HTTP_1_1;
		this.httpStatusCode = HttpStatusCode.OK.getHttpStatusCode();
		this.httpStatusDescription = HttpStatusCode.OK.getHttpStatusDescription();
		this.location = "/index.html";
		this.url = null;
		this.cookie = null;
		this.connection = "Keep-alive";
		this.keepAlive = "timeout=5, max=1000";
		this.body = null;
		this.contentType = TEXT_HTML;
		this.lengthOfBodyContent = 0;
	}

	public static byte[] from(View view, String httpStatusCode) {
		if (httpStatusCode.equals(HttpStatusCode.OK.getHttpStatusCode())) {
			return response200Header(view).getBytes();
		} else if (httpStatusCode.equals(HttpStatusCode.FOUND.getHttpStatusCode())) {
			return response302Header(view).getBytes();
		}
		return null;
	}

	private static String response200Header(View view) {
		StringBuilder builder = new StringBuilder();
		builder.append(view.httpVersion).append(" ")
			.append(view.httpStatusCode).append(" ")
			.append(view.httpStatusDescription).append(" ").append(END_WITH);
		builder.append(CONTENT_TYPE).append(" ")
			.append(view.contentType).append(END_WITH);
		builder.append(CONNECTION).append(" ")
				.append(view.connection).append(END_WITH);
		builder.append(KEEP_ALIVE).append(" ")
				.append(view.keepAlive).append(END_WITH);
		builder.append(CONTENT_LENGTH).append(" ")
			.append(view.lengthOfBodyContent).append(END_WITH);
		builder.append(COOKIE).append(" ")
			.append(view.cookie).append(END_WITH);
		builder.append(END_WITH);

		return builder.toString();
	}

	private static String response302Header(View view) {
		StringBuilder builder = new StringBuilder();
		builder.append(view.httpVersion).append(" ")
			.append(view.httpStatusCode).append(" ")
			.append(view.httpStatusDescription).append(" ").append(END_WITH);
		builder.append(LOCATION).append(" ")
			.append(HOST)
			.append(view.location).append(END_WITH);
		builder.append(CONNECTION).append(" ")
			.append(view.connection).append(END_WITH);
		builder.append(KEEP_ALIVE).append(" ")
			.append(view.keepAlive).append(END_WITH);
		builder.append(CONTENT_TYPE).append(" ")
			.append(view.contentType).append(END_WITH);
		builder.append(CONTENT_LENGTH).append(" ")
			.append(view.lengthOfBodyContent).append(END_WITH);
		builder.append(COOKIE).append(" ")
			.append(view.cookie).append("; Max-Age=1000").append("; Path=/").append(END_WITH);
		builder.append(END_WITH);

		return builder.toString();
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(final String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(final String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

	public int getLengthOfBodyContent() {
		return lengthOfBodyContent;
	}

	public void setLengthOfBodyContent(final int lengthOfBodyContent) {
		this.lengthOfBodyContent = lengthOfBodyContent;
	}

	public String getHttpStatusDescription() {
		return httpStatusDescription;
	}

	public void setHttpStatusDescription(final String httpStatusDescription) {
		this.httpStatusDescription = httpStatusDescription;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public byte[] getHeader() {
		return header;
	}

	public void setHeader(final byte[] header) {
		this.header = header;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(final String cookie) {
		this.cookie = cookie;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(final String connection) {
		this.connection = connection;
	}

	public String getKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(final String keepAlive) {
		this.keepAlive = keepAlive;
	}

	public String getBody() {
		return body;
	}

	public void setBody(final String body) {
		this.body = body;
	}
}
