package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class BaseHelpUrl extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("baseHelpPdfUrl")
	private String baseHelpPdfUrl;
	
	@JsonProperty("baseHelpVideoUrl")
	private String baseHelpVideoUrl;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("pId")
	private Integer pid;
	
	@JsonProperty("baseHelpHtmlUrl")
	private String baseHelpHtmlUrl;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	
	public Integer getPid() {
		return pid;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBaseHelpPdfUrl() {
		return baseHelpPdfUrl;
	}


	public void setBaseHelpPdfUrl(String baseHelpPdfUrl) {
		this.baseHelpPdfUrl = baseHelpPdfUrl;
	}


	public String getBaseHelpVideoUrl() {
		return baseHelpVideoUrl;
	}


	public void setBaseHelpVideoUrl(String baseHelpVideoUrl) {
		this.baseHelpVideoUrl = baseHelpVideoUrl;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public BaseHelpUrl() {
		// OmsModules
	}
	
	public String getBaseHelpHtmlUrl() {
			return baseHelpHtmlUrl;
		}
		
		
	public void setBaseHelpHtmlUrl(String baseHelpHtmlUrl) {
		this.baseHelpHtmlUrl = baseHelpHtmlUrl;
		}


	public String getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	/**
	 * @return the code
	 */
	
}