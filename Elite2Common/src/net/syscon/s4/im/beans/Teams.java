package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class Teams
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Teams extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("teamCode")
	private String teamCode;
	@JsonProperty("description")
	private String description;
	@JsonProperty("category")
	private String category;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("teamId")
	private Integer teamId;
	@JsonProperty("areaCode")
	private String areaCode;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("queueClusterId")
	private Integer queueClusterId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	@JsonProperty("code")
	private String code;
	@JsonProperty("functionType")
	private String functionType;
	private int returnValue;
	private String agyLocType;
	@JsonProperty("checkFlag")
	 private String checkFlag;
    private String currentCaseLoad;
   private Integer updateDeleteAllowedCount;
   @JsonProperty("canDisplay")
	private Boolean canDisplay = true;
   @JsonProperty("caseLoadId")
	private String caseLoadId;
   
   @JsonProperty("teamEmail")
	private String teamEmail;
   
   
   private List<String> functionList;
   
   private List<String> agyLocList;
   
   
	public Integer getUpdateDeleteAllowedCount() {
	return updateDeleteAllowedCount;
}

public void setUpdateDeleteAllowedCount(Integer updateDeleteAllowedCount) {
	this.updateDeleteAllowedCount = updateDeleteAllowedCount;
}

	public String getCurrentCaseLoad() {
	return currentCaseLoad;
}

public void setCurrentCaseLoad(String currentCaseLoad) {
	this.currentCaseLoad = currentCaseLoad;
}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getAgyLocType() {
		return agyLocType;
	}

	public void setAgyLocType(String agyLocType) {
		this.agyLocType = agyLocType;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * @param teamCode
	 *            teamCode to set
	 */
	public void setTeamCode(final String teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * return theteamCode
	 */
	public String getTeamCode() {
		return this.teamCode;
	}

	/**
	 * @param description
	 *            description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * return thedescription
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param category
	 *            category to set
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * return thecategory
	 */
	public String getCategory() {
		return this.category;
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
	 * @param teamId
	 *            teamId to set
	 */
	public void setTeamId(final Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * return theteamId
	 */
	public Integer getTeamId() {
		return this.teamId;
	}

	/**
	 * @param areaCode
	 *            areaCode to set
	 */
	public void setAreaCode(final String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * return theareaCode
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param queueClusterId
	 *            queueClusterId to set
	 */
	public void setQueueClusterId(final Integer queueClusterId) {
		this.queueClusterId = queueClusterId;
	}

	/**
	 * return thequeueClusterId
	 */
	public Integer getQueueClusterId() {
		return this.queueClusterId;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final  boolean inserted) {
		this.inserted = inserted;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getCaseLoadId() {
		return caseLoadId;
	}

	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public List<String> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<String> functionList) {
		this.functionList = functionList;
	}

	public List<String> getAgyLocList() {
		return agyLocList;
	}

	public void setAgyLocList(List<String> agyLocList) {
		this.agyLocList = agyLocList;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getTeamEmail() {
		return teamEmail;
	}

	public void setTeamEmail(String teamEmail) {
		this.teamEmail = teamEmail;
	}
}