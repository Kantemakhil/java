package net.syscon.s4.pkgs.tag_licence;

public interface TagLicenceRepository {
	public String getRequirement(final String pCommConditionCode, final String pCommConditionType,
			final String pCategoryType);
}