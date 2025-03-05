package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.cm.teamsworkflow.beans.WorkCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.workflow.maintenance.OcmworksRepository;
import net.syscon.s4.inst.workflow.maintenance.OcmworksService;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunction;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunctionCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplateCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTriggerCommitBean;
import net.syscon.s4.pkgs.ocmworks.OcmworksPkgService;
import net.syscon.s4.triggers.InternetAddressesT1Service;
import net.syscon.s4.triggers.InternetAddressesT2Service;



/**
 * Class OcmworksServiceImpl
 */
@Service
public class OcmworksServiceImpl extends BaseBusiness implements OcmworksService{

	@Autowired
	private OcmworksRepository ocmworksRepository;
	@Autowired
    private OcmworksPkgService OcmworksService;
	@Autowired
	private InternetAddressesT1Service internetAddressesT1Service;
	@Autowired
	private InternetAddressesT2Service internetAddressesT2Service;
	public static final String CONSTANTVALUE = "2";
	public static final String SUCCESSMSG = "success";

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules wfWorkTypesPostQuery(OmsModules paramBean) {
		return (OmsModules) ocmworksRepository.wfWorkTypesPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<WorkIwpTemplate> wfWorkTypesOnCheckDeleteMaster(WorkIwpTemplate paramBean) {
		return ocmworksRepository.wfWorkTypesOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<InternetAddresses> wfEmailReturnPreInsert(InternetAddresses paramBean) {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Work> wfWorkTypesExecuteQuery(Work searchRecord) {
		return ocmworksRepository.wfWorkTypesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_WORK_TYPES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Work wfWorkTypesCommit(WorkCommitBean commitBean) {
		Work returnBean = new Work();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (Work bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			String returnVal = ocmworksRepository.wfWorkTypesInsertWorks(commitBean.getInsertList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (Work bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfWorkTypesUpdateWorks(commitBean.getUpdateList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (Work bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				Integer childCount = OcmworksService.getCountOffenderAssociated(bean.getWorkType(),
						bean.getWorkSubType());
				if (childCount > 0) {
					returnBean.setSealFlag(CONSTANTVALUE);
					return returnBean;
				}
				String returnValue = onCheckDeleteMaster(bean);
				if(returnValue!= null) {
					returnBean.setSealFlag(returnValue);
					return returnBean;
				}
			}
			liReturn = ocmworksRepository.wfWorkTypesDeleteWorks(commitBean.getDeleteList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		return returnBean;
	}

	public String onCheckDeleteMaster(final Work bean) {
		Work returnBean = ocmworksRepository.onCheckdeleteMaster(bean);
		if (returnBean.getTemplateCount() != null) {
			return returnBean.getTemplateCount();
		} else if (returnBean.getTriggerCount() != null) {
			return returnBean.getTriggerCount();
		} else if (returnBean.getFunctionCount() != null) {
			return returnBean.getFunctionCount();
		} else if (returnBean.getEmailRecipientsCount() != null) {
			return returnBean.getEmailRecipientsCount();
		} else if (returnBean.getEmailReturnCount() != null) {
			return returnBean.getEmailReturnCount();
		} else {
			return null;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<WorkIwpTemplate> wfIwpTemplatesExecuteQuery(WorkIwpTemplate searchRecord) {
		return ocmworksRepository.wfIwpTemplatesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_IWP_TEMPLATES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public WorkIwpTemplate wfIwpTemplatesCommit(WorkIwpTemplateCommitBean commitBean) {
		WorkIwpTemplate returnBean = new WorkIwpTemplate();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WorkIwpTemplate bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			String insVal = ocmworksRepository.wfIwpTemplatesInsertWorkIwpTemplates(commitBean.getInsertList());
			if (insVal != null && "1".equals(insVal)) {
				returnBean.setSealFlag(SUCCESSMSG);
			} else {
				returnBean.setSealFlag(insVal);
				return returnBean;
			}

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (WorkIwpTemplate bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfIwpTemplatesUpdateWorkIwpTemplates(commitBean.getUpdateList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (WorkIwpTemplate bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				Integer chilCount = OcmworksService.getCountIwpDocuments(bean);
				if (chilCount > 0) {
					returnBean.setSealFlag(CONSTANTVALUE);
					return returnBean;
				}
			}
			liReturn = ocmworksRepository.wfIwpTemplatesDeleteWorkIwpTemplates(commitBean.getDeleteList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		return returnBean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<WorkTrigger> wfTriggersExecuteQuery(WorkTrigger searchRecord) {
		return ocmworksRepository.wfTriggersExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_TRIGGERS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public WorkTrigger wfTriggersCommit(WorkTriggerCommitBean commitBean) {
		WorkTrigger returnBean = new WorkTrigger();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WorkTrigger bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			String returnVal = ocmworksRepository.wfTriggersInsertWorkTriggers(commitBean.getInsertList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (WorkTrigger bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfTriggersUpdateWorkTriggers(commitBean.getUpdateList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmworksRepository.wfTriggersDeleteWorkTriggers(commitBean.getDeleteList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		return returnBean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<WorkFunction> wfFunctionsExecuteQuery(WorkFunction searchRecord) {
		return ocmworksRepository.wfFunctionsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_FUNCTIONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public WorkFunction wfFunctionsCommit(WorkFunctionCommitBean commitBean) {
		int liReturn = 0;
		WorkFunction returnBean = new WorkFunction();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (WorkFunction bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			String returnVal = ocmworksRepository.wfFunctionsInsertWorkFunctions(commitBean.getInsertList());
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (WorkFunction bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfFunctionsUpdateWorkFunctions(commitBean.getUpdateList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmworksRepository.wfFunctionsDeleteWorkFunctions(commitBean.getDeleteList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		return returnBean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<InternetAddresses> wfEmailRecipientsExecuteQuery(InternetAddresses searchRecord) {
		return ocmworksRepository.wfEmailRecipientsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_EMAIL_RECIPIENTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public InternetAddresses wfEmailRecipientsCommit(InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		InternetAddresses returnBean = new InternetAddresses();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (InternetAddresses bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				try {
					 internetAddressesT1Service.internetAddressesT1Trigger(bean.getOwnerClass(), bean.getOwnerId().longValue(), bean.getOwnerSeq().longValue(), bean.getOwnerCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String returnVal = ocmworksRepository.wfEmailRecipientsInsertInternetAddresses(commitBean.getInsertList());
			List<InternetAddresses> list = commitBean.getInsertList();
			for(InternetAddresses obj:list) {
				internetAddressesT2Service.internetAddressesT2Trigger(obj);
			}
			if (returnVal != null && "1".equals(returnVal)) {
				returnBean.setSealFlag(SUCCESSMSG);
			} else {
				returnBean.setSealFlag(returnVal);
				return returnBean;
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (InternetAddresses bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				try {
					 internetAddressesT1Service.internetAddressesT1Trigger(bean.getOwnerClass(), bean.getOwnerId().longValue(), bean.getOwnerSeq().longValue(), bean.getOwnerCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			liReturn = ocmworksRepository.wfEmailRecipientsUpdateInternetAddresses(commitBean.getUpdateList());
			List<InternetAddresses> list = commitBean.getUpdateList();
			for(InternetAddresses obj:list) {
				internetAddressesT2Service.internetAddressesT2Trigger(obj);
			}
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmworksRepository.wfEmailRecipientsDeleteInternetAddresses(commitBean.getDeleteList());
			List<InternetAddresses> list = commitBean.getDeleteList();
			for(InternetAddresses obj:list) {
				internetAddressesT2Service.internetAddressesT2Trigger(obj);
			}
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getWorkupdateList() != null && !commitBean.getWorkupdateList().isEmpty()) {
			for (Work bean : commitBean.getWorkupdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfWorkEmailUpdateWorks(commitBean.getWorkupdateList());
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		if (commitBean.getWorkdeleteList() != null && !commitBean.getWorkdeleteList().isEmpty()) {
			liReturn = ocmworksRepository.wfWorkEmailDeleteWorks(commitBean.getWorkdeleteList());
			
			if (liReturn == 1) {
				returnBean.setSealFlag(SUCCESSMSG);
			}
		}
		return returnBean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<InternetAddresses> wfEmailReturnExecuteQuery(InternetAddresses searchRecord) {
		return ocmworksRepository.wfEmailReturnExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Work> wfWorkEmailExecuteQuery(Work searchRecord) {
		return ocmworksRepository.wfWorkEmailExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWF_WORK_EMAIL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer wfWorkEmailCommit(WorkCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (Work bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfWorkEmailInsertWorks(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (Work bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmworksRepository.wfWorkEmailUpdateWorks(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmworksRepository.wfWorkEmailDeleteWorks(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgWorkflowTypeRecordGroup() {
		return ocmworksRepository.rgWorkflowTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAgyLocTypeRecordGroup() {
		return ocmworksRepository.rgAgyLocTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		return ocmworksRepository.rgWorkTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup(final String workType) {
		List<ReferenceCodes> returnList = ocmworksRepository.rgWorkSubTypeRecordGroup(workType);
		for(ReferenceCodes bean: returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsModules> rgModulesRecordGroup() {
		return ocmworksRepository.rgModulesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<IwpTemplates> rgTemplatesRecordGroup() {
		List<IwpTemplates> returnList = ocmworksRepository.rgTemplatesRecordGroup();
		for (IwpTemplates bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgTriggerNameRecordGroup() {
		return ocmworksRepository.rgTriggerNameRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		return ocmworksRepository.rgFunctionRecordGroup();

	}
	public Integer checkdays(final WorkTrigger bean) {
		return OcmworksService.getPreviousDays(bean);

	}
	
}