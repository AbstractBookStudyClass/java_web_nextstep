package chapter3.webapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewResolver {

	private static final Logger log = LoggerFactory.getLogger(ViewResolver.class);
	private static final String RESOURCES_PATH = "src/main/resources/template";

	public byte[] getView(final String url) throws IOException {
		Path path = Paths.get(RESOURCES_PATH + url);
		return Files.readAllBytes(path);
	}
}
