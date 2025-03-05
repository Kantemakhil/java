package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inmate.trust.deductions.OcdotfeeRepository;
import net.syscon.s4.inmate.trust.deductions.OcdotfeeService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;

@Service
public class OcdotfeeServiceImpl extends BaseBusiness implements OcdotfeeService {

	@Autowired
	private OcdotfeeRepository ocdotfeeRepository;
	
	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private TrustService trustService;
	
	@Autowired
	private OffenderBeneficiariesT2Service offenderBeneficiariesT2Service;

	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;
	
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;
	
	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;
	
	
	private static Logger logger = LogManager.getLogger(OcdotfeeServiceImpl.class.getName());
	

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfkchkOffDedOffDedRef(ReferenceCodes paramBean)  {
		ReferenceCodes referenceCodes = ocdotfeeRepository.cgfkchkOffDedOffDedRef(paramBean);
		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes CgfklkpOffDedOffDedRef(ReferenceCodes paramBean)  {
		ReferenceCodes referenceCodes = ocdotfeeRepository.cgfklkpOffDedOffDedRef(paramBean);
		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public DeductionTypes CgfkchkOffDedOffDedDed(DeductionTypes paramBean)  {
		DeductionTypes deductionTypes = ocdotfeeRepository.cgfkchkOffDedOffDedDed(paramBean);
		return deductionTypes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public TransactionTypes CgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean)  {
		TransactionTypes transactionTypes = ocdotfeeRepository.cgfkchkOffDrOffDrTxnTyp(paramBean);
		return transactionTypes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance()  {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 
	 */
	public List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions searchRecord)  {
		
		List<OffenderDeductions> returnList= ocdotfeeRepository.offDedExecuteQuery(searchRecord);
		for(OffenderDeductions obj :returnList){
			if(obj.getDeductionType() !=null){
				String dedtypeDesc =ocdotfeeRepository.getDedTypeDesc(obj.getDeductionType(),searchRecord.getCaseloadId());
				obj.setDeductionDesc(dedtypeDesc);
				
			}
			if(obj.getDeductionStatus() !=null){
				String dedStatusDesc =ocdotfeeRepository.getDedStatusDesc(obj.getDeductionStatus());
				obj.setDeductionStatusDesc(dedStatusDesc);
			}
			
		}
		return returnList  ;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DED
	 *
	 
	 */
	@Transactional
	public String offDedCommit(OffenderDeductionsCommitBean commitBean)  {
		int liReturn = 0;
		// insertRecords
		ArrayList<OffenderDeductions> oldRecList = new ArrayList<OffenderDeductions>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderDeductions obj :commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());			}
			List <OffenderDeductions> insertList =  preInsert(commitBean.getInsertList());
			liReturn = ocdotfeeRepository.offDedInsertOffenderDeductions(insertList);
			for (OffenderDeductions obj : insertList) {
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
			insertList.forEach(data -> {
				data.setCreateUserId(commitBean.getCreateUserId());
				postInsert(data);
			});
		}
		// updateRecords
		
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderDeductions obj :commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			OffenderDeductions oldObj = ocdotfeeRepository.offenderOldData(obj.getOffenderDeductionId());
			oldRecList.add(oldObj);
			}
			liReturn = ocdotfeeRepository.offDedUpdateOffenderDeductions(commitBean.getUpdateList());
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
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for(final OffenderDeductions offDeducBean: commitBean.getDeleteList()){
				 
				OffenderDeductions oldObj = ocdotfeeRepository.offenderOldData(offDeducBean.getOffenderDeductionId());
				oldRecList.add(oldObj);
				offDeducBean.setModifyUserId(commitBean.getCreateUserId());
			}
			ocdotfeeRepository.keyDelrec(commitBean.getDeleteList());
			liReturn = ocdotfeeRepository.offDedDeleteOffenderDeductions(commitBean.getDeleteList());
			
        for(final OffenderDeductions obj: commitBean.getDeleteList()){
				
				for(final OffenderDeductions oldRec: oldRecList){
					//trigger call
					//offenderDeductionsTjnService.offenderDeductionsTjn(obj, oldRec, "DELETE");
					OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
					offDedHty.setJnOperation("DEL");
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

					offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty, "DELETE");
				}
			}
		}
		return String.valueOf(liReturn);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 
	 */
	public List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts searchRecord)
			 {
		return ocdotfeeRepository.offDrExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DR
	 *
	 
	 */
	@Transactional
	public String offDrCommit(OffenderDeductionReceiptsCommitBean commitBean)  {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderDeductionReceipts obj :commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdotfeeRepository.offDrInsertOffenderDeductionReceipts(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderDeductionReceipts obj :commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdotfeeRepository.offDrUpdateOffenderDeductionReceipts(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for(OffenderDeductionReceipts obj :commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdotfeeRepository.offDrDeleteOffenderDeductionReceipts(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord)  {
		return ocdotfeeRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfilesCommitBean CommitBean)  {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 
	 */
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup()  {
		List<ReferenceCodes> returnlist= ocdotfeeRepository.cgfkOffDedDspDescriptionRecordGroup();
		returnlist.forEach(result ->{
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());
		});
			return returnlist;

	}

	/**
	 * This method is used to execute a record group
	 *
	 
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(final String caseloadId)  {
		List<DeductionTypes>  returnList= ocdotfeeRepository.cgfkOffDedDeductionTypeRecordGroup(caseloadId);
		returnList.forEach(result->{
			result.setCode(result.getDeductionType());
			result.setDescription(result.getDeductionDesc());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 
	 */
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup()  {
		List<TransactionTypes> resultList = ocdotfeeRepository.cgfkOffDrReceiptTxnTypeRecordGroup();
		resultList.forEach(data -> {
			if (!"Y".equals(data.getActiveFlag())) {
				data.setCanDisplay(false);
			}});
		return resultList;

	}

	@Override
	public void checkUniqueConstraint(final OffenderDeductions paramBean) {
		String recordExistsC = ocdotfeeRepository.checkUniqueConstraint(paramBean);
		if (recordExistsC != null) {
			logger.error("Row exists already with same CASELOAD_ID, OFFENDER_ID,DEDUCTION_TYPE,DEDUCTION_PRIORITY");
			throw new RuntimeException("ocdotfee.rwwithsamecase");
		}
	}
	
	private void createDeductionReceipts(final OffenderDeductions paramBean) {
		final String lvDedType = paramBean.getDeductionType();
		final String glbCsldId = paramBean.getCaseloadId();
		String lvReceipt = null;
		Long lvROffDedId = null;
		Long lvDOffDedId = null;
		String lvCsldType = null;
		BigDecimal lvPercentage = null;
		BigDecimal lvFlatRate = null;
		try {
			try {
				//lvCsldType = ocdotfeeRepository.getCaseloadType(glbCsldId);
				lvCsldType = trustService.getCaseloadType(glbCsldId);
				lvDOffDedId = paramBean.getOffenderDeductionId();
				lvROffDedId = ocdotfeeRepository.recordExistsC(lvDOffDedId);
				if (lvROffDedId == null) {
					List<OffenderDeductionReceipts> detailsC =  ocdotfeeRepository.detailsC(lvDedType,glbCsldId,lvCsldType);
					if (detailsC == null || detailsC.size() == 0) {
						logger.error("Transaction Fee Profile has not been setup for this transaction fee.");
						throw new RuntimeException("ocdotfee.transfeeproflehsntset");
					} else {
						for(OffenderDeductionReceipts data : detailsC) {
							data.setOffenderDeductionId(lvDOffDedId);
							data.setCreateUserId(paramBean.getCreateUserId());
						}
						ocdotfeeRepository.offDrInsertOffenderDeductionReceipts(detailsC);
					}
				}
			}catch (Exception e) {
				logger.error("Other Error in TRUST.GET_CASELOAD_TYPE", e);
				if (e.getMessage() != null && e.getMessage().startsWith("ocdotfee")) {
					throw new RuntimeException(e.getMessage());
				}
				throw new RuntimeException("ocdotfee.otrerrtstgetcsldtype");
			}
			lvDOffDedId = paramBean.getOffenderDeductionId();
			
		} catch (Exception e) {
			logger.error("Error in create_deduction_receipts", e);
			if (e.getMessage() != null && e.getMessage().startsWith("ocdotfee")) {
				throw new RuntimeException(e.getMessage());
			}
			throw new RuntimeException("ocdotfee.errindedtype");
		}
		
	}
	
	private void postInsert(final OffenderDeductions paramBean) {
		String dedType = paramBean.getDeductionType();     
		String glbCsldId = paramBean.getCaseloadId();
		Long offId = paramBean.getOffenderId();   
		Long dOffDedId = paramBean.getOffenderDeductionId();
		Long offBenId = null;
		BigDecimal corporate = null;
		BigDecimal unknownbenId = null;
		
		createDeductionReceipts(paramBean);
		offBenId = ocdotfeeRepository.getBeneficiaryId();
		corporate = ocdotfeeRepository.getPayeeCorporateId(glbCsldId,dedType);
		if (corporate == null) {
			unknownbenId = ocdotfeeRepository.getUnknownBenId();
		}
		OffenderBeneficiaries newRec = new OffenderBeneficiaries();
		newRec.setOffenderDeductionId(paramBean.getOffenderDeductionId());
		newRec.setAmount(paramBean.getAmount());
		offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "INSERT");
		ocdotfeeRepository.insertIntoOffenderBeneficiaries(offBenId, dOffDedId, offId, corporate, unknownbenId,paramBean.getCreateUserId());
		
	}
	
	private List<OffenderDeductions> preInsert(final List<OffenderDeductions> params) {
		try {
		Date sysdate = eliteDateService.getDBTime();
		String sysdateFormat = new SimpleDateFormat("MMddyyyy").format(sysdate);
		Integer llCnt = null; 
		
		for(final OffenderDeductions param :  params){
			Long dedId = null;
			Long offId = param.getOffenderId();
			String dedTp = param.getDeductionType();
			String infoNo = null;
			
			checkUniqueConstraint(param);
			dedId = ocdotfeeRepository.getDeductionId();
			
			try {
			if (llCnt == null) {
			llCnt = ocdotfeeRepository.getInfoSeq(offId, dedTp, sysdateFormat);
			infoNo = sysdateFormat;
			if (llCnt > 0) {
				infoNo = infoNo + "-" + llCnt;
			}
			} else {
				llCnt++;
				infoNo = sysdateFormat + "-" + llCnt;
			}
			} catch (Exception e) {
				logger.error("Error : Information Number", e);
				if (e.getMessage() != null && e.getMessage().startsWith("ocdotfee")) {
					throw new RuntimeException(e.getMessage());
				}
				throw new RuntimeException("ocdotfee.errinfonum");
			}
			
			param.setOffenderDeductionId(dedId);
			param.setInformationNumber(infoNo);
			param.setPriority(1);
			
			
			
			
		}
		} catch (Exception e) {
			logger.error("Internal Error : Could not generate Deduction Id", e);
			if (e.getMessage() != null && e.getMessage().startsWith("ocdotfee")) {
				throw new RuntimeException(e.getMessage());
			}
			throw new RuntimeException("ocdotfee.inerrcldntgrededid");
		}
		 
		return params;
	}

	@Override
	public List<OffenderDeductionReceipts> getPercentageAndFlatRate(final String deductionType, final String caseloadId,
			final String receiptTxnType) {
		return ocdotfeeRepository.getPercentageAndFlatRate(deductionType, caseloadId, receiptTxnType);
	}

}