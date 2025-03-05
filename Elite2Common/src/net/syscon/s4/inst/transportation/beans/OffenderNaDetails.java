package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderNaDetails extends BaseModel  implements Serializable {
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("nsOffenderId")
	private BigDecimal nsOffenderId;
	@JsonProperty("offenderIdDisplay")
	private Object offenderIdDisplay;
	@JsonProperty("offenderName")
	private Object offenderName;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("nsOffenderBookId")
	private BigDecimal nsOffenderBookId;
	@JsonProperty("livingUnitDescription")
	private Object livingUnitDescription;
	@JsonProperty("nbtType")
	private Object nbtType;
	@JsonProperty("nbtReason")
	private Object nbtReason;
	@JsonProperty("nsLevelCode")
	private Object nsLevelCode;
	@JsonProperty("nsReasonCode")
	private Object nsReasonCode;
	@JsonProperty("nsType")
	private Object nsType;
	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;
	@JsonProperty("moduleName")
	private String moduleName;
	
    public BigDecimal getOffenderBookId() {
       return this.offenderBookId;
    }

	    public void setOffenderBookId(final BigDecimal offenderBookId ) {
       this.offenderBookId = offenderBookId;
    }

	
    public Object getOffenderName() {
       return this.offenderName;
    }

	    public void setOffenderName(final Object offenderName ) {
       this.offenderName = offenderName;
    }

	
    public Object getLivingUnitDescription() {
       return this.livingUnitDescription;
    }

	    public void setLivingUnitDescription(final Object livingUnitDescription ) {
       this.livingUnitDescription = livingUnitDescription;
    }

	
    public Object getNsReasonCode() {
       return this.nsReasonCode;
    }

	    public void setNsReasonCode(final Object nsReasonCode ) {
       this.nsReasonCode = nsReasonCode;
    }

	
    public Object getNdoc() {
       return this.offenderIdDisplay;
    }

	    public void setOffenderIdDisplay(final Object offenderIdDisplay) {
       this.offenderIdDisplay = offenderIdDisplay;
    }

	
    public Object getNbtType() {
       return this.nbtType;
    }

	    public void setNbtType(final Object nbtType ) {
       this.nbtType = nbtType;
    }

	
    public Object getNsLevelCode() {
       return this.nsLevelCode;
    }

	    public void setNsLevelCode(final Object nsLevelCode ) {
       this.nsLevelCode = nsLevelCode;
    }

	
    public BigDecimal getOffenderId() {
       return this.offenderId;
    }

	    public void setOffenderId(final BigDecimal offenderId ) {
       this.offenderId = offenderId;
    }

	
    public BigDecimal getNsOffenderId() {
       return this.nsOffenderId;
    }

	    public void setNsOffenderId(final BigDecimal nsOffenderId ) {
       this.nsOffenderId = nsOffenderId;
    }

	
    public Object getNbtReason() {
       return this.nbtReason;
    }

	    public void setNbtReason(final Object nbtReason ) {
       this.nbtReason = nbtReason;
    }

	
    public Object getNsType() {
       return this.nsType;
    }

	    public void setNsType(final Object nsType ) {
       this.nsType = nsType;
    }

	
    public BigDecimal getNsOffenderBookId() {
       return this.nsOffenderBookId;
    }

	    public void setNsOffenderBookId(final BigDecimal nsOffenderBookId ) {
       this.nsOffenderBookId = nsOffenderBookId;
    }
	    
		public Long getScheduledTripId() {
			return this.scheduledTripId;
		}

		public void setScheduledTripId(final Long scheduledTripId) {
			this.scheduledTripId = scheduledTripId;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public Object getOffenderIdDisplay() {
			return offenderIdDisplay;
		}
		
		

}
