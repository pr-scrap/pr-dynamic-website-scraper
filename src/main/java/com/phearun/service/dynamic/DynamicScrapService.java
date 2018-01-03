package com.phearun.service.dynamic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.phearun.model.ContentType;
import com.phearun.model.DynamicWebsite;

@Transactional
@Service
public class DynamicScrapService {

	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2";

	public Map<String, Object> scrap(DynamicWebsite dynamic) throws IOException {

		Document doc = Jsoup.connect(dynamic.getUrlToScrap()).userAgent(USER_AGENT).get();

		Map<String, Object> wrapResults = new HashMap<>();
		wrapResults.put("website", dynamic.getWebsiteName());
		wrapResults.put("url", dynamic.getWebsiteUrl());
		wrapResults.put("scrap_url", dynamic.getUrlToScrap());

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
		wrapResults.put("contents", results);

		return wrapResults;
	}
}
