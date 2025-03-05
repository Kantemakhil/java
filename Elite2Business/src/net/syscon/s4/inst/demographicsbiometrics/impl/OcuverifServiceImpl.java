package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifService;
import net.syscon.s4.triggers.OffenderAlertsTwfService;

/**
 * Class OcuverifServiceImpl
 */
@Service
public class OcuverifServiceImpl extends BaseBusiness implements OcuverifService {

	@Autowired
	private OcuverifRepository ocuverifRepo;
	
	@Autowired
	private EliteDateService dateService;

	OffenderAlerts beanObj = new OffenderAlerts();
	private OffenderAlertsTwfService offenderAlertsTwfService;
	private static final String UPDATE = "UPDATE";
	/**
	 * Creates new OcuverifServiceImpl class Object
	 */
	public OcuverifServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<WorkFlowLogs> workFlExecuteQuery(final OffenderAlerts searchRecord) {
		beanObj = searchRecord;
		List<WorkFlowLogs> returnList = new ArrayList<WorkFlowLogs>();
		final List<WorkFlows> workFlowLst = (ArrayList<WorkFlows>) ocuverifRepo.workFlowsExecuteQuery(searchRecord);
		if (workFlowLst != null && !workFlowLst.isEmpty()) {
			final WorkFlows ojbWorkFlows = workFlowLst.get(0);
			returnList = (List<WorkFlowLogs>) ocuverifRepo.workFlExecuteQuery(ojbWorkFlows);
		} else{
			final WorkFlows workFlowObject = new WorkFlows();
			workFlowObject.setWorkFlowId(searchRecord.getWorkFlowId());
			returnList = (List<WorkFlowLogs>) ocuverifRepo.workFlExecuteQuery(workFlowObject);
		}
		
		String userId = null;
		String userName = null;
		for (WorkFlowLogs list : returnList) {
			userId = list.getCreateUserId();
			userName = ocuverifRepo.getUserName(userId);
			list.setCreateUserId(userName);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWORK_FL
	 *
	 */
	@Transactional
	public Integer workFlCommit(final WorkFlowLogsCommitBean commitBean) {
		int liReturn = 0;
		final List<OffenderAlerts> updateList = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final WorkFlowLogs obj : commitBean.getInsertList()) {
				final Integer workFlowSeq = ocuverifRepo.preInsert(obj);
				obj.setWorkFlowSeq(workFlowSeq);
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuverifRepo.workFlCommit(commitBean.getInsertList());
			if (liReturn == 1) {
				final OffenderAlerts bean = new OffenderAlerts();
				bean.setVerifiedFlag("Y");
				bean.setOffenderBookId(beanObj.getOffenderBookId());
				bean.setAlertSeq(beanObj.getAlertSeq());
				bean.setModifyUserId(commitBean.getCreateUserId());
				updateList.add(bean);
				liReturn = ocuverifRepo.offAlertUpdate(updateList);
				try {
					offenderAlertsTwfService.offenderAlertsTwfTrigger(bean, UPDATE);
				} catch (Exception e) {
				}
			}
		}
		return liReturn;
	}

	@Transactional
	public Integer workFlVerificationCommit(final WorkFlowLogsCommitBean commitBean) {
		int liReturn = 0;
		commitBean.getUpdateNextReviewDate().setModifyUserId(commitBean.getCreateUserId());
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final WorkFlowLogs obj : commitBean.getInsertList()) {
				final Integer workFlowSeq = ocuverifRepo.preInsert(obj);
				obj.setWorkFlowSeq(workFlowSeq);
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuverifRepo.workFlInsert(commitBean.getInsertList());

			if (liReturn == 1 && commitBean.getUpdateNextReviewDate() != null) {
				if (commitBean.getUpdateNextReviewDate().getNextReviewDate() != null) {
					ocuverifRepo.updateNextReviewDate(commitBean.getUpdateNextReviewDate());
				} else {
					String securityLevel = ocuverifRepo.getOffenderSecurityLevel(
							commitBean.getUpdateNextReviewDate().getOffenderBookId(),
							commitBean.getUpdateNextReviewDate().getCreateUserId());

					if (securityLevel != null) {
						Integer reviewDays = ocuverifRepo.getReviewDays(securityLevel);
						if (reviewDays != null) {
							Date nextReviewDate = new Date(dateService.getDBTime().getTime() + (86400000 * reviewDays));
							commitBean.getUpdateNextReviewDate().setNextReviewDate(nextReviewDate);
						} else {
							commitBean.getUpdateNextReviewDate().setNextReviewDate(null);
						}
					}
					ocuverifRepo.updateNextReviewDate(commitBean.getUpdateNextReviewDate());
				}
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final WorkFlowLogs obj : commitBean.getUpdateList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuverifRepo.workFlUpdate(commitBean.getUpdateList());
			if (liReturn == 1 && commitBean.getUpdateNextReviewDate() != null) {
				if (commitBean.getUpdateNextReviewDate().getNextReviewDate() != null) {
					ocuverifRepo.updateNextReviewDate(commitBean.getUpdateNextReviewDate());
				} else {
					String securityLevel = ocuverifRepo.getOffenderSecurityLevel(
							commitBean.getUpdateNextReviewDate().getOffenderBookId(),
							commitBean.getUpdateNextReviewDate().getCreateUserId());

					if (securityLevel != null) {
						Integer reviewDays = ocuverifRepo.getReviewDays(securityLevel);
						if (reviewDays != null) {
							Date nextReviewDate = new Date(dateService.getDBTime().getTime() + (86400000 * reviewDays));
							commitBean.getUpdateNextReviewDate().setNextReviewDate(nextReviewDate);
						} else {
							commitBean.getUpdateNextReviewDate().setNextReviewDate(null);
						}
					}
					ocuverifRepo.updateNextReviewDate(commitBean.getUpdateNextReviewDate());
				}
			}

		}

		return liReturn;
	}

}
