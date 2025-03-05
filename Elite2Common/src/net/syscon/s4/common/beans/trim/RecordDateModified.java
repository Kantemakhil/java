package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordDateModified {

	 @JsonProperty("IsClear")
	   private Boolean isClear;

	 @JsonProperty("IsTimeClear")
	   private Boolean isTimeClear;

	 @JsonProperty("DateTime")
	   private String dateTime;

	public Boolean getIsClear() {
		return isClear;
	}

	public void setIsClear(Boolean isClear) {
		this.isClear = isClear;
	}

	public Boolean getIsTimeClear() {
		return isTimeClear;
	}

	public void setIsTimeClear(Boolean isTimeClear) {
		this.isTimeClear = isTimeClear;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecordDateModified [isClear=");
		builder.append(isClear);
		builder.append(", isTimeClear=");
		builder.append(isTimeClear);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}
