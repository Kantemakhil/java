package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.triggers.OffenderDeductionsT1Repository;
import net.syscon.s4.triggers.OffenderDeductionsT1Service;
@Service
public class OffenderDeductionsT1ServiceImpl implements OffenderDeductionsT1Service{
	@Autowired
	OffenderDeductionsT1Repository offenderdeductionst1repository;
	
	@Override
	public Integer OffDedCommit(OffenderDeductionsCommitBean commitBean) {
		Integer lireturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for(OffenderDeductions bean: commitBean.getInsertList()) {
				if(bean.getMaxTotalAmount() != null) {
				BigDecimal seq = offenderdeductionst1repository.getNextSeq(bean.getOffenderDeductionId());
				bean.setDeductionSeq(seq);
				lireturn = 	offenderdeductionst1repository.insOffOblHty(bean);
				}
			}
			

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for(OffenderDeductions bean: commitBean.getInsertList()) {
				BigDecimal seq = offenderdeductionst1repository.getNextSeq(bean.getOffenderDeductionId());
				bean.setDeductionSeq(seq);
				OffenderDeductions returnBean = offenderdeductionst1repository.getMaxTotalAmt(bean);
				if(returnBean.getMaxTotalAmount() != null && bean.getMaxTotalAmount() != null &&
						bean.getMaxTotalAmount().equals(returnBean.getMaxTotalAmount() )) {
					
				}
				lireturn = 	offenderdeductionst1repository.insOffOblHty(bean);
			}
		}
		return lireturn;
	}

}
