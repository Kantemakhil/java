package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inmate.trust.deductions.OtdoalloRepository;
import net.syscon.s4.inmate.trust.deductions.OtdoalloService;

/**
 * Class OtdoalloServiceImpl
 */
@Service
public class OtdoalloServiceImpl extends BaseBusiness implements OtdoalloService {

	@Autowired
	private OtdoalloRepository otdoalloRepository;

	/**
	 * Creates new OtdoalloServiceImpl class Object
	 */
	public OtdoalloServiceImpl() {
		// OtdoalloServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public DeductionTypes CgfkchkOffDedOffDedDed(final DeductionTypes paramBean) {
		final DeductionTypes deductionTypes = otdoalloRepository.cgfkchkOffDedOffDedDed(paramBean);
		return deductionTypes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public TransactionTypes CgfkchkOffDrOffDrTxnTyp(final TransactionTypes paramBean) {
		final TransactionTypes transactionTypes = otdoalloRepository.cgfkchkOffDrOffDrTxnTyp(paramBean);
		return transactionTypes;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * 
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions searchRecord) {
		List<OffenderDeductions> returnList = new ArrayList<>();
		returnList = otdoalloRepository.offDedExecuteQuery(searchRecord);
		for (final OffenderDeductions returnBean : returnList) {
			if (returnBean.getDeductionType() != null) {
				final String modifyUserId = otdoalloRepository.getModifyUserId(returnBean.getDeductionType());
				returnBean.setModifyUserId(modifyUserId);
				final CaseloadDeductionProfiles caseloadDeducBean = otdoalloRepository.offDedPostQuery(returnBean);
				returnBean.setProcessPriorityNumber(caseloadDeducBean.getExternalPriorityNo());
				returnBean.setProcessPriorityNumb(caseloadDeducBean.getInternalPriorityNo());
//				if (caseloadDeducBean.getMaxMonthlyAmount() != null) {
//					returnBean.setMaxMonthlyAmount(caseloadDeducBean.getMaxMonthlyAmount());
//				}
//				if (caseloadDeducBean.getMaxTotalAmount() != null) {
//					returnBean.setMaxTotalAmount(caseloadDeducBean.getMaxTotalAmount());
//				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DED
	 *
	 * 
	 */
	@Transactional(rollbackFor=Exception.class)
	public String offDedCommit(final OffenderDeductionsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			String informationNum = null;
			int seq = 0;
			for(final OffenderDeductions offDeducBean: commitBean.getInsertList() ){
				final Long offenderDeductionId = otdoalloRepository.offDedPreInsert();
				offDeducBean.setOffenderDeductionId(offenderDeductionId);
				if (informationNum == null) {
					informationNum = offDedFindInformationNumber(offDeducBean);
				} else {
					seq++;
					informationNum = informationNum + "-" + seq;
				}
				
				offDeducBean.setInformationNumber(informationNum);
			}
			commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = otdoalloRepository.offDedInsertOffenderDeductions(commitBean.getInsertList());
			if( liReturn == 1) {
				for(final OffenderDeductions offDeducBean: commitBean.getInsertList() ){
					final List<OffenderDeductionReceipts> insertList = otdoalloRepository.offDedFindPostInsert(offDeducBean);
					if( insertList.size() > 0 ) {
						for( final OffenderDeductionReceipts insertDr: insertList) {
							insertDr.setOffenderDeductionId(offDeducBean.getOffenderDeductionId());
							insertDr.setCreateUserId(commitBean.getCreateUserId());
						}
						liReturn = otdoalloRepository.offDrInsertOffenderDeductionReceipts(insertList);	
					} else {
						throw new RuntimeException("2661");
					}
				}
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = otdoalloRepository.offDedUpdateOffenderDeductions(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = otdoalloRepository.offDedDeleteOffenderDeductions(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	private String offDedFindInformationNumber(final OffenderDeductions offDeducBean) {
		String informNumber;
		Integer inCount;
		informNumber = otdoalloRepository.offDedFindInformationNumber();
		offDeducBean.setInformationNumber(informNumber);
		inCount = otdoalloRepository.offDedFindInCount(offDeducBean);
		if(inCount != null && inCount > 0){
			informNumber = informNumber + "-" + inCount.toString();
		}
		return informNumber;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderDeductionReceipts> offDrExecuteQuery(final OffenderDeductionReceipts searchRecord) {
		return otdoalloRepository.offDrExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DR
	 *
	 * 
	 */
	@Transactional
	public Integer offDrCommit(final OffenderDeductionReceiptsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = otdoalloRepository.offDrInsertOffenderDeductionReceipts(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = otdoalloRepository.offDrUpdateOffenderDeductionReceipts(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = otdoalloRepository.offDrDeleteOffenderDeductionReceipts(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdoalloRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkOffDedDeductionStatusRecordGroup() {
		return otdoalloRepository.cgfkOffDedDeductionStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup() {
		return otdoalloRepository.cgfkOffDrReceiptTxnTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseLoadId) {
		return otdoalloRepository.cgfkOffDedDeductionTypeRecordGroup(caseLoadId);

	}

	@Override
	public OffenderDeductionReceipts offDrValidateRecieptTxnType(final OffenderDeductionReceipts searchBean) {
		return otdoalloRepository.offDrValidateRecieptTxnType(searchBean);
	}

	@Override
	public OffenderDeductions offDedValidateDeductionType(final OffenderDeductions searchBean) {
		final OffenderDeductions returnBean = new OffenderDeductions();
		if (searchBean.getDeductionType() != null) {
			final String modifyUserId = otdoalloRepository.getModifyUserId(searchBean.getDeductionType());
			returnBean.setModifyUserId(modifyUserId);
			final CaseloadDeductionProfiles caseloadDeducBean = otdoalloRepository.offDedPostQuery(searchBean);
			returnBean.setProcessPriorityNumber(caseloadDeducBean.getExternalPriorityNo());
			returnBean.setProcessPriorityNumb(caseloadDeducBean.getInternalPriorityNo());
			if (caseloadDeducBean.getMaxMonthlyAmount() != null) {
				returnBean.setMaxMonthlyAmount(caseloadDeducBean.getMaxMonthlyAmount());
			}
			if (caseloadDeducBean.getMaxTotalAmount() != null) {
				returnBean.setMaxTotalAmount(caseloadDeducBean.getMaxTotalAmount());
			}
		}
		return returnBean;
	}

	@Override
	public List<String> offDrKeyDelrec(final String caseloadId, final Long offenderId, final String deductionType) {
		return otdoalloRepository.offDrKeyDelrec(caseloadId, offenderId, deductionType);
	}

	@Override
	public BigDecimal cntDedRcpt(final BigDecimal offenderDeductionId) {
		return otdoalloRepository.cntDedRcpt(offenderDeductionId);
	}
	
	@Override
	public String insertOnNotAvaliable(final OffenderDeductions offDeducBean) {
		int liReturn = 0;
		final List<OffenderDeductionReceipts> insertList = otdoalloRepository.offDedFindPostInsert(offDeducBean);
		if( insertList.size() > 0 ) {
			for( final OffenderDeductionReceipts insertDr: insertList) {
				insertDr.setOffenderDeductionId(offDeducBean.getOffenderDeductionId());
			}
			liReturn = otdoalloRepository.offDrInsertOffenderDeductionReceipts(insertList);	
		}
		return String.valueOf(liReturn);
	}

}