package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordTitle {
   @JsonProperty("Value")	
   private String value;

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}
   
}
