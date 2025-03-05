package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.AgyIntLocProfilesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.movements.maintenance.OiunonasRepository;
import net.syscon.s4.inst.movements.maintenance.OiunonasService;
import net.syscon.s4.triggers.AgyIntLocAmendments;
import net.syscon.s4.triggers.AgyIntLocProfilesT1Service;

/**
 * Class OiunonasServiceImpl
 */
@Service
public class OiunonasServiceImpl extends BaseBusiness implements OiunonasService {

	@Autowired
	private OiunonasRepository oiunonasRepository;
	
	@Autowired
	private AgyIntLocProfilesT1Service agyIntLocProfilesT1Service;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgyIntLocProfiles> intLocProfExecuteQuery(final AgyIntLocProfiles searchRecord) {
		return oiunonasRepository.intLocProfExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstINT_LOC_PROF
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer intLocProfCommit(final AgyIntLocProfilesCommitBean commitBean) {
		int liReturn = 0;
		AgyIntLocAmendments newRef= new AgyIntLocAmendments();
		AgyIntLocAmendments old=new AgyIntLocAmendments();
		
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			
			for (AgyIntLocProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId()); 
			}
			final List<Long> intLocId = commitBean.getInsertList().stream().map(data -> data.getInternalLocationId())
					.collect(Collectors.toList());
			final List<String> intLocProfileCode = commitBean.getInsertList().stream()
					.map(data -> data.getIntLocProfileCode()).collect(Collectors.toList());
			final Integer dupCount = oiunonasRepository.preInsert(intLocId, intLocProfileCode);
			if (dupCount > 0) {
				return 2;
			}
			liReturn = oiunonasRepository.intLocProfInsertAgyIntLocProfiles(commitBean.getInsertList());
			String operationType="INSERTING";
			for (AgyIntLocProfiles agyIntLoc : commitBean.getInsertList()) {
				old.setInternalLocationId(agyIntLoc.getInternalLocationId());
				old.setIntLocProfileType(agyIntLoc.getIntLocProfileType());
				old.setIntLocProfileCode(agyIntLoc.getIntLocProfileCode());
				old.setAmendUserId(agyIntLoc.getCreateUserId());
				
			agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger(operationType, newRef,old );
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AgyIntLocProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oiunonasRepository.intLocProfUpdateAgyIntLocProfiles(commitBean.getUpdateList());
			String operationType="UPDATING";
			for (AgyIntLocProfiles agyIntLoc : commitBean.getUpdateList()) {
				old.setInternalLocationId(agyIntLoc.getInternalLocationId());
				old.setIntLocProfileType(agyIntLoc.getIntLocProfileType());
				old.setIntLocProfileCode(agyIntLoc.getIntLocProfileCode());
				old.setAmendUserId(agyIntLoc.getCreateUserId());
				
			agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger(operationType, newRef,old );
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oiunonasRepository.intLocProfDeleteAgyIntLocProfiles(commitBean.getDeleteList());
		}
		String operationType="DELETING";
		for (AgyIntLocProfiles agyIntLoc : commitBean.getDeleteList()) {
			old.setInternalLocationId(agyIntLoc.getInternalLocationId());
			old.setIntLocProfileType(agyIntLoc.getIntLocProfileType());
			old.setIntLocProfileCode(agyIntLoc.getIntLocProfileCode());
			old.setAmendUserId(agyIntLoc.getCreateUserId());
			
		agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger(operationType,old ,newRef );
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgNonAssoTypeRecordGroup() {
		return oiunonasRepository.rgNonAssoTypeRecordGroup();

	}
}