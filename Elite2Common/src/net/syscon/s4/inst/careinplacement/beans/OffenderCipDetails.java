package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCipDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("authByPerCode")
	private String authByPerCode;

	@JsonProperty("authByPerName")
	private String authByPerName;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("duration")
	private BigDecimal duration;

	@JsonProperty("durationType")
	private String durationType;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("effectiveTime")
	private Date effectiveTime;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("expiryTime")
	private Date expiryTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("placementReasonCode")
	private String placementReasonCode;

	@JsonProperty("placementType")
	private String placementType;

	@JsonProperty("relByPerCode")
	private String relByPerCode;

	@JsonProperty("relByPerName")
	private String relByPerName;

	@JsonProperty("releaseDate")
	private Date releaseDate;

	@JsonProperty("releaseTime")
	private Date releaseTime;

	@JsonProperty("reqByPerCode")
	private String reqByPerCode;

	@JsonProperty("reviewDate")
	private Date reviewDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

//	@JsonProperty("offenderBookings")
//	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("placementSeq")
	private long placementSeq;
	
	@JsonProperty("nbtDaysServed")
	 private Integer nbtDaysServed;
	
	@JsonProperty("nbtHoursServed")
    private String nbtHoursServed;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	/**
	 *
	 * @return
	 */
	public String getAgyLocId(){
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthByPerCode(){
		return authByPerCode;
	}

	/**
	 *
	 * @param authByPerCode
	 */
	public void setAuthByPerCode(final String authByPerCode) {
		this.authByPerCode = authByPerCode;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthByPerName(){
		return authByPerName;
	}

	/**
	 *
	 * @param authByPerName
	 */
	public void setAuthByPerName(final String authByPerName) {
		this.authByPerName = authByPerName;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText(){
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime(){
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId(){
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getDuration(){
		return duration;
	}

	/**
	 *
	 * @param duration
	 */
	public void setDuration(final BigDecimal duration) {
		this.duration = duration;
	}

	/**
	 *
	 * @return
	 */
	public String getDurationType(){
		return durationType;
	}

	/**
	 *
	 * @param durationType
	 */
	public void setDurationType(final String durationType) {
		this.durationType = durationType;
	}

	/**
	 *
	 * @return
	 */
	public Date getEffectiveDate(){
		return effectiveDate;
	}

	/**
	 *
	 * @param effectiveDate
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getEffectiveTime(){
		return effectiveTime;
	}

	/**
	 *
	 * @param effectiveTime
	 */
	public void setEffectiveTime(final Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate(){
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryTime(){
		return expiryTime;
	}

	/**
	 *
	 * @param expiryTime
	 */
	public void setExpiryTime(final Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime(){
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId(){
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getPlacementReasonCode(){
		return placementReasonCode;
	}

	/**
	 *
	 * @param placementReasonCode
	 */
	public void setPlacementReasonCode(final String placementReasonCode) {
		this.placementReasonCode = placementReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getPlacementType(){
		return placementType;
	}

	/**
	 *
	 * @param placementType
	 */
	public void setPlacementType(final String placementType) {
		this.placementType = placementType;
	}

	/**
	 *
	 * @return
	 */
	public String getRelByPerCode(){
		return relByPerCode;
	}

	/**
	 *
	 * @param relByPerCode
	 */
	public void setRelByPerCode(final String relByPerCode) {
		this.relByPerCode = relByPerCode;
	}

	/**
	 *
	 * @return
	 */
	public String getRelByPerName(){
		return relByPerName;
	}

	/**
	 *
	 * @param relByPerName
	 */
	public void setRelByPerName(final String relByPerName) {
		this.relByPerName = relByPerName;
	}

	/**
	 *
	 * @return
	 */
	public Date getReleaseDate(){
		return releaseDate;
	}

	/**
	 *
	 * @param releaseDate
	 */
	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getReleaseTime(){
		return releaseTime;
	}

	/**
	 *
	 * @param releaseTime
	 */
	public void setReleaseTime(final Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 *
	 * @return
	 */
	public String getReqByPerCode(){
		return reqByPerCode;
	}

	/**
	 *
	 * @param reqByPerCode
	 */
	public void setReqByPerCode(final String reqByPerCode) {
		this.reqByPerCode = reqByPerCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getReviewDate(){
		return reviewDate;
	}

	/**
	 *
	 * @param reviewDate
	 */
	public void setReviewDate(final Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag(){
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
//	public OffenderBookings getOffenderBookings(){
//		return offenderBookings;
//	}

	/**
	 *
	 * @param offenderBookings
	 */
//	public void setOffenderBookings(final OffenderBookings offenderBookings) {
//		this.offenderBookings = offenderBookings;
//	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderBookId(){
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getPlacementSeq(){
		return placementSeq;
	}

	/**
	 *
	 * @param placementSeq
	 */
	public void setPlacementSeq(final long placementSeq) {
		this.placementSeq = placementSeq;
	}

	/**
	 * @return the nbtDaysServed
	 */
	public Integer getNbtDaysServed() {
		return nbtDaysServed;
	}

	/**
	 * @param nbtDaysServed the nbtDaysServed to set
	 */
	public void setNbtDaysServed(final Integer nbtDaysServed) {
		this.nbtDaysServed = nbtDaysServed;
	}

	/**
	 * @return the nbtHoursServed
	 */
	public String getNbtHoursServed() {
		return nbtHoursServed;
	}

	/**
	 * @param nbtHoursServed the nbtHoursServed to set
	 */
	public void setNbtHoursServed(final String nbtHoursServed) {
		this.nbtHoursServed = nbtHoursServed;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
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

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}
	
	

	
}