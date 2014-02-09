package de.adv_boeblingen.seegerj.amed.lernsoftware.misc;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.http.client.utils.URIBuilder;

public class UriBuilder
		extends URIBuilder {
	private final ArrayList<String> pathList = new ArrayList<String>();

	@Override
	public String getPath() {
		StringBuilder builder = new StringBuilder();

		for (String pathElements : pathList) {
			builder.append("/").append(pathElements);
		}

		return builder.toString();
	}

	public void appendPathElement(String pathElement) {
		pathList.add(pathElement);
		refreshParent();
	}

	private void refreshParent() {
		super.setPath(getPath());
	}

	@Override
	public URIBuilder setPath(String path) {
		pathList.clear();

		StringTokenizer tokenizer = new StringTokenizer(path, "/");
		while (tokenizer.hasMoreElements()) {
			String pathElement = tokenizer.nextToken();
			pathList.add(pathElement);
		}

		refreshParent();
		return this;
	}
}
