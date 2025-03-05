package net.syscon.s4.cf.deductions.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.maintenance.OtmcfeesRepository;
import net.syscon.s4.cf.deductions.maintenance.OtmcfeesService;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmountsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetailsCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Class OtmcfeesServiceImpl
 */
@Service
public class OtmcfeesServiceImpl extends BaseBusiness implements OtmcfeesService {

	@Autowired
	private OtmcfeesRepository otmcfeesRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(final CaseloadDeductionProfiles searchRecord) {
		List<CaseloadDeductionProfiles> recordList = new ArrayList<>();
		String corpName = null;
		recordList = otmcfeesRepository.csldDpExecuteQuery(searchRecord);
		if (!recordList.isEmpty())
			for (final CaseloadDeductionProfiles object : recordList) {
				corpName = otmcfeesRepository
						.getCorporateName(String.valueOf(object.getPayeeCorporateId() != null ? object.getPayeeCorporateId() : null ));
				if (corpName != null) {
					object.setCorporateName(corpName);
				} else {
					object.setCorpNameNotFound("Y");
				}
			}
		return recordList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<CaseloadDeductionProfiles> csldDpCommit(final CaseloadDeductionProfilesCommitBean commitBean) {
		final List<CaseloadDeductionProfiles> liReturnData = new ArrayList<>();
		final CaseloadDeductionProfiles caseloadPrf = new CaseloadDeductionProfiles();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final CaseloadDeductionProfiles object : commitBean.getInsertList()) {
				BigDecimal exterPriorityNum = new BigDecimal(
						otmcfeesRepository.getExternalPriorityNumber(object.getCaseloadId()));
				object.setExternalPriorityNo(exterPriorityNum);
				//aading user to bean
				object.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.csldDpInsertCaseloadDeductionProfiles(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final CaseloadDeductionProfiles object : commitBean.getUpdateList()) {
				//aading user to bean
				object.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.csldDpUpdateCaseloadDeductionProfiles(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final CaseloadDeductionProfiles object : commitBean.getDeleteList()) {
				object.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.csldDpDeleteCaseloadDeductionProfiles(commitBean.getDeleteList());
		}
		caseloadPrf.setReturnValue(liReturn);
		liReturnData.add(caseloadPrf);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<TransactionFeeDetails> tranFdExecuteQuery(TransactionFeeDetails searchRecord) {
		return otmcfeesRepository.tranFdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTRAN_FD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<TransactionFeeDetails> tranFdCommit(final TransactionFeeDetailsCommitBean commitBean) {
		final List<TransactionFeeDetails> liReturnData = new ArrayList<>();
		final TransactionFeeDetails object = new TransactionFeeDetails();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TransactionFeeDetails transactionFeeDetails : commitBean.getInsertList()) {
				//aading user to bean
				transactionFeeDetails.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.tranFdInsertTransactionFeeDetails(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TransactionFeeDetails transactionFeeDetails : commitBean.getUpdateList()) {
				//aading user to bean
				transactionFeeDetails.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.tranFdUpdateTransactionFeeDetails(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (TransactionFeeDetails transactionFeeDetails : commitBean.getDeleteList()) {
				transactionFeeDetails.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.tranFdDeleteTransactionFeeDetails(commitBean.getDeleteList());
		}
		object.setReturnValue(liReturn);
		liReturnData.add(object);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<TieredTransactionFeeAmounts> tierTfaExecuteQuery(TieredTransactionFeeAmounts searchRecord) {
		return otmcfeesRepository.tierTfaExecuteQuery(searchRecord);

	}

	public Integer getOverLapCount(final TieredTransactionFeeAmounts paramBean) {
		return otmcfeesRepository.getOverLapCount(paramBean);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTIER_TFA
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<TieredTransactionFeeAmounts> tierTfaCommit(final TieredTransactionFeeAmountsCommitBean commitBean) {
		final List<TieredTransactionFeeAmounts> liReturnData = new ArrayList<>();
		final TieredTransactionFeeAmounts object = new TieredTransactionFeeAmounts();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TieredTransactionFeeAmounts transactionFeeAmt : commitBean.getInsertList()) {
				//aading user to bean
				transactionFeeAmt.setCreateUserId(commitBean.getCreateUserId());
			}

			liReturn = otmcfeesRepository.tierTfaInsertTieredTransactionFeeAmounts(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TieredTransactionFeeAmounts transactionFeeAmt : commitBean.getUpdateList()) {
				//aading user to bean
				transactionFeeAmt.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.tierTfaUpdateTieredTransactionFeeAmounts(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (TieredTransactionFeeAmounts transactionFeeAmt : commitBean.getDeleteList()) {
				transactionFeeAmt.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmcfeesRepository.tierTfaDeleteTieredTransactionFeeAmounts(commitBean.getDeleteList());
		}
		object.setReturnValue(liReturn);
		liReturnData.add(object);
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		return otmcfeesRepository.cgfkCsldDpPayeeCorporateIRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<DeductionTypes> cgfkTranFdReceiptDeductionRecordGroup(final String caseLoadType) {
		return otmcfeesRepository.cgfkTranFdReceiptDeductionRecordGroup(caseLoadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseLoadType) {
		return otmcfeesRepository.cgfkCsldDpDeductionTypeRecordGroup(caseLoadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseLoadType) {
		List<AccountCodes> accList = new ArrayList<>();
		accList = otmcfeesRepository.cgfkCsldDpAccountCodeRecordGroup(caseLoadType);
		if (!accList.isEmpty()) {
			for (final AccountCodes accountCodes : accList) {
				accountCodes.setCode(String.valueOf(accountCodes.getAccountCode()));

			}
		}
		return accList;

	}
	/**
	 * This method is used to get the corporate name based on corporate id
	 *
	 * @throws SQLException
	 */
	@Override
	public CaseloadDeductionProfiles getCorporateName(final CaseloadDeductionProfiles paramBean) {
		String corpName=null;
		corpName = otmcfeesRepository.getCorporateName(String.valueOf(paramBean.getPayeeCorporateId()));
		if(corpName!=null){
			paramBean.setCorporateName(corpName);
		}
		return paramBean;
	}

}