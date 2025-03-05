package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VBankChequeBeneficiaries;

/**
 * Interface OcutrahiRepository
 */
public interface OcutrahiRepository {
	List<BeneficiaryTransactions> benTxnExecuteQuery(BeneficiaryTransactions objBeneficiaryTransactions);

	List<VBankChequeBeneficiaries> vBcBenExecuteQuery(VBankChequeBeneficiaries objVBankChequeBeneficiaries);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

}
