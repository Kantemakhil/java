package net.syscon.s4.pkgs.indigent;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

public interface IndigentRepository {

	String getChkCaseloadC(final String pCsldId);

	List<OffenderSubAccounts> getChkIndAcCTrstAccCode(final BigDecimal pOffId, final String pCsldId,
			final String csldType);

	OffenderSubAccounts getCurChkIndIndDateNDays(final BigDecimal pOffId, final String pCsldId, final String pAgyLocId,
			final Long pTrustAcctCode);

	Integer getDiffIndDateNdays(final BigDecimal indDays, final Date indDate);

	SystemProfiles getSystemProfileC();

	Integer getAccCode();

	Double getSumOffSubActBalC(final BigDecimal pOffId, final Integer lvTrustAccCode);

	Date getMaxIndDateC(final BigDecimal pOffId, final Integer lvTrustAccCode);

	BigDecimal getMaxIndDays(final BigDecimal pOffId, final Integer lvTrustAccCode, final Date lvIndigentDate);

	Integer getDiffBtSysNIndDate(final Date indDate);
}
