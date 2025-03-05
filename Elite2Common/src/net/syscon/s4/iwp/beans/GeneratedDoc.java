package net.syscon.s4.iwp.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneratedDoc implements Serializable{
	
	private String generatedDocLocation;
	private Map<String, List<String>> bookmarValueMap;
	@JsonProperty("templateContent")
	private byte[] templateContent;
	
	private String templateType;
	public String getGeneratedDocLocation() {
		return generatedDocLocation;
	}
	public void setGeneratedDocLocation(String generatedDocLocation) {
		this.generatedDocLocation = generatedDocLocation;
	}
	public Map<String, List<String>> getBookmarValueMap() {
		return bookmarValueMap;
	}
	public void setBookmarValueMap(Map<String, List<String>> bookmarValueMap) {
		this.bookmarValueMap = bookmarValueMap;
	}
	public byte[] getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(byte[] templateContent) {
		this.templateContent = templateContent;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

}
