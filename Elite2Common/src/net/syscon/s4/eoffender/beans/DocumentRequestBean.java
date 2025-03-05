package net.syscon.s4.eoffender.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentRequestBean  extends BaseModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("eoffenderDetails")
	private EoffenderDetails eoffenderDetails;
	
	private Long offenderBookingId;
	private String moduleName;
	@JsonProperty("objectId")
	private String objectId;


	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Long getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(Long offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@JsonProperty("documentType")
	private String templateId;

	public EoffenderDetails getEoffenderDetails() {
		return eoffenderDetails;
	}

	public void setEoffenderDetails(EoffenderDetails eoffenderDetails) {
		this.eoffenderDetails = eoffenderDetails;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Override
	public String toString() {
		return "DocumentRequestBean [eoffenderDetails=" + eoffenderDetails + ", offenderBookingId=" + offenderBookingId
				+ ", moduleName=" + moduleName + ", objectId=" + objectId + ", templateId=" + templateId + "]";
	}
	

}
