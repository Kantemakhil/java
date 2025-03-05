package net.syscon.s4.eoffender.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadDocumentsBean  extends BaseModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("documentList")
	private List<Document> uploadedDocumentList;


	@JsonProperty("eoffenderDetails")
	private EoffenderDetails eoffenderDetails;


	@JsonProperty("documentType")
	private String templateId;

	public List<Document> getUploadedDocumentList() {
		return uploadedDocumentList;
	}

	public void setUploadedDocumentList(List<Document> uploadedDocumentList) {
		this.uploadedDocumentList = uploadedDocumentList;
	}

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

}
