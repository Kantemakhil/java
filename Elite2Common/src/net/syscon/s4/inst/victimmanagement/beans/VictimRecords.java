package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimRecords extends BaseModel implements Serializable {

	@JsonProperty("personId")
	private BigDecimal personId;
	@JsonProperty("victimId")
	private Integer victimId;
	@JsonProperty("preferredContactMethod")
	private String preferredContactMethod;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("registeredDatetime")
	private Date registeredDatetime;
	@JsonProperty("deactivatedDatetime")
	private Date deactivatedDatetime;
	@JsonProperty("note")
	private String note;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("name")
	private String name;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("age")
	private Integer age;
	@JsonProperty("gender")
	private String gender;


	public Date getRegisteredDatetime() {
		return registeredDatetime;
	}

	public void setRegisteredDatetime(Date registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}

	public Date getDeactivatedDatetime() {
		return deactivatedDatetime;
	}

	public void setDeactivatedDatetime(Date deactivatedDatetime) {
		this.deactivatedDatetime = deactivatedDatetime;
	}


	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getPersonId() {
		return personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	public Integer getVictimId() {
		return victimId;
	}

	public void setVictimId(Integer victimId) {
		this.victimId = victimId;
	}

	public String getPreferredContactMethod() {
		return preferredContactMethod;
	}

	public void setPreferredContactMethod(String preferredContactMethod) {
		this.preferredContactMethod = preferredContactMethod;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "VictimRecords [personId=" + personId + ", victimId=" + victimId + ", preferredContactMethod="
				+ preferredContactMethod + ", activeFlag=" + activeFlag + ", registeredDatetime=" + registeredDatetime
				+ ", deactivatedDatetime=" + deactivatedDatetime + ", note=" + note + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", name=" + name + ", sex=" + sex
				+ ", age=" + age + ", gender=" + gender + "]";
	}

}