package com.phearun.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phearun.repository.DynamicWebsiteRepository;
import com.phearun.service.dynamic.DynamicScrapService;

@Controller
public class ScrapController {
	
	private DynamicWebsiteRepository repo;
	private DynamicScrapService scrapService;
	
	public ScrapController(DynamicWebsiteRepository repo, DynamicScrapService scrapService) {
		this.repo = repo;
		this.scrapService = scrapService;
	}

	@RequestMapping({ "/", "home" })
	public String home(Model model) {
		model.addAttribute("websites", repo.findAll());
		return "index";
	}

	@GetMapping("/dynamic/list")
	public String listWebsitePage(Model model) {
		model.addAttribute("websites", repo.findAll());
		return "website";
	}
	
	@ResponseBody
	@GetMapping("/dynamic/scrap")
	public Map<String, Object> scrapByWebsiteId(@RequestParam Integer websiteId) throws IOException{
		System.out.println(websiteId);
		return scrapService.scrap(repo.findOne(websiteId));
	}
	
	
}
