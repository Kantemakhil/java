package net.syscon.s4.inst.legals.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.legals.OidojoinRepository;
import net.syscon.s4.inst.legals.OidojoinService;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.PriorHistory;
import net.syscon.s4.inst.legals.beans.PriorHistoryCommitBean;
@Service
public class OidojoinServiceImpl implements OidojoinService{
	@Autowired
	OidojoinRepository oidojoinRepository;
	@Override
	public List<PriorHistory> populateGridData(CourtCases offenderCases) {
		List<PriorHistory> resultList = new ArrayList<PriorHistory>();
		resultList = oidojoinRepository.populateGridData(offenderCases);
		return resultList;
	}
	@Override
	public List<BailStatus> populateOrderType() {
		List<BailStatus> resultList = new ArrayList<BailStatus>();
		resultList = oidojoinRepository.populateOrderType();
		return resultList;
	}
	@Override
	public List<BailStatus> populateStatus() {
		List<BailStatus> resultList = new ArrayList<BailStatus>();
		resultList = oidojoinRepository.populateStatus();
		return resultList;
	}
	@Override
	public List<BailStatus> populateSource() {
		List<BailStatus> resultList = new ArrayList<BailStatus>();
		resultList = oidojoinRepository.populateSource();
		return resultList;
	}
	@Override
	public List<BailStatus> populateCounty() {
		List<BailStatus> resultList = new ArrayList<BailStatus>();
		resultList = oidojoinRepository.populateCounty();
		return resultList;
	}
	
	@Override
	public List<BailStatus> populateState() {
		List<BailStatus> resultList = new ArrayList<BailStatus>();
		resultList = oidojoinRepository.populateState();
		return resultList;
	}
	@Override
	@Transactional
	public Integer insertNewRecord(PriorHistoryCommitBean priorHistoryCommitBean) {
		
		int liReturn = 0; 
		if(null!=priorHistoryCommitBean.getInsertList() && priorHistoryCommitBean.getInsertList().size()>0) {
			liReturn=insertPriorHistoryData(priorHistoryCommitBean.getInsertList());
		}
		if(null!=priorHistoryCommitBean.getUpdateList() && priorHistoryCommitBean.getUpdateList().size()>0) {
			liReturn=updatePriorHistoryData(priorHistoryCommitBean.getUpdateList());
		}
		if(null!=priorHistoryCommitBean.getDeleteList() && priorHistoryCommitBean.getDeleteList().size()>0) {
			for (PriorHistory object : priorHistoryCommitBean.getDeleteList()) {
				object.setModifyUserId(priorHistoryCommitBean.getCreateUserId());
			}
			liReturn=deletePriorHistoryData(priorHistoryCommitBean.getDeleteList());
		}
		
		return  liReturn;
	}
	@Transactional
	private Integer insertPriorHistoryData(List<PriorHistory> insertList) {
		int liReturn = 0;  
		List<PriorHistory> recordSavingObject = new ArrayList<>();
		if (insertList.size() > 0) {
			for (int i = 0; i <insertList.size(); i++) {
				
			Integer offenseSeq=getPreInsertOffenseSeq(insertList.get(i).getOffenderBookId());
			recordSavingObject = new ArrayList<>();
			final PriorHistory newPriorHistoryData = insertList.get(i);
			if(offenseSeq!=null)
			{
			newPriorHistoryData.setOffenseSeq(Integer.valueOf(offenseSeq));
			}
			else
			{
				newPriorHistoryData.setOffenseSeq(1);
			}
			if(insertList.get(i).getVerified()=="true") {
				newPriorHistoryData.setVerified("Y");
			} else {
				newPriorHistoryData.setVerified("N");
			}
			recordSavingObject.add(newPriorHistoryData);
			liReturn = oidojoinRepository.insertPriorHistoryData(recordSavingObject);
		}}
		return liReturn;
	} 
	
	@Transactional
	private Integer updatePriorHistoryData(List<PriorHistory> updatetList) {
		int liReturn = 0;
		List<PriorHistory> recordSavingObject = new ArrayList<>();
		if (updatetList.size() > 0) {
		for (int i = 0; i < updatetList.size(); i++) {
			recordSavingObject =  new ArrayList<>();
			final PriorHistory newPriorHistoryData = updatetList.get(i);
			if(updatetList.get(i).getVerified()=="true") {
				newPriorHistoryData.setVerified("Y");
			} else {
				newPriorHistoryData.setVerified("N");
			}
			recordSavingObject.add(newPriorHistoryData);
			liReturn = oidojoinRepository.updatePriorHistoryData(updatetList);
		}}
		return liReturn;
	} 
	@Transactional
	public Integer deletePriorHistoryData(final List<PriorHistory> deleteList) {
		int liReturn = 0;
		liReturn=oidojoinRepository.deletePriorHistoryData(deleteList);
		return liReturn;
	}
	private Integer getPreInsertOffenseSeq(final Long offenderBookId) {
		return oidojoinRepository.getPreInsertOffenseSeq(offenderBookId);
	}
}
