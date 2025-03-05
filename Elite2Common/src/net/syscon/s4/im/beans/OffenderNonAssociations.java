package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_NON_ASSOCIATIONS database table.
 * 
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public class OffenderNonAssociations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("internalLocationFlag")
	private String internalLocationFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nsLevelCode")
	private String nsLevelCode;

	@JsonProperty("nsOffenderBookId")
	private BigDecimal nsOffenderBookId;

	@JsonProperty("nsReasonCode")
	private String nsReasonCode;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("recipNsReasonCode")
	private String recipNsReasonCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("transportFlag")
	private String transportFlag;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("nsOffenderId")
	private Long nsOffenderId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("prisionLocation")
	private String prisionLocation;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("typeSeq")
	private Long typeSeq;

	@JsonProperty("nsType")
	private String nsType;

	@JsonProperty("nsEffectiveDate")
	private Date nsEffectiveDate;

	@JsonProperty("nsExpiryDate")
	private Date nsExpiryDate;

	@JsonProperty("authorizedStaff")
	private String authorizedStaff;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("updateNonAssociation")
	private String updateNonAssociation;
	
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	
	@JsonProperty("offenderName")
	private String offenderName;
	
	@JsonProperty("naDetailsList")
	private List<OffenderNaDetails> naDetailsList; 

	/**
	 * Creates new OffenderMilitaryWarZones class Object
	 */
	public OffenderNonAssociations() {
		// OffenderNonAssociations
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getNsOffenderId() {
		return nsOffenderId;
	}

	public void setNsOffenderId(final Long nsOffenderId) {
		this.nsOffenderId = nsOffenderId;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getInternalLocationFlag() {
		return this.internalLocationFlag;
	}

	public void setInternalLocationFlag(final String internalLocationFlag) {
		this.internalLocationFlag = internalLocationFlag;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNsLevelCode() {
		return this.nsLevelCode;
	}

	public void setNsLevelCode(final String nsLevelCode) {
		this.nsLevelCode = nsLevelCode;
	}

	public BigDecimal getNsOffenderBookId() {
		return this.nsOffenderBookId;
	}

	public void setNsOffenderBookId(final BigDecimal nsOffenderBookId) {
		this.nsOffenderBookId = nsOffenderBookId;
	}

	public String getNsReasonCode() {
		return this.nsReasonCode;
	}

	public void setNsReasonCode(final String nsReasonCode) {
		this.nsReasonCode = nsReasonCode;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getRecipNsReasonCode() {
		return this.recipNsReasonCode;
	}

	public void setRecipNsReasonCode(final String recipNsReasonCode) {
		this.recipNsReasonCode = recipNsReasonCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTransportFlag() {
		return this.transportFlag;
	}

	public void setTransportFlag(final String transportFlag) {
		this.transportFlag = transportFlag;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the prisionLocation
	 */
	public String getPrisionLocation() {
		return prisionLocation;
	}

	/**
	 * @param prisionLocation
	 *            the prisionLocation to set
	 */
	public void setPrisionLocation(final String prisionLocation) {
		this.prisionLocation = prisionLocation;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the typeSeq
	 */
	public Long getTypeSeq() {
		return typeSeq;
	}

	/**
	 * @param typeSeq
	 *            the typeSeq to set
	 */
	public void setTypeSeq(final Long typeSeq) {
		this.typeSeq = typeSeq;
	}

	/**
	 * @return the nsType
	 */
	public String getNsType() {
		return nsType;
	}

	/**
	 * @param nsType
	 *            the nsType to set
	 */
	public void setNsType(final String nsType) {
		this.nsType = nsType;
	}

	/**
	 * @return the nsEffectiveDate
	 */
	public Date getNsEffectiveDate() {
		return nsEffectiveDate;
	}

	/**
	 * @param nsEffectiveDate
	 *            the nsEffectiveDate to set
	 */
	public void setNsEffectiveDate(final Date nsEffectiveDate) {
		this.nsEffectiveDate = nsEffectiveDate;
	}

	/**
	 * @return the nsExpiryDate
	 */
	public Date getNsExpiryDate() {
		return nsExpiryDate;
	}

	/**
	 * @param nsExpiryDate
	 *            the nsExpiryDate to set
	 */
	public void setNsExpiryDate(final Date nsExpiryDate) {
		this.nsExpiryDate = nsExpiryDate;
	}

	/**
	 * @return the authorizedStaff
	 */
	public String getAuthorizedStaff() {
		return authorizedStaff;
	}

	/**
	 * @param authorizedStaff
	 *            the authorizedStaff to set
	 */
	public void setAuthorizedStaff(final String authorizedStaff) {
		this.authorizedStaff = authorizedStaff;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the updateNonAssociation
	 */
	public String getUpdateNonAssociation() {
		return updateNonAssociation;
	}

	/**
	 * @param updateNonAssociation
	 *            the updateNonAssociation to set
	 */
	public void setUpdateNonAssociation(final String updateNonAssociation) {
		this.updateNonAssociation = updateNonAssociation;
	}

	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public List<OffenderNaDetails> getNaDetailsList() {
		return naDetailsList;
	}

	public void setNaDetailsList(List<OffenderNaDetails> naDetailsList) {
		this.naDetailsList = naDetailsList;
	}

}
