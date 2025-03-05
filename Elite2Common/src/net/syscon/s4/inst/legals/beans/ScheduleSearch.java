package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.common.validators.ValidBirthDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ScheduleSearch extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;	

	@JsonProperty("sDate")
	@ValidBirthDate(message = "date.format.not.valid", pattern = Utilities.DATE_PATTEN)
	private Date sDate;
	
	@JsonProperty("sTime")
	private Date sTime;

	@JsonProperty("sfacility")
	@NotNull
	private String sfacility;

	@JsonProperty("livingUnit1")
	private String livingUnit1;

	@JsonProperty("livingUnit2")
	private String livingUnit2;

	@JsonProperty("livingUnit3")
	private String livingUnit3;

	@JsonProperty("offenderId")
	private String offenderId;
	
	@JsonProperty("offenderName")
	private String offenderName;

	/*public String getFacility() {
		return sfacility;
	}*/

	/*public void setFacility(String sfacility) {
		this.sfacility = sfacility;
	}*/
	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public String getLivingUnit1() {
		return livingUnit1;
	}

	public void setLivingUnit1(String livingUnit1) {
		this.livingUnit1 = livingUnit1;
	}

	public String getLivingUnit2() {
		return livingUnit2;
	}

	public void setLivingUnit2(String livingUnit2) {
		this.livingUnit2 = livingUnit2;
	}

	public String getLivingUnit3() {
		return livingUnit3;
	}

	public void setLivingUnit3(String livingUnit3) {
		this.livingUnit3 = livingUnit3;
	}

	public String getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}
	
	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public String getSfacility() {
		return sfacility;
	}

	public void setSfacility(String sfacility) {
		this.sfacility = sfacility;
	}
	
	@AssertTrue(message = "Date and Facility is Mandatory")
	private boolean isValid() {
		if ("CCG".equals(this.sfacility) || "ITAG".equals(this.sfacility) || "ECJ".equals(this.sfacility)) {
			return this.sfacility != null;
		}
		return true;
	}
	
}