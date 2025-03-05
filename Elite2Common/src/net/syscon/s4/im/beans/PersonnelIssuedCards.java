package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnelIssuedCards extends BaseModel implements Serializable {

	
	private static final long serialVersionUID = 1L;
}
