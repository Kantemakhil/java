package net.syscon.s4.pkgs.tag_search;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;

public interface TagSearchService {
	List<OffenderIdentifier> getOffenderIdentifiers(final Long offenderId);

	List<Offenders> getOffenderRecords(final Offenders offenders);

	List<Offenders> getPartialRecords(final Offenders offenders);

	Date[] getDateRange(final String birthYear, final Integer birthRange);

	Date[] getAgeDateRange(final Date ageDate, final Integer ageRange);

	List<TagSearchPopulateOffDetails> populateOffDetails(final TagSearchPopulateOffDetails searchBean);

	Integer deleteCourseActivityAreas(final Long crsActyId,String modifyUserId);

	String getCourseActivityAreaDesc(final String areaCode);
}