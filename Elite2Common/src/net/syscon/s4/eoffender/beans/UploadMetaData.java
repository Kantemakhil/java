package net.syscon.s4.eoffender.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class UploadMetaData extends BaseModel implements Serializable{

	
	public UploadMetaData(){
		
	}
	
	public UploadMetaData(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("label")
	private String label;
	
	@JsonProperty("value")
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
