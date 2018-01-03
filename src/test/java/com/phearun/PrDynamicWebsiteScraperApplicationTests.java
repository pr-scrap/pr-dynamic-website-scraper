package com.phearun;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.phearun.model.ContentType;
import com.phearun.model.DynamicWebsite;
import com.phearun.model.FieldSelector;
import com.phearun.repository.DynamicWebsiteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrDynamicWebsiteScraperApplicationTests {

	@Autowired
	DynamicWebsiteRepository repo;
	
	@Test
	public void contextLoads() {
		
		List<FieldSelector> fieldSelectors = new ArrayList<>();
		fieldSelectors.add(new FieldSelector("title", "div.title span.web", ContentType.TEXT));
		fieldSelectors.add(new FieldSelector("link", "a", ContentType.LINK));
		fieldSelectors.add(new FieldSelector("date", "span.pub-date", ContentType.TEXT));

		DynamicWebsite dynamic = new DynamicWebsite();
		dynamic.setUrlToScrap("http://news.sabay.com.kh/topics/technology");
		dynamic.setRowSelector("div.list-item");
		dynamic.setFieldSelectors(fieldSelectors);
		
		repo.save(dynamic);
	}

	@Transactional
	@Test
	public void test(){
		DynamicWebsite dynamic = repo.findOne(205);
		System.out.println(dynamic);
		dynamic.getFieldSelectors().forEach(fs->{
			System.out.println(fs);
		});
	}
	
}
