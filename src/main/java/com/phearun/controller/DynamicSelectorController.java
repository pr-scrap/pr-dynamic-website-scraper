package com.phearun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phearun.model.DynamicWebsite;
import com.phearun.repository.DynamicWebsiteRepository;
import com.phearun.repository.FieldSelectorRepository;

@Controller
@RequestMapping("/dynamic")
public class DynamicSelectorController {
	
	@Autowired
	private DynamicWebsiteRepository repo;
	
	@Autowired
	private FieldSelectorRepository fieldSelectorRepo;
	
	@GetMapping("/create")
	public String addWebsitePage(DynamicWebsite dynamicWebsite, Model model) {
		model.addAttribute("dynamicWebsite", dynamicWebsite);
		model.addAttribute("addStatus", true);
		return "dynamic";
	}
	
	@GetMapping("/edit")
	public String editWebsitePage(@RequestParam("id") Integer websiteId, Model model) {
		System.out.println(websiteId);
		model.addAttribute("dynamicWebsite", repo.findOne(websiteId));
		model.addAttribute("addStatus", false);
		return "dynamic";
	}
	
	@ResponseBody
	@PostMapping("/create")
	public ResponseEntity<?> postDynamicWebsite(@RequestBody DynamicWebsite dynamicWebsite) {
		System.out.println(dynamicWebsite);
		dynamicWebsite.setFieldSelectors(dynamicWebsite.getFieldSelectors());
		repo.save(dynamicWebsite);
		return ResponseEntity.ok(true);
	}
	
	@ResponseBody
	@PutMapping("/create")
	public ResponseEntity<?> updateDynamicWebsite(@RequestBody DynamicWebsite dynamicWebsite) {
		System.out.println("update: " + dynamicWebsite);
		System.out.println(dynamicWebsite.getFieldSelectors());
		
		fieldSelectorRepo.removeFieldSelectorByWebsiteId(dynamicWebsite.getId());
		dynamicWebsite.setFieldSelectors(dynamicWebsite.getFieldSelectors());
		
		repo.save(dynamicWebsite);
		return ResponseEntity.ok(true);
	}
	
	@ResponseBody
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeWebsiteByWebsiteId(@PathVariable Integer id) {
		repo.delete(id);
		return ResponseEntity.ok(true);
	}
	
	
}
