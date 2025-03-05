package net.syscon.s4.inst.transportation.beans;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;


/**
 * Class ScheduledTripAssignments10ScheduledTrips10StaffMembers
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledTripAssignments extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("staffId")
	private Long staffId;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("butExit")
	private String butExit;
	
    public String getLastName() {
       return this.lastName;
    }

	    public void setLastName(final String lastName ) {
       this.lastName = lastName;
    }

	
    public String getFirstName() {
       return this.firstName;
    }

	    public void setFirstName(final String firstName ) {
       this.firstName = firstName;
    }

	
    public String getButExit() {
       return this.butExit;
    }

	    public void setButExit(final String butExit ) {
       this.butExit = butExit;
    }

	
    public Long getStaffId() {
       return this.staffId;
    }

	    public void setStaffId(final Long staffId ) {
       this.staffId = staffId;
    }

}