package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;



/**
 * The persistent class for the PROGRAM_SERVICES_PROFILES database table.
 * 
 */
public class ProgramServicesProfiles extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	// bi-directional many-to-one association to ProgramService
	//private ProgramService programService;

	private long programId;

	private String programProfileType;

	private String programProfileCode;
    private String profileGenderCode;
    private String profileEtiCityCode;
    private String profileAgeRangeCode;
    private String profileFacilityCode;
    private String profileInGroupCode;
    private String profileExGroupCode;
    private String pCode;
    private String pType;
    private int serverCode;
    
    public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	private int returnValue;
    
	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	public String getProfileGenderCode() {
		return profileGenderCode;
	}

	public void setProfileGenderCode(String profileGenderCode) {
		this.profileGenderCode = profileGenderCode;
	}

	public String getProfileEtiCityCode() {
		return profileEtiCityCode;
	}

	public void setProfileEtiCityCode(String profileEtiCityCode) {
		this.profileEtiCityCode = profileEtiCityCode;
	}

	public String getProfileAgeRangeCode() {
		return profileAgeRangeCode;
	}

	public void setProfileAgeRangeCode(String profileAgeRangeCode) {
		this.profileAgeRangeCode = profileAgeRangeCode;
	}

	public String getProfileFacilityCode() {
		return profileFacilityCode;
	}

	public void setProfileFacilityCode(String profileFacilityCode) {
		this.profileFacilityCode = profileFacilityCode;
	}

	public String getProfileInGroupCode() {
		return profileInGroupCode;
	}

	public void setProfileInGroupCode(String profileInGroupCode) {
		this.profileInGroupCode = profileInGroupCode;
	}

	public String getProfileExGroupCode() {
		return profileExGroupCode;
	}

	public void setProfileExGroupCode(String profileExGroupCode) {
		this.profileExGroupCode = profileExGroupCode;
	}

	public ProgramServicesProfiles() {
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public String getProgramProfileType() {
		return programProfileType;
	}

	public void setProgramProfileType(String programProfileType) {
		this.programProfileType = programProfileType;
	}

	public String getProgramProfileCode() {
		return programProfileCode;
	}

	public void setProgramProfileCode(String programProfileCode) {
		this.programProfileCode = programProfileCode;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

//	public ProgramService getProgramService() {
//		return this.programService;
//	}
//
//	public void setProgramService(ProgramService programService) {
//		this.programService = programService;
//	}

}
