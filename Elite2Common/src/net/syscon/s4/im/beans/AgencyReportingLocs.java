package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class AgencyLocationCounts
 */
public class AgencyReportingLocs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("countTypeId")
	private Integer countTypeId;
	@JsonProperty("agySeq")
	private Integer agySeq;
	@JsonProperty("locationType")
	private String locationType;
	@JsonProperty("location1Id")
	private Integer location1Id;
	@JsonProperty("location2Id")
	private Integer location2Id;
	@JsonProperty("location3Id")
	private Integer location3Id;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	private boolean inserted;
	@JsonProperty("location1Code")
	private String location1Code;
	@JsonProperty("location2Code")
	private String location2Code;
	@JsonProperty("location3Code")
	private String location3Code;
	@JsonProperty("activeCount")
	private Integer activeCount;
	/**
	 * @param countTypeId
	 *            countTypeId to set
	 */
	public void setCountTypeId(final Integer countTypeId) {
		this.countTypeId = countTypeId;
	}

	/**
	 * return thecountTypeId
	 */
	public Integer getCountTypeId() {
		return this.countTypeId;
	}

	/**
	 * @param agySeq
	 *            agySeq to set
	 */
	public void setAgySeq(final Integer agySeq) {
		this.agySeq = agySeq;
	}

	/**
	 * return theagySeq
	 */
	public Integer getAgySeq() {
		return this.agySeq;
	}

	/**
	 * @param locationType
	 *            locationType to set
	 */
	public void setLocationType(final String locationType) {
		this.locationType = locationType;
	}

	/**
	 * return thelocationType
	 */
	public String getLocationType() {
		return this.locationType;
	}

	/**
	 * @param location1Id
	 *            location1Id to set
	 */
	public void setLocation1Id(final Integer location1Id) {
		this.location1Id = location1Id;
	}

	/**
	 * return thelocation1Id
	 */
	public Integer getLocation1Id() {
		return this.location1Id;
	}

	/**
	 * @param location2Id
	 *            location2Id to set
	 */
	public void setLocation2Id(final Integer location2Id) {
		this.location2Id = location2Id;
	}

	/**
	 * return thelocation2Id
	 */
	public Integer getLocation2Id() {
		return this.location2Id;
	}

	/**
	 * @param location3Id
	 *            location3Id to set
	 */
	public void setLocation3Id(final Integer location3Id) {
		this.location3Id = location3Id;
	}

	/**
	 * return thelocation3Id
	 */
	public Integer getLocation3Id() {
		return this.location3Id;
	}

	/**
	 * @param listSeq
	 *            listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * return thelistSeq
	 */
	public Integer getListSeq() {
		return this.listSeq;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param activeFlag
	 *            activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * return theactiveFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * @param expiryDate
	 *            expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * return theexpiryDate
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	public String getLocation1Code() {
		return location1Code;
	}

	public void setLocation1Code(String location1Code) {
		this.location1Code = location1Code;
	}

	public String getLocation2Code() {
		return location2Code;
	}

	public void setLocation2Code(String location2Code) {
		this.location2Code = location2Code;
	}

	public String getLocation3Code() {
		return location3Code;
	}

	public void setLocation3Code(String location3Code) {
		this.location3Code = location3Code;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	public Integer getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(Integer activeCount) {
		this.activeCount = activeCount;
	}

}