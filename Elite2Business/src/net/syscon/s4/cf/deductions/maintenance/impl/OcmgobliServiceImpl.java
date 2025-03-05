package net.syscon.s4.cf.deductions.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.maintenance.OcmgobliRepository;
import net.syscon.s4.cf.deductions.maintenance.OcmgobliService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.GroupedObligationsCommitBean;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.ObligationGroupsCommitBean;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Class OcmgobliServiceImpl
 */
@Service
public class OcmgobliServiceImpl extends BaseBusiness implements OcmgobliService {

	@Autowired
	private OcmgobliRepository ocmgobliRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<ObligationGroups> obGrpExecuteQuery(final ObligationGroups searchRecord) {
		return ocmgobliRepository.obGrpExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOB_GRP
	 *
	 */
	@Transactional
	public ObligationGroups obGrpCommit(final ObligationGroupsCommitBean commitBean) {
		ObligationGroups returnData = new ObligationGroups();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (ObligationGroups obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			    liReturn = ocmgobliRepository.obGrpInsertObligationGroups(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (ObligationGroups obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmgobliRepository.obGrpUpdateObligationGroups(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (ObligationGroups obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmgobliRepository.obGrpDeleteObligationGroups(commitBean.getDeleteList());
		}
		if (liReturn == 0) {
			returnData.setSealFlag("0");
		} else {
			returnData.setSealFlag("1");
		}

		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<GroupedObligations> grpObExecuteQuery(final GroupedObligations searchRecord) {
		return ocmgobliRepository.grpObExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGRP_OB
	 *
	 */
	@Transactional
	public Integer grpObCommit(final GroupedObligationsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (GroupedObligations obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmgobliRepository.grpObInsertGroupedObligations(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (GroupedObligations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmgobliRepository.grpObUpdateGroupedObligations(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (GroupedObligations obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmgobliRepository.grpObDeleteGroupedObligations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<DeductionTypes> cgfkGrpObDeductionTypeRecordGroup() {
		return ocmgobliRepository.cgfkGrpObDeductionTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<SanctionNotices> cgfkSanctionNoticesRecordGroup() {
		return ocmgobliRepository.cgfkSanctionNoticesRecordGroup();

	}

}