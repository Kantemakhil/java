package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VBankChequeBeneficiaries;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcutrahiRepository;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcutrahiService;

/**
 * Class OcutrahiServiceImpl
 */
@Service
public class OcutrahiServiceImpl extends BaseBusiness implements OcutrahiService {

	@Autowired
	private OcutrahiRepository ocutrahiRepository;

	/**
	 * Creates new OcutrahiServiceImpl class Object
	 */
	public OcutrahiServiceImpl() {
		// OcutrahiServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<BeneficiaryTransactions> benTxnExecuteQuery(final BeneficiaryTransactions searchRecord) {
		List<BeneficiaryTransactions> returnList = new ArrayList<>();
		returnList = ocutrahiRepository.benTxnExecuteQuery(searchRecord);
		for (final BeneficiaryTransactions returnRow : returnList) {
			if (returnRow.getTxnPostUsage() != null && "DR".equals(returnRow.getTxnPostUsage())) {
				returnRow.setTxnEntryAmount(returnRow.getTxnEntryAmount().multiply(new BigDecimal(-1)));
			}
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VBankChequeBeneficiaries> vBcBenExecuteQuery(final VBankChequeBeneficiaries searchRecord) {
		return ocutrahiRepository.vBcBenExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocutrahiRepository.sysPflExecuteQuery(searchRecord);

	}

}