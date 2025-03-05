package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordCreator {

	@JsonProperty("LocationFormattedName")
	private LocationFormattedName locationFormattedName;

	@JsonProperty("TrimType")
	private String TrimType;

	@JsonProperty("Uri")
	private String uri;

	@JsonProperty("Icon")
	private Icon icon;

	@JsonProperty("NameString")
	private String nameString;

	public LocationFormattedName getLocationFormattedName() {
		return locationFormattedName;
	}

	public void setLocationFormattedName(LocationFormattedName locationFormattedName) {
		this.locationFormattedName = locationFormattedName;
	}

	public String getTrimType() {
		return TrimType;
	}

	public void setTrimType(String trimType) {
		TrimType = trimType;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecordCreator [locationFormattedName=");
		builder.append(locationFormattedName);
		builder.append(", TrimType=");
		builder.append(TrimType);
		builder.append(", uri=");
		builder.append(uri);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", nameString=");
		builder.append(nameString);
		builder.append("]");
		return builder.toString();
	}
}
