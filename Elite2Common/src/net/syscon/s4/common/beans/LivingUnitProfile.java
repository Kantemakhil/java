/**
 *
 */
package net.syscon.s4.common.beans;

import java.io.Serializable;

/**
 * @author ankur.gupta
 *
 */
public class LivingUnitProfile extends BaseModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private String livingUnitId;
	private String profileId;
	private String agyLocId;
	private String description;
	private String intLocProfileType;
	private String intLocProfileCode;

	public String getLivingUnitId() {
		return livingUnitId;
	}
	public void setLivingUnitId(String livingUnitId) {
		this.livingUnitId = livingUnitId;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
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
	public String getIntLocProfileType() {
		return intLocProfileType;
	}
	public void setIntLocProfileType(String intLocProfileType) {
		this.intLocProfileType = intLocProfileType;
	}
	public String getIntLocProfileCode() {
		return intLocProfileCode;
	}
	public void setIntLocProfileCode(String intLocProfileCode) {
		this.intLocProfileCode = intLocProfileCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LivingUnitProfile [livingUnitId=");
		builder.append(livingUnitId);
		builder.append(", profileId=");
		builder.append(profileId);
		builder.append(", agyLocId=");
		builder.append(agyLocId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", intLocProfileType=");
		builder.append(intLocProfileType);
		builder.append(", intLocProfileCode=");
		builder.append(intLocProfileCode);
		builder.append("]");
		return builder.toString();
	}


}
