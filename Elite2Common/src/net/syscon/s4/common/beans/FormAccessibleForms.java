package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the FORM_ACCESSIBLE_FORMS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormAccessibleForms extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("originatingForm")
	private String originatingForm;
	@JsonProperty("destinationForm")
	private String destinationForm;
	@JsonProperty("description")
	private String description;
	@JsonProperty("stgId")
	private BigDecimal stgId;
	@JsonProperty("checkFlag")
	private String checkFlag;
	
	@JsonProperty("chkData")
	private String chkData;
	
	
	
	
	public String getChkData() {
		return chkData;
	}

	public void setChkData(String chkData) {
		this.chkData = chkData;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getBookId() {
		return bookId;
	}

	public void setBookId(BigDecimal bookId) {
		this.bookId = bookId;
	}

	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("bookId")
	private BigDecimal  bookId;
	
	public FormAccessibleForms() {
		// FormAccessibleForms
	}

	public String getOriginatingForm() {
		return originatingForm;
	}

	public void setOriginatingForm(final String originatingForm) {
		this.originatingForm = originatingForm;
	}

	public String getDestinationForm() {
		return destinationForm;
	}

	public void setDestinationForm(final String destinationForm) {
		this.destinationForm = destinationForm;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the stgId
	 */
	public BigDecimal getStgId() {
		return stgId;
	}

	/**
	 * @param stgId
	 *            the stgId to set
	 */
	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

	/**
	 * @return the checkFlag
	 */
	public String getCheckFlag() {
		return checkFlag;
	}

	/**
	 * @param checkFlag
	 *            the checkFlag to set
	 */
	public void setCheckFlag(final String checkFlag) {
		this.checkFlag = checkFlag;
	}

}
