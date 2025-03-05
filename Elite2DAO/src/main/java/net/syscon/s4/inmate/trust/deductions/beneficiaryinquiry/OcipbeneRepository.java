package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.BankChequeBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.PersonAddressV;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcipbeneRepository
 */
public interface OcipbeneRepository {
	PersonAddressV perPostQuery(Persons paramBean);

	List<Persons> perExecuteQuery(Persons objPersons);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	Integer perUpdatePersons(List<Persons> lstPersons);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries objOffBen);

	OffenderDeductions cgfkchkOffBncOffBncOff(OffenderBeneficiaries paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Offenders offenderDetailsExecuteQuery(OffenderDeductions paramBean);

	BigDecimal montlyAmountRecieved(BigDecimal offDeductionId, BigDecimal personId);

	BigDecimal recursiveAmountRecieved(BigDecimal offDeductionId);

	List<BankChequeBeneficiaries> bankChequeBeneficiariesExecuteQuery(Persons objSearchDao);

	BigDecimal bankChequeBeneficiariesCheckAmount(BigDecimal objSearchDao);

	BigDecimal offenderCreditPriorPayments(Persons paramBean);

	Map<String, Object> calculateBeneficiaries(Long personId);

	OffenderBeneficiaries procedureQueryOne(Long personId);

	BigDecimal procedureQueryTwo(Long personId);

	BigDecimal procedureQueryThree(Long personId);

}
