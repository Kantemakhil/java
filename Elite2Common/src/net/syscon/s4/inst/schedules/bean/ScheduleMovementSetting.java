package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ScheduleMovementSetting extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("settingCode")
    private String settingCode;

    @JsonProperty("settingDescription")
    @NotNull
    @Size(max = 50)
    private String settingDescription;

    @JsonProperty("settingValue")
    private String settingValue;

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
    
    @JsonProperty("settingCodeValue")
	private byte[] settingCodeValue;

    public byte[] getSettingCodeValue() {
		return settingCodeValue;
	}

	public void setSettingCodeValue(byte[] settingCodeValue) {
		this.settingCodeValue = settingCodeValue;
	}

	public String getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    public void setSettingDescription(String settingDescription) {
        this.settingDescription = settingDescription;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
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
}
