package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Repository;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Service;
@Service
public class OffenderBeneficiariesT1ServiceImpl extends BaseBusiness implements OffenderBeneficiariesT1Service {
	
	@Autowired
	private OffenderBeneficiariesT1Repository repository;
	
	@Autowired
	private OffenderMonBeneficiariesTjnServiceImpl offenderMonBeneficiariesTjnServiceImpl;
	
	@Override
	public OffenderBeneficiaries offenderBeneficiariesT1Trigger(OffenderBeneficiaries newRec,
			OffenderBeneficiaries oldRec) {
		
		BigDecimal lvMaxTotalAmount=null;
		BigDecimal lvMaxMonthlyAmount=null;
		BigDecimal lvMaxRecursiveAmount=null;
		String existFlag=null;
		
		OffenderDeductions offded = repository.dedAmountType(newRec.getOffenderDeductionId());
		if (offded != null) {
			lvMaxTotalAmount = offded.getMaxTotalAmount();
			lvMaxMonthlyAmount = offded.getMaxMonthlyAmount();
			lvMaxRecursiveAmount = offded.getMaxRecursiveAmount();
		}
		if(newRec != null && oldRec!= null ) {
		if((newRec.getReceivedAmount() != null ? newRec.getReceivedAmount() : 0) != (oldRec.getReceivedAmount() != null ? oldRec.getReceivedAmount() : 0)) {
			if(lvMaxMonthlyAmount != null && lvMaxMonthlyAmount.intValue() > 0) {
				existFlag=repository.thisMonthBeneficiary(newRec.getBeneficiaryId());
				if(existFlag != null && existFlag.equals("Y")) {
					OffenderMonDeductions getOldOffenderMonBenef = new OffenderMonDeductions();
					getOldOffenderMonBenef = repository.getOffenderMonBeneficiaries(newRec.getBeneficiaryId());
					repository.updateOffenderMonBeneficiaries(newRec.getBeneficiaryId(),getReceivedAmount(newRec.getReceivedAmount(),oldRec.getReceivedAmount()) );
					OffenderMonDeductions oldObj = new OffenderMonDeductions();
					oldObj.setBeneficiaryId(getOldOffenderMonBenef.getBeneficiaryId());
					oldObj.setMonthlyDeductionDate(getOldOffenderMonBenef.getMonthlyDeductionDate());
					oldObj.setOffenderDeducttionId(getOldOffenderMonBenef.getOffenderDeducttionId());
					oldObj.setReceivedAmount(getOldOffenderMonBenef.getReceivedAmount());
					oldObj.setPersonId(getOldOffenderMonBenef.getPersonId());
					oldObj.setCorporateId(getOldOffenderMonBenef.getCorporateId());
					oldObj.setUnknownBenId(getOldOffenderMonBenef.getUnknownBenId());
					oldObj.setSealFlag(getOldOffenderMonBenef.getSealFlag());
					oldObj.setCreateUserId(getOldOffenderMonBenef.getCreateUserId());
					//Added trigger from offender_mon_beneficiaries
					offenderMonBeneficiariesTjnServiceImpl.offenderMonBeneficiariesTjn(new OffenderMonDeductions(), oldObj, "UPDATE");
				} else {
					OffenderBeneficiaries obj=newRec;
					obj.setReceivedAmount(getReceivedAmount(newRec.getReceivedAmount(),oldRec.getReceivedAmount()) );
					repository.insertOffenderMonBeneficiaries(obj);
					OffenderMonDeductions newObj = new OffenderMonDeductions();
					newObj.setBeneficiaryId(newRec.getBeneficiaryId());
					newObj.setMonthlyDeductionDate(newRec.getMonthlyDeductionDate());
					newObj.setOffenderDeducttionId(newRec.getOffenderDeductionId());
					newObj.setReceivedAmount(newRec.getReceivedAmount());
					newObj.setPersonId(newRec.getPersonId()); 
					newObj.setCorporateId(newRec.getCorporateId());
					newObj.setUnknownBenId(newRec.getUnknownBenId()!=null?newRec.getUnknownBenId().toString():null);
					newObj.setSealFlag(newRec.getSealFlag());
					newObj.setCreateUserId(newRec.getCreateUserId());
					//Added trigger from offender_mon_beneficiaries
					offenderMonBeneficiariesTjnServiceImpl.offenderMonBeneficiariesTjn(newObj, new OffenderMonDeductions(), "INSERT");
				}
			}
		}
	}
		
		return newRec;
	}
	
	private BigDecimal getReceivedAmount(BigDecimal newRecReceivedAmount,BigDecimal oldRecReceivedAmount){
		return (newRecReceivedAmount != null ? newRecReceivedAmount: BigDecimal.ZERO).subtract(oldRecReceivedAmount != null ? oldRecReceivedAmount: BigDecimal.ZERO);

	}

}
