package net.syscon.s4.pkgs.tag_prg;

import java.math.BigDecimal;

public interface TagPrgService {

	String getProviderName(final String providerPartyClass, final Long providerPartyId, final String providerPartyCode);

	Integer courseVacancy(final Long courseActivityId);
	
	String prgApptEventClass(Long offenderBookId, String agyLocId);
	
	BigDecimal creditHours(BigDecimal offenderBookId,BigDecimal seq,BigDecimal offenderSentCondId);

}