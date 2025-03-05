package net.syscon.s4.sa.recordmaintenance;

import java.util.Map;

public class ProcessDescription {
	private Map<String, Object> uniqueId;
	
	private Map<String, Object> formData;
	
	private String formType;

	public Map<String, Object> getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Map<String, Object> uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Map<String, Object> getFormData() {
		return formData;
	}

	public void setFormData(Map<String, Object> formData) {
		this.formData = formData;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}
	
}
