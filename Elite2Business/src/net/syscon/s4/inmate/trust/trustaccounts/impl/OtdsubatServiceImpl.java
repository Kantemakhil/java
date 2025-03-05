package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.trust.trustaccounts.OtdsubatRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdsubatService;
import net.syscon.s4.pkgs.otdsubat.OtdsubatPkgService;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;

/**
 * Class OtdsubatServiceImpl
 */
@Service
public class OtdsubatServiceImpl extends BaseBusiness implements OtdsubatService {

	private final static String SUPV = "SUPV";

	private final static String REG = "REG";

	@Autowired
	private OtdsubatRepository otdsubatRepository;
	
	@Autowired
	private OtdsubatPkgService otdsubatPkgService;
	
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service;
	/**
	 * Creates new OtdsubatServiceImpl class Object
	 */
	public OtdsubatServiceImpl() {
		// OtdsubatServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffTxn2OffTxnRef(final ReferenceCodes paramBean) {
		return otdsubatRepository.cgfkchkOffTxn2OffTxnRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(final ReferenceCodes paramBean) {
		return otdsubatRepository.cgfkchkOffTxnOffTxnRef(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTransactions> offTxn2ExecuteQuery(final OffenderTransactions searchRecord) {
		return otdsubatRepository.offTxn2ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN2
	 *
	 */
	@Transactional
	public List<OffenderTransactions> offTxn2Commit(final OffenderTransactionsCommitBean commitBean,String userName) {
		List<OffenderTransactions> liReturn = new ArrayList<OffenderTransactions>();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = offtxn2InsertOffenderTransactions(commitBean.getInsertList(), userName);
		}
//		// updateRecords
//		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
//			liReturn = otdsubatRepository.offTxn2UpdateOffenderTransactions(commitBean.getUpdateList());
//		}
		return liReturn;
	}

	private List<OffenderTransactions> offtxn2InsertOffenderTransactions(List<OffenderTransactions> insertList,String userName) {

		Integer crAccountCode = 0;
		Integer drAcCode = 0;
		for (final OffenderTransactions obj : insertList) {
			Long offenderId=otdsubatRepository.getRootOffenderId(obj.getOffenderIdDisplay(),obj.getCaseloadId(),obj.getBookingNo());
			obj.setOffenderId(offenderId);
			crAccountCode = otdsubatRepository.getAccountCode(obj.getCaseloadType(), obj.getFmSubAccountType());
			if (crAccountCode != 0) {
				if (crAccountCode == 2101) {
					drAcCode = 2102;
				} else {
					drAcCode = 2101;
				}
			}
			if (crAccountCode != 0 && drAcCode != 0) {
				List<TransactionOperation> checkFlags = otdsubatRepository.getCheckProductionFlag(obj.getModuleName(),
						obj.getTxnType(), crAccountCode, drAcCode, obj.getCaseloadId());
				for (final TransactionOperation objTemp : checkFlags) {
					obj.setChequeProductionFlag(objTemp.getChequeProductionFlag());
					obj.setCrAccountCode(objTemp.getDrAccountCode().intValue());
					// obj.setDrAcountCode(objTemp.getDrAccountCode().intValue());

				}
				List<OffenderTransactions> corporateidandNames = otdsubatRepository.getCorporateIdName(
						obj.getModuleName(), obj.getTxnType(), crAccountCode, drAcCode, obj.getCaseloadId(),userName);
				if (corporateidandNames.size() > 0) {

					for (final OffenderTransactions corpTemp : corporateidandNames) {
						if (corpTemp.getPayeeCorporateId() != null) {
							obj.setPayeeCorporateId(corpTemp.getPayeeCorporateId());
						} else {
							obj.setPayeeCorporateId(0);
						}
						obj.setCorporateName(corpTemp.getCorporateName());
					}
				} else {
					obj.setPayeeCorporateId(0);
				}

			}
//			obj.setCrAccountCode(crAccountCode);
//			obj.setDrAcountCode(drAcCode);
			final Integer txnId = otdsubatRepository.getTxnId();
			obj.setTxnId(txnId);
		}
		
	//	final Integer result = otdsubatRepository.offtxn2InsertOffenderTransactions(insertList);
		//Procedure call
		final Integer result= otdsubatPkgService.processTransaction(insertList,userName);
		if (result != 1) {
			final OffenderTransactions offTransError = new OffenderTransactions();
			offTransError.setErrorMessage("Error");
			List<OffenderTransactions> offTransErrorList = new ArrayList<>();
			offTransErrorList.add(offTransError);
			return offTransErrorList;
		}
		if (insertList.size() > 0) {
			List<FeeAccounts> feeList = otdsubatRepository.prepaidFeeCodes();
			insertList.forEach(bo -> {
				for (FeeAccounts obj : feeList) {
					if (obj.getFeeCode().equalsIgnoreCase(bo.getFmSubAccountType())) {
						prepaideAccountStatus(bo, bo.getFmSubAccountType());
					}
					if (obj.getFeeCode().equalsIgnoreCase(bo.getToSubAccountType())) {
						prepaideAccountStatus(bo, bo.getToSubAccountType());
					}
				}
			});
		}

		return insertList;
	}

	// Prepaide Account Status
	public Integer prepaideAccountStatus(OffenderTransactions bean, String subAccountType) {
		FeeAccountProfiles feeAccProf = new FeeAccountProfiles();
		Integer retVal = 0;
		if (bean.getPreWithholdAmount() != null && bean.getTxnEntryAmount() != null) {
			// get OffenderFeeId
			Long offenderFeeId = otdsubatRepository.getOffenderFeeId(bean.getOffenderBookId(), subAccountType);
			if (offenderFeeId != null) {
				Double superVisionFeeAmt = (bean.getPreWithholdAmount() - bean.getTxnEntryAmount());
				String supervisionStatus = otdsubatRepository.getFeeAccountProfileData(offenderFeeId);
				if (superVisionFeeAmt == 0d) {
					if (supervisionStatus != null && "P".equalsIgnoreCase(supervisionStatus)) {
						feeAccProf.setFeeActStatus("A");// Active
						feeAccProf.setOffenderFeeId(BigDecimal.valueOf(offenderFeeId));
						retVal = otdsubatRepository.updateFeeAccountProfiles(feeAccProf);
						//Trigger call
						offFeeAccountProfileT2Service.offFeeAccountProfileT2(feeAccProf);
					}
				}
				if (superVisionFeeAmt > 0) {
					if (supervisionStatus != null && "A".equalsIgnoreCase(supervisionStatus)) {
						feeAccProf.setFeeActStatus("P");// Prepaid
						feeAccProf.setOffenderFeeId(BigDecimal.valueOf(offenderFeeId));
						retVal = otdsubatRepository.updateFeeAccountProfiles(feeAccProf);
						//Trigger call
						offFeeAccountProfileT2Service.offFeeAccountProfileT2(feeAccProf);
					}
				}
			}
		}
		return retVal;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<TransactionOperation> cgfkOffTxn2SubAccountTypeRecordGroup(final String caseLoadId) {
		final List<TransactionOperation> returnList= otdsubatRepository.cgfkOffTxn2SubAccountTypeRecordGroup(caseLoadId);
		returnList.forEach(result-> {
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(final String fromSubAccount,
			final String caseLoadId) {
		return otdsubatRepository.cgfkOffTxnSubAccountTypeRecordGroup(fromSubAccount, caseLoadId);

	}

	@Override
	public String getDescription(final String caseloadType, final String txnType) {
		return otdsubatRepository.getDescription(caseloadType, txnType);
	}

	@Override
	public String getacCode(final String code, final String caseloadType) {
		return otdsubatRepository.getacCode(code, caseloadType);
	}

	@Override
	public String getBal(final String offenderId, final String caseloadId, final String acCode) {
		return otdsubatRepository.getBal(offenderId, caseloadId,acCode);
	}

	@Override
	public String getAcClosedFlg(final Long offenderId, final String caseloadId) {
		return otdsubatRepository.getAcClosedFlg(offenderId, caseloadId);
	}

}