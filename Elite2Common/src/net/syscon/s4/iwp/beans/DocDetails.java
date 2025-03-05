package net.syscon.s4.iwp.beans;



import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocDetails extends BaseModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("keyLogin")
	private String keyLogin;
	
	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("offenderBookingNo")
	private String offenderBookingNo;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("objectType")
	private String objectType;

	@JsonProperty("objectId")
	private Long objectId;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("osUser")
	private String osUser;
	
	@JsonProperty("userRole")
	private String userRole;
	
	@JsonProperty("lastName")
	private String lastName;
	
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("middleName")
	private String middleName;
	
	@JsonProperty("currentCaseLoad")
	private String currentCaseLoad;
	
	@JsonProperty("prisonLocation")
	private String prisonLocation;
	
	@JsonProperty("offSupLevel")
	private String offSupLevel;
	
	@JsonProperty("trimUser")
	private String trimUser;
	
	@JsonProperty("statusDisplay")
	private String statusDisplay;
	
	private Date birthDate;
	
	@JsonProperty("eventId")
	private Long eventId;

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getKeyLogin() {
		return keyLogin;
	}

	public void setKeyLogin(String keyLogin) {
		this.keyLogin = keyLogin;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderBookingNo() {
		return offenderBookingNo;
	}

	public void setOffenderBookingNo(String offenderBookingNo) {
		this.offenderBookingNo = offenderBookingNo;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOsUser() {
		return osUser;
	}

	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	

	public String getCurrentCaseLoad() {
		return currentCaseLoad;
	}

	public void setCurrentCaseLoad(String currentCaseLoad) {
		this.currentCaseLoad = currentCaseLoad;
	}
	
	

	public String getPrisonLocation() {
		return prisonLocation;
	}

	public void setPrisonLocation(String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	public String getOffSupLevel() {
		return offSupLevel;
	}

	public void setOffSupLevel(String offSupLevel) {
		this.offSupLevel = offSupLevel;
	}

	
	public String getTrimUser() {
		return trimUser;
	}

	public void setTrimUser(String trimUser) {
		this.trimUser = trimUser;
	}
	


	public String getStatusDisplay() {
		return statusDisplay;
	}

	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EoffenderDetails [keyLogin=");
		builder.append(keyLogin);
		builder.append(", offenderBookId=");
		builder.append(offenderBookId);
		builder.append(", offenderBookingNo=");
		builder.append(offenderBookingNo);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", objectType=");
		builder.append(objectType);
		builder.append(", objectId=");
		builder.append(objectId);
		builder.append(", moduleName=");
		builder.append(moduleName);
		builder.append(", offenderIdDisplay=");
		builder.append(offenderIdDisplay);
		builder.append(", description=");
		builder.append(description);
		builder.append(", osUser=");
		builder.append(osUser);
		builder.append(", userRole=");
		builder.append(userRole);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", currentCaseLoad=");
		builder.append(currentCaseLoad);
		builder.append(", prisonLocation");
		builder.append(prisonLocation);
		builder.append(", offSupLevel");
		builder.append(offSupLevel);
		builder.append(", trimUser");
		builder.append(trimUser);
		builder.append(", statusDisplay");
		builder.append(statusDisplay);
		builder.append("]");
		return builder.toString();
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


}
