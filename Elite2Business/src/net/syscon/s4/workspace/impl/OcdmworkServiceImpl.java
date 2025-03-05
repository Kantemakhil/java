package net.syscon.s4.workspace.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.OcdaworkRepository;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.workspace.OcdmworkRepository;
import net.syscon.s4.workspace.OcdmworkService;

/**
 * Class OcdmworkServiceImpl
 */
@Service
public class OcdmworkServiceImpl extends BaseBusiness implements OcdmworkService {

	@Autowired
	private OcdmworkRepository ocdmworkRepository;
	@Autowired
	private OcdaworkRepository ocdaworkRepository;

	/**
	 * Creates new OcdmworkServiceImpl class Object
	 */
	public OcdmworkServiceImpl() {
		// OcdmworkServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> memoPostQuery(final ReferenceCodes paramBean) {
		return ocdmworkRepository.memoPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<TagWorkflowBrowseQueue> workExecuteQuery(final TagWorkflowBrowseQueue searchRecord) {
		List<TagWorkflowBrowseQueue> returnList = new ArrayList<>();
		//Below line is commented due to QUEUE is not supporting in postgre Db
	//	returnList = ocdmworkRepository.workExecuteQuery(searchRecord);
		for (final TagWorkflowBrowseQueue obj : returnList) {
			if (obj.getOffenderBookId() != null) {
				final TagWorkflowBrowseQueue returnObj = ocdaworkRepository.getOffenderDetails(obj.getOffenderBookId());
				obj.setLastName(returnObj.getLastName());
				obj.setFirstName(returnObj.getFirstName());
				obj.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
			}
			if (obj.getWorkId() != null) {
				final TagWorkflowBrowseQueue returnObj = ocdaworkRepository.getWorkDetails(obj.getWorkId());
				obj.setWorkflowType(returnObj.getWorkflowType());
				obj.setWorkType(returnObj.getWorkType());
				obj.setWorkSubType(returnObj.getWorkSubType());
				obj.setModuleName(returnObj.getModuleName());
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWORK
	 *
	 * @
	 */
	@Transactional
	public Integer workCommit(final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final TagWorkflowBrowseQueue offTransactions : commitBean.getInsertList()) {
				if ("true".equals(offTransactions.getCompleteFlag())) {
					liReturn = ocdmworkRepository.workUpdateTagWorkflowBrowseQueue(offTransactions);
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<TagWorkflowBrowseQueue> memoExecuteQuery(final TagWorkflowBrowseQueue searchRecord) {
		List<TagWorkflowBrowseQueue> returnList = new ArrayList<>();
		returnList = ocdmworkRepository.memoExecuteQuery(searchRecord);
		for (final TagWorkflowBrowseQueue obj : returnList) {
			if (obj.getOffenderBookId() != null) {
				final TagWorkflowBrowseQueue returnObj = ocdaworkRepository.getOffenderDetails(obj.getOffenderBookId());
				obj.setLastName(returnObj.getLastName());
				obj.setFirstName(returnObj.getFirstName());
				obj.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
			}
			if (obj.getWorkId() != null) {
				final TagWorkflowBrowseQueue returnObj = ocdaworkRepository.getWorkDetails(obj.getWorkId());
				obj.setWorkflowType(returnObj.getWorkflowType());
				obj.setWorkType(returnObj.getWorkType());
				obj.setWorkSubType(returnObj.getWorkSubType());
				obj.setModuleName(returnObj.getModuleName());
				obj.setManualCloseFlag(returnObj.getManualCloseFlag());
			}

		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMEMO
	 *
	 * @
	 */
	@Transactional
	public Integer memoCommit(final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final TagWorkflowBrowseQueue obj : commitBean.getUpdateList()) {
				liReturn = ocdaworkRepository.getRemoveFromQueue(obj);
				if ("1".equals(obj.getAcknowledgementRequired())) {
					liReturn = ocdaworkRepository.getRemoveFromQueue(obj);
				}
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return ocdmworkRepository.rgReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		return ocdmworkRepository.rgTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		return ocdmworkRepository.rgWorkTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSubtypeRecordGroup() {
		return ocdmworkRepository.rgSubtypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCompletedRecordGroup() {
		return ocdmworkRepository.rgCompletedRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSeverityRecordGroup() {
		return ocdmworkRepository.rgSeverityRecordGroup();

	}

}