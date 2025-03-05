package net.syscon.s4.pkgs.tag_licence;

import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

public interface TagLicenceService {
	 OffenderSentConditions getPostQueryRec(final OffenderSentConditions offSentCond);

	 String getRequirement(final String pCommConditionCode, final String pCommConditionType,
			final String pCategoryType);
}