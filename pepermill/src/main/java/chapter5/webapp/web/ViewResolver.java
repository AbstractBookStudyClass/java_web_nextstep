package chapter5.webapp.web;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chapter5.webapp.web.http.HttpResponse;

public class ViewResolver {

	private static final Logger log = LoggerFactory.getLogger(ViewResolver.class);
	private static final String RESOURCES_PATH = "src/main/resources/template";

	public void sendView(final HttpResponse response, final OutputStream out) throws IOException {
		sendView(getDocument(response), new DataOutputStream(out));
	}

	private byte[] getDocument(final HttpResponse httpResponse) throws IOException {
		byte[] header = httpResponse.getResponse().getBytes();
		byte[] body;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Path path = Paths.get(RESOURCES_PATH + httpResponse.getHtmlLocation());
		outputStream.write(header);
		outputStream.write(Files.readAllBytes(path));
		return outputStream.toByteArray();
	}

	private void sendView(byte[] document, DataOutputStream dos) throws IOException{
		dos.write(document, 0, document.length);
		dos.flush();
	}
}
