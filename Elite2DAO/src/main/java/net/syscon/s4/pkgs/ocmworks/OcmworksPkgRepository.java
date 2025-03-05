package net.syscon.s4.pkgs.ocmworks;

import java.math.BigDecimal;

public interface OcmworksPkgRepository {
	Integer getPrevDays(final String pTriggerName);

	Integer getCountIwpDocsCur(final BigDecimal pTemplateId);

	Integer getCountOffAssociated(final String pCaseNoteType, final String pCaseNoteSubType);
}