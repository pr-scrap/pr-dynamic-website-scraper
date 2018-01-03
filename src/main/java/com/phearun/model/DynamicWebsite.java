package com.phearun.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicWebsite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String websiteName;

	private String websiteUrl;

	private String urlToScrap;

	private String rowSelector;
	
	@OneToMany(mappedBy = "dynamicWebsite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FieldSelector> fieldSelectors;
	
	public void setFieldSelectors(List<FieldSelector> fieldSelectors){
		this.fieldSelectors = new ArrayList<>();
		fieldSelectors.forEach(fieldSelector -> {
			fieldSelector.setDynamicWebsite(this);
			this.fieldSelectors.add(fieldSelector);
		});
	}

	@Override
	public String toString() {
		return "DynamicWebsite [id=" + id + ", websiteName=" + websiteName + ", websiteUrl=" + websiteUrl
				+ ", urlToScrap=" + urlToScrap + ", rowSelector=" + rowSelector + "]";
	}
	
}
