package chapter3.webapp.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewResolver {

	private static final Logger log = LoggerFactory.getLogger(ViewResolver.class);
	private static final String RESOURCES_PATH = "src/main/resources/template";

	public byte[] getView(View view) {
		byte[] header = view.getHeader();
		byte[] body = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// 실제 html 문서와 헤더를 더해서 전달한다.
		try {
			body = getDocument(view);
			view.setLengthOfBodyContent(body.length);
			header = View.from(view, view.getHttpStatusCode());

			log.debug("----Header----");
			log.debug(new String(header));
			outputStream.write(header);

			if (!view.getHttpStatusCode().equals("302")){
				outputStream.write(body);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException("문서가 존재하지 않습니다");
		}

		log.debug("====Path====");
		log.debug(new String(outputStream.toByteArray()));
		return outputStream.toByteArray();
	}

	private byte[] getDocument(View view) throws IOException {
		String url;
		if (view.getUrl() == null) {
			url = view.getLocation();
		} else {
			url = view.getUrl();
		}

		if (view.getBody() != null) {
			return view.getBody().getBytes();
		} else {
			Path path = Paths.get(RESOURCES_PATH + url);
			return Files.readAllBytes(path);
		}
	}
}
