package net.syscon.s4.im.beans;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VIntLocOffenders extends BaseModel implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@JsonProperty("agyLocId")
private String agyLocId;
@JsonProperty("rootInternalLocationId")
private int rootInternalLocationId;
@JsonProperty("offenderBookId")
private int offenderBookId;
@JsonProperty("offenderId")
private int offenderId;
@JsonProperty("lastName")
private String lastName;
@JsonProperty("firstName")
private String firstName;
@JsonProperty("offenderIdDisplay")
private String offenderIdDisplay;
@JsonProperty("internalLocationId")
private int internalLocationId;
@JsonProperty("internalLocationDesc")
private String internalLocationDesc;
@JsonProperty("parentInternalLocationId")
private int parentInternalLocationId;
@JsonProperty("inOutStatus")
private String inOutStatus;
@JsonProperty("livingUnitId")
private int livingUnitId;
@JsonProperty("livingUnitDesc")
private String livingUnitDesc;
@JsonProperty("alertFlag")
private String alertFlag;
private boolean inserted;

 /**
 * @param	agyLocId
*agyLocId	 to set
*/
public void setAgyLocId(String agyLocId){
	 this.agyLocId = agyLocId;
}

/**
*return theagyLocId
*/
public String getAgyLocId(){
	 return this.agyLocId;
}

 /**
 * @param	rootInternalLocationId
*rootInternalLocationId	 to set
*/
public void setRootInternalLocationId(int rootInternalLocationId){
	 this.rootInternalLocationId = rootInternalLocationId;
}

/**
*return therootInternalLocationId
*/
public int getRootInternalLocationId(){
	 return this.rootInternalLocationId;
}

 /**
 * @param	offenderBookId
*offenderBookId	 to set
*/
public void setOffenderBookId(int offenderBookId){
	 this.offenderBookId = offenderBookId;
}

/**
*return theoffenderBookId
*/
public int getOffenderBookId(){
	 return this.offenderBookId;
}

 /**
 * @param	offenderId
*offenderId	 to set
*/
public void setOffenderId(int offenderId){
	 this.offenderId = offenderId;
}

/**
*return theoffenderId
*/
public int getOffenderId(){
	 return this.offenderId;
}

 /**
 * @param	lastName
*lastName	 to set
*/
public void setLastName(String lastName){
	 this.lastName = lastName;
}

/**
*return thelastName
*/
public String getLastName(){
	 return this.lastName;
}

 /**
 * @param	firstName
*firstName	 to set
*/
public void setFirstName(String firstName){
	 this.firstName = firstName;
}

/**
*return thefirstName
*/
public String getFirstName(){
	 return this.firstName;
}

 /**
 * @param	offenderIdDisplay
*offenderIdDisplay	 to set
*/
public void setOffenderIdDisplay(String offenderIdDisplay){
	 this.offenderIdDisplay = offenderIdDisplay;
}

/**
*return theoffenderIdDisplay
*/
public String getOffenderIdDisplay(){
	 return this.offenderIdDisplay;
}

 /**
 * @param	internalLocationId
*internalLocationId	 to set
*/
public void setInternalLocationId(int internalLocationId){
	 this.internalLocationId = internalLocationId;
}

/**
*return theinternalLocationId
*/
public int getInternalLocationId(){
	 return this.internalLocationId;
}

 /**
 * @param	internalLocationDesc
*internalLocationDesc	 to set
*/
public void setInternalLocationDesc(String internalLocationDesc){
	 this.internalLocationDesc = internalLocationDesc;
}

/**
*return theinternalLocationDesc
*/
public String getInternalLocationDesc(){
	 return this.internalLocationDesc;
}

 /**
 * @param	parentInternalLocationId
*parentInternalLocationId	 to set
*/
public void setParentInternalLocationId(int parentInternalLocationId){
	 this.parentInternalLocationId = parentInternalLocationId;
}

/**
*return theparentInternalLocationId
*/
public int getParentInternalLocationId(){
	 return this.parentInternalLocationId;
}

 /**
 * @param	inOutStatus
*inOutStatus	 to set
*/
public void setInOutStatus(String inOutStatus){
	 this.inOutStatus = inOutStatus;
}

/**
*return theinOutStatus
*/
public String getInOutStatus(){
	 return this.inOutStatus;
}

 /**
 * @param	livingUnitId
*livingUnitId	 to set
*/
public void setLivingUnitId(int livingUnitId){
	 this.livingUnitId = livingUnitId;
}

/**
*return thelivingUnitId
*/
public int getLivingUnitId(){
	 return this.livingUnitId;
}

 /**
 * @param	livingUnitDesc
*livingUnitDesc	 to set
*/
public void setLivingUnitDesc(String livingUnitDesc){
	 this.livingUnitDesc = livingUnitDesc;
}

/**
*return thelivingUnitDesc
*/
public String getLivingUnitDesc(){
	 return this.livingUnitDesc;
}

 /**
 * @param	alertFlag
*alertFlag	 to set
*/
public void setAlertFlag(String alertFlag){
	 this.alertFlag = alertFlag;
}

/**
*return thealertFlag
*/
public String getAlertFlag(){
	 return this.alertFlag;
}
	/**
	 * @return the inserted
	*/
	public boolean isInserted() {
		return inserted;
	}
	/**
	 * @param inserted the inserted to set
	*/
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

}