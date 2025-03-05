package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderObligationHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Repository;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
@Service
public class OffenderDeductionsT2ServiceImpl extends BaseBusiness implements OffenderDeductionsT2Service{
	
	@Autowired
	private OffenderDeductionsT2Repository repository;
	
	@Override
	public void offenderDeductionsT2Trigger(OffenderDeductions newRec, OffenderDeductions oldRec, final String operationType) {
		Long lvDeductionSeq =null;
		if(operationType.equalsIgnoreCase("INSERT")) {
			if(newRec.getMaxTotalAmount() != null) {
				lvDeductionSeq=repository.nextDedSeqC(newRec.getOffenderDeductionId());
				OffenderObligationHty insertObj=new OffenderObligationHty();
				insertObj.setOffenderDeductionId(newRec.getOffenderDeductionId() != null ? BigDecimal.valueOf(newRec.getOffenderDeductionId()):null);
				insertObj.setDeductionSeq(lvDeductionSeq != null ? lvDeductionSeq.intValue() :null);
				insertObj.setInformationNumber(newRec.getInformationNumber());
				insertObj.setDeductionType(newRec.getDeductionType());
				insertObj.setAdjustedAmount(BigDecimal.ZERO);
				insertObj.setMaxTotalAmount(newRec.getMaxTotalAmount());
				insertObj.setModifiedUserId(newRec.getCreateUserId());
				insertObj.setCreateUserId(newRec.getCreateUserId());
				repository.insertOffenderObligationHty(insertObj);
			}
		} else if(operationType.equalsIgnoreCase("UPDATE")) {
			if((newRec.getMaxTotalAmount() != null ? newRec.getMaxTotalAmount() :BigDecimal.ZERO) != (oldRec.getMaxTotalAmount() != null ? oldRec.getMaxTotalAmount() :BigDecimal.ZERO)) {
				lvDeductionSeq=repository.nextDedSeqC(newRec.getOffenderDeductionId());
				OffenderObligationHty insertObj=new OffenderObligationHty();
				insertObj.setOffenderDeductionId(newRec.getOffenderDeductionId() != null ? BigDecimal.valueOf(newRec.getOffenderDeductionId()):null);
				insertObj.setDeductionSeq(lvDeductionSeq != null ? lvDeductionSeq.intValue() :null);
				insertObj.setInformationNumber(newRec.getInformationNumber());
				insertObj.setDeductionType(newRec.getDeductionType());
				insertObj.setAdjustedAmount((newRec.getMaxTotalAmount() != null ? newRec.getMaxTotalAmount() :BigDecimal.ZERO).subtract((oldRec.getMaxTotalAmount() != null ? oldRec.getMaxTotalAmount() :BigDecimal.ZERO)));
				insertObj.setMaxTotalAmount(newRec.getMaxTotalAmount());
				insertObj.setModifiedUserId(newRec.getCreateUserId());
				insertObj.setCreateUserId(newRec.getCreateUserId());
				repository.insertOffenderObligationHty(insertObj);
			}
		}
	}

}
