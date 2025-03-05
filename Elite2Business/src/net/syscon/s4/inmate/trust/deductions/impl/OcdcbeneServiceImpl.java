package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VClearAccountPayables;
import net.syscon.s4.inmate.beans.VClearAccountPayablesCommitBean;
import net.syscon.s4.inmate.trust.deductions.OcdcbeneRepository;
import net.syscon.s4.inmate.trust.deductions.OcdcbeneService;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.pkgs.ocdcbene.OcdcbenePkgService;

/**
 * Class OcdcbeneServiceImpl
 */
@Service
public class OcdcbeneServiceImpl extends BaseBusiness implements OcdcbeneService {

	@Autowired
	private OcdcbeneRepository ocdcbeneRepository;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OcdcbenePkgService ocdcbenePkgService;

	/**
	 * Creates new OcdcbeneServiceImpl class Object
	 */
	public OcdcbeneServiceImpl() {
		// OcdcbeneServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public String createFormGlobals(final OmsModules paramBean) {
		return ocdcbeneRepository.createFormGlobals(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VClearAccountPayables> payeeAbExecuteQuery(final VClearAccountPayables searchRecord) {
		final List<VClearAccountPayables> returnList = ocdcbeneRepository.payeeAbExecuteQuery(searchRecord);
		String personFlag = null;
		for (final VClearAccountPayables obj : returnList) {
			personFlag = null;
			if (obj.getPersonId() != null) {
				personFlag = ocdcbeneRepository.getPersonsInfoCursor(obj.getPersonId());
			} else if (obj.getCorporateId() != null) {
				personFlag = ocdcbeneRepository.getCorporateInfoCursor(obj.getCorporateId());
			}
			obj.setCaseloadIdTemp(personFlag);
			final BigDecimal minBal = ocdcbeneRepository.getMinimuimBalCursor(obj.getAccountCode());
			obj.setMinBalAmount(minBal);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPAYEE_AB
	 *
	 */
	@Transactional
	public Integer payeeAbCommit(final VClearAccountPayablesCommitBean commitBean) {
		Integer liReturn = null;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VClearAccountPayables obj : commitBean.getUpdateList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = payeeAbUpdateVClearAccountPayables(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer payeeAbUpdateVClearAccountPayables(final List<VClearAccountPayables> lstOffenderAlerts) {
		for (final VClearAccountPayables obj : lstOffenderAlerts) {
			if ("Y".equals(obj.getCgnbtCaseloadId()) && obj.getTotalAmount() != null) {
		//		ocdcbeneRepository.checkCaseloadlockType(obj.getCaseloadId());
				commonService.lockFormModule(obj.getCaseloadId(),obj.getCreateUserId());
				final Integer txnIdNextVal = ocdcbeneRepository.txnIdNextValData();
//				Integer processBankChequeData = ocdcbeneRepository.processBankCheque(obj.getPersonId(),
//						obj.getCorporateId(), obj.getAccountCode(), obj.getCorporateName(), obj.getCaseloadId(),
//						obj.getTotalAmount(), txnIdNextVal);
				Integer processBankChequeData =	ocdcbenePkgService.processBankCheque(obj.getPersonId(), obj.getCorporateId(), obj.getAccountCode(),  obj.getCorporateName(), obj.getCaseloadId(), "OCDCBENE", obj.getTotalAmount(), BigDecimal.valueOf(txnIdNextVal), obj.getCreateUserId());
		//		ocdcbeneRepository.checkCaseloadUnlockType(obj.getCaseloadId());
				commonService.unlockFormModule(obj.getCaseloadId(),obj.getCreateUserId());
				if (processBankChequeData != 1) {
					return 0;
				}
			}
		}
		return 1;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<BeneficiaryTransactions> benTxnExecuteQuery(final BeneficiaryTransactions searchRecord) {
		final List<BeneficiaryTransactions> returnList = ocdcbeneRepository.benTxnExecuteQuery(searchRecord);
		for (final BeneficiaryTransactions obj : returnList) {
			final BeneficiaryTransactions offenderNames = ocdcbeneRepository.offenderNameCursor(obj);
			final String postingType = ocdcbeneRepository
					.txnPostingTypeCursor(Integer.valueOf(obj.getAccountCode().toString()));
			obj.setOffenderIdDisplay(offenderNames.getOffenderIdDisplay());
			obj.setRootOffenderId(offenderNames.getRootOffenderId());
			obj.setLastFirstName(offenderNames.getLastFirstName());
			obj.setSealFlag(postingType);

		}
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocdcbeneRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public String checkLock(final String caseLoadId) {
		return ocdcbeneRepository.checkLock(caseLoadId);
	}
}