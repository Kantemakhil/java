package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.HoldsCommitBean;
import net.syscon.s4.inst.legals.OcuccideRepository;
import net.syscon.s4.inst.legals.OcuccideService;
import net.syscon.s4.inst.legals.beans.CaseIdentifiers;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.IdentifierCommitBean;
import net.syscon.s4.inst.legals.beans.IdentifierType;


@Service
public class OcuccideServiceImpl implements OcuccideService {

	@Autowired
	OcuccideRepository ocuccideRepository;
	
	@Override
	public List<CaseIdentifiers> caseIdentifiers(Long caseId) {		
		return ocuccideRepository.caseIdentifiers( caseId);
	}

	@Override
	public List<IdentifierType> identifierType() {
		return (List<IdentifierType>) ocuccideRepository.identifierType();
	}	
	
	@Transactional
	public int[] updateIdentifierData(String caseId, IdentifierCommitBean identifierBeanCommit) {
		
		int[] returnRows = {}; 
		if(null!=identifierBeanCommit.getInsertList() && identifierBeanCommit.getInsertList().size()>0) {
			returnRows =insertData(identifierBeanCommit.getInsertList(), caseId);
		}
		if(null!=identifierBeanCommit.getUpdateList() && identifierBeanCommit.getUpdateList().size()>0) {
			returnRows = updateData(identifierBeanCommit.getUpdateList());
		}
		if(null!=identifierBeanCommit.getDeleteList() && identifierBeanCommit.getDeleteList().size()>0) {
			returnRows =deleteData(identifierBeanCommit.getDeleteList(), caseId);
		}
		return  returnRows;
	}
	
	@Transactional
	private int[] insertData(List<CaseIdentifiers> insertList,String userId) {
		int[] returnRows = {}; 
		List<CaseIdentifiers> recordSavingObject = new ArrayList<>();
		for ( CaseIdentifiers newRecord :insertList) {
			recordSavingObject = new ArrayList<>();			
			newRecord.setCreateUserId(userId);
			newRecord.setModifyUserId(userId);					
			recordSavingObject.add(newRecord);
		}		
		returnRows = ocuccideRepository.insertIdentifierData(insertList);
		return returnRows;
	} 
	
	@Transactional
	private int[] updateData(List<CaseIdentifiers> updatetList) {
		int[] liReturn = {};
		List<CaseIdentifiers> recordSavingObject = new ArrayList<>();
		for ( CaseIdentifiers newIdentifierData :updatetList) {
			recordSavingObject = new ArrayList<>();			
			recordSavingObject.add(newIdentifierData);
		}
		liReturn = ocuccideRepository.updateIdentifierData(updatetList);
		return liReturn;
	} 
	
	@Transactional
	public int[] deleteData(final List<CaseIdentifiers> deleteList, String userName) {
		int[] liReturn = {};
		List<CaseIdentifiers> recordSavingObject = new ArrayList<>();
		for ( CaseIdentifiers newIdentifierData :deleteList) {
			newIdentifierData.setModifyUserId(userName);
			recordSavingObject = new ArrayList<>();
			recordSavingObject.add(newIdentifierData);
		}
		liReturn =  ocuccideRepository.deleteIdentifierData(deleteList);
		return liReturn;
	}
}
