package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.triggers.OffenderDeductionsJn;
import net.syscon.s4.triggers.OffenderDeductionsTjnRepository;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;
@Service
public class OffenderDeductionsTjnServiceImpl extends BaseBusiness implements OffenderDeductionsTjnService{
	
	@Autowired
	private OffenderDeductionsTjnRepository repository;

	@Override
	public void offenderDeductionsTjn(OffenderDeductions newRec, OffenderDeductions oldRec, String operationType) {
		
		
		if(operationType.equalsIgnoreCase("INSERT")) {
			OffenderDeductionsJn insertObj=new OffenderDeductionsJn();
			insertObj.setJnOperation("INS");
			insertObj.setJnSession(BigDecimal.ZERO);
			insertObj.setOffenderDeductionId(newRec.getOffenderDeductionId());
			insertObj.setCaseloadId(newRec.getCaseloadId());
			insertObj.setOffenderId(newRec.getOffenderId());
			insertObj.setCreditLimit(newRec.getCreditLimit() != null?newRec.getCreditLimit().doubleValue():null);
			insertObj.setDeductionType(newRec.getDeductionType());
			insertObj.setDeductionStatus(newRec.getDeductionStatus());
			insertObj.setDeductionPriority(newRec.getDeductionPriority() != null?newRec.getDeductionPriority().intValue():null);
			insertObj.setInformationNumber(newRec.getInformationNumber());
			insertObj.setDeductionPercentage(newRec.getDeductionPercentage() != null ?newRec.getDeductionPercentage().intValue() : null);
			insertObj.setProcessPriorityNumber(newRec.getProcessPriorityNumber() != null ?newRec.getProcessPriorityNumber().intValue():null);
			insertObj.setEffectiveDate(newRec.getEffectiveDate());
			insertObj.setCommentText(newRec.getCommentText());
			insertObj.setFifoFlag(newRec.getFifoFlag());
			insertObj.setPayeePersonId(newRec.getPayeePersonId() != null ?newRec.getPayeePersonId().longValue():null);
			insertObj.setPayeeCorporateId(newRec.getPayeeCorporateId() != null ?newRec.getPayeeCorporateId().longValue():null);
			insertObj.setMaxMonthlyAmount(newRec.getMaxMonthlyAmount() != null?newRec.getMaxMonthlyAmount().doubleValue():null);
			insertObj.setMaxTotalAmount(newRec.getMaxTotalAmount() != null?newRec.getMaxTotalAmount().doubleValue():null);
			insertObj.setDeductionAmount(newRec.getDeductionAmount()!= null ?newRec.getDeductionAmount().doubleValue():null);
			insertObj.setAdjustmentReasonCode(newRec.getAdjustmentReasonCode());
			insertObj.setAdjustmentAmount(newRec.getAdjustmentAmount() != null?newRec.getAdjustmentAmount().doubleValue():null);
			insertObj.setAdjustmentUserId(newRec.getAdjustmentUserId());
			insertObj.setAdjustmentTxnId(newRec.getAdjustmentTxnId() != null ?newRec.getAdjustmentTxnId().longValue():null);
			insertObj.setAdjustmentText(newRec.getAdjustmentText());
			insertObj.setModifyDate(newRec.getModifyDate());
			insertObj.setPayDeductionFlag(newRec.getPayDeductionFlag());
			insertObj.setMaxRecursiveAmount(newRec.getMaxRecursiveAmount() != null ?newRec.getMaxRecursiveAmount().doubleValue():null);
			insertObj.setGroupId(newRec.getGroupId() != null ? newRec.getGroupId().longValue() :null);
			insertObj.setCaseId(newRec.getCaseId() != null ?newRec.getCaseId().longValue():null);
			insertObj.setParentDeductionId(newRec.getParentDeductionId() != null ? newRec.getParentDeductionId().longValue():null);
			insertObj.setJsStatus(newRec.getJsStatus());
			insertObj.setCollectAgencyAmount(newRec.getCollectAgencyAmount() != null ? newRec.getCollectAgencyAmount().doubleValue() :null);
			insertObj.setCollectAgencyFlag(newRec.getCollectAgencyFlag());
			insertObj.setCollectSentDate(newRec.getCollectSentDate());
			insertObj.setOffenderPaymentProfileId(newRec.getOffenderPaymentProfileId() != null ?newRec.getOffenderPaymentProfileId().longValue():null);
			insertObj.setSealFlag(newRec.getSealFlag());
			insertObj.setJnOracleUser(newRec.getCreateUserId());
			
			insertObj.setCreateUserId(newRec.getCreateUserId());
			insertObj.setModifyUserId(newRec.getModifyUserId());
			repository.insertOffenderDeductionsJn(insertObj);
		} else if(operationType.equalsIgnoreCase("UPDATE")) {
			OffenderDeductionsJn insertObj=new OffenderDeductionsJn();
			insertObj.setJnOperation("UPD");
			insertObj.setJnSession(BigDecimal.ZERO);
			insertObj.setOffenderDeductionId(oldRec.getOffenderDeductionId());
			insertObj.setCaseloadId(oldRec.getCaseloadId());
			insertObj.setOffenderId(oldRec.getOffenderId());
			insertObj.setCreditLimit(oldRec.getCreditLimit() != null?oldRec.getCreditLimit().doubleValue():null);
			insertObj.setDeductionType(oldRec.getDeductionType());
			insertObj.setDeductionStatus(oldRec.getDeductionStatus());
			insertObj.setDeductionPriority(oldRec.getDeductionPriority() != null?oldRec.getDeductionPriority().intValue():null);
			insertObj.setInformationNumber(oldRec.getInformationNumber());
			insertObj.setDeductionPercentage(oldRec.getDeductionPercentage() != null ?oldRec.getDeductionPercentage().intValue() : null);
			insertObj.setProcessPriorityNumber(oldRec.getProcessPriorityNumber() != null ?oldRec.getProcessPriorityNumber().intValue():null);
			insertObj.setEffectiveDate(oldRec.getEffectiveDate());
			insertObj.setCommentText(oldRec.getCommentText());
			insertObj.setFifoFlag(oldRec.getFifoFlag());
			insertObj.setPayeePersonId(oldRec.getPayeePersonId() != null ?oldRec.getPayeePersonId().longValue():null);
			insertObj.setPayeeCorporateId(oldRec.getPayeeCorporateId() != null ?oldRec.getPayeeCorporateId().longValue():null);
			insertObj.setMaxMonthlyAmount(oldRec.getMaxMonthlyAmount() != null?oldRec.getMaxMonthlyAmount().doubleValue():null);
			insertObj.setMaxTotalAmount(oldRec.getMaxTotalAmount() != null?oldRec.getMaxTotalAmount().doubleValue():null);
			insertObj.setDeductionAmount(oldRec.getDeductionAmount()!= null ?oldRec.getDeductionAmount().doubleValue():null);
			insertObj.setAdjustmentReasonCode(oldRec.getAdjustmentReasonCode());
			insertObj.setAdjustmentAmount(oldRec.getAdjustmentAmount() != null?oldRec.getAdjustmentAmount().doubleValue():null);
			insertObj.setAdjustmentUserId(oldRec.getAdjustmentUserId());
			insertObj.setAdjustmentTxnId(oldRec.getAdjustmentTxnId() != null ?oldRec.getAdjustmentTxnId().longValue():null);
			insertObj.setAdjustmentText(oldRec.getAdjustmentText());
			insertObj.setModifyDate(oldRec.getModifyDate());
			insertObj.setPayDeductionFlag(oldRec.getPayDeductionFlag());
			insertObj.setMaxRecursiveAmount(oldRec.getMaxRecursiveAmount() != null ?oldRec.getMaxRecursiveAmount().doubleValue():null);
			insertObj.setGroupId(oldRec.getGroupId() != null ? oldRec.getGroupId().longValue() :null);
			insertObj.setCaseId(oldRec.getCaseId() != null ?oldRec.getCaseId().longValue():null);
			insertObj.setParentDeductionId(oldRec.getParentDeductionId() != null ? oldRec.getParentDeductionId().longValue():null);
			insertObj.setJsStatus(oldRec.getJsStatus());
			insertObj.setCollectAgencyAmount(oldRec.getCollectAgencyAmount() != null ? oldRec.getCollectAgencyAmount().doubleValue() :null);
			insertObj.setCollectAgencyFlag(oldRec.getCollectAgencyFlag());
			insertObj.setCollectSentDate(oldRec.getCollectSentDate());
			insertObj.setOffenderPaymentProfileId(oldRec.getOffenderPaymentProfileId() != null ?oldRec.getOffenderPaymentProfileId().longValue():null);
			insertObj.setSealFlag(oldRec.getSealFlag());
			insertObj.setCreateUserId(oldRec.getCreateUserId());
			insertObj.setModifyUserId(oldRec.getModifyUserId());
			//insertObj.setJnOracleUser(insertObj.getCreateUserId());
			insertObj.setJnOracleUser(oldRec.getCreateUserId());
			repository.insertOffenderDeductionsJn(insertObj);
		} else if(operationType.equalsIgnoreCase("DELETE")) {
			OffenderDeductionsJn insertObj=new OffenderDeductionsJn();
			insertObj.setJnOperation("DEL");
			insertObj.setJnSession(BigDecimal.ZERO);
			insertObj.setOffenderDeductionId(oldRec.getOffenderDeductionId());
			insertObj.setCaseloadId(oldRec.getCaseloadId());
			insertObj.setOffenderId(oldRec.getOffenderId());
			insertObj.setCreditLimit(oldRec.getCreditLimit() != null?oldRec.getCreditLimit().doubleValue():null);
			insertObj.setDeductionType(oldRec.getDeductionType());
			insertObj.setDeductionStatus(oldRec.getDeductionStatus());
			insertObj.setDeductionPriority(oldRec.getDeductionPriority() != null?oldRec.getDeductionPriority().intValue():null);
			insertObj.setInformationNumber(oldRec.getInformationNumber());
			insertObj.setDeductionPercentage(oldRec.getDeductionPercentage() != null ?oldRec.getDeductionPercentage().intValue() : null);
			insertObj.setProcessPriorityNumber(oldRec.getProcessPriorityNumber() != null ?oldRec.getProcessPriorityNumber().intValue():null);
			insertObj.setEffectiveDate(oldRec.getEffectiveDate());
			insertObj.setCommentText(oldRec.getCommentText());
			insertObj.setFifoFlag(oldRec.getFifoFlag());
			insertObj.setPayeePersonId(oldRec.getPayeePersonId() != null ?oldRec.getPayeePersonId().longValue():null);
			insertObj.setPayeeCorporateId(oldRec.getPayeeCorporateId() != null ?oldRec.getPayeeCorporateId().longValue():null);
			insertObj.setMaxMonthlyAmount(oldRec.getMaxMonthlyAmount() != null?oldRec.getMaxMonthlyAmount().doubleValue():null);
			insertObj.setMaxTotalAmount(oldRec.getMaxTotalAmount() != null?oldRec.getMaxTotalAmount().doubleValue():null);
			insertObj.setDeductionAmount(oldRec.getDeductionAmount()!= null ?oldRec.getDeductionAmount().doubleValue():null);
			insertObj.setAdjustmentReasonCode(oldRec.getAdjustmentReasonCode());
			insertObj.setAdjustmentAmount(oldRec.getAdjustmentAmount() != null?oldRec.getAdjustmentAmount().doubleValue():null);
			insertObj.setAdjustmentUserId(oldRec.getAdjustmentUserId());
			insertObj.setAdjustmentTxnId(oldRec.getAdjustmentTxnId() != null ?oldRec.getAdjustmentTxnId().longValue():null);
			insertObj.setAdjustmentText(oldRec.getAdjustmentText());
			insertObj.setModifyDate(oldRec.getModifyDate());
			insertObj.setPayDeductionFlag(oldRec.getPayDeductionFlag());
			insertObj.setMaxRecursiveAmount(oldRec.getMaxRecursiveAmount() != null ?oldRec.getMaxRecursiveAmount().doubleValue():null);
			insertObj.setGroupId(oldRec.getGroupId() != null ? oldRec.getGroupId().longValue() :null);
			insertObj.setCaseId(oldRec.getCaseId() != null ?oldRec.getCaseId().longValue():null);
			insertObj.setParentDeductionId(oldRec.getParentDeductionId() != null ? oldRec.getParentDeductionId().longValue():null);
			insertObj.setJsStatus(oldRec.getJsStatus());
			insertObj.setCollectAgencyAmount(oldRec.getCollectAgencyAmount() != null ? oldRec.getCollectAgencyAmount().doubleValue() :null);
			insertObj.setCollectAgencyFlag(oldRec.getCollectAgencyFlag());
			insertObj.setCollectSentDate(oldRec.getCollectSentDate());
			insertObj.setOffenderPaymentProfileId(oldRec.getOffenderPaymentProfileId() != null ?oldRec.getOffenderPaymentProfileId().longValue():null);
			insertObj.setSealFlag(oldRec.getSealFlag());
			insertObj.setJnOracleUser(newRec.getCreateUserId());
			
			insertObj.setCreateUserId(oldRec.getCreateUserId());
			insertObj.setModifyUserId(oldRec.getModifyUserId());
			repository.insertOffenderDeductionsJn(insertObj);
		}
		 
		
        
         
		
	}
}
