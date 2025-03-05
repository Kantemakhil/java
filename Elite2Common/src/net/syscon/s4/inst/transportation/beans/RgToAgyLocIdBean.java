package net.syscon.s4.inst.transportation.beans;
import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class RgToAgyLocIdBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class RgToAgyLocIdBean extends BaseModel implements Serializable {

	private String agyLocId;
	private String description;
	private String code;
	private String icrggq0;
	private Integer scheduledTripId;
	private String routeName;
	private Date departureDate;
	private String fromAgyLocId;
	private String toAgyLocId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}
	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}
	public String getToAgyLocId() {
		return toAgyLocId;
	}
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}
	private String errorMessage;

	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcrggq0() {
		return icrggq0;
	}
	public void setIcrggq0(String icrggq0) {
		this.icrggq0 = icrggq0;
	}
	public Integer getScheduledTripId() {
		return scheduledTripId;
	}
	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
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
	public String toString() {
	 return "{AGY_LOC_ID="+agyLocId+",DESCRIPTION="+description+",ICRGGQ_0="+icrggq0+"}";
	}
}

