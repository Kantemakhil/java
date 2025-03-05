package net.syscon.s4.pkgs.tag_search;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Offenders;

public interface TagSearchRepository {
	List<OffenderIdentifier> getOffenderIdentifiers(final Long offenderId);

	String getProfileValue(final String profileType, final String profileCode);

	List<Offenders> resultSetOperationOne(final Offenders offenders);

	List<Offenders> resultSetOperationTwo(final Offenders offenders);

	List<Offenders> resultSetOperationThree(final Offenders offenders);

	List<Offenders> resultSetOperationFour(final String vOffenderIdDisplay);

	List<Offenders> resultSetOperationFive(final String vOffenderIdDisplay, final String bookingNo);

	List<Offenders> resultSetOperationSix(final String bookingNo);

	List<Offenders> resultSetOperationSeven(final String bookingNo, final String identifier,
			final String identifierType);

	List<Offenders> resultSetOperationEigth(final String bookingNo, final String identifier,
			final String identifierType, final String vOffenderIdDisplay);

	List<Offenders> getPartialRecordsSelectOperation(final Offenders offenders);

	List<Offenders> getPartialRecordsSelectOperationOne(final Offenders offenders);

	public OffenderBookings getCommunityDetailsCur(final BigDecimal pRootOffenderId);

	public String getAgyDescription(final String pAgyLocId);

	public List<AgencyInternalLocations> getInternalLocId(final Integer pInternalLocationId);

	public OffenderBookings getPrisonDetailsCur(final BigDecimal pRootOffenderId);

	public OffenderBookings prisonDetailsInactiveCur(final BigDecimal pRootOffenderId);

	Integer deleteCourseActivityAreasDeleteOperation(final Long crsActyId,String modifyUserId);

	List<Areas> cArea(final String areaCode);

	List<Offenders> resultSetOperationDob(Offenders offenders);
}