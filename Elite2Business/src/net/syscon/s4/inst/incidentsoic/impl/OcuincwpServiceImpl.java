package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeapons;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeaponsCommitBean;
import net.syscon.s4.inst.incidentsoic.OcuincwpRepository;
import net.syscon.s4.inst.incidentsoic.OcuincwpService;

@Service
public class OcuincwpServiceImpl extends BaseBusiness implements OcuincwpService {
	
	@Autowired
	private OcuincwpRepository ocuincwpRepository;
	
	private static Logger logger = LogManager.getLogger(OcuincwpServiceImpl.class.getName());
	
	public OcuincwpServiceImpl() {
		super();
	}
	
	@Transactional
	@Override
	public Integer offednerWeaponsInsertQuery(final OffenderWeaponsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for(OffenderWeapons obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = offenderWeaponcommitInsert(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for(OffenderWeapons obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = offenderWeaponcommitUpdate(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = offenderWeaponcommitDelete(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}
	
	@Transactional
	public Integer offenderWeaponcommitInsert(final List<OffenderWeapons> LInsertList){
			int insertPartiesCount = 0;
			int updatePartiesCount = 0;
			
			final List<OffenderWeapons> updateList = new ArrayList<OffenderWeapons>();
				insertPartiesCount = ocuincwpRepository.offenderWeaponcommitInsert(LInsertList);
			if (updateList.size() > 0) {
				updatePartiesCount = ocuincwpRepository.offenderWeaponcommitUpdate(updateList);
			}
			return insertPartiesCount + updatePartiesCount;
	}
	
	@Transactional
	public Integer offenderWeaponcommitUpdate(List<OffenderWeapons> lUpdateList){
		
		Integer resultNo = 0;
		 resultNo=ocuincwpRepository.offenderWeaponcommitUpdate(lUpdateList);
		 return resultNo;
	}
	
	@Transactional
	public Integer offenderWeaponcommitDelete(List<OffenderWeapons> lDeleteList){
		final Integer chargesList = ocuincwpRepository.offenderWeaponcommitDelete(lDeleteList);
		return chargesList;
	}

	@Override
	public List<OffenderWeapons> offenderWeaponsData(OffenderWeapons objSearchDao) {
		List<OffenderWeapons> serachList=new ArrayList<>();
		try {
			serachList=ocuincwpRepository.offenderWeaponsData( objSearchDao);
		} catch (Exception e) {
			
		}
		return serachList;
	}






}
