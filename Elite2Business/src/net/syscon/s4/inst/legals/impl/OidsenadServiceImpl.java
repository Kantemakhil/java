package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.legals.OidsenadRepository;
import net.syscon.s4.inst.legals.OidsenadService;
import net.syscon.s4.inst.legals.beans.AdjustCommitBean;
import net.syscon.s4.inst.legals.beans.AdjustmentDetails;
import net.syscon.s4.inst.legals.beans.AdjustmentDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
@Service
public class OidsenadServiceImpl implements OidsenadService{
	@Autowired
	OidsenadRepository oidsenadRepository;

	@Override
	public List<SentenceAdjustment> populateSentAdjustment(CourtCases offenderCases) {
		List<SentenceAdjustment> resultList = new ArrayList<SentenceAdjustment>();
		resultList = oidsenadRepository.populateSentAdjustment(offenderCases);
		return resultList;
	}
	@Override
	public List<BailStatus> populateDebitTypeData() {
		List<BailStatus> resultList = new ArrayList<BailStatus>();
		resultList = oidsenadRepository.populateDebitTypeData();
		return resultList;
	}
	@Override
	public List<AdjustmentDetails> populateDebitCredit(SentenceAdjustment sentenceAdjustment) {
		List<AdjustmentDetails> resultList = new ArrayList<AdjustmentDetails>();
		resultList = oidsenadRepository.populateDebitCredit(sentenceAdjustment);
		return resultList;
	}
	@Override
	@Transactional
	public Integer insertDebitCreditRecord(AdjustmentDetailsCommitBean adjustmentDetailsCommitBean)
	{

		int liReturn = 0; 
		if(null!=adjustmentDetailsCommitBean.getInsertList() && adjustmentDetailsCommitBean.getInsertList().size()>0) {
			for (AdjustmentDetails object : adjustmentDetailsCommitBean.getInsertList()) {
				object.setCreateUserId(adjustmentDetailsCommitBean.getCreateUserId());
			}
			liReturn=insertDebitCreditData(adjustmentDetailsCommitBean.getInsertList());
		}
		if(null!=adjustmentDetailsCommitBean.getUpdateList() && adjustmentDetailsCommitBean.getUpdateList().size()>0) {
			for (AdjustmentDetails object : adjustmentDetailsCommitBean.getUpdateList()) {
				object.setModifyUserId(adjustmentDetailsCommitBean.getCreateUserId());
			}
			liReturn=updateDebitCreditData(adjustmentDetailsCommitBean.getUpdateList());
		}
		if(null!=adjustmentDetailsCommitBean.getDeleteList() && adjustmentDetailsCommitBean.getDeleteList().size()>0) {
			for (AdjustmentDetails object : adjustmentDetailsCommitBean.getDeleteList()) {
				object.setModifyUserId(adjustmentDetailsCommitBean.getCreateUserId());
			}
			liReturn=deleteDebitCreditData(adjustmentDetailsCommitBean.getDeleteList());
		}
		return  liReturn;
	}
	@Transactional
	private Integer insertDebitCreditData(List<AdjustmentDetails> insertList) {
		int liReturn = 0;  
		List<AdjustmentDetails> recordSavingObject = new ArrayList<>();
		if (insertList.size() > 0) {
			for (AdjustmentDetails adjustmentDetails : insertList) {
				Long sentAdjustId=getPreInsertSentAdjId();
				recordSavingObject = new ArrayList<>();
				final AdjustmentDetails newAdjustmentDetailData = adjustmentDetails;
				newAdjustmentDetailData.setOffenderSentenceAdjustId(sentAdjustId);
				recordSavingObject.add(newAdjustmentDetailData);
				liReturn = oidsenadRepository.insertDebitCreditRecord(recordSavingObject);
			}
		
		}
		return liReturn;
	}
	@Transactional
	private Integer updateDebitCreditData(List<AdjustmentDetails> updateList) {
		int liReturn = 0;  
		List<AdjustmentDetails> recordSavingObject = new ArrayList<>();
		if (updateList.size() > 0) {
			for (AdjustmentDetails adjustmentDetails : updateList) {
			recordSavingObject = new ArrayList<>();
			final AdjustmentDetails newAdjustmentDetailData = adjustmentDetails;
			recordSavingObject.add(newAdjustmentDetailData);
			liReturn = oidsenadRepository.updateDebitCreditRecord(recordSavingObject);
		}}
		return liReturn;
	}
	@Transactional
	private Integer deleteDebitCreditData(List<AdjustmentDetails> deleteList) {
		int liReturn = 0;  
		List<AdjustmentDetails> recordSavingObject = new ArrayList<>();
		if (deleteList.size() > 0) {
			for (int i = 0; i <deleteList.size(); i++) {
			recordSavingObject = new ArrayList<>();
			final AdjustmentDetails newAdjustmentDetailData = deleteList.get(i);
			recordSavingObject.add(newAdjustmentDetailData);
			liReturn = oidsenadRepository.deleteDebitCreditRecord(recordSavingObject);
		}}
		return liReturn;
	}
	private Long getPreInsertSentAdjId() {
		return oidsenadRepository.getPreInsertSentAdjId();
	}

	
	
	@Override
	public List<BailStatus> populateAdjustTypeLov() {
		return oidsenadRepository.populateAdjustTypeLov();
	}
	@Override
	public List<Adjustments> populateAdjustData(Long offenderBookId) {
		return oidsenadRepository.populateAdjustData(offenderBookId);
	}
	@Transactional
	public Integer updateAdjustData(AdjustCommitBean adjustBean) {
		Integer returnRows = 0; 
		if(adjustBean.getInsertList().size() > 0){
			for(Adjustments adjust : adjustBean.getInsertList()){
				adjust.setKeyDatesAdjustId(getAdjustKeyId());
				adjust.setCreateUserId(adjustBean.getCreateUserId());
			}
			returnRows = insertAdjustRecord(adjustBean.getInsertList());
			
		}
		
		if(adjustBean.getUpdateList().size() > 0){
			for(Adjustments adjust : adjustBean.getUpdateList()){
				adjust.setModifyUserId(adjustBean.getCreateUserId());
			}
			returnRows = updateAdjustRecord(adjustBean.getUpdateList());
		}
		
		if(null != adjustBean.getDeleteList() && adjustBean.getDeleteList().size() > 0){
			for(Adjustments adjust : adjustBean.getDeleteList()){
				adjust.setModifyUserId(adjustBean.getCreateUserId());
			}
			returnRows = deleteAdjustRecord(adjustBean.getDeleteList());			
		}
		return returnRows;
	}
	
	@Transactional
	private Integer deleteAdjustRecord(List<Adjustments> deleteList) {
		return oidsenadRepository.deleteAdjustRecord(deleteList);
	}
	@Transactional
	private Integer prePostEventCall(List<Adjustments> insertList) {
		return oidsenadRepository.prePostEventCall(insertList);
		
	}
	@Transactional
	private Long getAdjustKeyId(){
		Long adjId=null;
		adjId = oidsenadRepository.getNextAdjustId();
		return adjId;
	}
	
	@Transactional
	private Integer insertAdjustRecord(List<Adjustments> insertList) {
		return oidsenadRepository.insertAdjustRecord(insertList);
	}
	
	@Transactional
	private Integer updateAdjustRecord(List<Adjustments> updateList) {
		return oidsenadRepository.updateAdjustRecord(updateList);
	}
	@Override
	public Integer postInsertAdjustRecord(List<Adjustments> insertList) {
		return oidsenadRepository.postInsertAdjustRecord(insertList);
	}
}
