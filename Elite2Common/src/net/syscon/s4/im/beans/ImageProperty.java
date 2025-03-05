package net.syscon.s4.im.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageProperty extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JsonProperty("imagePropertyId")
	private long imagePropertyId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("property")
	private String property;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("image")
	private Images image;

	/**
	*
	*@return 
	*/
	public long getImagePropertyId(){
		return imagePropertyId;
	}

	/**
	*
	*@param imagePropertyId 
	*/
	public void setImagePropertyId(long imagePropertyId){
		this.imagePropertyId = imagePropertyId;
	}

	/**
	*
	*@return 
	*/
	public Date getCreateDatetime(){
		return createDatetime;
	}

	/**
	*
	*@param createDatetime 
	*/
	public void setCreateDatetime(Date createDatetime){
		this.createDatetime = createDatetime;
	}

	/**
	*
	*@return 
	*/
	public String getCreateUserId(){
		return createUserId;
	}

	/**
	*
	*@param createUserId 
	*/
	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	/**
	*
	*@return 
	*/
	public Date getModifyDatetime(){
		return modifyDatetime;
	}

	/**
	*
	*@param modifyDatetime 
	*/
	public void setModifyDatetime(Date modifyDatetime){
		this.modifyDatetime = modifyDatetime;
	}

	/**
	*
	*@return 
	*/
	public String getModifyUserId(){
		return modifyUserId;
	}

	/**
	*
	*@param modifyUserId 
	*/
	public void setModifyUserId(String modifyUserId){
		this.modifyUserId = modifyUserId;
	}

	/**
	*
	*@return 
	*/
	public String getProperty(){
		return property;
	}

	/**
	*
	*@param property 
	*/
	public void setProperty(String property){
		this.property = property;
	}

	/**
	*
	*@return 
	*/
	public String getSealFlag(){
		return sealFlag;
	}

	/**
	*
	*@param sealFlag 
	*/
	public void setSealFlag(String sealFlag){
		this.sealFlag = sealFlag;
	}

	/**
	*
	*@return 
	*/
	public Images getImage(){
		return image;
	}

	/**
	*
	*@param image 
	*/
	public void setImage(Images image){
		this.image = image;
	}

}