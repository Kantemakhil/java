package net.syscon.s4.cf.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OtdocfeeRepository;
import net.syscon.s4.cf.deductions.OtdocfeeService;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmountsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTxnFeeDetailsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TransactionFeeDetails;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inmate.trust.deductions.OcdotfeeRepository;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;

/**
 * Class OtdocfeeServiceImpl
 */
@Service
public class OtdocfeeServiceImpl extends BaseBusiness implements OtdocfeeService {

	@Autowired
	private OtdocfeeRepository otdocfeeRepository;
	
	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;
	
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;
	
	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;
	
	@Autowired
	private OcdotfeeRepository ocdotfeeRepository;
	
	@Autowired
	private OffenderBeneficiariesT2Service offenderBeneficiariesT2Service;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Long offBkgOnCheckDeleteMaster(final OffenderDeductions paramBean) {
		return otdocfeeRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OffenderPaymentPlan cgrichkOffenderDeductions(final OffenderPaymentPlan paramBean) {
		OffenderPaymentPlan obj = new OffenderPaymentPlan();
		final Long count = otdocfeeRepository.otdocfeeCgrichkOffenderDeductionsInformationNumber(paramBean);
		if (count > 0) {
			obj.setListSeq(BigDecimal.valueOf(count));
		} else {
			obj.setListSeq(BigDecimal.ZERO);
		}
		final Long countOne = otdocfeeRepository.otdocfeeCgrichkOffenderDeductionsOffenderDeductionId(paramBean);
		if (count > 0) {
			obj.setSealFlag(countOne.toString());
		} else {
			obj.setSealFlag("null");
		}
		return obj;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Object offDedPreInsert(final SysDual paramBean) {
		return otdocfeeRepository.offDedPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkchkOffDedOffDedRef(final ReferenceCodes paramBean) {
		return otdocfeeRepository.cgfkchkOffDedOffDedRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffDedOffDedRef(final ReferenceCodes paramBean) {
		return otdocfeeRepository.cgfklkpOffDedOffDedRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<DeductionTypes> cgfkchkOffDedOffDedDed(final DeductionTypes paramBean) {
		return otdocfeeRepository.cgfkchkOffDedOffDedDed(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<DeductionTypes> cgfkchkOffTfdOffTfdDed(final DeductionTypes paramBean) {
		return otdocfeeRepository.cgfkchkOffTfdOffTfdDed(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Long cgrichkOffenderDeductions(final OffenderTransactions paramBean) {
		return otdocfeeRepository.cgrichkOffenderDeductions(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		return otdocfeeRepository.cgwhenNewFormInstance(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions searchRecord) {
		return otdocfeeRepository.offDedExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DED
	 *
	 */
	@Transactional
	public Integer offDedCommit(final OffenderDeductionsCommitBean commitBean) {
		int liReturn = 0;
		List<OffenderDeductions> recordSavingObject = new ArrayList<>();
		ArrayList<OffenderDeductions> oldRecList = new ArrayList<OffenderDeductions>();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				
//				final int valueAlertSeq = alertPreInsertc(offenderBookId);
				final OffenderDeductions offenderAlertObj = commitBean.getInsertList().get(i);
				offenderAlertObj.setOffenderDeductionId(otdocfeeRepository.getOffenderId());
				offenderAlertObj.setCollectAgencyFlag("N");
				offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
				offenderAlertObj.setModifyUserId(commitBean.getCreateUserId());
				recordSavingObject.add(offenderAlertObj);
				liReturn = otdocfeeRepository.offDedInsertOffenderDeductions(recordSavingObject);
				//Trigger call
				if (liReturn == 1) {
				for (OffenderDeductions obj : recordSavingObject) {
					offenderDeductionsT2Service.offenderDeductionsT2Trigger(obj, new OffenderDeductions(), "INSERT");
					//offenderDeductionsTjnService.offenderDeductionsTjn(obj, new OffenderDeductions(), "INSERT");

					OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
					offDedHty.setJnOperation("INS");
					offDedHty.setJnOracleUser(obj.getCreateUserId());
					offDedHty.setOffenderDeductionId(obj.getOffenderDeductionId());
					offDedHty.setCaseloadId(obj.getCaseloadId());
					offDedHty.setCreditLimit(obj.getCreditLimit());
					offDedHty.setDeductionType(obj.getDeductionType());
					offDedHty.setDeductionStatus(obj.getDeductionStatus());
					offDedHty.setDeductionPriority(
							(obj.getDeductionPriority() != null ? obj.getDeductionPriority().doubleValue() : null));
					offDedHty.setInformationNumber(obj.getInformationNumber());
					offDedHty.setDeductionPercentage(
							(obj.getDeductionPercentage() != null ? obj.getDeductionPercentage().doubleValue() : null));
					offDedHty.setProcessPriorityNumber(
							(obj.getProcessPriorityNumb() != null ? obj.getProcessPriorityNumb().doubleValue() : null));
					offDedHty.setEffectiveDate(obj.getEffectiveDate());
					offDedHty.setCommentText(obj.getCommentText());
					offDedHty.setFifoFlag(obj.getFifoFlag());
					offDedHty.setPayeePersonId((obj.getPayeePersonId() != null ? obj.getPayeePersonId().longValue() : null));
					offDedHty.setPayeeCorporateId(
							(obj.getPayeeCorporateId() != null ? obj.getPayeeCorporateId().longValue() : null));
					offDedHty.setMaxMonthlyAmount(obj.getMaxMonthlyAmount());
					offDedHty.setMaxTotalAmount(obj.getMaxTotalAmount());
					offDedHty.setDeductionAmount(obj.getDeductionAmount());
					offDedHty.setAdjustmentReasonCode(obj.getAdjustmentReasonCode());
					offDedHty.setAdjustmentAmount(obj.getAdjustmentAmount());
					offDedHty.setAdjustmentUserId(obj.getAdjustmentUserId());
					offDedHty.setAdjustmentTxnId(
							(obj.getAdjustmentTxnId() != null ? obj.getAdjustmentTxnId().longValue() : null));
					offDedHty.setAdjustmentText(obj.getAdjustmentText());
					offDedHty.setModifyDate(obj.getModifyDate());
					offDedHty.setPayDeductionFlag(obj.getPayDeductionFlag());
					offDedHty.setMaxRecursiveAmount(obj.getMaxRecursiveAmount());
					offDedHty.setGroupId((obj.getGroupId() != null ? obj.getGroupId().longValue() : null));
					offDedHty.setCaseId((obj.getCaseId() != null ? obj.getCaseId().longValue() : null));
					offDedHty.setParentDeductionId(
							(obj.getParentDeductionId() != null ? obj.getParentDeductionId().longValue() : null));
					offDedHty.setJsStatus(obj.getJsStatus());
					offDedHty.setCollectAgencyAmount(obj.getCollectAgencyAmount());
					offDedHty.setCollectAgencyFlag(obj.getCollectAgencyFlag());
					offDedHty.setCollectSentDate(obj.getCollectSentDate());
					offDedHty.setOffenderPaymentProfileId(
							(obj.getOffenderPaymentProfileId() != null ? obj.getOffenderPaymentProfileId().longValue() : null));
					offDedHty.setSealFlag(obj.getSealFlag());
					offDedHty.setCreateUserId(obj.getCreateUserId());
					offDedHty.setModifyUserId(obj.getModifyUserId());

					offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty, "INSERT");
				}
				}
				if(liReturn != 1) {
					return liReturn;
				}
				liReturn = otdocfeePostQueryTwoPayeeCorporateId(offenderAlertObj);
			}
			
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for(OffenderDeductions obj :commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			OffenderDeductions oldObj = ocdotfeeRepository.offenderOldData(obj.getOffenderDeductionId());
			oldRecList.add(oldObj);
			}
			liReturn = otdocfeeRepository.offDedUpdateOffenderDeductions(commitBean.getUpdateList());
			if (liReturn > 0) {
				for (OffenderDeductions obj : commitBean.getUpdateList()) {
					for (OffenderDeductions oldRec : oldRecList) {
						if (oldRec.getOffenderDeductionId().equals(obj.getOffenderDeductionId())) {
							offenderDeductionsT2Service.offenderDeductionsT2Trigger(obj, oldRec, "UPDATE");
							//offenderDeductionsTjnService.offenderDeductionsTjn(obj, oldRec, "UPDATE");
							OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
							offDedHty.setJnOperation("UPD");
							offDedHty.setJnOracleUser(obj.getModifyUserId());
							offDedHty.setOffenderDeductionId(oldRec.getOffenderDeductionId());
							offDedHty.setCaseloadId(oldRec.getCaseloadId());
							offDedHty.setCreditLimit(oldRec.getCreditLimit());
							offDedHty.setDeductionType(oldRec.getDeductionType());
							offDedHty.setDeductionStatus(oldRec.getDeductionStatus());
							offDedHty.setDeductionPriority(
									(oldRec.getDeductionPriority() != null ? oldRec.getDeductionPriority().doubleValue() : null));
							offDedHty.setInformationNumber(oldRec.getInformationNumber());
							offDedHty.setDeductionPercentage(
									(oldRec.getDeductionPercentage() != null ? oldRec.getDeductionPercentage().doubleValue()
											: null));
							offDedHty.setProcessPriorityNumber(
									(oldRec.getProcessPriorityNumb() != null ? oldRec.getProcessPriorityNumb().doubleValue()
											: null));
							offDedHty.setEffectiveDate(oldRec.getEffectiveDate());
							offDedHty.setCommentText(oldRec.getCommentText());
							offDedHty.setFifoFlag(oldRec.getFifoFlag());
							offDedHty.setPayeePersonId(
									(oldRec.getPayeePersonId() != null ? oldRec.getPayeePersonId().longValue() : null));
							offDedHty.setPayeeCorporateId(
									(oldRec.getPayeeCorporateId() != null ? oldRec.getPayeeCorporateId().longValue() : null));
							offDedHty.setMaxMonthlyAmount(oldRec.getMaxMonthlyAmount());
							offDedHty.setMaxTotalAmount(oldRec.getMaxTotalAmount());
							offDedHty.setDeductionAmount(oldRec.getDeductionAmount());
							offDedHty.setAdjustmentReasonCode(oldRec.getAdjustmentReasonCode());
							offDedHty.setAdjustmentAmount(oldRec.getAdjustmentAmount());
							offDedHty.setAdjustmentUserId(oldRec.getAdjustmentUserId());
							offDedHty.setAdjustmentTxnId(
									(oldRec.getAdjustmentTxnId() != null ? oldRec.getAdjustmentTxnId().longValue() : null));
							offDedHty.setAdjustmentText(oldRec.getAdjustmentText());
							offDedHty.setModifyDate(oldRec.getModifyDate());
							offDedHty.setPayDeductionFlag(oldRec.getPayDeductionFlag());
							offDedHty.setMaxRecursiveAmount(oldRec.getMaxRecursiveAmount());
							offDedHty.setGroupId((oldRec.getGroupId() != null ? oldRec.getGroupId().longValue() : null));
							offDedHty.setCaseId((oldRec.getCaseId() != null ? oldRec.getCaseId().longValue() : null));
							offDedHty.setParentDeductionId(
									(oldRec.getParentDeductionId() != null ? oldRec.getParentDeductionId().longValue() : null));
							offDedHty.setJsStatus(oldRec.getJsStatus());
							offDedHty.setCollectAgencyAmount(oldRec.getCollectAgencyAmount());
							offDedHty.setCollectAgencyFlag(oldRec.getCollectAgencyFlag());
							offDedHty.setCollectSentDate(oldRec.getCollectSentDate());
							offDedHty.setOffenderPaymentProfileId((oldRec.getOffenderPaymentProfileId() != null
									? oldRec.getOffenderPaymentProfileId().longValue()
									: null));
							offDedHty.setSealFlag(oldRec.getSealFlag());
							offDedHty.setCreateUserId(oldRec.getCreateUserId());
							offDedHty.setModifyUserId(oldRec.getModifyUserId());

							offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty, "UPDATE");
						}
					}
				}
			}
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
	public List<OffenderTxnFeeDetails> offTfdExecuteQuery(final OffenderTxnFeeDetails searchRecord) {
		return otdocfeeRepository.offTfdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TFD
	 *
	 * 
	 */
	@Transactional
	public Integer offTfdCommit(final OffenderTxnFeeDetailsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderTxnFeeDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otdocfeeRepository.offTfdInsertOffenderTxnFeeDetails(commitBean.getInsertList());
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderTxnFeeDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otdocfeeRepository.offTfdUpdateOffenderTxnFeeDetails(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderTxnFeeDetails obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otdocfeeRepository.offTfdDeleteOffenderTxnFeeDetails(commitBean.getDeleteList());
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
	public List<OffenderTierTxnFeeAmounts> offTtfExecuteQuery(final OffenderTierTxnFeeAmounts searchRecord) {
		return otdocfeeRepository.offTtfExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TTF
	 *
	 * 
	 */
	@Transactional
	public Integer offTtfCommit(final OffenderTierTxnFeeAmountsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderTierTxnFeeAmounts obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otdocfeeRepository.offTtfInsertOffenderTierTxnFeeAmounts(commitBean.getInsertList());
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderTierTxnFeeAmounts obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				}
			liReturn = otdocfeeRepository.offTtfUpdateOffenderTierTxnFeeAmounts(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderTierTxnFeeAmounts obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				}
			liReturn = otdocfeeRepository.offTtfDeleteOffenderTierTxnFeeAmounts(commitBean.getDeleteList());
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
		return otdocfeeRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		return otdocfeeRepository.cgfkOffDedDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<DeductionTypes> cgfkOffTfdReceiptDeductionRecordGroup(final String caseloadType) {
		return otdocfeeRepository.cgfkOffTfdReceiptDeductionRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseloadId, final String caseloadType) {
		return otdocfeeRepository.cgfkOffDedDeductionTypeRecordGroup(caseloadId, caseloadType);

	}
	
	public Integer otdocfeePopulateDetailsData(final OffenderDeductions paramBean) {
		Integer data = null;
		if ("N".equals(paramBean.getCommentText())) {
			Integer collectionFeeDetailsRowInsert =0;
			collectionFeeDetailsRowInsert =otdocfeePopulateDetails(paramBean);
			if (collectionFeeDetailsRowInsert == -1) {
		     data = -1;		
		     return data;
			}
		}
		if ("N".equals(paramBean.getSealFlag())) {
			Integer amountListInsert= 0;
			amountListInsert =otdocfeeGetOffenderDeductionId(paramBean);
			if (amountListInsert  ==-2) {
				data = -2;
				return data;
			}
		}
		data = 1;
		return data;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public Integer otdocfeePopulateDetails(final OffenderDeductions paramBean) {
		Integer returnList =0;
		OffenderTxnFeeDetails obj = new OffenderTxnFeeDetails();
		obj.setOffenderDeductionId(paramBean.getOffenderDeductionId());
		List<OffenderDeductions> returnValue = new ArrayList();
		String offnCaseloadId = otdocfeeRepository.otdocfeeGetCaseloadType(paramBean.getCaseloadId());
		Integer offdedId = otdocfeeRepository.otdocfeePopulateDetails(obj);
		List<TransactionFeeDetails> returnData = otdocfeeRepository
				.otdocfeePopulateDetailsReceiptDeductionType(paramBean.getDeductionType(), paramBean.getCaseloadId());
		if (!returnData.isEmpty()) {
			for (final TransactionFeeDetails object : returnData) {
				OffenderTxnFeeDetails objectOne = new OffenderTxnFeeDetails();
				objectOne.setOffenderDeductionId(paramBean.getOffenderDeductionId());
				objectOne.setReceiptDeductionType(object.getReceiptDeductionType());
				objectOne.setCreateUserId(paramBean.getCreateUserId());
				 returnList = otdocfeeRepository.insertIntoOffenderTxnFeeDetails(objectOne);
			}
		} else {
			OffenderDeductions objectTwo = new OffenderDeductions();
			objectTwo.setErrorMessage("Error :Transaction Fee Profile has not been setup for this transaction fee.");
			returnList =-1;
			returnValue.add(objectTwo);
		}
		return returnList;
	}

	public Integer otdocfeeGetOffenderDeductionId(
			final OffenderDeductions paramBean) {
		Integer returnList =0;
		List<OffenderTxnFeeDetails> txnFeeObj = new ArrayList<OffenderTxnFeeDetails>();
		OffenderTxnFeeDetails returnValue = new OffenderTxnFeeDetails();
		TieredTransactionFeeAmounts obj = new TieredTransactionFeeAmounts();
		List<TieredTransactionFeeAmounts> returnList1 = new ArrayList();
		Long objectOne = otdocfeeRepository.otdocfeeGetOffenderDeductionId(paramBean.getOffenderDeductionId());
		
		List<TieredTransactionFeeAmounts> returnObj = otdocfeeRepository
				.otdocfeePopulateDetailsTieredTransactionFeeAmounts(paramBean);
		if (!returnObj.isEmpty()) {
			for (final TieredTransactionFeeAmounts object : returnObj) {
				OffenderTierTxnFeeAmounts objectTwo = new OffenderTierTxnFeeAmounts();
				objectTwo.setOffenderDeductionId(paramBean.getOffenderDeductionId());
				objectTwo.setToAmount(object.getToAmount());
				objectTwo.setFromAmount(object.getFromAmount());
				objectTwo.setPercentage(object.getPercentage());
				objectTwo.setFeeAmount(object.getFeeAmount());
				objectTwo.setCreateUserId(paramBean.getCreateUserId());
				 returnList = otdocfeeRepository.insertIntoOffenderTierTxnFeeAmounts(objectTwo);
			}
		} else {
			TieredTransactionFeeAmounts objectTwo = new TieredTransactionFeeAmounts();
			objectTwo.setErrorMessage("Error: Transaction Fee Profile has not been setup for this transaction fee.");
			returnList1.add(objectTwo);
			returnList = -2;
		}
		return returnList;

	}

	public Integer otdocfeePostQueryTwoPayeeCorporateId(final OffenderDeductions paramBean) {
		final CaseloadDeductionProfiles returnList = new CaseloadDeductionProfiles();
		Long benificaryId = otdocfeeRepository.otdocfeePostQueryTwo();
		returnList.setCaseloadId(paramBean.getCaseloadId());
		returnList.setDeductionType(paramBean.getDeductionType());
		BigDecimal payeeCorpId = BigDecimal.ZERO;
		Integer unknownBenId = 0;
		payeeCorpId = otdocfeeRepository.otdocfeePostQueryTwoPayeeCorporateId(returnList);
		if (payeeCorpId == BigDecimal.ZERO) {
			unknownBenId = otdocfeeRepository.otdocfeePostQueryTwoUnknownBenId();
		}
		OffenderBeneficiaries object = new OffenderBeneficiaries();
		object.setBeneficiaryId(benificaryId);
		object.setOffenderDeductionId(paramBean.getOffenderDeductionId());
		object.setOffenderId(BigDecimal.valueOf(paramBean.getOffenderId()));
		object.setCorporateId(payeeCorpId);
		object.setUnknownBenId(BigDecimal.valueOf(unknownBenId));
		object.setCreateUserId(paramBean.getCreateUserId());
		OffenderBeneficiaries newRec = new OffenderBeneficiaries();
		newRec.setOffenderDeductionId(paramBean.getOffenderDeductionId());
		newRec.setAmount(paramBean.getAmount());
		offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "INSERT");
		Integer obj = otdocfeeRepository.insertIntoOffenderBeneficiaries(object);
		return obj;
	}
	 
	public Integer getOverLapCount(final OffenderTierTxnFeeAmounts paramBean) {
		return otdocfeeRepository.getOverLapCount(paramBean);
	}
}