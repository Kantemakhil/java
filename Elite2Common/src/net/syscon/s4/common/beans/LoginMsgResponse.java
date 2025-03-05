package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LoginMsgResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("languages")
	private List<SelectOption> languages;
	
	@JsonProperty("lang")
	private String lang;
		
	@JsonProperty("msgs")
	private Map<String, String> msgs;
	
	/**
	 * @return the languages
	 */
	public List<SelectOption> getLanguages() {
		return languages;
	}

	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(List<SelectOption> languages) {
		this.languages = languages;
	}

	/**
	 * @return the msgs
	 */
	public Map<String, String> getMsgs() {
		return msgs;
	}

	/**
	 * @param msgs the msgs to set
	 */
	public void setMsgs(Map<String, String> msgs) {
		this.msgs = msgs;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

}
