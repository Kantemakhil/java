package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.Images;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderIdentifyingMark extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("bodyPartCode")
	private String bodyPartCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("markType")
	private String markType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("partOrientationCode")
	private String partOrientationCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sideCode")
	private String sideCode;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("idMarkSeq")
	private long idMarkSeq;

	@JsonProperty("nbtMarkTypeDesc")
	private String nbtMarkTypeDesc;

	@JsonProperty("nbtSideCodeDesc")
	private String nbtSideCodeDesc;

	@JsonProperty("nbtBodyPartDesc")
	private String nbtBodyPartDesc;

	@JsonProperty("nbtOrientationDesc")
	private String nbtOrientationDesc;
	
	@JsonProperty("imageFlag")
	private String imageFlag;
	
	@JsonProperty("imageUrl")
	private String imageUrl;
	
	@JsonProperty("imageId")
	private BigDecimal  imageId; 
	
	@JsonProperty("images")
	private List<Images> images;


	public String getImageFlag() {
		return imageFlag;
	}

	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}

	/**
	 * @return the nbtMarkTypeDesc
	 */
	public String getNbtMarkTypeDesc() {
		return nbtMarkTypeDesc;
	}

	/**
	 * @param nbtMarkTypeDesc
	 *            the nbtMarkTypeDesc to set
	 */
	public void setNbtMarkTypeDesc(String nbtMarkTypeDesc) {
		this.nbtMarkTypeDesc = nbtMarkTypeDesc;
	}

	/**
	 * @return the nbtSideCodeDesc
	 */
	public String getNbtSideCodeDesc() {
		return nbtSideCodeDesc;
	}

	/**
	 * @param nbtSideCodeDesc
	 *            the nbtSideCodeDesc to set
	 */
	public void setNbtSideCodeDesc(String nbtSideCodeDesc) {
		this.nbtSideCodeDesc = nbtSideCodeDesc;
	}

	/**
	 * @return the nbtBodyPartDesc
	 */
	public String getNbtBodyPartDesc() {
		return nbtBodyPartDesc;
	}

	/**
	 * @param nbtBodyPartDesc
	 *            the nbtBodyPartDesc to set
	 */
	public void setNbtBodyPartDesc(String nbtBodyPartDesc) {
		this.nbtBodyPartDesc = nbtBodyPartDesc;
	}

	/**
	 * @return the nbtOrientationDesc
	 */
	public String getNbtOrientationDesc() {
		return nbtOrientationDesc;
	}

	/**
	 * @param nbtOrientationDesc
	 *            the nbtOrientationDesc to set
	 */
	public void setNbtOrientationDesc(String nbtOrientationDesc) {
		this.nbtOrientationDesc = nbtOrientationDesc;
	}

	/**
	 *
	 * @return
	 */
	public String getBodyPartCode() {
		return bodyPartCode;
	}

	/**
	 *
	 * @param bodyPartCode
	 */
	public void setBodyPartCode(String bodyPartCode) {
		this.bodyPartCode = bodyPartCode;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getMarkType() {
		return markType;
	}

	/**
	 *
	 * @param markType
	 */
	public void setMarkType(String markType) {
		this.markType = markType;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getPartOrientationCode() {
		return partOrientationCode;
	}

	/**
	 *
	 * @param partOrientationCode
	 */
	public void setPartOrientationCode(String partOrientationCode) {
		this.partOrientationCode = partOrientationCode;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getSideCode() {
		return sideCode;
	}

	/**
	 *
	 * @param sideCode
	 */
	public void setSideCode(String sideCode) {
		this.sideCode = sideCode;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getIdMarkSeq() {
		return idMarkSeq;
	}

	/**
	 *
	 * @param idMarkSeq
	 */
	public void setIdMarkSeq(long idMarkSeq) {
		this.idMarkSeq = idMarkSeq;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getImageId() {
		return imageId;
	}

	public void setImageId(BigDecimal imageId) {
		this.imageId = imageId;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

}