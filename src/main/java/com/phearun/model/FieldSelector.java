package com.phearun.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldSelector {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String field;

	private String selector;

	private ContentType contentType;

	@ManyToOne
	private DynamicWebsite dynamicWebsite;

	public FieldSelector(String field, String selector, ContentType contentType) {
		super();
		this.field = field;
		this.selector = selector;
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "FieldSelector [id=" + id + ", field=" + field + ", selector=" + selector + ", contentType="
				+ contentType + "]";
	}

}
