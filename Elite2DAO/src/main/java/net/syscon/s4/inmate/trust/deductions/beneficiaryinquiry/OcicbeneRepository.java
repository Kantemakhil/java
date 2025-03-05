package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcicbeneRepository
 */
public interface OcicbeneRepository {

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries object);

	OffenderDeductions cgfkchkOffBncOffBncOff(OffenderDeductions paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<CorporateAddressV> vCorpExecuteQuery(CorporateAddressV object);

	OffenderBeneficiaries offBncPostQuery(OffenderBeneficiaries obj);

	OffenderDeductions offBncPopulateWriteOff(OffenderBeneficiaries obj);

	String offBncCheckDedCat(String deductionType);

	Integer offBncGetMonAmt(OffenderBeneficiaries obj);

	BigDecimal offBncRecMonth(OffenderBeneficiaries obj);

	BigDecimal getChequeSum(BigDecimal corporateId);

	BigDecimal getPaymentAmount(BigDecimal corporateId);

	BigDecimal calculateBeneficiariesTotal(OffenderBeneficiaries searchRecord);

	Integer corpUpdateSuspendDate(Corporates beanObj);

	String caseloadFlagData(String string, String string2);

}
