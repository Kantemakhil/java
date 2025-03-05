package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the STG_IDENTIFIERS database table.
 * 
 */
public class StgIdentifiers implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("detail")
	private String detail;
	@JsonProperty("imageData")
	private byte[] imageData;
	@JsonProperty("imageSize")
	private BigDecimal imageSize;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("profileType")
	private String profileType;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("stgId")
	private Long stgId;
	@JsonProperty("identifierSeq")
	private Long identifierSeq;

	public StgIdentifiers() {
	}

	public Long getStgId() {
		return stgId;
	}

	public void setStgId(final Long stgId) {
		this.stgId = stgId;
	}

	public Long getIdentifierSeq() {
		return identifierSeq;
	}

	public void setIdentifierSeq(final Long identifierSeq) {
		this.identifierSeq = identifierSeq;
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(final String detail) {
		this.detail = detail;
	}

	public byte[] getImageData() {
		return this.imageData;
	}

	public void setImageData(final byte[] imageData) {
		this.imageData = imageData;
	}

	public BigDecimal getImageSize() {
		return this.imageSize;
	}

	public void setImageSize(final BigDecimal imageSize) {
		this.imageSize = imageSize;
	}

	public Object getModifyDatetime() {
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

	public String getProfileType() {
		return this.profileType;
	}

	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
