package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the TEAM_MEMBERS database table.
 * 
 */
public class VExtOwnershipTransfer extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("offenderLastName")
	private String offenderLastName;

	@JsonProperty("offenderFirstName")
	private String offenderFirstName;

	@JsonProperty("assStaffId")
	private Long assStaffId;

	@JsonProperty("staffLastName")
	private String staffLastName;
	
	@JsonProperty("staffFirstName")
	private String staffFirstName;

	@JsonProperty("staffName")
	private String staffName;
	

	
	@JsonProperty("agyLocIdTo")
	private String agyLocIdTo;
	
	@JsonProperty("VagylocIdTo")
	private String VagylocIdTo;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("v_offenderId")
	private Long v_offenderId;
	
	@JsonProperty("vOffenderFileSeq")
	private Long vOffenderFileSeq;
	
	@JsonProperty("extTransferId")
	private Long extTransferId;
	
	@JsonProperty("agyLocIdFrom")
	private String agyLocIdFrom;
	
	@JsonProperty("transferDate")
	private Date transferDate;

	@JsonProperty("transferFlag")
	private String transferFlag;   

	@JsonProperty("description")
	private String description;	
	
	@JsonProperty("code")
	private String code;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	
	
	
	
	public String getVagylocIdTo() {
		return VagylocIdTo;
	}

	public void setVagylocIdTo(String vagylocIdTo) {
		VagylocIdTo = vagylocIdTo;
	}


	
	public Long getV_offenderId() {
		return v_offenderId;
	}

	public void setV_offenderId(Long v_offenderId) {
		this.v_offenderId = v_offenderId;
	}

	public Long getvOffenderFileSeq() {
		return vOffenderFileSeq;
	}

	public void setvOffenderFileSeq(Long vOffenderFileSeq) {
		this.vOffenderFileSeq = vOffenderFileSeq;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	
	
	
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getOffenderLastName() {
		return offenderLastName;
	}

	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	public Long getAssStaffId() {
		return assStaffId;
	}

	public void setAssStaffId(Long assStaffId) {
		this.assStaffId = assStaffId;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public String getAgyLocIdTo() {
		return agyLocIdTo;
	}

	public void setAgyLocIdTo(String agyLocIdTo) {
		this.agyLocIdTo = agyLocIdTo;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getExtTransferId() {
		return extTransferId;
	}

	public void setExtTransferId(Long extTransferId) {
		this.extTransferId = extTransferId;
	}

	public String getAgyLocIdFrom() {
		return agyLocIdFrom;
	}

	public void setAgyLocIdFrom(String agyLocIdFrom) {
		this.agyLocIdFrom = agyLocIdFrom;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getTransferFlag() {
		return transferFlag;
	}

	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 

}
