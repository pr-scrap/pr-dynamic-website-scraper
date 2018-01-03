package com.phearun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.phearun.model.ContentType;
import com.phearun.model.DynamicWebsite;
import com.phearun.model.FieldSelector;

public class DynamicFieldScrap {

	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2";

	public static void main(String[] args) throws IOException {
		test();
	}

	public static void test() throws IOException {

		List<FieldSelector> fieldSelectors = new ArrayList<>();
		fieldSelectors.add(new FieldSelector("title", "div.title span.web", ContentType.TEXT));
		fieldSelectors.add(new FieldSelector("link", "a", ContentType.LINK));
		fieldSelectors.add(new FieldSelector("date", "span.pub-date", ContentType.TEXT));

		DynamicWebsite dynamic = new DynamicWebsite();
		dynamic.setUrlToScrap("http://news.sabay.com.kh/topics/technology");
		dynamic.setRowSelector("div.list-item");
		dynamic.setFieldSelectors(fieldSelectors);

		Document doc = Jsoup.connect(dynamic.getUrlToScrap()).userAgent(USER_AGENT).get();
		List<Map<String, String>> results = new ArrayList<>();

		if (dynamic.getRowSelector() != null) {
			Elements elements = doc.select(dynamic.getRowSelector());
			elements.forEach(element -> {
				Map<String, String> result = new HashMap<>();
				dynamic.getFieldSelectors().forEach(fieldSelector -> {
					String data = null;
					if (fieldSelector.getContentType() == ContentType.LINK) {
						data = element.select(fieldSelector.getSelector()).attr("href");
					} else if (fieldSelector.getContentType() == ContentType.IMAGE) {
						data = element.select(fieldSelector.getSelector()).attr("src");
					} else {
						data = element.select(fieldSelector.getSelector()).text();
					}
					result.put(fieldSelector.getField(), data);
				});
				results.add(result);
			});
		}

		// TODO: Results
		results.forEach(result -> {
			result.forEach((k, v) -> {
				System.out.println(String.format("Result[field=%s, value=%s]", k, v));
			});
			System.out.println();
		});
	}

}
