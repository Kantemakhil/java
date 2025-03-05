package net.syscon.s4.sa.usersystemsecurity.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class OmsUsersList extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("userSeq")
    private Long userSeq;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("password")
    private String password;

    @JsonProperty("passwordExpiryDate")
    private Date passwordExpiryDate;

    @JsonProperty("accountStatus")
    private String accountStatus;

    @JsonProperty("createDatetime")
    private Date createDatetime;

    @JsonProperty("failedLoginAttempts")
    private Long failedLoginAttempts;

    @JsonProperty("modifyDatetime")
    private Date modifyDatetime;

    @JsonProperty("createUserId")
    private String createUserId;

    @JsonProperty("modifyUserId")
    private String modifyUserId;
    
	@JsonProperty("sealFlag")
	private String sealFlag;
	
    public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Long getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Long failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Date getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Date modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    @Override
    public String getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}
