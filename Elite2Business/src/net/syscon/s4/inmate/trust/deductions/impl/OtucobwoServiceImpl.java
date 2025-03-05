package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OtucobwoRepository;
import net.syscon.s4.inmate.trust.deductions.OtucobwoService;

/**
 * Class OcdoobliServiceImpl
 */
@Service 
public class OtucobwoServiceImpl extends BaseBusiness implements OtucobwoService {
	
	@Autowired
	private OtucobwoRepository otucobwoRepository;

/**
 * This method is used to execute a record group
 *
*/
public List<TransactionOperation> cgfkCobwoAdjustmentReasoRecordGroup(final String caseloadId)  {
	List<TransactionOperation> returnList= otucobwoRepository.cgfkCobwoAdjustmentReasoRecordGroup(caseloadId);
	returnList.forEach(result ->{
		result.setCode(result.getTxnType());
		result.setDescription(result.getDescription());
		
		
	});
	return returnList;

}

@Override
public List<OffenderAdjustmentTxns> save(OffenderAdjustmentTxns bean) {
	BigDecimal writeAmount =BigDecimal.valueOf(0);
	Long txnId= otucobwoRepository.getTxnId();
	bean.setTxnId(txnId);
	Long txnSeq =Long.valueOf(1);
	bean.setTxnEntrySeq(txnSeq);
	List<OffenderAdjustmentTxns> list=otucobwoRepository.writeOffGl(bean);
	for(OffenderAdjustmentTxns listBean: list){
		bean.setDrAccountCode(listBean.getDrAccountCode());
		bean.setAccountCode(listBean.getAccountCode());
		bean.setTxnPostingType(listBean.getTxnPostingType());
		bean.setCr(listBean.getCr());
		bean.setTxnType(listBean.getTxnType());
		listBean.setTxnId(bean.getTxnId());
		listBean.setTxnEntrySeq(bean.getTxnEntrySeq());
		
	}
	Integer drOne = otucobwoRepository.insertintoGltransactionsDr(bean);
	if(drOne==1){
		Integer crOne = otucobwoRepository.insertintoGltransactionsCr(bean);
		if(crOne ==1){
		Integer count=otucobwoRepository.insertintoOffnederAdjustmentTxns(bean);
		writeAmount = bean.getLvWriteOffAmount().add( bean.getAdjustmentAmount());
		bean.setLvWriteOffAmount(writeAmount);
		Integer updateCount=otucobwoRepository.updateOffenderdeductions(bean);
			
		}
	}
	return list;
}

@Override
public List<OffenderDeductions> offenderDeductionCur(Long deductionId) {
	return otucobwoRepository.offenderDeductionCur(deductionId);
}

}