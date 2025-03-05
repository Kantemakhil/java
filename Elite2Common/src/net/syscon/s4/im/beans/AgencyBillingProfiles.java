package net.syscon.s4.im.beans;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class AgencyBillingProfiles
 */
@XmlRootElement
public class AgencyBillingProfiles extends BaseModel {

private Integer AGENCY_ID;
private String CASELOAD_ID;
private String BILLING_TYPE;
private String FREQUENCY;
private Integer RATE;
private Integer FIRST_DAY_RATE;
private Integer LAST_DAY_RATE;
private String ALL_OFFENDER_FLAG;
private String ACTIVE_FLAG;
private String IMPRISONMENT_STATUS;
private Integer LEAVE_RATE;
private Timestamp CREATE_DATETIME;
private String CREATE_USER_ID;
private Timestamp MODIFY_DATETIME;
private String MODIFY_USER_ID;
private Integer DELAY_DAYS;
private String SEAL_FLAG;
private boolean inserted;
private String errorMessage;
private Long impAgyBillingDetailId;

 /**
 * @param	AGENCY_ID
*AGENCY_ID	 to set
*/
public void setAGENCY_ID(Integer AGENCY_ID){
			 this.AGENCY_ID = AGENCY_ID;
		}

/**
*return theAGENCY_ID
*/
public Integer getAGENCY_ID(){
			 return this.AGENCY_ID;
		}

 /**
 * @param	CASELOAD_ID
*CASELOAD_ID	 to set
*/
public void setCASELOAD_ID(String CASELOAD_ID){
			 this.CASELOAD_ID = CASELOAD_ID;
		}

/**
*return theCASELOAD_ID
*/
public String getCASELOAD_ID(){
			 return this.CASELOAD_ID;
		}

 /**
 * @param	BILLING_TYPE
*BILLING_TYPE	 to set
*/
public void setBILLING_TYPE(String BILLING_TYPE){
			 this.BILLING_TYPE = BILLING_TYPE;
		}

/**
*return theBILLING_TYPE
*/
public String getBILLING_TYPE(){
			 return this.BILLING_TYPE;
		}

 /**
 * @param	FREQUENCY
*FREQUENCY	 to set
*/
public void setFREQUENCY(String FREQUENCY){
			 this.FREQUENCY = FREQUENCY;
		}

/**
*return theFREQUENCY
*/
public String getFREQUENCY(){
			 return this.FREQUENCY;
		}

 /**
 * @param	RATE
*RATE	 to set
*/
public void setRATE(Integer RATE){
			 this.RATE = RATE;
		}

/**
*return theRATE
*/
public Integer getRATE(){
			 return this.RATE;
		}

 /**
 * @param	FIRST_DAY_RATE
*FIRST_DAY_RATE	 to set
*/
public void setFIRST_DAY_RATE(Integer FIRST_DAY_RATE){
			 this.FIRST_DAY_RATE = FIRST_DAY_RATE;
		}

/**
*return theFIRST_DAY_RATE
*/
public Integer getFIRST_DAY_RATE(){
			 return this.FIRST_DAY_RATE;
		}

 /**
 * @param	LAST_DAY_RATE
*LAST_DAY_RATE	 to set
*/
public void setLAST_DAY_RATE(Integer LAST_DAY_RATE){
			 this.LAST_DAY_RATE = LAST_DAY_RATE;
		}

/**
*return theLAST_DAY_RATE
*/
public Integer getLAST_DAY_RATE(){
			 return this.LAST_DAY_RATE;
		}

 /**
 * @param	ALL_OFFENDER_FLAG
*ALL_OFFENDER_FLAG	 to set
*/
public void setALL_OFFENDER_FLAG(String ALL_OFFENDER_FLAG){
			 this.ALL_OFFENDER_FLAG = ALL_OFFENDER_FLAG;
		}

/**
*return theALL_OFFENDER_FLAG
*/
public String getALL_OFFENDER_FLAG(){
			 return this.ALL_OFFENDER_FLAG;
		}

 /**
 * @param	ACTIVE_FLAG
*ACTIVE_FLAG	 to set
*/
public void setACTIVE_FLAG(String ACTIVE_FLAG){
			 this.ACTIVE_FLAG = ACTIVE_FLAG;
		}

/**
*return theACTIVE_FLAG
*/
public String getACTIVE_FLAG(){
			 return this.ACTIVE_FLAG;
		}

 /**
 * @param	IMPRISONMENT_STATUS
*IMPRISONMENT_STATUS	 to set
*/
public void setIMPRISONMENT_STATUS(String IMPRISONMENT_STATUS){
			 this.IMPRISONMENT_STATUS = IMPRISONMENT_STATUS;
		}

/**
*return theIMPRISONMENT_STATUS
*/
public String getIMPRISONMENT_STATUS(){
			 return this.IMPRISONMENT_STATUS;
		}

 /**
 * @param	LEAVE_RATE
*LEAVE_RATE	 to set
*/
public void setLEAVE_RATE(Integer LEAVE_RATE){
			 this.LEAVE_RATE = LEAVE_RATE;
		}

/**
*return theLEAVE_RATE
*/
public Integer getLEAVE_RATE(){
			 return this.LEAVE_RATE;
		}

 /**
 * @param	CREATE_DATETIME
*CREATE_DATETIME	 to set
*/
public void setCREATE_DATETIME(Timestamp CREATE_DATETIME){
			 this.CREATE_DATETIME = CREATE_DATETIME;
		}

/**
*return theCREATE_DATETIME
*/
public Timestamp getCREATE_DATETIME(){
			 return this.CREATE_DATETIME;
		}

 /**
 * @param	CREATE_USER_ID
*CREATE_USER_ID	 to set
*/
public void setCREATE_USER_ID(String CREATE_USER_ID){
			 this.CREATE_USER_ID = CREATE_USER_ID;
		}

/**
*return theCREATE_USER_ID
*/
public String getCREATE_USER_ID(){
			 return this.CREATE_USER_ID;
		}

 /**
 * @param	MODIFY_DATETIME
*MODIFY_DATETIME	 to set
*/
public void setMODIFY_DATETIME(Timestamp MODIFY_DATETIME){
			 this.MODIFY_DATETIME = MODIFY_DATETIME;
		}

/**
*return theMODIFY_DATETIME
*/
public Timestamp getMODIFY_DATETIME(){
			 return this.MODIFY_DATETIME;
		}

 /**
 * @param	MODIFY_USER_ID
*MODIFY_USER_ID	 to set
*/
public void setMODIFY_USER_ID(String MODIFY_USER_ID){
			 this.MODIFY_USER_ID = MODIFY_USER_ID;
		}

/**
*return theMODIFY_USER_ID
*/
public String getMODIFY_USER_ID(){
			 return this.MODIFY_USER_ID;
		}

 /**
 * @param	DELAY_DAYS
*DELAY_DAYS	 to set
*/
public void setDELAY_DAYS(Integer DELAY_DAYS){
			 this.DELAY_DAYS = DELAY_DAYS;
		}

/**
*return theDELAY_DAYS
*/
public Integer getDELAY_DAYS(){
			 return this.DELAY_DAYS;
		}

 /**
 * @param	SEAL_FLAG
*SEAL_FLAG	 to set
*/
public void setSEAL_FLAG(String SEAL_FLAG){
			 this.SEAL_FLAG = SEAL_FLAG;
		}

/**
*return theSEAL_FLAG
*/
public String getSEAL_FLAG(){
			 return this.SEAL_FLAG;
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
	/**
	 * @return the errorMessage
	*/
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	*/
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the impAgyBillingDetailId
	 */
	public Long getImpAgyBillingDetailId() {
		return impAgyBillingDetailId;
	}

	/**
	 * @param impAgyBillingDetailId the impAgyBillingDetailId to set
	 */
	public void setImpAgyBillingDetailId(final Long impAgyBillingDetailId) {
		this.impAgyBillingDetailId = impAgyBillingDetailId;
	}
	
	

}