package net.syscon.s4.pkgs.deductions;

import java.util.Date;

import java.util.Date;

import java.util.Date;

import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

public interface DeductionsService {

	String chkOffenderDeductions(final String pCsldId, final Long pOffId, final String pTransType,
			final Long pShadowId);

	void updateIndigentDate(final Integer accountCode, final Long offenderId, final String caseloadId,
			final String userName);

	Integer createConditionDeductions(final OffenderSentConditions offSentCon, final String userName);

	String getAcAndSetIndDate(final Long offenderId, final String caseloadId, final String userName);

	Date createDefaultDeductions(final String pCsldId, final Integer pOffId, final String userName);

	Integer updateDeductionStatus(final SuspendDeductions obj, final String userName);

	Integer suspendOffDeductions(final String userName);
}
