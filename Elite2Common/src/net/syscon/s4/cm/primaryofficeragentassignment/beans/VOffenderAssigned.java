package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VOffenderAssigned
 */
public class VOffenderAssigned extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("sacStaffId")
	private Long sacStaffId;

	@JsonProperty("offenderLastName")
	private String offenderLastName;

	@JsonProperty("offenderFirstName")
	private String offenderFirstName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("staffLastName")
	private String staffLastName;

	@JsonProperty("staffFirstName")
	private String staffFirstName;

	@JsonProperty("transferFlag")
	private String transferFlag;

	@JsonProperty("extTransferId")
	private Long extTransferId;

	@JsonProperty("agyLocIdFrom")
	private String agyLocIdFrom;

	@JsonProperty("agyIocIdTo")
	private String agyIocIdTo;

	@JsonProperty("transferDate")
	private Date transferDate;

	@JsonProperty("assStaffId")
	private Long assStaffId;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the sacStaffId
	 */
	public Long getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @param sacStaffId the sacStaffId to set
	 */
	public void setSacStaffId(Long sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return offenderLastName;
	}

	/**
	 * @param offenderLastName the offenderLastName to set
	 */
	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	/**
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	/**
	 * @param offenderFirstName the offenderFirstName to set
	 */
	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the staffLastName
	 */
	public String getStaffLastName() {
		return staffLastName;
	}

	/**
	 * @param staffLastName the staffLastName to set
	 */
	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	/**
	 * @return the staffFirstName
	 */
	public String getStaffFirstName() {
		return staffFirstName;
	}

	/**
	 * @param staffFirstName the staffFirstName to set
	 */
	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	/**
	 * @return the transferFlag
	 */
	public String getTransferFlag() {
		return transferFlag;
	}

	/**
	 * @param transferFlag the transferFlag to set
	 */
	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}

	/**
	 * @return the extTransferId
	 */
	public Long getExtTransferId() {
		return extTransferId;
	}

	/**
	 * @param extTransferId the extTransferId to set
	 */
	public void setExtTransferId(Long extTransferId) {
		this.extTransferId = extTransferId;
	}

	/**
	 * @return the agyLocIdFrom
	 */
	public String getAgyLocIdFrom() {
		return agyLocIdFrom;
	}

	/**
	 * @param agyLocIdFrom the agyLocIdFrom to set
	 */
	public void setAgyLocIdFrom(String agyLocIdFrom) {
		this.agyLocIdFrom = agyLocIdFrom;
	}

	/**
	 * @return the agyIocIdTo
	 */
	public String getAgyIocIdTo() {
		return agyIocIdTo;
	}

	/**
	 * @param agyIocIdTo the agyIocIdTo to set
	 */
	public void setAgyIocIdTo(String agyIocIdTo) {
		this.agyIocIdTo = agyIocIdTo;
	}

	/**
	 * @return the transferDate
	 */
	public Date getTransferDate() {
		return transferDate;
	}

	/**
	 * @param transferDate the transferDate to set
	 */
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	/**
	 * @return the assStaffId
	 */
	public Long getAssStaffId() {
		return assStaffId;
	}

	/**
	 * @param assStaffId the assStaffId to set
	 */
	public void setAssStaffId(Long assStaffId) {
		this.assStaffId = assStaffId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

}