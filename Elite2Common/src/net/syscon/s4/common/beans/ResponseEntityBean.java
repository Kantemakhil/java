package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ResponseEntityBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("vHeaderBlock")
	private VHeaderBlock vHeaderBlock;
	
	@JsonProperty("liReturn")
	private int liReturn;

	public VHeaderBlock getvHeaderBlock() {
		return vHeaderBlock;
	}

	public void setvHeaderBlock(VHeaderBlock vHeaderBlock) {
		this.vHeaderBlock = vHeaderBlock;
	}

	public int getLiReturn() {
		return liReturn;
	}

	public void setLiReturn(int liReturn) {
		this.liReturn = liReturn;
	}
	
}
