package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputState extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5550236272035698443L;
	
	private String id;
	private String uri;
	private String value;
	private Object[] options;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Object[] getOptions() {
		return options;
	}
	public void setOptions(Object[] options) {
		this.options = options;
	}
	
	
}
