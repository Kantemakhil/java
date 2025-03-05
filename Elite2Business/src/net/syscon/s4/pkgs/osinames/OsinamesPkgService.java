package net.syscon.s4.pkgs.osinames;

import java.math.BigDecimal;
import java.util.Map;

public interface OsinamesPkgService {

	Map<String, Object> getOffDetailsByBookId(final Long offenderBookId);

	Map<String, Object> getOffenderDetails(final String pOffenderIdDisplay, final String pAgyLocId,
			final String pCaseloadId);

	BigDecimal getOffBookId(final String vstOffIdDisplay, String userId);

}