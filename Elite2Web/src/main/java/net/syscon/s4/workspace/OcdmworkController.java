package net.syscon.s4.workspace;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Class OcdmworkController
 */
@EliteController
public class OcdmworkController {
	@Autowired
	private OcdmworkService ocdmworkService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdmworkController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/workExecuteQuery", method = RequestMethod.POST)
	public List<TagWorkflowBrowseQueue> workExecuteQuery(@RequestBody final TagWorkflowBrowseQueue searchBean) {
		List<TagWorkflowBrowseQueue> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocdmworkService.workExecuteQuery(searchBean);
		} catch (Exception e) {
			final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
			logger.error("In method workExecuteQuery :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/workCommit", method = RequestMethod.POST)
	public @ResponseBody Integer workCommit(@RequestBody final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdmworkService.workCommit(commitBean);
		} catch (Exception e) {

			logger.error("In method workCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdmworkService.rgReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgReasonRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdmworkService.rgTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/rgWorkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdmworkService.rgWorkTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgWorkTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSubtype LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/rgSubtypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubtypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdmworkService.rgSubtypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgSubtypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCompleted LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/rgCompletedRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCompletedRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdmworkService.rgCompletedRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgCompletedRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSeverity LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/rgSeverityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSeverityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdmworkService.rgSeverityRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgSeverityRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/memoExecuteQuery", method = RequestMethod.POST)
	public List<TagWorkflowBrowseQueue> memoExecuteQuery(@RequestBody final TagWorkflowBrowseQueue searchBean) {
		List<TagWorkflowBrowseQueue> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocdmworkService.memoExecuteQuery(searchBean);
		} catch (Exception e) {
			final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
			logger.error("In method memoExecuteQuery :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdmwork/memoCommit", method = RequestMethod.POST)
	public @ResponseBody Integer memoCommit(@RequestBody final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdmworkService.memoCommit(commitBean);
		} catch (Exception e) {

			logger.error("In method memoCommit :", e);
		}
		return liReturn;
	}

}