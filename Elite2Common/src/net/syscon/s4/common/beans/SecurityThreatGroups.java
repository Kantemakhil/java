package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
@SuppressWarnings("PMD.ExcessivePublicCount")
public class SecurityThreatGroups extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("stgId")
	private Integer stgId;

	@JsonProperty("stgLevel")
	private String stgLevel;

	@JsonProperty("stgCode")
	private String stgCode;

	@JsonProperty("description")
	private String description;

	@JsonProperty("parentStgId")
	private Integer parentStgId;

	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("historyText")
	private String historyText;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("code")
	private String code;

	@JsonProperty("description1")
	private String description1;

	@JsonProperty("butDetail1")
	private String butDetail1;

	@JsonProperty("butRealign")
	private String butRealign;

	@JsonProperty("butDetail2")
	private String butDetail2;

	@JsonProperty("butRealign2")
	private String butRealign2;

	@JsonProperty("lpValue")
	private Integer lpValue;

	@JsonProperty("lpGang")
	private Integer lpGang;

	@JsonProperty("lpSet")
	private Integer lpSet;

	@JsonProperty("canDisplay")
	private boolean canDisplay = true;

	/**
	 * @return the canDisplay
	 */
	public boolean isCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay
	 *            the canDisplay to set
	 */
	public void setCanDisplay(final boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	/**
	 * @param stgId
	 *            stgId to set
	 */
	public void setStgId(final Integer stgId) {
		this.stgId = stgId;
	}

	/**
	 * return the stgId
	 */
	public Integer getStgId() {
		return this.stgId;
	}

	/**
	 * @param stgLevel
	 *            stgLevel to set
	 */
	public void setStgLevel(final String stgLevel) {
		this.stgLevel = stgLevel;
	}

	/**
	 * return the stgLevel
	 */
	public String getStgLevel() {
		return this.stgLevel;
	}

	/**
	 * @param stgCode
	 *            stgCode to set
	 */
	public void setStgCode(final String stgCode) {
		this.stgCode = stgCode;
	}

	/**
	 * return the stgCode
	 */
	public String getStgCode() {
		return this.stgCode;
	}

	/**
	 * @param description
	 *            description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param parentStgId
	 *            parentStgId to set
	 */
	public void setParentStgId(final Integer parentStgId) {
		this.parentStgId = parentStgId;
	}

	/**
	 * return the parentStgId
	 */
	public Integer getParentStgId() {
		return this.parentStgId;
	}

	/**
	 * @param listSeq
	 *            listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * return the listSeq
	 */
	public Integer getListSeq() {
		return this.listSeq;
	}

	/**
	 * @param effectiveDate
	 *            effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * return theeffectiveDate
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * @param historyText
	 *            historyText to set
	 */
	public void setHistoryText(final String historyText) {
		this.historyText = historyText;
	}

	/**
	 * return thehistoryText
	 */
	public String getHistoryText() {
		return this.historyText;
	}

	/**
	 * @param activeFlag
	 *            activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * return the activeFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
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
	 * return the createDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the modifyUserId
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
	 * return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param expiryDate
	 *            expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * return the expiryDate
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
	 * return the sealFlag
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
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the description1
	 */
	public String getDescription1() {
		return description1;
	}

	/**
	 * @param description1
	 *            the description1 to set
	 */
	public void setDescription1(final String description1) {
		this.description1 = description1;
	}

	/**
	 * @return the butDetail1
	 */
	public String getButDetail1() {
		return butDetail1;
	}

	/**
	 * @param butDetail1
	 *            the butDetail1 to set
	 */
	public void setButDetail1(final String butDetail1) {
		this.butDetail1 = butDetail1;
	}

	/**
	 * @return the butRealign
	 */
	public String getButRealign() {
		return butRealign;
	}

	/**
	 * @param butRealign
	 *            the butRealign to set
	 */
	public void setButRealign(final String butRealign) {
		this.butRealign = butRealign;
	}

	/**
	 * @return the butDetail2
	 */
	public String getButDetail2() {
		return butDetail2;
	}

	/**
	 * @param butDetail2
	 *            the butDetail2 to set
	 */
	public void setButDetail2(final String butDetail2) {
		this.butDetail2 = butDetail2;
	}

	/**
	 * @return the butRealign2
	 */
	public String getButRealign2() {
		return butRealign2;
	}

	/**
	 * @param butRealign2
	 *            the butRealign2 to set
	 */
	public void setButRealign2(final String butRealign2) {
		this.butRealign2 = butRealign2;
	}

	/**
	 * @return the lpValue
	 */
	public Integer getLpValue() {
		return lpValue;
	}

	/**
	 * @param lpValue
	 *            the lpValue to set
	 */
	public void setLpValue(final Integer lpValue) {
		this.lpValue = lpValue;
	}

	/**
	 * @return the lpGang
	 */
	public Integer getLpGang() {
		return lpGang;
	}

	/**
	 * @param lpGang
	 *            the lpGang to set
	 */
	public void setLpGang(final Integer lpGang) {
		this.lpGang = lpGang;
	}

	/**
	 * @return the lpSet
	 */
	public Integer getLpSet() {
		return lpSet;
	}

	/**
	 * @param lpSet
	 *            the lpSet to set
	 */
	public void setLpSet(final Integer lpSet) {
		this.lpSet = lpSet;
	}

}