package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.sa.admin.OtmlockrRepository;
import net.syscon.s4.sa.admin.OtmlockrService;
import net.syscon.s4.sa.admin.beans.LockedModulesCommitBean;

/**
 * Class OtmlockrServiceImpl
 */
@Service
public class OtmlockrServiceImpl extends BaseBusiness implements OtmlockrService {

	@Autowired
	private OtmlockrRepository otmlockrRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<LockedModules> lockModExecuteQuery(final LockedModules searchRecord) {
		return otmlockrRepository.lockModExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstLOCK_MOD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer lockModCommit(final LockedModulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (LockedModules bean : commitBean.getInsertList()) {
				// aading user to bean
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmlockrRepository.lockModInsertLockedModules(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (LockedModules bean : commitBean.getUpdateList()) {
				// aading user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmlockrRepository.lockModUpdateLockedModules(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			List<Long> repotingLocIdList = getRepotingLocId(commitBean.getDeleteList());
			if(repotingLocIdList.size() > 0) {
				deleteInitiateRecordsOfAgencyLocationCounts(repotingLocIdList);
				deleteInitiateRecords(repotingLocIdList);
			}
			liReturn = otmlockrRepository.lockModDeleteLockedModules(commitBean.getDeleteList());
		}
		return liReturn;
	}
	
	private List<Long> getRepotingLocId(List<LockedModules> lockedModulesList) {
		Long repotingLocId = null;
		List<Long> repotingLocIdList = new ArrayList<Long>();
		for(LockedModules lockedModules :lockedModulesList) {
			repotingLocId = otmlockrRepository.getRepotingLocId(lockedModules);
			if(repotingLocId != null) {
				repotingLocIdList.add(repotingLocId);
			}
		}
		return repotingLocIdList;
	}
	
	private void deleteInitiateRecordsOfAgencyLocationCounts(List<Long> repotingLocIdList) {
		for(Long repotingLocId : repotingLocIdList) {
			otmlockrRepository.deleteInitiateRecordsOfAgencyLocationCounts(repotingLocId);
		}
	}
	
	private void deleteInitiateRecords(List<Long> repotingLocIdList) {
		for(Long repotingLocId : repotingLocIdList) {
			otmlockrRepository.deleteInitiateRecords(repotingLocId);
		}
	}

}