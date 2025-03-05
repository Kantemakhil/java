package net.syscon.s4.pkgs.ocdcbene;

import java.math.BigDecimal;

public interface OcdcbenePkgService {

	Integer processBankCheque(final BigDecimal pPersonId, final BigDecimal pCorporateId, final BigDecimal pAccountCode,
			final String pPayeeName, final String pCaseLoadId, final String pModuleName,
			final BigDecimal pTotalAmount, final BigDecimal pNewTxnId,final String userName);

}
