package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VEepbiMins extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("cni")
	private long cni;
	@JsonProperty("alternateMin")
	private long alternateMin;
	public long getCni() {
		return cni;
	}
	public void setCni(long cni) {
		this.cni = cni;
	}
	public long getAlternateMin() {
		return alternateMin;
	}
	public void setAlternateMin(long alternateMin) {
		this.alternateMin = alternateMin;
	}
	
	

}
