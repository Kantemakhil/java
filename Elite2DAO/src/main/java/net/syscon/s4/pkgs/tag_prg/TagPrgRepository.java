package net.syscon.s4.pkgs.tag_prg;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OffenderBookings;

public interface TagPrgRepository {

	String getProviderNameFromTeams(final Long providerPartyId);

	String getProviderNameFromCorporates(final Long providerPartyId);

	String getProviderNameFromPerson(final Long providerPartyId);

	String getProviderNameFromAgencyLocation(final String providerPartyCode);

	Long getCapacity(final Long courseActivityId);

	Long getCount(final Long courseActivityId);

	OffenderBookings getRecFrmOffBkgs(Long offenderBookId);
	
	BigDecimal creditHoursWithoutSentCond (BigDecimal offenderBookId, BigDecimal seq);
	
	BigDecimal creditHoursWithSentCond (BigDecimal offenderBookId, BigDecimal seq, BigDecimal offenderSentCondId);

}