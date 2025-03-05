package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenceByStatute extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("act")
	private String act;
	
	@JsonProperty("legislativeBody")
	private String legislativeBody;
	
	@JsonProperty("hoCode")
	private String hoCode;
	
	@JsonProperty("severityRanking")
	private String severityRanking;
	
	@JsonProperty("statuteCode")
	private String statuteCode;
	
	@JsonProperty("offenceId")
	private Long offenceId;

	@JsonProperty("activeFlag")
	private String activeFlag;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getLegislativeBody() {
		return legislativeBody;
	}

	public void setLegislativeBody(String legislativeBody) {
		this.legislativeBody = legislativeBody;
	}

	public String getHoCode() {
		return hoCode;
	}

	public void setHoCode(String hoCode) {
		this.hoCode = hoCode;
	}

	public String getSeverityRanking() {
		return severityRanking;
	}

	public void setSeverityRanking(String severityRanking) {
		this.severityRanking = severityRanking;
	}

	public String getStatuteCode() {
		return statuteCode;
	}

	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

	public Long getOffenceId() {
		return offenceId;
	}

	public void setOffenceId(Long offenceId) {
		this.offenceId = offenceId;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	


}