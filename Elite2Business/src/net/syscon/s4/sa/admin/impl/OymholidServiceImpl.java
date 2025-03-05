package net.syscon.s4.sa.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.of.payroll.maintenance.SystemEventsCommitBean;
import net.syscon.s4.sa.admin.OymholidRepository;
import net.syscon.s4.sa.admin.OymholidService;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompens;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompensCommitBean;
import net.syscon.s4.sa.admin.beans.CaseloadWorkGroups;

/**
 * Class OymholidServiceImpl
 */
@Service
public class OymholidServiceImpl extends BaseBusiness implements OymholidService {

	@Autowired
	private OymholidRepository oymholidRepository;

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 */
	public List<SystemEvents> sysEventExecuteQuery(final SystemEvents searchRecord) {
		return oymholidRepository.sysEventExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstSYS_EVENT
	 */
	@Transactional
	public Integer sysEventCommit(final SystemEventsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SystemEvents obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oymholidRepository.insertSystemEvents(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SystemEvents obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oymholidRepository.updateSystemEvents(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oymholidRepository.deleteSystemEvents(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 */
	public List<CaseloadGrpHolCompens> csldGhcExecuteQuery(final CaseloadGrpHolCompens searchRecord) {
		return oymholidRepository.csldGhcExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstCSLD_GHC
	 */
	@Transactional
	public Integer csldGhcCommit(final CaseloadGrpHolCompensCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			
			for (CaseloadGrpHolCompens obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oymholidRepository.insertCaseloadGrpHolCompens(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CaseloadGrpHolCompens obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oymholidRepository.updateCaseloadGrpHolCompens(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oymholidRepository.deleteCaseloadGrpHolCompens(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkCsldGhcCompensationCodRecordGroup() {
		return oymholidRepository.cgfkCsldGhcCompensationCodRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<CaseloadWorkGroups> cgfkCsldGhcWorkGroupIdRecordGroup(final String caseloadType) {
		return oymholidRepository.cgfkCsldGhcWorkGroupIdRecordGroup(caseloadType);
	}

}