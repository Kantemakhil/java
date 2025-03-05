package net.syscon.s4.eoffender.beans;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DBDocument {
	
	@JsonProperty("id_generated")
	private String idGenerated;
	
	@JsonProperty("documentId")
	private String documentId;
	
	@JsonProperty("offender_book_id")
	private String offenderBookId;
	
	@JsonProperty("offenderBookingNo")
	private String offenderBookingNo;
	
	@JsonProperty("template_name")
	private String templateName;
	
	@JsonProperty("file_data")
	private byte[] fileData;
	
	@JsonProperty("document_name")
	private String documentName;
	
	@JsonProperty("offDOB")
	private String offDOB;
	
	@JsonProperty("documentAuthor")
	private String documentAuthor;

	@JsonProperty("trimUser")
	private String trimUser;
	
	@JsonProperty("fileEXt")
	private String fileExt;
	
	@JsonProperty("status")
	private String status;
	
	private String offednerName;
	
	public String getOffDOB() {
		return offDOB;
	}

	public void setOffDOB(String offDOB) {
		this.offDOB = offDOB;
	}

	public String getDocumentAuthor() {
		return documentAuthor;
	}

	public void setDocumentAuthor(String documentAuthor) {
		this.documentAuthor = documentAuthor;
	}

	public String getTrimUser() {
		return trimUser;
	}

	public void setTrimUser(String trimUser) {
		this.trimUser = trimUser;
	}

	public String getOffenderDisplayId() {
		return offenderDisplayId;
	}

	public void setOffenderDisplayId(String offenderDisplayId) {
		this.offenderDisplayId = offenderDisplayId;
	}

	@JsonProperty("offenderDisplayId")
	private String offenderDisplayId;
	

	
	public String getIdGenerated() {
		return idGenerated;
	}

	public void setIdGenerated(String idGenerated) {
		this.idGenerated = idGenerated;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getOffednerName() {
		return offednerName;
	}

	public void setOffednerName(String offednerName) {
		this.offednerName = offednerName;
	}

	public String getOffenderBookingNo() {
		return offenderBookingNo;
	}

	public void setOffenderBookingNo(String offenderBookingNo) {
		this.offenderBookingNo = offenderBookingNo;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DBDocument [idGenerated=" + idGenerated + ", documentId=" + documentId + ", offenderBookId="
				+ offenderBookId + ", offenderBookingNo=" + offenderBookingNo + ", templateName=" + templateName
				+ ", fileData=" + Arrays.toString(fileData) + ", documentName=" + documentName + ", offDOB=" + offDOB
				+ ", documentAuthor=" + documentAuthor + ", trimUser=" + trimUser + ", fileExt=" + fileExt + ", status="
				+ status + ", offednerName=" + offednerName + ", offenderDisplayId=" + offenderDisplayId + "]";
	}
	
	
}
